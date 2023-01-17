import java.awt.event.KeyEvent;
import java.io.*;

public class Player extends Tank
{
	public Player(double x, double y, double dir)
	{
		super(new File("img/player.png"), x, y, dir);
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		switch (event.getKeyCode())
		{
		case KeyEvent.VK_W:
			forward = true;
			break;
		case KeyEvent.VK_S:
			backward = true;
			break;
		case KeyEvent.VK_A:
			rotatingCC = true;
			break;
		case KeyEvent.VK_D:
			rotatingCW = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		switch (event.getKeyCode())
		{
		case KeyEvent.VK_W:
			forward = false;
			break;
		case KeyEvent.VK_S:
			backward = false;
			break;
		case KeyEvent.VK_A:
			rotatingCC = false;
			break;
		case KeyEvent.VK_D:
			rotatingCW = false;
			break;
		}
	}
}
