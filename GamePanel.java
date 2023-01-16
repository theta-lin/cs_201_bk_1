import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JFrame
{
	int width = 1280;
	int height = 720;

	boolean state = false;
	Image buffer = null;

	public void launch()
	{
		setTitle("Tank");
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);
		setVisible(true);

		this.addKeyListener(new GamePanel.KeyMonitor());

		while (true)
		{
			repaint();
			try
			{
				Thread.sleep(10);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics graphics)
	{
		if (buffer == null) buffer = this.createImage(width, height);
		Graphics gBuffer = buffer.getGraphics();
		if (state)
		{
			gBuffer.setColor(Color.red);
		}
		else
		{
			gBuffer.setColor(Color.black);
		}
		gBuffer.fillRect(0, 0, width, height);
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
			switch (event.getKeyCode())
			{
			case KeyEvent.VK_ENTER:
				state = !state;
				break;
			}
		}
	}
}
