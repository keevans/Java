/**
 *	Assignment: Project 2
 *	Due date: 03/24/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Hand class stores the information for the Hand objects which are made up of
 * three PlayingCard objects stored in an array. This class allows the Player to access
 * information about the three cards.
 *
 * @author Kate Evans
 */

public class Hand implements Comparable<Hand>{

  /**
  * An array that holds the 3 PlayingCard objects that make up a hand.
  */  
  private PlayingCard[] cards = new PlayingCard[3];
  
  /**
  * An int that represents the type of hand (straight, flush, pair, etc).
  * Used by the compareTo to determine which hand wins.
  */
  private int handType = 0;
  
  /**
  * The constructor that initializes each Hand object. It is made up of three
  * PlayingCard objects which are placed in an array from least to greatest, except
  * in the case of pairs where the pair will always take the highest two spots regardless,
  * of the rank of the third card.
  *
  * @param PlayingCard card1 The first PlayingCard object.
  * @param PlayingCard card2 The second PlayingCard object.
  * @param PlayingCard card3 The third PlayingCard object.
  */
  public Hand(PlayingCard card1, PlayingCard card2, PlayingCard card3){
    
    /**
    * Each card is compared to determine what order the Hand should be set in.
    */  
    int c1 = card1.compareTo(card2);
    int c2 = card2.compareTo(card3);
    int c3 = card3.compareTo(card1);
    
    /**
    * Card order: card1, card2, card3
    */  
    if(c1 == -1 && c2 == -1 && c3 == 1){
      cards[0] = card1;
      cards[1] = card2;
      cards[2] = card3;
    }//1
    
    /**
    * Card order: card1, card3, card2
    */  
    if(c1 == -1 && c2 == 1 && c3 == 1){
      cards[0] = card1;
      cards[1] = card3;
      cards[2] = card2;
    }//2
    
    /**
    * Card order: card2, card1, card3
    */  
    if(c1 == 1 && c2 == -1 && c3 == 1){
      cards[0] = card2;
      cards[1] = card1;
      cards[2] = card3;
    }//3
    
    /**
    * Card order: card2, card3, card1
    */  
    if(c1 == 1 && c2 == -1 && c3 == -1){
      cards[0] = card2;
      cards[1] = card3;
      cards[2] = card1;
    }//4
    
    /**
    * Card order: card3, card2, card1
    */  
    if(c1 == -1 && c2 == 1 && c3 == -1){
      cards[0] = card3;
      cards[1] = card1;
      cards[2] = card2;
    }//5
    
    /**
    * Card order: card3, card2, card1
    */  
    if(c1 == 1 && c2 == 1 && c3 == -1){
      cards[0] = card3;
      cards[1] = card2;
      cards[2] = card1;
    }//6
    
    /**
    * Card order: card1, card2, card3
    * card2 and card3 are a pair.
    */  
    if(c1 == -1 && c2 == 0 && c3 == 1){
      cards[0] = card1;
      cards[1] = card2;
      cards[2] = card3;
    }//7
    
    /**
    * Card order: card1, card3, card2
    * card2 and card3 are a pair.
    */  
    if(c1 == 1 && c2 == 0 && c3 == -1){
      cards[0] = card1;
      cards[1] = card3;
      cards[2] = card2;
    }//8
    
    /**
    * Card order: card2, card1, card3
    * card1 and card3 are a pair.
    */  
    if(c1 == 1 && c2 == -1 && c3 == 0){
      cards[0] = card2;
      cards[1] = card1;
      cards[2] = card3;
    }//9
    
    /**
    * Card order: card2, card3, card1
    * card1 and card3 are a pair.
    */  
    if(c1 == -1 && c2 == 1 && c3 == 0){
      cards[0] = card2;
      cards[1] = card3;
      cards[2] = card1;
    }//10
    
    /**
    * Card order: card3, card1, card2
    * card1 and card2 are a pair.
    */  
    if(c1 == 0 && c2 == 1 && c3 == 1){
      cards[0] = card3;
      cards[1] = card1;
      cards[2] = card2;
    }//11
    
    /**
    * Card order: card3, card2, card1
    * card1 and card2 are a pair.
    */  
    if(c1 == 0 && c2 == -1 && c3 == -1){
      cards[0] = card3;
      cards[1] = card2;
      cards[2] = card1;
    }//12
    
    /**
    * Card order: card3, card2, card1
    * card1 and card2 are a pair.
    */  
    if(c1 == 0 && c2 == -1 && c3 == 1){
      cards[0] = card3;
      cards[1] = card2;
      cards[2] = card1;
    }//13
    
    /**
    * Card order: card3, card1, card2
    * card1 and card2 are a pair.
    */  
    if(c1 == 0 && c2 == 1 && c3 == -1){
      cards[0] = card3;
      cards[1] = card1;
      cards[2] = card2;
    }//14
    
    /**
    * Card order: card1, card2, card3
    * All three cards have the same rank
    */  
    if(c1 == 0 && c2 == 0 && c3 == 0){
      cards[0] = card1;
      cards[1] = card2;
      cards[2] = card3;
    }//15   
    
    /**
    * handType is set by calling the handType method
    */  
    handType = handType();
  }//end method
  
