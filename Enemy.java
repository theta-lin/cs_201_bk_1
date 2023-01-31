import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Random;

public class Enemy extends Tank
{  Random random=new Random();
   public Enemy(GamePanel gp, double x, double y, double dir)
	{
		super(gp, new File("img/enemy.png"), x, y, dir);
	}
    public void getRandomDirection () {
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
            
    @Override
    public void update(double sec)
    {
      getRandomDirection();
      super.update(sec);
    }
    
}