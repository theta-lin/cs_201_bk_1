
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.plaf.FontUIResource;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame
{

    int width = 840;
    int height = 560;

    int state = 0;

    Background background = new Background();

    public void launch() {

        setTitle("Tank Warfare");

        setSize(width, height);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(3);

        setResizable(true);

        setVisible(true);

        
        this.addKeyListener(new GameWindow.KeyMonitor());

        while(true){
            repaint();
            try {
                Thread.sleep(25);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        
    }

    public void paint(Graphics g)
    {
        background.paintSelf(g);
        g.setColor(Color.BLUE);
        g.setFont(new Font("仿宋",Font.BOLD, 50));

        if (state == 0){

            g.drawString("开始游戏", 300,400);
            g.drawString("Tank Warfare", 250,200);
        
        }
        else if (state == 1){
            GamePanel;
        }
    }

    class KeyMonitor extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_ENTER:
                state = 1;
                break;
            }

        }
			

        public static void main(String[] args){
        GameWindow gp = new GameWindow();
        gp.launch();
        }

    }
}