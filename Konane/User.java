import java.util.*;


public class User{

	private Board board; // boolean valid remove, boolean valid move, move, remove, size
	private int piece; //value 1 = black, 2 = white
	private int other;

	Scanner scanner = new Scanner(System.in);


	public User(Board b, int p){
		piece = p;
		board = b;

		if(p == 1){
			other = 2;
		}
		if(p == 2)
			other = 1;
		}
	}

	//returns true if still can play, returns false if not? 
	public boolean play(){
		boolean canMove;
		
	    if (board.getEmpty() == 0 || board.getEmpty() == 1){
	    	System.out.print("Please enter piece to remove: "); 
	        int removeR = scanner.nextInt();
	        int removeC = scanner.nextInt();
	        System.out.println("RemoveR: " + removeR + " RemoveC: " + removeC);
	    		board.remove(removeR, removeC, piece);
	    		canMove = true;

	    }
	    else{

	      System.out.print("Please enter piece to move: "); 
	      int moveR = scanner.nextInt();
	      int moveC = scanner.nextInt();
	      System.out.println("MoveR: " + moveR + " MoveC: " + moveC);
	      System.out.print("Please enter location to move to: "); 
	      int locationR = scanner.nextInt();
	      int locationC = scanner.nextInt();
	      System.out.println("LocationR: " + locationR + " LocationC: " + locationC);
	      if(board.validMove(moveR, moveC, locationR, locationC, piece)){
	    	board.move(moveR, moveC, locationR, locationC, piece);
	    		canMove = true;
	      }
	      else{
	    	System.out.println("###################Invalid move############################");
	    	canMove = false;
	      }
        }
	    return canMove;
	}


}