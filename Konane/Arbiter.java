//*********************************************
// Arbiter.java         
//
// Konane arbiter. 
// To run: java Arbiter
// it should be pretty self-explanatory
//*********************************************

import java.util.*;
import java.text.NumberFormat;

public class Arbiter
{
    public static void main (String[] args)
    {
	int n_games;         // number of games played
	int first_wins = 0;  // number of wins for first player
	int second_wins = 0; // number of wins for second player
	boolean display;     // display the board or not


	// Get info from user
	Scanner scan = new Scanner(System.in);

	System.out.print("Please enter board size: ");
	int board_size = scan.nextInt();

	System.out.print("Display board after each move? (y/n)");
	String disp = scan.next();
	display = (disp.toLowerCase()).equals("y");

	System.out.print("How many games? ");
	n_games = scan.nextInt();

	// Start playing games
	for(int k = 0; k < n_games; k++){

	    //initialize board
	    Board board = new Board(board_size);
	
	    //initialize players
	    // feel free to replace your own types in here
	    SmartPlayer first = new SmartPlayer(board, 1);
	    //DumbPlayer second = new DumbPlayer(board, 2);

	    //SmartPlayer second = new SmartPlayer(board, 2);
	    //DumbPlayer first = new DumbPlayer(board, 1);

	    //User first = new User(board, 1);
	    //RandomPlayer first = new RandomPlayer(board, 1);
	    //RandomPlayer second = new RandomPlayer(board, 2);

	    //MinimaxPlayer first = new MinimaxPlayer(board, 1);
	    MinimaxPlayer second = new MinimaxPlayer(board, 2);

	    int winner = 0; // no winner yet

	    if(display)
		board.displayBoard();

	    while(winner == 0){
		System.out.println("First player's turn");
		// wait, to give the user a chance to read the display
		if(display){
		    try {Thread.sleep(2000);} 
		    catch (InterruptedException e){}
		}

		//let first play
		if(first.play()){
		    //he was able to make a move
		    if(display)
			board.displayBoard();
		    System.out.println("Second player's turn");
		    // wait, to give the user a chance to read the display
		    if(display){
			try {Thread.sleep(2000);} 
			catch (InterruptedException e){}
		    }

		    //let second play
		    if(second.play()){
			//he was able to make a move
			if(display)
			    board.displayBoard();
		    }		
		    else{
			//second couldn't move
			winner = 1;
		    }
		}
		else{
		    //first couldn't move
		    winner = 2;
		}
	    }
	    
	    //we have a winner
	    if(winner == 1){
		System.out.println("Second can't move. First wins!");
		first_wins++;
	    }
	    else{
		second_wins++;
		System.out.println("First can't move. Second wins!");
	    }
	}

	// Print number of wins, formatted
	NumberFormat fmt = NumberFormat.getPercentInstance();
	System.out.println("First won " + first_wins + " times, that is " + 
			   fmt.format((double)first_wins/n_games));
	System.out.println("Second won " + second_wins + " times, that is " + 
			   fmt.format((double)second_wins/n_games));
    }

}	
	
	
