/**
 *	Assignment: Project 2
 *	Due date: 03/24/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Player class stores the information for each Player object and allows the Dealer
 * to access information about each Players Hand.
 *
 * @author Kate Evans
 */
public class Player implements Comparable<Player>{
  /**
  * An Hand object representing the Player's hand.
  */  
  protected Hand playerHand;
 
  /**
  * The name of the player.
  */  
  protected String playerName = " ";
  
  public Player(){
  
  }
  
  /**
  * The constructor for Player. It initializes each Player object.
  *
  * @param String	name Accepts the name for each Player.
  * @param Hand	hand Accepts the Hand for each Player.
  */
  public Player(String name, Hand hand){
    playerName = name;
    playerHand = hand;
  }//end method
  
  /**
  * Allows the program to find name of the Player
  *
  * @return The player's name.
  */
  public String getName(){
    return playerName;
  }
  
  /**
  * Allows the program to find the Player's Hand
  *
  * @return The player's Hand.
  */
  public Hand getHand(){
    return playerHand;
  }//end method
  
  /**
  * Allows the program to find the type of Hand the Player has.
  *
  * @return The hand type.
  */
  public int getHandType(){
    int type = playerHand.handType();
    return type;
  }//end method
  
  /**
  * Allows the program to find the highest ranking card in a Player's Hand.
  *
  * @return The player's highest card.
  */
  public int getHighestRankingCard(){
    int card = playerHand.getHighestRankingCard();
    return card;
  }//end method
  
  /**
  * Compares the Player's Hand to another Player's Hand to determine who won the game.
  *
  * @return The int representing who won the game..
  */
  public int compareTo(Player obj){
    int compare = playerHand.compareTo(obj.getHand());
    return compare;
  }//end method
  
  /**
  * Allows the Player objects to be easily printed.
  *
  * @return The concatenated string of the Player's Hand and name.
  */
  public String toString(){
    String string = playerHand.toString();
    
    return string + playerName;
  }//end method

}//end class