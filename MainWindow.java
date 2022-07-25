package src;
import src.View.*;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(String title){
        super(title);
        MyPanel pan = new MyPanel();
        //Menu menu = new Menu();
        Container c = getContentPane();
        //c.add(menu);
        c.add(pan);
        //pan.setVisible(false);
        setSize(1400, 1000);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        ImageIcon img = new ImageIcon("icon.png");
        setIconImage(img.getImage());
        System.out.println("Before");
        // while(menu.check != 1){
        //     System.out.println(menu.check);
        // }
        System.out.println("Play");
        //menu.setVisible(false);
        pan.setVisible(true);
        pan.GameLoop2();
        
    }
    public static void main(String[] args) throws Exception {
        MainWindow mw = new MainWindow("Blackjack");
    }
}
