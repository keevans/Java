//*********************************************
// MinimaxPlayer.java         
//
// implements a player that randomly chooses its next move 
//*********************************************

import java.util.*;
public class MinimaxPlayer{
	private Board board; // boolean valid remove, boolean valid move, move, remove, size
	private int piece;
	private int other;

	//************************************************************
    //Constructor
    //initializes a MinimaxPlayer given a Board Object and the player's piece
    //************************************************************
	public MinimaxPlayer(Board b, int p){
		piece = p;
		board = b;

		if(p == 1){
			other = 2;
		}
		else{
			other = 1;
		}
	}

	//************************************************************
    //Makes the player play the game and determines if the player has another move
    //Returns: true or false
    //************************************************************
	public boolean play(){
		boolean canMove;
		int size = board.getSize();

		//condition for the first or second move of the game
	    if (board.getEmpty() == 0 || board.getEmpty() == 1){
	    	Board nextMove = minimax(board);
	    	if(nextMove == null){
	    		canMove = false;
	    	}
	    	else{
	    	  //initializing the row and column variables
	    	  int row = -1;
	    	  int column = -1;
	    	  //Comparing the original board with the nextMove board
	    	  //The nextMove board will have more and differently positioned empty positions
	    	  for(int i = 0; i < size; i++){
	    		for(int j = 0; j < size; j++){
	    			//Moves through each space on the board to see if it's empty
	    			boolean check1 = board.isEmpty(i,j);
	    			boolean check2 = nextMove.isEmpty(i,j);
	    			if(check1 == false && check2 == true){
	    				//If difference found it checks that it is the correct piece
	    				if((i+j)%2+1 == piece){
	    					//notes the difference between the two boards
	    					row = i;
	    					column = j;
	    				}
	    			}
	    		}
	    	}
	    	//removes the piece from the board
	    	board.remove(row, column, piece);
	    	canMove = true;
	    	}
	    }
	    else{
	    	Board nextMove = minimax(board);
	    	if(nextMove == null){
	    		canMove = false;
	    	}
	    	else{
	    	  //initializing the positions for the piece to move and where to move it to
	    	  int startR = -1;
	    	  int startC = -1;
	    	  int finalR = -1;
	    	  int finalC = -1;
	    	  //Comparing the original board with the nextMove board
	    	  //The nextMove board will have more and differently positioned empty positions
	    	  for(int i = 0; i < size; i++){
	    		  for(int j = 0; j < size; j++){
	    		  	  //Moves through each space on the board to see if it's empty
	    			  boolean check1 = board.isEmpty(i,j);
	    			  boolean check2 = nextMove.isEmpty(i,j);
	    			  //Checks to see if an empty space on the first board now has a piece on the second
	    			  if(check1 == true && check2 == false){
	    			  	//If difference found it checks if the space holds the correct piece
	    			  	if((i+j)%2+1 == piece){
	    			  		//Notes the location to move the piece too
	    					finalR = i;
	    				    finalC = j;
	    				}
	    			  }
	    			  //Checks to see if a postion that had a piece is now empty
	    			  if(check1 == false && check2 == true){
	    			  	//If difference found it checks if the space holds the correct piece
	    				if((i+j)%2+1 == piece){
	    					//Notes the location of the piece to move
	    					startR = i;
	    					startC = j;
	    				}
	    			}
	    		}
	    	}
	    	//moves the piece
	    	board.move(startR, startC, finalR, finalC, piece);
	    	canMove = true;
	    	}
	    }
	    return canMove;
	}

