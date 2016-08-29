//*********************************************
// RandomPlayer.java         
//
// implements a player that randomly chooses its next move 
//*********************************************


import java.util.*;
public class RandomPlayer{

	private Board board; // Board object
	private int piece; // The player's piece
	private int other; //The opponent's piece

	//************************************************************
    //Constructor
    //Initializes a RandomPlayer given a Board Object and the player's piece
    //************************************************************
	public RandomPlayer(Board b, int p){
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
		
	    if (board.getEmpty() == 0 || board.getEmpty() == 1){
	    	ArrayList<Board> options = generateBoards(board);
	    	int length = options.size();
	    	Random rand = new Random();  
	    	int random = rand.nextInt(length);
	    	Board move = options.get(random);
	    	int row = -1;
	    	int column = -1;
	    	for(int i = 0; i < size; i++){
	    		for(int j = 0; j < size; j++){
	    			boolean check1 = board.isEmpty(i,j);
	    			boolean check2 = move.isEmpty(i,j);
	    			if(check1 == false && check2 == true){
	    				if((i+j)%2+1 == piece){
	    					row = i;
	    					column = j;
	    				}
	    			}
	    		}
	    	}
	    	board.remove(row, column, piece);
	    	canMove = true;

	    }
	    else{
	    	ArrayList<Board> options = generateBoards(board);
	    	int length = options.size();
	    	if(length !=0){
	    	  Random rand = new Random();  
	    	  int random = rand.nextInt(length);
	    	  Board move = options.get(random);
	    	  int startR = -1;
	    	  int startC = -1;
	    	  int finalR = -1;
	    	  int finalC = -1;
	    	  for(int i = 0; i < size; i++){
	    		  for(int j = 0; j < size; j++){
	    			  boolean check1 = board.isEmpty(i,j);
	    			  boolean check2 = move.isEmpty(i,j);
	    			  if(check1 == true && check2 == false){
	    			  	if((i+j)%2+1 == piece){
	    					finalR = i;
	    				    finalC = j;
	    				}
	    			  }
	    			  if(check1 == false && check2 == true){
	    				if((i+j)%2+1 == piece){
	    					startR = i;
	    					startC = j;
	    				}
	    			}
	    		}
	    	}
	    	board.move(startR, startC, finalR, finalC, piece);
	    	canMove = true;
	      }
	      else{
	    	canMove = false;
	      }
        }
	    return canMove;
	}

	//************************************************************
    //Generates the next possible moves from a Board Object
    //Parameters: Board b: Board Oject
    //Returns: An ArrayList of Board Objects
    //************************************************************
	private ArrayList<Board> generateBoards(Board b){
		Board start = new Board(b);
		ArrayList<Board> list = new ArrayList<Board>();
		int [] rMoves = {-2, 2, 0, 0};
		int [] cMoves = {0, 0, -2, 2};
		int size = start.getSize();
		
		if(board.getEmpty() == 0 || board.getEmpty() == 1){
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					if((i+j)%2+1 == piece){
						if(start.validRemove(i,j,piece)){
							Board hold = new Board(start);
							hold.remove(i,j,piece);
							list.add(hold);
						}
					}
				}
			}
		}
		else{
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
						if((i+j)%2+1 == piece){
							for(int k = 0; k < 4; k++){
								int x = i + rMoves[k];
								int y = j + cMoves[k];
								if(start.validMove(i,j,x,y,piece)){
									Board hold = new Board(start);
									hold.move(i,j,x,y,piece);
									list.add(hold);
								}//end if validMove
							}//end for k
						}//end if %2 == 1
				}//end for j
			}//end for i
		}//end else
		return list;
	}//end method
}//end class





	












