
package entity;

import PackageMain.Jpanel;
import java.awt.Color;
import java.awt.Graphics2D;
    

public class dog extends entity {

    public boolean delete=false;
    
    public dog(Jpanel pn) {
        super(pn);
        height=20;
        width=20;
        worldX=pn.windownCol-10;
        worldY=pn.windownRow-100-height;
        speed=2;
        setDefaultValue();
    }
    
    public void update()
    {
        //UPDATE COLLISION AREA
        rectArea.x=worldX;
        rectArea.y=worldY;
        
        worldX-=speed;
        if(worldX<=0)
            delete=true;
    }
    
    
    @Override
    public void draw(Graphics2D g2d)
    {
        super.draw(g2d);
        g2d.setColor(Color.white);
        g2d.drawRect(worldX+3, worldY+3, width-6, height-6);

    }
}
