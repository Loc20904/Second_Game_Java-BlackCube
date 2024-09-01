
package entity;

import PackageMain.Jpanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class entity {
    public Jpanel pn;
    
    public int worldX;
    public int worldY;
    
    public int ScreanX;
    public int ScreanY;
    
    public int speed;
    public int height;
    public int width;
    
    public BufferedImage image1,image2;
    
    //COLLISSION SETTING
    public Rectangle rectArea=new Rectangle();
    public int collisionWidth;
    public int collisionHeight;
    public boolean collistionOn=false;
    
    
    public entity(Jpanel pn)
    {
        this.pn=pn;
    }
    
    public void setDefaultValue()
    {
        collisionWidth=width;
        collisionHeight=height;
        
        rectArea.x=worldX;
        rectArea.y=worldY;
        rectArea.width=collisionWidth;
        rectArea.height=collisionHeight;
    }

    public void draw(Graphics2D g2d)
    {
        g2d.setColor(Color.black);
        g2d.fillRect(worldX, worldY, width, height);
    }
}
