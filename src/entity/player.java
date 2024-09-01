
package entity;

import PackageMain.Jpanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class player extends entity {

    
    public boolean jump=false;
    public int jumpCount=0;
    public boolean fallDown=false;
    
    public player(Jpanel pn) {
        super(pn);
        height=20;
        width=20;
        worldX=pn.windownCol/4;
        worldY=pn.windownRow-100-height;
        setDefaultValue();
    }
    
    public void update()
    {
        //UPDATE COLLISION AREA
        rectArea.x=worldX;
        rectArea.y=worldY;
           
        if(pn.keyh.keyJump==true)
        {
            jump=true;
        }
        if(jump)
        {
            Jump();
        }
        
        pn.cCheck.checkInteract(this, pn.dog);
        pn.cCheck.checkInteract(this, pn.rocket);
        if(collistionOn)
        {
            pn.state=pn.gameOver;
            if(pn.highestScore<pn.playTime)
            {
                pn.highestScore=pn.playTime;
                pn.saveScore();
            }
            collistionOn=false;
        }
    }
    
    public void Jump()
    {
        if (!fallDown) 
        {
            
            jumpCount++;
            worldY -= getV();
            if(jumpCount>25)
                fallDown=true;
        } 
        else 
            {
                jumpCount--;
                if (jumpCount == 0) 
                {
                    jump = false;
                    fallDown=false;
                    worldY++;
                    return;
                }
                worldY += getV();
            }
    }
    
    public double getV()
    {
        int s=30;
        int t=jumpCount;
        double v=s/t;
        return v;
    }
    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.setColor(Color.gray);
        g2d.fillRect(worldX, worldY, width, height);
        super.draw(g2d); 
    }
}
