import java.io.*;

public class Tank extends GameObject
{
	double v = 150;
	double vRot = Math.PI;
	boolean forward = false;
	boolean backward = false;
	boolean rotatingCC = false;
	boolean rotatingCW = false;

	public Tank(File imgFile, double x, double y, double dir)
	{
		super(imgFile, new File("bound/tank.txt"), x, y, dir);
	}

	@Override
	public void update(long millis)
	{
		double sec = millis / 1000.0;
		if (forward)
		{
			x += Math.cos(dir) * v * sec;
			y += Math.sin(dir) * v * sec;
			updateBound();
		}
		if (backward)
		{
			x -= Math.cos(dir) * v * sec;
			y -= Math.sin(dir) * v * sec;
			updateBound();
		}

		if (rotatingCC)
		{
			dir -= vRot * sec;
			updateImg();
			updateBound();
		}
		if (rotatingCW)
		{
			dir += vRot * sec;
			updateImg();
			updateBound();
		}
	}
}
