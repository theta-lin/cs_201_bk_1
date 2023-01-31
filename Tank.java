import java.awt.geom.*;
import java.io.*;

public class Tank extends GameObject
{
	final double v = 150;
	final double vRot = Math.PI;
	final double cdMax = 0.5;

	boolean forward = false;
	boolean backward = false;
	boolean rotatingCC = false;
	boolean rotatingCW = false;
	boolean shooting = false;

	double cd = 0;

	public Tank(GamePanel gp, File imgFile, double x, double y, double dir)
	{
		super(gp, imgFile, new File("bound/tank.txt"), x, y, dir);
	}

	private boolean collide()
	{
		if (gp.outOfBound(transBound)) return true;

		synchronized(gp.objs)
		{
			for (GameObject obj : gp.objs)
			{
				if (obj != this && (obj instanceof Tank || obj instanceof Wall))
				{
					Area intersection = (Area)transBound.clone();
					intersection.intersect(obj.transBound);
					if (!intersection.isEmpty()) return true;
				}
			}
		}
		return false;
	}

	protected void shoot()
	{
		Bullet bullet = new Bullet(gp, x, y, dir);
		bullet.forward((img.getWidth() + bullet.getWidth()) * 1.05 / 2);
		gp.newObjs.add(bullet);
	}

	@Override
	public void update(double sec)
	{
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

		if (cd > 0)
		{
			cd -= sec;
		}
		if (shooting && cd <= 0)
		{
			shoot();
			cd = cdMax;
		}
	}
}
