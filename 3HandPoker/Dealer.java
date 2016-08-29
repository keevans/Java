/**
 *	Assignment: Project 2
 *	Due date: 03/24/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Dealer class is the class that controls the poker game.
 * In this class the card deck is created, shuffled, hands and players are created, 
 * and the winner of each game is decided.
 * This class is a child of Player.
 *
 * @author Kate Evans
 */
import jsjf.*;
import java.util.*;
import java.io.*;
public class Dealer extends Player implements Comparable<Player> {
  
  /**
  * An array that holds the deck of PlayingCards
  */  
  private PlayingCard[] cards = new PlayingCard [52];
  
  /**
  * A stack that holds the shuffled deck of cards and is used to create each Hand.
  */  
  private ArrayStack<PlayingCard> stack = new ArrayStack<PlayingCard>(52);
  
  public Dealer(){
  
  }
  
  /**
  * The constructor for Dealer. It calls the constructor in Player to initialize
  * one Dealer.
  *
  * @param String	name Accepts the name for the Dealer.
  * @param Hand	hand Accepts the Hand for the Dealer.
  */
  public Dealer(String name, Hand hand){
    super(name, hand);
  }
  
  /**
  * The deal method controls the game. It creates and shuffles the deck of cards using
  * and array and a stack. It then creates the Hand objects and distributes them to each
  * new Player object. It then calls a method to determine a winner and prints the results.
  *
  * @throws IOException due to the try-catch block printing Exceptions to a file
  */
  public void deal()throws IOException{
    try{
    
      /**
      * Creates, shuffles, and send the deck to the ArrayStack.
      */  
      populateCards();
    	shuffleCardsArray();
    	toArrayStack();
    	
      /**
      * Creates each hand.
      */  
   	  Hand hand1 = setHand();
   	  Hand hand2 = setHand();
   	  Hand hand3 = setHand();
   	  Hand hand4 = setHand();
   	  Hand hand5 = setHand();
   	  
      /**
      * Creates four Players and a Dealer, passing them the previously created Hands.
      */  
      Player player1 = new Player("Bobby", hand1);
    	Player player2 = new Player("Maria", hand2);
    	Player player3 = new Player("Susan", hand3);
    	Player player4 = new Player("David", hand4);
    	Player dealer = new Dealer("Dealer", hand5);
    
      /**
      * Calls the determineWinner method to get the winner for each game.
      */  
    	String game1 = determineWinner(player1, dealer);
    	String game2 = determineWinner(player2, dealer);
    	String game3 = determineWinner(player3, dealer);
    	String game4 = determineWinner(player4, dealer);
    	
    	/**
      * Prints the results using \t to separate the information.
      */  
   	  System.out.println("Player 1\t(" + player1.getName() + "):\t" + player1.getHand() + "\t" + game1 +
   	                     "\n" + "Player 2\t(" + player2.getName() + "):\t" + player2.getHand() + "\t" + game2 +
   	                     "\n" + "Player 3\t(" + player3.getName() + "):\t" + player3.getHand() + "\t" + game3 +
   	                     "\n" + "Player 4\t(" + player4.getName() + "):\t" + player4.getHand() + "\t" + game4 +
   	                     "\n" + "Dealer\t(" + dealer.getName() + "):\t" + dealer.getHand());
    }
    
    /**
    * Catches and exceptions thrown and prints then to the file exceptions.txt
    */  
    catch(Exception exception){
      String file = "exceptions.txt";
      FileWriter fw = new FileWriter(file);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter outFile = new PrintWriter(bw);
      outFile.print(exception.getMessage());
      outFile.print(exception.getStackTrace());
      outFile.close();
    }
  }//end method
  
  /**
  * Creates the deck of cards by filling the array with 52 PlayingCard objects.
  */  
  private void populateCards(){
    /**
    * The char for the cards suit and then counters for each suit to determine ranks
    */  
    char cardSuit;
    int count1 = 2;
    int count2 = 2;
    int count3 = 2;
    int count4 = 2;
    
    /**
    * For loop that creates the cards based on the index of the array that they are being
    * added to.
    */  
    for(int index = 0; index < cards.length; index++){
      if(index >= 0 && index <= 12){
        cardSuit = 'H';
        cards[index] = new PlayingCard(count1, cardSuit);
        count1++;
      }
      else if(index >= 13 && index <= 25){
        cardSuit = 'C';
        cards[index] = new PlayingCard(count2, cardSuit);
        count2++;
      }
      else if(index >= 26 && index <= 38){
        cardSuit = 'D';
        cards[index] = new PlayingCard(count3, cardSuit);
        count3++;
      }
      else if(index >= 39 && index <= 51){
        cardSuit = 'S';
        cards[index] = new PlayingCard(count4, cardSuit);
        count4++;
      }
    }//end for
  }//end method
  
