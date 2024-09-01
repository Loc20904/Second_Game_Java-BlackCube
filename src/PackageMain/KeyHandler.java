

package PackageMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    public Jpanel pn;
    public boolean keyJump;

    
    public KeyHandler(Jpanel pn)
    {
        this.pn=pn;;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        
        if(pn.state==pn.playState)
        {
            if(code == KeyEvent.VK_J && keyJump==false)
            {
                pn.playSE(3);
                keyJump=true;
            }
            if(code == KeyEvent.VK_L)
            {
                pn.stopMusic();
                pn.playMusic(2);
                pn.music=2;
            }
           
        }
        else if(pn.state==pn.startState)
        {
            if(code == KeyEvent.VK_ENTER)
            {
                pn.state=pn.playState;
                pn.sm.spawn(pn.playTime);
                pn.timeNextSpawn=pn.sm.getTimeNextSpawn();
                pn.playMusic(1);
            }
        }
        else
        {
            if(code == KeyEvent.VK_ENTER)
            {
                pn.player.collistionOn=false;
                pn.playTime=0;
                pn.reTry();
                pn.sm.spawn(pn.playTime);
                pn.timeNextSpawn=pn.sm.getTimeNextSpawn();
                pn.state=pn.playState;
            }
             if(code == KeyEvent.VK_ESCAPE)
            {
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code == KeyEvent.VK_J)
        {
            keyJump=false;
        }
    }

}
