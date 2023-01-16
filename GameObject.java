import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;

public class GameObject
{
	protected BufferedImage img;
	protected BufferedImage rotated;
	protected double x;
	protected double y;
	protected double dir;

	public GameObject(File imgFile, double x, double y, double dir)
	{
		try
		{
			img = ImageIO.read(imgFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		rotated = rotate(img, dir);
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void paint(Graphics graphics)
	{
		graphics.drawImage(rotated, (int)(x - rotated.getWidth() / 2.0), (int)(y - rotated.getHeight() / 2.0), null);
	}

	public void keyPressed(KeyEvent event)
	{
	}

	public void keyReleased(KeyEvent event)
	{
	}

	public void update(long millis)
	{
	}

	public static BufferedImage rotate(BufferedImage img, double angle)
	{
		int w = img.getWidth();
		int h = img.getHeight();

		int nw = (int)(w * Math.abs(Math.sin(angle)) + h * Math.abs(Math.cos(angle)));
		int nh = (int)(h * Math.abs(Math.sin(angle)) + w * Math.abs(Math.cos(angle)));

		BufferedImage rotated = new BufferedImage(nw, nh, img.getType());
		Graphics2D g2d = rotated.createGraphics();
		g2d.translate(nw / 2, nh / 2);
		g2d.rotate(angle);
		g2d.translate(-w / 2, -h / 2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		return rotated;
	}
}
