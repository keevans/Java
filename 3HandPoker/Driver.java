/**
 *	Assignment: Project 2
 *	Due date: 03/24/2014
 *	Instructor: Dr. DePasquale
 *	Submitted by: Kate Evans
 */
 
 /**
 * The Driver class contains the main method that initiates the program, a game of
 * three card poker where each Player is playing against the Dealer.
 *
 * @author Kate Evans
 */

import java.io.*;
public class Driver{
  /**
  * The main method calls the deal method in Dealer to start the program.
  *
  * @param String [] args
  * @throws IOException Thrown due to exception handling in Dealer
  */
  public static void main(String [] args)throws IOException{
    Dealer dealer = new Dealer();
    dealer.deal();
  }//end main
}//end class