	//************************************************************
    //Generates the next possible moves from a Board Object
    //Parameters: Board b: Board Oject
    //Returns: An ArrayList of Board Objects
    //************************************************************
	private ArrayList<Board> generateBoards(Board b, int player){
		Board start = new Board(b); //copy of the orginal board
		ArrayList<Board> list = new ArrayList<Board>(); //arraylist to hold the possible moves
		int [] rMoves = {-2, 2, 0, 0}; //array of directions the pieces can move in the rows
		int [] cMoves = {0, 0, -2, 2};//array of directions the pieces can move in the columns
		int size = start.getSize(); //size of board

		//condition for the 1st and 2nd moves of the game		
		if(board.getEmpty() == 0 || board.getEmpty() == 1){
			//goes through all positions on the board
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					//checks that the correct piece is being used
					if((i+j)%2+1 == player){
						//checks the removal is valid
						if(start.validRemove(i,j,player)){
							//copies the board
							Board hold = new Board(start);
							//removes the piece on the board
							hold.remove(i,j,player);
							//adds the new board to the arraylist of possible moves
							list.add(hold);
						}
					}
				}
			}
		}
		else{
		  //goes through all positions on the board
		  for(int i = 0; i < size; i++){
		    for(int j = 0; j < size; j++){
				//checks that the correct piece is being used
				if((i+j)%2+1 == player){
				  //goes through all the possible moves for the rows and columns
				  for(int k = 0; k < 4; k++){
					int x = i + rMoves[k];//finds the new row position
					int y = j + cMoves[k];//finds the new column position
					//checks if the move is valid
					if(start.validMove(i,j,x,y,player)){
					  //copies the board
					  Board hold = new Board(start);
					  //makes the move on the copy
					  hold.move(i,j,x,y,player);
					  //adds the new board to the arraylist of possible moves
					  list.add(hold);
					  }//end if validMove
				  }//end for k
			    }//end if %2 == 1
			}//end for j
		  }//end for i
		}//end else
		return list;
	}//end method

	//************************************************************
    //Evaluates the board based on corners and edges occupied and number of "islands"
    //Parameters: Board b, int player: Board Object, The player making the move
    //Returns: The evaluation int multiplied by the number of empty spaces
    //************************************************************
	private int evaluate(Board b, int player){
	  int evaluation = 0;
	  int size = b.getSize();
	  int map[][] = new int[size][size];
	  int otherPlayer;
	  if(player == 1){
	  	otherPlayer = 2;
	  }
	  else{
	  	otherPlayer = 1;
	  }

	  for(int i = 0; i < size; i++){
		for(int j = 0; j < size; j++){
		  if((i == 0 || j == 0 || i == 3 || j == 3) && ((i+j)%2+1 == player)){
			evaluation++;
		  }
		  if(((i == 0 && j == 0) || (i == 0 && j == 3) || (i == 3 && j == 0) || (i == 3 && j == 3)) && ((i+j)%2+1 == player)){
			evaluation++;
		  }
		  if(b.isEmpty(i,j)){
		  	map[i][j] = 0;
		  }
		  else{
		  	map[i][j] = (i+j)%2+1;
		  }
		}
	  }
	  for(int k = 0; k < size-3; k++){
	  	for (int l = 0; l < size-3; l++){
	  		if(map[k][l]== otherPlayer && map[k+1][l+1]== player && map[k+2][l+2]== otherPlayer){
	  			evaluation++;
	  		}
	  		if(map[l][k]== otherPlayer && map[l+1][k+1]== player && map[l+2][k+2]== otherPlayer){
	  			evaluation++;
	  		}
	  	}
	  }
	  return evaluation*b.getEmpty();
    }
    

	//************************************************************
    //Searches through the possible moves to find the best one
    //Parameters: Board b: Board Object
    //Returns: Board for the next move
    //************************************************************
    int depth = 0; 
	private Board minimax(Board b){
		ArrayList<Board> options = new ArrayList<Board>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		ArrayList<Board> toEvaluate = generateBoards(b, piece);
		for(int i = 0; i < toEvaluate.size(); i++){
			Board temp = toEvaluate.get(i);
			int v = maxValue(temp, -1000, 1000, depth);
			options.add(temp);
			values.add(v);
		}
		int index = 0;
		for(int j = 0; j < values.size()-1; j++){
			int val1 = values.get(j);
			int val2 = values.get(j+1);
			if(val1 > val2){
				index = j;
			}
			else{
				index = j+1;
			}
		}
		if(options.size() == 0){
			return null;
		}
		else{
		Board finalBoard = options.get(index);
		return finalBoard;
	    }
	}
	//************************************************************
    //Finds the max value for a path
    //Parameters: Board b, int alpha, int beta, int depth: Board Object, the alpha value, the beta value, the depth the search has reached
    //Returns: the max value
    //************************************************************
	private int maxValue(Board b, int alpha, int beta, int depth){
		depth++;
		int v = -1000;
		if(depth == 4){
			v = evaluate(b,piece);
		}
		if (depth < 4){
		  ArrayList<Board> options = generateBoards(b, piece);//min will be other
		  for(int i = 0; i < options.size(); i++){
			Board temp = options.get(i);
			int hold = minValue(temp, alpha, beta, depth);
		    v = max(v, hold);
			if (v >= beta){
				return v;

			}
			alpha = max(alpha, v);
		  }
	  }
	  //System.out.println("Max: " + v);
	  return v;

	}

	//************************************************************
    //Finds the min value for a path
    //Parameters: Board b, int alpha, int beta, int depth: Board Object, the alpha value, the beta value, the depth the search has reached
    //Returns: the min value
    //************************************************************
	private int minValue(Board b, int alpha, int beta, int depth){
		depth++;
		int v = 1000;
		if(depth == 4){
			v = evaluate(b, piece);
		}
		else{
		  ArrayList<Board> options = generateBoards(b, other);
		  for(int i = 0; i < options.size(); i++){
			Board temp = options.get(i);
			int hold = maxValue(temp, alpha, beta, depth);
			v = min(v, hold);
			if (v <= alpha){
				return v;
			}
			beta = max(beta, v);
		  }
	  }
	  //System.out.println("Min: " + v);
	  return v;

	}

	//************************************************************
    //Compare two integers to find the larger one
    //Parameters: int b, int v: the first value, the second value
    //Returns: the max value
    //************************************************************
	private int max(int b, int v){
		int r = 0;
		if(b >= v){
			r = b;
		}
		if(b < v){
			r = v;
		}
		return r;
	}
	//************************************************************
    //Compare two integers to find the smaller one
    //Parameters: int b, int v: the first value, the second value
    //Returns: the min value
    //************************************************************
	private int min(int a, int v){
		int r = 0;
		if(a <= v){
			r = a;
		}
		if(a > v){
			r = v;
		}
		return r;
	}
}



