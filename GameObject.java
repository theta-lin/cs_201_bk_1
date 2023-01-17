import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;

public abstract class GameObject
{
	protected BufferedImage img;
	protected double x;
	protected double y;
	protected double dir;

	private BufferedImage rotImg;

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
		updateRotation();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public void paint(Graphics graphics)
	{
		graphics.drawImage(rotImg, (int)(x - rotImg.getWidth() / 2.0), (int)(y - rotImg.getHeight() / 2.0), null);
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

	protected void updateRotation()
	{
		int w = img.getWidth();
		int h = img.getHeight();

		int nw = (int)(w * Math.abs(Math.sin(dir)) + h * Math.abs(Math.cos(dir)));
		int nh = (int)(h * Math.abs(Math.sin(dir)) + w * Math.abs(Math.cos(dir)));

		rotImg = new BufferedImage(nw, nh, img.getType());
		Graphics2D g2d = rotImg.createGraphics();
		g2d.translate(nw / 2, nh / 2);
		g2d.rotate(dir);
		g2d.translate(-w / 2, -h / 2);
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
	}
}
