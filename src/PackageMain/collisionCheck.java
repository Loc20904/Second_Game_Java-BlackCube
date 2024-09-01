
package PackageMain;

import entity.dog;
import entity.entity;


public class collisionCheck {

    public Jpanel pn;
    
    public collisionCheck(Jpanel pn)
    {
        this.pn=pn;
    }
    
    public void checkInteract(entity entity, entity[] taget)
    {
        for(int i=0;i<taget.length;i++)
        {
            if(taget[i]!=null)
            {
//                if((taget[i].rectArea.x>=entity.rectArea.x &&
//                    taget[i].rectArea.x<(entity.rectArea.x+entity.rectArea.width)) &&
//                   (taget[i].rectArea.y>=entity.rectArea.y &&
//                    taget[i].rectArea.y<(entity.rectArea.y+entity.rectArea.height))  
//                   )
                if(entity.rectArea.intersects(taget[i].rectArea))
                {
                    entity.collistionOn=true;
                }
            }
        }
    }
    
}
