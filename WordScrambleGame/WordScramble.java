
/**
 * Kate Evans
 * CSC 220-03
 * Project 3
 *
 * This is a word unscrambling game. The program reads a random word from a text file
 * then scrambles the word and allows the user to solve the word by moving letters around
 * or entering a guess.
 */

import java.util.*;
import java.io.*;
public class WordScramble
{
  public static void main(String [] args)throws IOException {
      
      /** Variable that controls the while loop. */
      boolean gameWon = false;
      
      Scanner fileScan = new Scanner (new File("words.txt"));
      Scanner scan = new Scanner (System.in); 
        
      Random random = new Random();
        
      /** Variables for reading a random word. */
      int lineNumber = random.nextInt(10);
      int lineCount = 0;
      String input = "";

      while (fileScan.hasNext()){
        /**  the selected word will be from a random line*/
        if (lineCount == lineNumber){
        input = fileScan.nextLine();
        }
        /** Reads each line and increases the count avoid reading a line that doesn't exist. */
        if (lineCount < 9){
          fileScan.nextLine();
          lineCount++;
		}
      }
      
      int index = input.length(); /** Determines the size of the arrays. */
      int count = 0; /** How many moves it takes the user to solve the word. */
      int randomLoop = random.nextInt(100); /** How many times the shuffling loop runs. */
      
      int x = 0; /** Used for printing the index values. */
      int y = 0;
      int z = 0;
        
      char[] word = new char[index]; /** Holds the original word. */
      char[] shuffle = new char[index]; /** Holds the shuffled word. */
      char[] answer = new char[index]; /** Holds the user's answer for option 2. */
      
      /** Places each character in an individual section of the array. */
      for(int i = 0; i < index; i++){
        word[i] = input.charAt(i);
        shuffle[i] = input.charAt(i);
      }
      
      /** Shuffles the word. */
      for(int i = 0; i < randomLoop; i++){
        /** Selects are random indicies. */
        int randomIndex1 = random.nextInt(index);
        int randomIndex2 = random.nextInt(index);
        
        /** Switches the letters at the random indices. */    
        char letter1 = shuffle[randomIndex1];
        char letter2 = shuffle[randomIndex2];
        char letterSwitch = letter1;
        
        shuffle[randomIndex1] = letter2;
        shuffle[randomIndex2] = letter1;
      }
      
      /** The initial point of entry for the user. */
      System.out.print("\n--------------------\n");
      System.out.print("0 ");
      for (int i = 0; i < index - 1; i++){
        y = x + 1;
        z = y;
        x = z;
        System.out.print(z + " ");     
      }
      System.out.println("");
        
      /** Prints the shuffled word. */
      for(int i = 0; i < shuffle.length; i++){
        System.out.print(shuffle[i] + " ");     
      }
      System.out.print("\n--------------------\n");
      System.out.println("\nEnter 1 to swap letters.\nEnter 2 to solve.\nEnter 3 to quit.\n");
            
      int userInput = scan.nextInt();
      
      /** The loop that runs the game. */
      while (gameWon == false){
        /** The index values are printed again. */
        x = 0;
        y = 0;
        z = 0;
        
        System.out.print("\n--------------------\n");
        System.out.print("0 ");
        for (int i = 0; i < index - 1; i++){
          y = x + 1;
          z = y;
          x = z;
          System.out.print(z + " ");
              
        }
        System.out.println("");
        
        /** Prints the shuffled word. */
        for(int i = 0; i < shuffle.length; i++){
          System.out.print(shuffle[i] + " ");
            
        }
        System.out.print("\n--------------------\n");
        
        /** Controls what happens when the user selects options 1, 2, or 3. */
        switch(userInput){
          case 1:
            /** User inputs two indicies. */
            System.out.println("\n\nEnter the indices separated by a space: ");
            int index1 = scan.nextInt(); 
            int index2 = scan.nextInt();
            
            /** Switches the letters only if a valid index is entered. */
            if(index1 < shuffle.length && index2 < shuffle.length){
              char letter1 = shuffle[index1];
              char letter2 = shuffle[index2];
              char letterSwitch = letter1;
            
              shuffle[index1] = letter2;
              shuffle[index2] = letter1;
            }
            else{
              System.out.print("\nPlease enter a valid index.\n");
            }
           
            count++; /** Keeps track of user's moves. */
            
            /** Checks if the word is unshuffled yet. If not it restarts the original menu.*/
            if(Arrays.equals(word , shuffle)){
              gameWon = true;
              System.out.println("\nCongratulations! You have unscrambled the word " + input + " in " + count + " steps!");
              System.exit(0);
            }
            else{
              System.out.print("\n--------------------\n");
              System.out.print("0 ");
              for (int i = 0; i < index - 1; i++){
                y = x + 1;
                z = y;
                x = z;
                System.out.print(z + " ");     
              }
              System.out.println("");
        
              /** Prints the shuffled word. */
      
              for(int i = 0; i < shuffle.length; i++){
                System.out.print(shuffle[i] + " ");     
              }
              System.out.print("\n--------------------\n");
              System.out.println("\nEnter 1 to swap letters.\nEnter 2 to solve.\nEnter 3 to quit.\n");
              userInput = scan.nextInt();
            }
          break;
          
          case 2:
          
            /** User guesses the answer. */
            scan.nextLine(); /** Starts the nextLine scanner. Didn't work without it. */
            System.out.print("\nPlease enter your answer: ");
            
            String userAnswer = scan.nextLine();
            
            /** Places user's answer in an array. */
            int possibleAnswer = userAnswer.length();
            for(int i = 0; i < possibleAnswer; i++){
              answer[i] = userAnswer.charAt(i);
            }
            /** Checks if answer is correct. If not it restarts the original menu. */
            if(Arrays.equals(word , answer)){
              System.out.println("\nCongratulations! You have unscrambled the word " + input + " in " + count + " steps!");
              System.exit(0);
            }
            else{
              gameWon = false;
              System.out.print("\nWrong answer. Try again.\n");
                
              System.out.println("\nEnter 1 to swap letters.\nEnter 2 to solve.\nEnter 3 to quit.\n");
              userInput = scan.nextInt();
            }
          break;
          case 3:
            System.out.println("\nThe word was " + input + "\nThank you for playing.\n");
            System.exit(0);
          break;
          
          default:
            System.out.println("\nPlease enter a valid integer.\n");
        }
        
      }
    }
    
}
