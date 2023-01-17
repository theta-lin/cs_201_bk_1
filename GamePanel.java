import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class GamePanel extends JFrame
{
	int width = 1920;
	int height = 1080;

	Image buffer = null;
	Player player = new Player(400, 400, 0);

	public void launch()
	{
		setTitle("Tank");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);

		this.addKeyListener(new GamePanel.KeyMonitor());

		long delay = 10;
		while (true)
		{
			repaint();
			try
			{
				Thread.sleep(delay);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			player.update(delay);
		}
	}

	@Override
	public void paint(Graphics graphics)
	{
		if (buffer == null) buffer = this.createImage(width, height);

		Graphics gBuffer = buffer.getGraphics();
		gBuffer.setColor(Color.black);
		gBuffer.fillRect(0, 0, width, height);
		player.paint(gBuffer);

		graphics.drawImage(buffer, 0, 0, null);
	}

	public static void main(String[] args)
	{
		GamePanel gp = new GamePanel();
		gp.launch();
	}

	class KeyMonitor extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent event)
		{
			player.keyPressed(event);
		}

		@Override
		public void keyReleased(KeyEvent event)
		{
			player.keyReleased(event);
		}
	}
}
