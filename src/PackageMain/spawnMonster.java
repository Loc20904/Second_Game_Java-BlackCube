

package PackageMain;

import java.util.Random;


public class spawnMonster {

    public Jpanel pn;
    int speedMonster=2;
    public Random rd=new Random();
    
    public spawnMonster(Jpanel pn)
    {
        this.pn=pn;
    }
    
    public void spawn(double time)
    {
        if(time<30)//ONLY SPAWN DOG
        {
            pn.addMonster(speedMonster);
        }
        else if(time>=30 && time<60)//CAN SPAWN FLY
        {
            int temp=Math.abs(rd.nextInt()%2)+1;
            if(temp==1)
            {
                temp=Math.abs(rd.nextInt()%2)+1;
                if(temp==1)
                {
                    pn.addMonster(speedMonster);
                }
                else
                {
                   
                    pn.addMonster(speedMonster,pn.windownCol-30,true);
                    pn.addMonster(speedMonster);
                }
            }
            else
            {
                temp=pn.windownRow-100-(Math.abs(rd.nextInt())%45+20);
                pn.addMonster(speedMonster,temp,false);
                pn.addMonster(speedMonster);
            }   
        }
        else//AFTER 120s INCREASING SPEED
        {
            if(time%30==0)
            {
                speedMonster++;
            }
            int temp=Math.abs(rd.nextInt()%2)+1;
            if(temp==1)
            {
                temp=Math.abs(rd.nextInt()%2)+1;
                if(temp==1)
                {
                    pn.addMonster(speedMonster);
                }
                else
                {
                    pn.addMonster(speedMonster,pn.windownCol-30,true);
                    pn.addMonster(speedMonster);
                }
            }
            else
            {
                temp=pn.windownRow-100-(Math.abs(rd.nextInt())%45+30);
                pn.addMonster(speedMonster,temp,false);
                pn.addMonster(speedMonster);
            }
            
            if(time%160==0 && pn.music==1)
            {
                pn.stopMusic();
                pn.playMusic(2);
                pn.music=2;
            }
            else if(time%160==0 && pn.music==2)
            {
                pn.stopMusic();
                pn.playMusic(1);
                pn.music=1;
            }
        }
    }
    public int getTimeNextSpawn()
    {
        return 60+(Math.abs(rd.nextInt()%30));
    }
}
