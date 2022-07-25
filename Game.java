package src.Model;
import java.io.*;
import java.util.*;

public class Game{

    private Vector<card> koloda;
    private Vector<card> player;
    private Vector<card> player1;
    private Vector<card> player2;
    private Vector<card> dealer;
    private Double player_cash;
    private Double player1_cash = 100.0;
    private Double player2_cash = 100.0;

    public Game(int cash){
        player_cash = (double) cash;
        koloda = new Vector<card>();
        player = new Vector<card>();
        player1 = new Vector<card>();
        player2 = new Vector<card>();
        dealer = new Vector<card>();
    }
    
    public Double get_player_cash(){
        return player_cash;
    }

    public Double get_player1_cash(){
        return player1_cash;
    }

    public Double get_player2_cash(){
        return player2_cash;
    }

    public void shuffle(){
        final Random rand = new Random();
        for (int i = 0; i < koloda.size(); i++) {
            int j = rand.nextInt(104);
            card tmp = koloda.get(j);
            koloda.set(j, koloda.get(i));
            koloda.set(i, tmp);
        }
    }

    public String getCard(){
        StringBuilder ret = new StringBuilder();
        if ((koloda.firstElement().name >= '2') && koloda.firstElement().name <= '9') 
            ret.append(koloda.firstElement().name);
        else if (koloda.firstElement().name == 'A')
            ret.append("ace");
        else if (koloda.firstElement().name == 'J')
            ret.append("jack");
        else if (koloda.firstElement().name == 'Q')
            ret.append("queen");
        else if (koloda.firstElement().name == 'K')
            ret.append("king");
        else
            ret.append("10");
        ret.append("_of_");
        ret.append(koloda.firstElement().mast);
        ret.append(".png");
        //koloda.remove(0);
        System.out.println(ret.toString());
        return ret.toString();
    }

    public int give_card(int sum, int to_player){
        if (to_player == 0) {
            player.add(koloda.firstElement());
        }
        else if(to_player == 1){
            player1.add(koloda.firstElement());
        }
        else if(to_player == 2){
            player2.add(koloda.firstElement());
        }
        else if(to_player == 3){
            dealer.add(koloda.firstElement());
        }
        if ((koloda.firstElement().name >= '2') && koloda.firstElement().name <= '9') sum += koloda.firstElement().name - '0';
        else if (koloda.firstElement().name == 'A') {
            if (sum < 11) sum += 11;
            else sum += 1;
        }
        else sum += 10;
        if (koloda.firstElement().name == '0') System.out.println("10 " + koloda.firstElement().mast);
        else System.out.println(koloda.firstElement().name + " " + koloda.firstElement().mast);
        koloda.remove(0);
        return sum;
    }

    public void make_cards(){
        char cards[] = { '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A' };
	    String masti[] = { "clubs", "diamonds", "hearts", "spades" };
	    for (int i = 0; i < 52; i++) {
		    card tmp = new card();
		    tmp.mast = masti[i % 4];
		    tmp.name = cards[i / 4];
		    koloda.add(tmp);
            koloda.add(tmp);
	    }
    }

    public boolean player1Decide(int sum){
        if (sum <= 11) {
            return true;
        }
        if (sum == 12) {
            if( (dealer.get(0).name >= '4') && (dealer.get(0).name <= '6')){
                return false;
            }
            else{
                return true;
            }
        }
        if ( (sum >= 13) && (sum <= 16) ){
            if( (dealer.get(0).name >= '2') && (dealer.get(0).name <= '6')){
                return false;
            }
            else{
                return true;
            }
        }
        else {
            return false;
        }


    }

    public boolean player2Decide(int sum){
        if(sum > 16){
            return false;
        }
        else{
            return true;
        }
    }

    public void Play(){
        make_cards();
        int input, bet, sum = 0, sum_dealer = 0;
        Scanner in = new Scanner(System.in);
        while (1 == 1) {
            System.out.println("Enter 1 to start, 2 to see your cash, 3 to exit");
            input = in.nextInt();
            if (input == 1) {
                sum = 0;
                sum_dealer = 0;
                System.out.println("Make tour bet: ");
                bet = in.nextInt();
                if ( (bet > player_cash) || (bet < 0) )
                {
                    System.out.println("Error ");
                    continue;
                }
                player_cash -= bet;
                shuffle();
                System.out.println("Your 2 cards: ");
                sum = give_card(sum, 0);
                sum = give_card(sum, 0);
                System.out.println("Sum is: " + sum);
                for (int j = 0; j < 20; j++)
                {
                    System.out.println("Enter 1 to take another one, enter 2 to stop");
                    input = in.nextInt();
                    if (input == 1)
                    {
                        sum = give_card(sum, 0);
                        System.out.println("Sum is: " + sum);
                        if (sum > 21)
                        {
                            System.out.println("This is too much");
                            break;
                        }
                    }
                    else break;
                }
                if (sum <= 21)
                {
                    System.out.println("Dealer's 2 cards: ");
                    sum_dealer = give_card(sum_dealer, 3);
                    sum_dealer = give_card(sum_dealer, 3);
                    System.out.println("Dealer's Sum is: " + sum_dealer);
                    while (sum_dealer < 17)
                    {
                        sum_dealer = give_card(sum_dealer, 3);
                        System.out.println("Dealer's Sum is: " + sum_dealer);
                        if (sum_dealer > 21) break;
                    }
                    if ((sum > sum_dealer) || (sum_dealer > 21))
                    {
                        System.out.println("You win!!!");
                        player_cash += bet * 2;
                    }
                    else if (sum == sum_dealer)
                    {
                        System.out.println("Draw");
                        player_cash += bet;
                    }
                    else System.out.println("You lose...");
                }
                else System.out.println("You lose...");
                while (player.size() != 0) {
                    koloda.add(player.firstElement());
                    player.remove(0);
                }
                while (dealer.size() != 0) {
                    koloda.add(dealer.firstElement());
                    dealer.remove(0);
                }
            }
            else if (input == 2) System.out.println("Cash: " + player_cash);
            else if (input == 3){
                in.close();
                break;
            } 
            else System.out.println("Error");
        }        
    }
    public void cardsClear(){
   
        while (player.size() != 0) {
            koloda.add(player.firstElement());
            player.remove(0);
        }


        while (player1.size() != 0) {
            koloda.add(player1.firstElement());
            player1.remove(0);
        }
 

        while (player2.size() != 0) {
            koloda.add(player2.firstElement());
            player2.remove(0);
        }

        while (dealer.size() != 0) {
            koloda.add(dealer.firstElement());
            dealer.remove(0);
        }
    }
    public void minusBet(Double bet, Double p1bet, Double p2bet){
        player_cash -= bet;
        player1_cash -= p1bet;
        player2_cash -= p2bet;
    }

    public void returnBet(int to_player, Double bet){
        if(to_player == 0){
            player_cash += bet;
        }
        if(to_player == 1){
            player1_cash += bet;
        }
        if(to_player == 2){
            player2_cash += bet;
        }
    }

    public void giveWin(int to_player, Double bet){
        if(to_player == 0){
            player_cash += bet * 1.5;
        }
        if(to_player == 1){
            player1_cash += bet * 1.5;
        }
        if(to_player == 2){
            player2_cash += bet * 1.5;
        }
    }
}