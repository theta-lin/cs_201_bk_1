import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;

public abstract class GameObject
{
	protected GamePanel gp;
	protected BufferedImage img;
	protected Area bound;
	protected double x;
	protected double y;
	protected double dir;

	public boolean dead = false;

	public BufferedImage rotImg;
	public Area transBound;

	public GameObject(GamePanel gp, File imgFile, File boundFile, double x, double y, double dir)
	{
		this.gp = gp;
		this.x = x;
		this.y = y;
		this.dir = dir;

		try
		{
			img = ImageIO.read(imgFile);
			updateImg();

			Scanner scanner = new Scanner(boundFile);
			int npoints = scanner.nextInt();
			int[] xpoints = new int[npoints];
			int[] ypoints = new int[npoints];
			for (int i = 0; i < npoints; ++i)
			{
				xpoints[i] = scanner.nextInt();
				ypoints[i] = scanner.nextInt();
			}
			scanner.close();
			bound = new Area(new Polygon(xpoints, ypoints, npoints));
			updateBound();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public int getWidth()
	{
		return img.getWidth();
	}

	public int getHeight()
	{
		return img.getHeight();
	}

	public void paint(Graphics graphics)
	{
		graphics.drawImage(rotImg, (int)(x - rotImg.getWidth() / 2.0), (int)(y - rotImg.getHeight() / 2.0), null);
	}

	public void paintBound(Graphics graphics)
	{
		Graphics2D g2d = (Graphics2D)graphics.create();
		g2d.setColor(Color.WHITE);
		g2d.draw(transBound);
		g2d.dispose();
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

	protected void updateImg()
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

	public void updateBound()
	{
		AffineTransform t = new AffineTransform();
		t.translate(x - img.getWidth() / 2, y - img.getHeight() / 2);
		t.rotate(dir, img.getWidth() / 2, img.getHeight() / 2);
		transBound = bound.createTransformedArea(t);
	}

	public void onHit()
	{
		dead = true;
	}
}