  /**
  * Shuffles the deck of cards in the array.
  */  
  private void shuffleCardsArray(){
    /**
    * Temporary place holder.
    */  
    PlayingCard [] temp = new PlayingCard[1];
    Random random = new Random();
    /**
    * For loop that changes the range in which the random number can be generated.
    * minIndex is the lowest number the Random number generator can choose from and is
    * one of the indexes for the cards being shuffled.
    */  
    for(int minIndex = 0; minIndex < 52; minIndex++){
      int randomIndex = random.nextInt(52 - minIndex) + minIndex;
      temp[0] = cards[minIndex];
      cards[minIndex] = cards[randomIndex];
      cards[randomIndex] = temp[0];
    }
  }//end method
  
  /**
  * Pushes the contents of the array onto the stack.
  */  
  private void toArrayStack(){
    for(int index = 0; index < cards.length; index++){
      stack.push(cards[index]);
    }
  }//end method
  
  /**
  * Creates a Hand object when called by popping elements off of the stack.
  *
  * @return A Hand object.
  */
  private Hand setHand(){
    PlayingCard a = stack.pop();
    PlayingCard b = stack.pop();
    PlayingCard c = stack.pop();
    Hand hand = new Hand(a,b,c);
    
    return hand;
  }//end method
  
  /**
  * Determines the winner of each game and produces the output that is printed in the
  * deal method.
  *
  * @param Player player1 Accepts on of the four Player objects.
  * @param Player player2 Accepts the Dealer object.
  * @return A String of output to be printed detailing who won the game and how.
  */
  private String determineWinner(Player player1, Player player2){
    /**
    * Compare the Player and the Dealer to determine who won
    */ 
    int game = player1.compareTo(player2);
    
    /**
    * Gets the type of Hand the Player has.
    */ 
    int intType = player1.getHandType();
    
    /**
    * Gets the highest ranking card for the Player.
    */ 
    int card = player1.getHighestRankingCard();
    String type = "";
    String outcome = "";
    
    /**
    * A series of if statements for determining what to print for each type of hand
    * the player has. Not used for determining the winner, just for output.
    */ 
    if(intType == 6){
      type = "a straight flush";
    }
    if(intType == 5){
      if (card == 11){
        type = "three Jacks";
      }
      else if (card == 12){
        type = "three Queens";
      }
      else if (card == 13){
        type = "three Kings";
      }
      else if (card == 14){
        type = "three Aces";
      }
      else{
        type = "three " + card + "s";
      }
    }
    if(intType == 4){
      type = "a straight";
    }
    if(intType == 3){
      type = "a flush";
    }
    if(intType == 2){
      
      if (card == 11){
        type = "a pair of Jacks";
      }
      else if (card == 12){
        type = "a pair of Queens";
      }
      else if (card == 13){
        type = "a pair of Kings";
      }
      else if (card == 14){
        type = "a pair of Aces";
      }
      else{
        type = "a pair of " + card + "s";
      }
    }
    if(intType == 1){
      if (card == 11){
        type = "a Jack high card";
      }
      else if (card == 12){
        type = "a Queen high card";
      }
      else if (card == 13){
        type = "a King high card";
      }
      else if (card == 14){
        type = "an Ace high card";
      }
      else{
        type = "a high card of " + card;
      }
    }
    
    /**
    * A series of if statements that determine who won the game based on
    * the compareTo statement at the top of the method.
    */ 
    if(game == -1){
      outcome ="(lost to the dealer with " + type + ")";
    }
    if(game == 1){
      outcome ="(beat the dealer with " + type + ")";
    }
    if(game == 0){
      /**
      * The tie breaker section that uses the highest ranking cards in the deck to
      * determine if there is a winner or not.
      */ 
      int rankPlayer = player1.getHighestRankingCard();
      int rankDealer = player2.getHighestRankingCard();
      
      if(rankPlayer < rankDealer){
        outcome ="(lost to the dealer with " + type + ")";
      }
      if(rankPlayer > rankDealer){
        outcome ="(beat the dealer with " + type + ")";
      }
      if(rankPlayer == rankDealer){
        outcome ="(push with " + type + ")";
      }
    }
    return outcome;
  }//end method
  /**
  * compareTo not here as it is inherited from Player
  */
  
}//end class