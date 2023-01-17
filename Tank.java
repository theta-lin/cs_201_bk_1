import java.awt.geom.*;
import java.io.*;

public class Tank extends GameObject
{
	double v = 150;
	double vRot = Math.PI;
	boolean forward = false;
	boolean backward = false;
	boolean rotatingCC = false;
	boolean rotatingCW = false;

	public Tank(GamePanel gp, File imgFile, double x, double y, double dir)
	{
		super(gp, imgFile, new File("bound/tank.txt"), x, y, dir);
	}

	private boolean collide()
	{
		for (GameObject obj : gp.objs)
		{
			Area intersection = (Area)this.transBound.clone();
			intersection.intersect(obj.transBound);
			if (   obj != this
				&& !intersection.isEmpty())
			{
				return true;
			}
		};
		return false;
	}

	@Override
	public void update(long millis)
	{
		double sec = millis / 1000.0;

		double xOld = x, yOld = y, dirOld = dir;

		if (forward)
		{
			x += Math.cos(dir) * v * sec;
			y += Math.sin(dir) * v * sec;
		}
		if (backward)
		{
			x -= Math.cos(dir) * v * sec;
			y -= Math.sin(dir) * v * sec;
		}

		if (rotatingCC)
		{
			dir -= vRot * sec;
		}
		if (rotatingCW)
		{
			dir += vRot * sec;
		}

		updateBound();
		if (collide())
		{
			x = xOld; y = yOld; dir = dirOld;
			updateBound();
		}
		else if (dir != dirOld)
		{
			updateImg();
		}
	}
}
