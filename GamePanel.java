import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.awt.Rectangle;
import java.util.*;

public class GamePanel extends JFrame
{
	int width = 1920;
	int height = 1080;

    int state = 0;

	Rectangle leftBound = new Rectangle(-width, 0, width, height);
	Rectangle rightBound  = new Rectangle(width, 0, width, height);
	Rectangle upBound = new Rectangle(0, -height, width, height);
	Rectangle downBound = new Rectangle(0, height, width, height);

	volatile boolean showBound = false;

	Image buffer;
	List<GameObject> objs = Collections.synchronizedList(new LinkedList<GameObject>());
	List<GameObject> newObjs;
	Player player;

	public void launch()
	{
		setTitle("Tank");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);

		player = new Player(this, 400, 300, 0);

		synchronized(objs)
		{
			objs.add(player);
			objs.add(new Wall(this, 128, 128, 0));
			objs.add(new Wall(this, 256, 128, 0));
			objs.add(new Wall(this, 384, 128, 0));
			objs.add(new Wall(this, 200, 200, Math.toRadians(40)));
			objs.add(new Wall(this, 500, 500, Math.toRadians(60)));
			objs.add(new Wall(this, 400, 800, Math.toRadians(60)));
			objs.add(new Wall(this, 600, 400, Math.toRadians(60)));
			objs.add(new Enemy(this,100,700,0));
			objs.add(new Enemy(this,600,900,0));
			objs.add(new Enemy(this,800,900,0));
			objs.add(new Enemy(this,700,500,0));
		}

		this.addKeyListener(new GamePanel.KeyMonitor());

		long step = 10;
		while (true)
		{
			repaint();

			try
			{
				Thread.sleep(step);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			if (state == 1)
			{
				newObjs = Collections.synchronizedList(new LinkedList<GameObject>());
				synchronized(objs)
				{
					objs.forEach((obj) -> obj.update(step / 1000.0));
					objs.removeIf((obj) -> obj.dead);
					objs.addAll(newObjs);
				}
			}
		}
	}

	public boolean outOfBound(Area area)
	{
		if (   area.intersects(leftBound) || area.intersects(rightBound)
			|| area.intersects(upBound) || area.intersects(downBound))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void paint(Graphics graphics)
	{
		if (buffer == null) buffer = this.createImage(width, height);

		Graphics gBuffer = buffer.getGraphics();
		gBuffer.setColor(Color.blue);
		gBuffer.setFont(new Font("SansSerif", Font.BOLD, 50));
		gBuffer.fillRect(0, 0, width, height);

		if (state == 0)
		{
			gBuffer.setColor(Color.white);
			gBuffer.drawString("Start Game", 300, 400);
			gBuffer.drawString("Tank Warfare", 250, 200);
		}
		else if (state == 1)
		{
			synchronized(objs)
			{
				objs.forEach((obj) -> obj.paint(gBuffer));
				if (showBound) objs.forEach((obj) -> obj.paintBound(gBuffer));
			}
		}
		else if (state == 4){
			gBuffer.drawString("Win", 220, 220);
		}
		else if (state ==4){
			gBuffer.drawString("Fail", 220, 220);
		}

		graphics.drawImage(buffer, 0, 0, null);
	}

	public static void main(String[] args)
	{
		GamePanel gp = new GamePanel();
		gp.launch();
	}

	//while (true){
	//    if(EnemyList.size()==0 && enemyCount == 4)
	//}

	class KeyMonitor extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent event)
		{
			if (state == 0)
			{
				if (event.getKeyCode() == KeyEvent.VK_ENTER) state = 1;
			}
			else if (state == 1)
			{
				if (event.getKeyCode() == KeyEvent.VK_B) showBound = !showBound;
				player.keyPressed(event);
			}
		}

		@Override
		public void keyReleased(KeyEvent event)
		{
			player.keyReleased(event);
		}
	}
}
