import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Background {
    Image bg = Toolkit.getDefaultToolkit().getImage("img/BG/jpg");
    
    public void paintSelf(Graphics g)
    {
        g.drawImage(bg, 0, 0, null);
    }
}
