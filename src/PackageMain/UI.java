
package PackageMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class UI {
    
    public Jpanel pn;
    
    public Graphics2D g2d; 
    Font arial_20, arial_40;
    
    public UI(Jpanel pn)
    {
        this.pn=pn;
        arial_20=new Font("Arial",Font.PLAIN, 20);
        arial_40=new Font("Arial",Font.BOLD, 40);
    }

    public void draw(Graphics2D g2d)
    {
        this.g2d=g2d;
        if(pn.state==pn.startState)
        {
            BufferedImage temp;
            try {
                temp = ImageIO.read(getClass().getResourceAsStream("/logo_ingame.png"));
                g2d.drawImage(temp,165,10,400,90,null);
            } catch (IOException ex) {
                System.out.println("Load image fail");
            }
            g2d.setFont(arial_40);
            g2d.setColor(Color.BLACK);
            g2d.drawString("PRESS ENTER TO START",135, pn.windownRow/2-50);
            g2d.setFont(arial_20);
            g2d.drawString("PRESS J TO JUMP OVER ENERMY", 200, pn.windownRow/2);
        }
        else if(pn.state==pn.playState)
        {
            g2d.setFont(arial_20);
            drawTime();
        }
        else
        {
            pn.stopMusic();
            g2d.setFont(arial_40);
            g2d.setColor(Color.BLACK);
            g2d.drawString("PRESS ENTER TO RESTART",95, pn.windownRow/2-70);
            g2d.setFont(arial_20);
            g2d.drawString(String.format("Your score: %.2f",pn.playTime), 165, pn.windownRow/2-30);
            g2d.drawString(String.format("Highest score: %.2f",pn.highestScore), 400, pn.windownRow/2-30);
            g2d.drawString("<>PRESS ESC TO QUIT", 10, pn.windownRow-20);
        }
    }

    public void drawTime()
    {
        g2d.setColor(Color.black);
        g2d.drawString(String.format("Time: %.2f",pn.playTime), 10, 20);
    }
    
}