  /**
  * The handType method determines if the Hand is a straight flush(6), three of a kind(5),
  * a straight(4), a flush(3), a pair(2), or a set of random unrelated cards(1).
  *
  * @return The int type of a Hand
  */
  public int handType(){
    int type;
    
    /**
    * The card ranks are accessed.
    */  
    int cardRank1 = ((PlayingCard) (cards[0])).getRank();
    int cardRank2 = ((PlayingCard) (cards[1])).getRank();
    int cardRank3 = ((PlayingCard) (cards[2])).getRank();
    
    /**
    * The card suits are accessed.
    */ 
    char cardSuit1 = ((PlayingCard) (cards[0])).getSuit();
    char cardSuit2 = ((PlayingCard) (cards[1])).getSuit();
    char cardSuit3 = ((PlayingCard) (cards[2])).getSuit();
    
    /**
    * if statements are used to determine the type.
    */ 
    if(cardRank1 == cardRank2-1 && cardRank2 == cardRank3-1){
      if(cardSuit1 == cardSuit2 && cardSuit2 == cardSuit3){
        type = 6;
      }
      else{
        type = 4;
      }
    }
    else if(cardRank1 == cardRank2 && cardRank2 == cardRank3){
      type = 5;
    }
    else if(cardSuit1 == cardSuit2 && cardSuit2 == cardSuit3){
      type = 3;
    }
    else if((cardRank1 == cardRank2 || cardRank2 == cardRank3) && cardRank1 != cardRank3){
      type = 2;
    }
    else{
      type = 1;
    }
    
    return type;
  }//end method
  /**
  * Allows the program to find the rank of the highest ranking card which will always be 
  * in the last postion of the array. Used for tie breakers.
  *
  * @return The int rank of the highest ranking card
  */
  public int getHighestRankingCard(){
    int highestRank = ((PlayingCard) (cards[2])).getRank();
    return highestRank;
  }//end method
  
  /**
  * The compareTo method allows Hand objects to be Comparable.
  * This is used when determining which player won the game. This method
  * uses the handType to determine which Hand is greater than the other.
  *
  * @param Hand obj A Hand object for comparison.
  * @return The int the tells which Hand wins.
  */
  public int compareTo(Hand obj){
    int compare = 0;
    
    if(handType < obj.handType()){
      compare = -1;
    }
    if(handType == obj.handType()){
      compare = 0;
    }
    if(handType > obj.handType()){
      compare = 1;
    }
    
    return compare;
  }//end method
  
  /**
  * Allows the Hand objects to be easily printed.
  *
  * @return The concatenated string of the PlayingCard objects.
  */
  public String toString(){
    String c1 = ((PlayingCard) (cards[0])).toString();
    String c2 = ((PlayingCard) (cards[1])).toString();
    String c3 = ((PlayingCard) (cards[2])).toString();
    
    return c1 + "\t" + c2 + "\t" +c3;
  }//end method
}//end class