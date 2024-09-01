
package PackageMain;

import entity.dog;
import entity.player;
import entity.rocket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class Jpanel extends JPanel implements Runnable{
    //SETTING WINDOWN
    public int windownCol=700;
    public int windownRow=400;
    
    public int FPS=60;
    
    public int state=1;
    public int startState=1;
    public int playState=2;
    public int gameOver=3;
    
    public double highestScore=0;
    public double playTime=0;
    public int monsCount=0;
    public int timeNextSpawn=0;
    
    int music=1;
    
    public Jpanel()
    {
        this.setPreferredSize(new Dimension(windownCol,windownRow));
        this.setBackground(Color.white);
        this.addKeyListener(keyh);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        loadScore();
    }
    
    //DECLARE ALL THINGS
    Thread gameThread;
    public KeyHandler keyh=new KeyHandler(this);
    public player player=new player(this);
    public dog dog[]=new dog[20]; 
    public rocket rocket[]=new rocket[20];
    public collisionCheck cCheck=new collisionCheck(this);
    public spawnMonster sm=new spawnMonster(this);
    public UI ui=new UI(this);
    public sound sound=new sound(this);
    
    
    public void threadGameStart()
    {
        gameThread =new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawingtime=1000000000/FPS;
        double nextDrawingtime=System.nanoTime()+drawingtime;
        
        while(true)
        {
            update();
            repaint();
                      
            try {
                double reaminingtime=nextDrawingtime-System.nanoTime();
                reaminingtime=reaminingtime/1000000;
                
                if(reaminingtime<0)
                    reaminingtime=0;
                
                Thread.sleep((long) reaminingtime);
                
                nextDrawingtime+=drawingtime;
            } catch (InterruptedException ex) {
                Logger.getLogger(Jpanel.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
        
    }

    public void update()
    {
        if(state==playState)
        {
            //UPDATE PLAYER
            player.update();

            //UPDATE MONSTER
            for (int i = 0; i < dog.length; i++) {
                if (dog[i] != null) {
                    dog[i].update();
                    if (dog[i].delete) {
                        dog[i] = null;
                    }
                }
            }
            for (int i = 0; i < rocket.length; i++) {
                if (rocket[i] != null) {
                    rocket[i].update();
                    if (rocket[i].delete) {
                        rocket[i] = null;
                    }
                }
            }
            monsCount++;
            if(monsCount==timeNextSpawn)
            {
                sm.spawn(playTime);
                timeNextSpawn=sm.getTimeNextSpawn();
                monsCount=0;
            }
        }
        else
        {
            
        }
    }
    
    public void addMonster(int spped)        
    {
        for (int i = 0; i < dog.length; i++) {
            if (dog[i] == null) {
                dog[i] = new dog(this);
                dog[i].speed=spped;
                return;
            }
        }
    }
    
    public void addMonster(int spped,int y,boolean check)        
    {
        if(!check)
        {
            for (int i = 0; i < rocket.length; i++) {
                if (rocket[i] == null) {
                    rocket[i] = new rocket(this, y);
                    rocket[i].speed = spped;
                    return;
                }
            }
        }
        else
        {
            for (int i = 0; i < dog.length; i++) {
                if (dog[i] == null) {
                    dog[i] = new dog(this);
                    dog[i].worldX=y;
                    dog[i].speed = spped;
                    return;
                }
            }
        }
        
    }
    
    public void reTry()
    {
        playTime=0;
        for (int i = 0; i < dog.length; i++) {
            if (dog[i] != null) {
                dog[i]=null;
            }
        }
        for (int i = 0; i < rocket.length; i++) {
            if (rocket[i] != null) {
                rocket[i]=null;
            }
        }
        timeNextSpawn=0;
        monsCount=0;
        player.setDefaultValue();
    }
    
    public void playMusic(int i)
    {
        sound.setFile(i);   
        sound.play();
    }
    public void playSE(int i)
    {
        sound.setFile(i);
        sound.play();
    }
    public void stopMusic()
    {
        sound.stop();
    }
    
    @Override
    public void paintComponent(Graphics gd)
    {
        super.paintComponent(gd);
        Graphics2D g2d=(Graphics2D)gd;
        
        if(state==playState)
        {
            playTime+=(double)1/60;   
        }
        g2d.fillRect(0, windownRow - 100, windownCol, 2);
        //DRAW ENERMY
        for (int i = 0; i < dog.length; i++) {
            if (dog[i] != null) {
                dog[i].draw(g2d);
            }
        }
        for (int i = 0; i < rocket.length; i++) {
            if (rocket[i] != null) {
                rocket[i].draw(g2d);
            }
        }
        //DRAW PLAYER
        player.draw(g2d);
        
        ui.draw(g2d);
        
        g2d.dispose();
    }
    
    public void saveScore()
    {
        try {
            BufferedWriter wf=new BufferedWriter(new FileWriter(new File("data.txt"),false));
            wf.write(""+playTime);
            wf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadScore() 
    {
        try {
            BufferedReader rf=new BufferedReader(new FileReader("data.txt"));
            try {
                String data=rf.readLine();
                highestScore=Double.parseDouble(data);
                
                rf.close();
            } catch (IOException ex) {
                System.out.println("Read data fail");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
