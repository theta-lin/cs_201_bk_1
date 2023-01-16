import java.awt.event.KeyEvent;
import java.io.*;

public class Tank extends GameObject
{
	double v = 150;
	double vRot = Math.PI;
	boolean forward = false;
	boolean rotatingCC = false;
	boolean rotatingCW = false;

	public Tank(File imgFile, double x, double y, double dir)
	{
		super(imgFile, x, y, dir);
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		switch (event.getKeyCode())
		{
		case KeyEvent.VK_W:
			forward = true;
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
		case KeyEvent.VK_A:
			rotatingCC = false;
			break;
		case KeyEvent.VK_D:
			rotatingCW = false;
			break;
		}
	}

	@Override
	public void update(long millis)
	{
		double sec = millis / 1000.0;
		if (forward)
		{
			x += Math.cos(dir) * v * sec;
			y += Math.sin(dir) * v * sec;
		}
		if (rotatingCC)
		{
			dir -= vRot * sec;
			rotated = rotate(img, dir);
		}
		if (rotatingCW)
		{
			dir += vRot * sec;
			rotated = rotate(img, dir);
		}
	}
}
