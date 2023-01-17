import java.io.*;

public class Wall extends GameObject
{
	public Wall(GamePanel gp, double x, double y, double dir)
	{
		super(gp, new File("img/wall.png"), new File("bound/wall.txt"), x, y, dir);
	}
}
