import java.awt.geom.*;
import java.io.*;

public class Bullet extends GameObject
{
	double v = 200;

	public Bullet(GamePanel gp, double x, double y, double dir)
	{
		super(gp, new File("img/bullet.png"), new File("bound/bullet.txt"), x, y, dir);
	}

	private boolean collide()
	{
		boolean hit = gp.outOfBound(transBound);
		synchronized(gp.objs)
		{
			for (GameObject obj : gp.objs)
			{
				if (obj != this && (obj instanceof Tank || obj instanceof Wall))
				{
					Area intersection = (Area)transBound.clone();
					intersection.intersect(obj.transBound);
					if (!intersection.isEmpty())
					{
						obj.onHit();
						hit = true;
					}
				}
			}
		}
		return hit;
	}

	@Override
	public void update(double sec)
	{
		x += Math.cos(dir) * v * sec;
		y += Math.sin(dir) * v * sec;
		updateBound();
		if (collide()) this.onHit();
	}

	public void forward(double dist)
	{
		x += Math.cos(dir) * dist;
		y += Math.sin(dir) * dist;
		updateBound();
	}
}
