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
        int rnum1 = random.nextInt(3);
        switch (rnum1){
            case 0:
            rotatingCC = true;
            rotatingCW = false;
            break;
            case 1:
            rotatingCC = false;
            rotatingCW = true;
            break;
            case 2:
            rotatingCC = false;
            rotatingCW = false;
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
            
    @Override
    public void update(double sec)
    {
      getRandomDirection();
      super.update(sec);
    }
    
}