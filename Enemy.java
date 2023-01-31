import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Tank
{
	Random random=new Random();

	final double moveCdMax = 2;
	final double turnCdMax = 0.3;
	final double stateCdMax = 2;
	final double posCdMax = 0.1;
	double moveCd = 0;
	double turnCd = 0;
	double stateCd = 0;
	double posCd = posCdMax;

	boolean attacking = false;

	double xPre, yPre;

	ArrayList<Enemy> Enemy

	public Enemy(GamePanel gp, double x, double y, double dir)
	{
		super(gp, new File("img/enemy.png"), x, y, dir);
		xPre = x;
		yPre = y;
	}
    public void getRandomDirection() {
		if (moveCd <= 0)
		{
			moveCd = moveCdMax;

			int rnum = random.nextInt(2);
			switch (rnum) {
				case 0:
				   forward = true;
				   backward = false;
				break;
				case 1:
				   forward = false;
				   backward = true;
				break;
				default:
				   return;
			}
		}

		if (attacking)
		{
			double px = 0, py = 0;
			synchronized(gp.objs)
			{
				for (GameObject obj : gp.objs)
				{
					if (obj instanceof Player)
					{
						px = obj.getX() - x;
						py = obj.getY() - y;
						break;
					}
				}
			}

			double dx = Math.cos(dir), dy = Math.sin(dir);

			if (dx * py - dy * px > 0)
			{
				rotatingCC = false;
				rotatingCW = true;
			}
			else
			{
				rotatingCC = true;
				rotatingCW = false;
			}

			shooting = true;
		}
		else
		{
			if (turnCd <= 0)
			{
				turnCd = turnCdMax;

				int rnum1 = random.nextInt(7);
				switch (rnum1){
					case 0:
					case 1:
					case 2:
					case 3:
					case 4:
					rotatingCC = false;
					rotatingCW = false;
					break;
					case 5:
					rotatingCC = true;
					rotatingCW = false;
					break;
					case 6:
					rotatingCC = false;
					rotatingCW = true;
					break;

					default:
					 return;
				}

				int rnum2 = random.nextInt(4);
				switch (rnum2){
					case 0:
					shooting=true;
					break;
					case 1:
					shooting=false;
					break;
					case 2:
					shooting=false;
					break;
					case 3:
					shooting=false;
					break;

					default:
					 return;
				}
			}
		}

		if (posCd <= 0)
		{
			posCd = posCdMax;
			if (xPre == x && yPre == y)
			{
				//System.out.println("stuck");
				if (forward)
				{
					forward = false;
					backward = true;
				}
				else
				{
					forward = true;
					backward = false;
				}
			}
			xPre = x;
			yPre = y;
		}
    }

    @Override
    public void update(double sec)
    {
		if (moveCd > 0) moveCd -= sec;
		if (turnCd > 0) turnCd -= sec;
		if (stateCd > 0) stateCd -= sec;
		if (posCd > 0) posCd -= sec;
		if (stateCd <= 0)
		{
			stateCd = stateCdMax;
			int rnum = random.nextInt(2);
			if (rnum == 0)
			{
				attacking = !attacking;
				moveCd = 0;
				turnCd = 0;
			}
			//System.out.println(attacking);
		}

		getRandomDirection();
		super.update(sec);
    }

}
