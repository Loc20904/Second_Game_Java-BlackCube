

package PackageMain;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import jdk.dynalink.linker.support.Guards;


public class Main {

    public static void main(String[] args) {
        JFrame windown=new JFrame();
        windown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windown.setResizable(false);
        windown.setTitle("Second Game");
       
        
        //ADD PANEL TO FRAME
        Jpanel jpn=new Jpanel();
        windown.add(jpn);
        windown.pack();
        jpn.threadGameStart();
        
        windown.setLocationRelativeTo(null);
        windown.setVisible(true);
        
    }
    
}
