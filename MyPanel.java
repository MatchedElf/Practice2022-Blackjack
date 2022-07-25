package src.View;
import src.Model.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;

import java.io.*;
import java.time.format.FormatStyle;
import java.util.*;
import javax.imageio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MyPanel extends JPanel{
    Integer sum = 0; 
    Integer sum_dealer = 0; 
    Integer p1sum = 0; 
    Integer p2sum = 0; 

    private BufferedImage image, card;
    private boolean takeCard = false;
    private boolean stopTaking = false;

    AudioInputStream audioInputStream;
    AudioInputStream audioInputStream1;

    Vector<AudioInputStream> sound = new Vector<AudioInputStream>();
    Clip clip, clip1;

    int playCounter = 0;

    int input = 0;

    Double bet = 0.0;

    private Game gam = new Game(100);

    private Vector<String> playerView = new Vector<String>();
    private Vector<String> dealerView = new Vector<String>();
    private Vector<String> player1View = new Vector<String>();
    private Vector<String> player2View = new Vector<String>();

    JButton play = new JButton("Играть");
    JLabel text = new JLabel(sum.toString());
    JLabel text1 = new JLabel(p1sum.toString());
    JLabel text2 = new JLabel(p2sum.toString());
    JLabel textD = new JLabel(sum_dealer.toString());
    JLabel status = new JLabel("");
    JLabel status1 = new JLabel("");
    JLabel status2 = new JLabel("");
    JTextField textField = new JTextField();
    JButton ok = new JButton("Ok");
    JLabel cashShow = new JLabel(gam.get_player_cash().toString());
    JLabel cashShow1 = new JLabel(gam.get_player1_cash().toString());
    JLabel cashShow2 = new JLabel(gam.get_player2_cash().toString());

    boolean dealerShow = false;

    class MyMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            // playerView.add(gam.getCard());
            // System.out.println("Clicked");
            // repaint();
        }
        public void mouseEntered(MouseEvent e){};
        public void mouseExited(MouseEvent e){};
        public void mousePressed(MouseEvent e){};
        public void mouseReleased(MouseEvent e){};
    }

    class TakeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button");
            if(stopTaking == false){
                //takeCard = true;
                //sum = sum;
                System.out.println("Sum is: " + sum);
                playerView.add(gam.getCard());
                sum = gam.give_card(sum, 0);
                repaint();
                System.out.println("Sum is: " + sum);
                if (sum > 21)
                {
                    System.out.println("This is too much");
                    stopTaking = true;
                }
            }
        }
    }

    class StopActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button");
            stopTaking = true;
        }
    }

    class PlayActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button");
            input = 1;
        }
    }
    class OkActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button");
            try {
                bet = Double.parseDouble(textField.getText());
            } catch (NumberFormatException e1) {
                status.setText("Incorrect");
            }
        }
    }

    public MyPanel(){
        setLayout(null);
        MyMouseListener ml = new MyMouseListener();
        this.addMouseListener(ml);
        gam.make_cards();
        musicP();
        JButton take = new JButton("Взять ещё");
        JButton stop = new JButton("Хватит");
        take.setBounds(1250, 670, 100, 40);
        play.setBounds(575, 400, 200, 50);
        stop.setBounds(1250, 730, 100, 40);
        text.setBounds(575, 730, 200, 50);
        text1.setBounds(0, 260, 200, 50);
        text2.setBounds(1200, 260, 200, 50);
        textD.setBounds(575, 60, 200, 50);
        status.setBounds(575, 680, 200, 50);
        status1.setBounds(220, 300, 200, 50);
        status2.setBounds(900, 300, 200, 50);
        cashShow.setBounds(1150, 0, 200, 40);
        cashShow1.setBounds(0, 320, 200, 40);
        cashShow2.setBounds(1200, 320, 200, 40);
        textField.setBounds(575, 400, 200, 50);
        ok.setBounds(655, 465, 40, 40);
        add(play);
        add(take);
        add(stop);
        add(text);
        add(text1);
        add(text2);
        add(textD);
        add(status);
        add(status1);
        add(status2);
        add(cashShow);
        add(cashShow1);
        add(cashShow2);
        add(textField);
        add(ok);
        //take.setVisible(false);
        take.setBackground(Color.WHITE);
        //cashShow.setForeground(Color.WHITE);
        cashShow.setBackground(Color.WHITE);
        cashShow.setHorizontalAlignment(JLabel.CENTER);
        cashShow.setVerticalAlignment(JLabel.CENTER);
        cashShow.setOpaque(true);
        cashShow.setFont(new Font("Verdana", Font.PLAIN, 20));
        cashShow1.setBackground(Color.WHITE);
        cashShow1.setHorizontalAlignment(JLabel.CENTER);
        cashShow1.setVerticalAlignment(JLabel.CENTER);
        cashShow1.setOpaque(true);
        cashShow1.setFont(new Font("Verdana", Font.PLAIN, 20));
        cashShow2.setBackground(Color.WHITE);
        cashShow2.setHorizontalAlignment(JLabel.CENTER);
        cashShow2.setVerticalAlignment(JLabel.CENTER);
        cashShow2.setOpaque(true);
        cashShow2.setFont(new Font("Verdana", Font.PLAIN, 20));
        status.setBackground(Color.WHITE);
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setVerticalAlignment(JLabel.CENTER);
        //status.setOpaque(true);
        status.setFont(new Font("Verdana", Font.PLAIN, 30));
        status.setForeground(Color.yellow);
        status1.setBackground(Color.WHITE);
        status1.setHorizontalAlignment(JLabel.CENTER);
        status1.setVerticalAlignment(JLabel.CENTER);
        //status1.setOpaque(true);
        status1.setFont(new Font("Verdana", Font.PLAIN, 30));
        status1.setForeground(Color.yellow);
        status2.setBackground(Color.WHITE);
        status2.setHorizontalAlignment(JLabel.CENTER);
        status2.setVerticalAlignment(JLabel.CENTER);
        //status2.setOpaque(true);
        status2.setFont(new Font("Verdana", Font.PLAIN, 30));
        status2.setForeground(Color.yellow);
        text.setBackground(Color.WHITE);
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setVerticalAlignment(JLabel.CENTER);
        text.setOpaque(true);
        text.setFont(new Font("Verdana", Font.PLAIN, 20));
        text1.setBackground(Color.WHITE);
        text1.setHorizontalAlignment(JLabel.CENTER);
        text1.setVerticalAlignment(JLabel.CENTER);
        text1.setOpaque(true);
        text1.setFont(new Font("Verdana", Font.PLAIN, 20));
        text2.setBackground(Color.WHITE);
        text2.setHorizontalAlignment(JLabel.CENTER);
        text2.setVerticalAlignment(JLabel.CENTER);
        text2.setOpaque(true);
        text2.setFont(new Font("Verdana", Font.PLAIN, 20));
        textD.setBackground(Color.WHITE);
        textD.setHorizontalAlignment(JLabel.CENTER);
        textD.setVerticalAlignment(JLabel.CENTER);
        textD.setOpaque(true);
        textD.setFont(new Font("Verdana", Font.PLAIN, 20));
        ok.setBackground(Color.WHITE);
        ok.setHorizontalAlignment(JLabel.CENTER);
        ok.setVerticalAlignment(JLabel.CENTER);
        ok.setOpaque(true);
        ok.setFont(new Font("Verdana", Font.PLAIN, 5));
        ok.setText("Ok");
        TakeActionListener act = new TakeActionListener();
        StopActionListener st = new StopActionListener();
        PlayActionListener pl = new PlayActionListener();
        OkActionListener okl = new OkActionListener();
        take.addActionListener(act);
        stop.addActionListener(st);
        play.addActionListener(pl);
        ok.addActionListener(okl);
        //take.setLocation(800, 1000);
        //take.setBounds(900, 900, 100, 30);
        //take.addActionListener(new ListenerAction());
        try {                
            image = ImageIO.read(new File("Back2.png"));
        } 
        catch (IOException ex) {
            // handle exception...
        }
        //GameLoop();
        //invalidate();
        
    }

    void musicP() {
        try 
        {    
        audioInputStream = AudioSystem.getAudioInputStream(new File("Trofim.wav").getAbsoluteFile());
        audioInputStream1 = AudioSystem.getAudioInputStream(new File("Поворот.wav").getAbsoluteFile());
        sound.add(audioInputStream);
        sound.add(audioInputStream1);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        //clip.loop(0);    
        } 
        catch (Exception e) {}
    }

    public void GameLoop2(){
        gam.make_cards();
        Scanner in = new Scanner(System.in);
        System.out.println("Game");
        while (1==1) {

            //System.out.println("Enter 1 to start, 2 to see your cash, 3 to exit");
            //input = in.nextInt();
            status.setText("");
            Double p1bet;
            Double p2bet;
            if (input == 1) {
                status.setVisible(false);
                status1.setVisible(false);
                status2.setVisible(false);
                dealerShow = false;
                play.setVisible(false);
                ok.setVisible(true);
                textField.setVisible(true);
                repaint();
                input = 0;
                sum = 0;
                if(gam.get_player1_cash() >= 20) p1bet = 20.0;
                else if(gam.get_player1_cash() > 0) p1bet = gam.get_player1_cash();
                else p1bet = 0.0;
                if(gam.get_player2_cash() >= 0) p2bet = gam.get_player2_cash() / 2;
                else p2bet = 0.0;
                p1sum = 0;
                p2sum = 0;
                sum_dealer = 0;
                //System.out.println("Make tour bet: ");
                //bet = in.nextInt();
                bet = 0.0;
                while(bet == 0){
                    System.out.println(bet);
                }
                textField.setVisible(false);
                ok.setVisible(false);
                if ( (bet > gam.get_player_cash()) || (bet < 0) )
                {
                    bet = 0.0;
                }
                gam.minusBet(bet, p1bet, p2bet);
                gam.shuffle();
                System.out.println("Your cards: ");
                playerView.add(gam.getCard());
                sum = gam.give_card(sum, 0);
                repaint();
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                playerView.add(gam.getCard());
                sum = gam.give_card(sum, 0);
                repaint();
                System.out.println("Sum is: " + sum);

                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){

                }
                System.out.println("Player 1 cards: ");
                player1View.add(gam.getCard());
                p1sum = gam.give_card(p1sum, 1);
                repaint();
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){

                }
                player1View.add(gam.getCard());
                p1sum = gam.give_card(p1sum, 1);
                repaint();
                System.out.println("Player 1 Sum is: " + p1sum);
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }

                System.out.println("Player 2 cards: ");
                player2View.add(gam.getCard());
                p2sum = gam.give_card(p2sum, 2);
                repaint();
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){

                }
                player2View.add(gam.getCard());
                p2sum = gam.give_card(p2sum, 2);
                repaint();
                System.out.println("Player 2 Sum is: " + p2sum);
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }

                System.out.println("Dealer's 2 cards: ");
                dealerView.add(gam.getCard());
                sum_dealer = gam.give_card(sum_dealer, 3);
                repaint();
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                dealerView.add(gam.getCard());
                sum_dealer = gam.give_card(sum_dealer, 3);
                repaint();
                System.out.println("Dealer's Sum is: " + sum_dealer);
                while(!stopTaking)
                {
                    //System.out.println("Enter 1 to take another one, enter 2 to stop");
                    //input = in.nextInt();
                    if (sum > 21)
                    {
                        System.out.println("This is too much");
                        try{
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException e){
                            System.out.println("Exc");
                        }
                        break;
                    }
                    System.out.println("ST - " + stopTaking);
                    //else break;
                }
                System.out.println("ST - " + stopTaking);
                stopTaking = false;
                repaint();
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                while(gam.player1Decide(p1sum)){
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        System.out.println("Exc");
                    }
                    player1View.add(gam.getCard());
                    p1sum = gam.give_card(p1sum, 1);
                    repaint();
                }
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                while(gam.player2Decide(p2sum)){
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        System.out.println("Exc");
                    }
                    player2View.add(gam.getCard());
                    p2sum = gam.give_card(p2sum, 2);
                    repaint();
                }
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                dealerShow = true;
                repaint();
                try{
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }    
                while (sum_dealer < 17)
                {
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e){
                        System.out.println("Exc");
                    }
                    dealerView.add(gam.getCard());
                    sum_dealer = gam.give_card(sum_dealer, 3);
                    repaint();
                    System.out.println("Dealer's Sum is: " + sum_dealer);
                    if (sum_dealer > 21) break;
                }
                try{
                    Thread.sleep(2000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                status.setVisible(true);
                status1.setVisible(true);
                status2.setVisible(true);
                if (((sum > sum_dealer) || (sum_dealer > 21)) && (sum <= 21))
                {
                    try{    
                        AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new File("Деньги.wav").getAbsoluteFile());
                        Clip clip1 = AudioSystem.getClip();
                        clip1.open(audioInputStream1);
                        clip1.start();
        
                    } 
                    catch (Exception e) {}
                    System.out.println("You win!!!");
                    status.setText("You win!!!");
                    repaint();
                    gam.giveWin(0, bet);
                }
                else if ((sum == sum_dealer) && (sum <= 21))
                {
                    System.out.println("Draw");
                    status.setText("Draw");
                    repaint();
                    gam.returnBet(0, bet);
                }
                else{
                    System.out.println("You lose...");
                    status.setText("You lose...");
                    repaint();
                } 
                if (((p1sum > sum_dealer) || (sum_dealer > 21)) && (p1sum <= 21))
                {
                    System.out.println("p1 win!!!");
                    status1.setText("You win!!!");
                    repaint();
                    gam.giveWin(1, p1bet);
                }
                else if ((p1sum == sum_dealer) && (p1sum <= 21))
                {
                    System.out.println("p1 Draw");
                    status1.setText("Draw");
                    repaint();
                    gam.returnBet(1, p1bet);
                }
                else{
                    System.out.println("p1 lose...");
                    status1.setText("You lose...");
                    repaint();
                } 
                if (((p2sum > sum_dealer) || (sum_dealer > 21)) && (p2sum <= 21))
                {
                    System.out.println("p2 win!!!");
                    status2.setText("You win!!!");
                    repaint();
                    gam.giveWin(2, p2bet);
                }
                else if ((p2sum == sum_dealer) && (p2sum <= 21))
                {
                    System.out.println("p2 Draw");
                    status2.setText("Draw");
                    repaint();
                    gam.returnBet(2, p2bet);
                }
                else{
                    System.out.println("p2 lose...");
                    status2.setText("You lose...");
                    repaint();
                } 

                //}
                gam.cardsClear();
                playerView.clear();
                player1View.clear();
                player2View.clear();
                dealerView.clear();
                
                repaint();
                try{
                    Thread.sleep(3000);
                }
                catch(InterruptedException e){
                    System.out.println("Exc");
                }
                play.setVisible(true);

            }
            // else if (input == 2) System.out.println("Cash: " + gam.get_player_cash());
            // else if (input == 3){
            //     in.close();
            //     break;
            // } 
            // else System.out.println("Error");
        }        
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);        
        g.drawImage(image, 0, 0, this);
        if(playerView.size() > 0){
            try{    
                AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(new File("Карта1.wav").getAbsoluteFile());
                Clip clip1 = AudioSystem.getClip();
                clip1.open(audioInputStream1);
                clip1.start();

            } 
            catch (Exception e) {}
        } 
        for(int i = 0; i < playerView.size(); i++){
            try {                
                card = ImageIO.read(new File(playerView.get(i)));
            } 
            catch (IOException ex) {
                  // handle exception...
            }
            g.drawImage(card, 610 + i * 60, 630, 50, 72, this);
        }

        for(int i = 0; i < player1View.size(); i++){
            try {                
                card = ImageIO.read(new File(player1View.get(i)));
            } 
            catch (IOException ex) {
                  // handle exception...
            }
            g.drawImage(card, 210 + i * 60, 560, 50, 72, this);
        }

        for(int i = 0; i < player2View.size(); i++){
            try {                
                card = ImageIO.read(new File(player2View.get(i)));
            } 
            catch (IOException ex) {
                  // handle exception...
            }
            g.drawImage(card, 1010 + i * 60, 560, 50, 72, this);
        }

        if(dealerShow){
            for(int i = 0; i < dealerView.size(); i++){
                try {                
                    card = ImageIO.read(new File(dealerView.get(i)));
                    //card = g.createflipped(card);
                } 
                catch (IOException ex) {
                      // handle exception...
                }
                g.drawImage(card, 610 + i * 60, 535, 50, 72, this);
                //g.draw
            }
        }
        else if((!dealerShow) && (dealerView.size() != 0)){
            try {                
                card = ImageIO.read(new File(dealerView.get(0)));
                //card = g.createflipped(card);
            } 
            catch (IOException ex) {
                  // handle exception...
            }
            g.drawImage(card, 610, 535, 50, 72, this);
            if(dealerView.size() > 1){
                try {                
                    card = ImageIO.read(new File("Rub.png"));
                    //card = g.createflipped(card);
                } 
                catch (IOException ex) {
                      // handle exception...
                }
                g.drawImage(card, 670, 535, 50, 72, this);
            }
        }

        text.setText("Sum is: " + sum.toString());
        text1.setText("P1 sum is: " + p1sum.toString());
        text2.setText("P2 sum is: " + p2sum.toString());
        if(dealerShow){
            textD.setText("Dealer Sum is: " + sum_dealer.toString());
        }
        else{
            textD.setText("Dealer Sum is: ?");
        }
        cashShow.setText("$ is: " + gam.get_player_cash().toString());
        cashShow1.setText("$ is: " + gam.get_player1_cash().toString());
        cashShow2.setText("$ is: " + gam.get_player2_cash().toString());
        if(clip.isActive() == false){
            playCounter++;
            try 
            {    
            clip1 = AudioSystem.getClip();
            clip1.open(sound.get(playCounter % sound.size()));
            //clip1.start();
            //clip.loop(0);    
            } 
            catch (Exception e) {}
        }
    }
}
