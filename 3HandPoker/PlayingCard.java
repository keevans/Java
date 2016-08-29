/**
 *	Assignment: Project 2
 *	Due date: 03/24/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The PlayingCard class stores the information for each PlayingCard object
 * used in the poker game. It also allows the other classes to access information
 * on each PlayingCard object.
 *
 * @author Kate Evans
 */

public class PlayingCard implements Comparable<PlayingCard>{

  /**
  * Holds the suit for each card in the deck. It will be 'H', 'C', 'S', or 'D'
  */
  private char suit;
  
  /**
  * Holds the rank for each card in the deck. It will be 1-14.
  */
  private int rank;
  
  /**
  * The constructor for PlayingCard. It initializes each PlayingCard object.
  *
  * @param int	tempRank Accepts the rank for each card.
  * @param char	tempSuit Accepts the suit for each card.
  */
  public PlayingCard(int tempRank, char tempSuit){
    suit = tempSuit;
    rank = tempRank;
  }
  
  /**
  * Allows the other classes to access the suit of a PlayingCard object.
  *
  * @return The character representing the suit.
  */
  public char getSuit(){
    return suit;
  }
  
  /**
  * Allows the other classes to access the rank of a PlayingCard object.
  *
  * @return The int representing the rank.
  */
  public int getRank(){
    return rank;
  }
  
  /**
  * The compareTo method allows PlayingCard objects to be Comparable.
  * This is used when creating a Hand object and makes it easier to determine
  * which player won a tie breaker.
  *
  * @param PlayingCard obj A PlayingCard object for comparison.
  * @return The int the tells if the rank of one PlayingCard is less than,
  * 				greater than, or equal to the rank of another PlayingCard.
  */
  public int compareTo(PlayingCard obj){
    int compare = 0;
    int rank2 = obj.getRank();
    
    if(rank < rank2){
      compare = -1;
    }
    if(rank == rank2){
      compare = 0;
    }
    if (rank > rank2){
      compare = 1;
    }
    return compare;
  }
  
  /**
  * Allows the PlayingCard objects to be easily printed.
  *
  * @return The concatenated string of a cards rank and suit.
  */
  public String toString(){
    String string;
    if (rank == 11){
      string = "J"+suit;
    }
    else if (rank == 12){
      string = "Q"+suit;
    }
    else if (rank == 13){
      string = "K"+suit;
    }
    else if (rank == 14){
      string = "A"+suit;
    }
    else{
      string = ""+ rank+suit;
    }
    return string;    
  }
}