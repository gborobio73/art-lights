package hello;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GonTests {
	
	static char[] invalid_board = {'A', 'O', 'X', 'X', 'N', 'X', 'O', 'O', 'E'};
    static char[] invalid_board_2 = {'O', '\0', 'X', 'O', '\0', 'X','O', '\0', 'X'};
    static char[] invalid_board_3 = {'X', '\0', 'O', 'X', 'X', 'O', 'X', 'O', 'O'};
    static char[] invalid_board_4 = {'O', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'X'};
    static char[] too_big_board = {'O', 'X', 'O', 'O', 'X', 'O', 'X', 'X', 'X', 'O', 'X', 'O'};
    static char[] too_small_board = {'O', 'X', 'O', 'O', 'X', 'O'};
    static char[] unfinished_board = {'O', 'X', '\0', 'O', 'O', 'X', 'X', 'O', 'X'};
    static char[] unfinished_board_again = {'O', 'X', '\0', '\0', 'X', '\0', '\0', 'O', 'X'};
    static char[] unfinished_board_2 = {'X', 'O', 'X', 'O', 'X', 'X', 'O', '\0', 'O'};
    static char[] unfinished_board_3 = {'X', 'O', 'X', 'X', 'X', '\0', 'O', '\0', 'O'};
    static char[] tie_board = {'O', 'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X'};
    static char[] crosses_won_early = {'\0', 'O', '\0', '\0', 'O', '\0', 'X', 'X', 'X'};
    static char[] crosses_won = {'X', 'X', 'O', '\0', 'X', '\0', 'O', 'O', 'X'};
    static char[] crosses_won_2 = {'O', '\0', 'X', 'O', '\0', 'X', '\0', '\0', 'X'};
    static char[] noughts_won = {'X', 'O', 'O', 'X', 'X', 'O', '\0', 'X', 'O'};
    static char[] noughts_won_again = {'\0', 'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O'};
    
    @Before
    	public void setup() {
    }

    @Test
 	public void testInvalid() {
    	assertEquals(4, solution(invalid_board));
    }
    @Test
 	public void testInvalid2() {
    	assertEquals(4, solution(invalid_board_2));
    }
    @Test
 	public void testInvalid3() {
    	assertEquals(4, solution(invalid_board_3));
    }
    @Test
 	public void testInvalid4() {
    	assertEquals(4, solution(invalid_board_4));
    }
    @Test
 	public void testTooBig() {
    	assertEquals(4, solution(too_big_board));
    }
    @Test
 	public void testTooSmall() {
    	assertEquals(4, solution(too_small_board));
    }
    @Test
 	public void testUnfinished() {
    	assertEquals(3, solution(unfinished_board));
    }
    @Test
 	public void testUnfinishedAgain() {
    	assertEquals(3, solution(unfinished_board_again));
    }
    @Test
 	public void testUnfinished2() {
    	assertEquals(3, solution(unfinished_board_2));
    }
    @Test
 	public void testUnfinished3() {
    	assertEquals(3, solution(unfinished_board_3));
    }
    @Test
 	public void testTie() {
    	assertEquals(2, solution(tie_board));
    }
    @Test
 	public void testCrossesWonEarly() {
    	assertEquals(1, solution(crosses_won_early));
    }
    @Test
 	public void testCrossesWon() {
    	assertEquals(1, solution(crosses_won));
    }
    @Test
 	public void testCrossesWon2() {
    	assertEquals(1, solution(crosses_won_2));
    }
    @Test
 	public void testNoughtsWon() {
    	assertEquals(0, solution(noughts_won));
    }
    @Test
 	public void testNoughtsWonAgain() {
    	assertEquals(0, solution(noughts_won_again));
    }


    
	  private static int solution(char board[]){
			//return 3 (unfinished as default)
		int result = 3;
		
		//number of O and X
		int nX = 0;
		int nO = 0;
		
		//default false but if noticed a char otherwise than X, O or whitespace then invalid board
		boolean unknownChar = false;
		
		//magic square eg. see http://mathworld.wolfram.com/MagicSquare.html
		// 8,1,6
		// 3,5,7
		// 4,9,2
		// All columns rows and diagonals have the sum of 15
		int magicSquare[] = {8,1,6,3,5,7,4,9,2};
		
		//initialize for both O and X
		int magicSquareX[] = new int[9];
		int magicSquareO[] = new int[9];
		
		//count instances of X and O
		//assign values for their magic squares zero if not X or respectively O there
		for(int i=0;i<board.length;i++){
			if(board[i] == 'X'){
				nX++;
				magicSquareX[i] = magicSquare[i];
				magicSquareO[i] = 0;
			}
			else if(board[i] == 'O'){
				nO++;
				magicSquareO[i] = magicSquare[i];
				magicSquareX[i] = 0;
			}
			//if character is something else than X O or whitespace then we have a unknow char => invalid board
			else if(board[i]!=' ' && board[i]!='\0'){
				unknownChar = true;
			}
		}
		//invalid board start with this since otherwise unnecessary calculations
		//1. board should always be size 9
		//2. Since X always starts there should not be more O than X
		//3. If there is 2 or more X than O then the board is also invalid since X at some point has put twice in a row
		//4. Unknown character (other than O X or whitespace)
		if(board.length!=9 || nO>nX || nX-nO>1 || unknownChar){
			result = 4;
		}
		else if(nX>2){ //don't bother checking for solution if nX<3 because then there cannot be a solution and game is unfinished
	    
		  //calculate if either X or O has a winning row/column/diagonal
		  boolean xWins = calculateMagicSquare(magicSquareX);
		  boolean oWins = calculateMagicSquare(magicSquareO);
		  
		  //something fishy has happened since both can't have a winning combination
		  //i.e. the game hasn't been played by the rules
		  if(xWins && oWins){
			  result = 4;
		  }
		  //X has a winning combination  O not X wins
		  else if(xWins && !oWins){
			  result = 1;
		  }
		  //O has a winning combination  X not O wins
		  else if(oWins && !xWins){
			  result = 0;
		  }
		  //tie if no one has a winning combination and there are 9 pieces on the board if less pieces then unfinished
		  else if((!xWins&&!oWins) && nX+nO==9){
	          result = 2;
	      }
		}
		return result;
	}
	
	//see if any row, column or diagonal has the sum of 15
	private static boolean calculateMagicSquare(int[] magicSquare) {
		boolean solution = false;
		//check rows
		solution = checkRows(magicSquare);
		//check columns
		if(!solution){
			solution = checkColumns(magicSquare);
	
		}
		//check diagonals if no winning combination yet
	
		if(!solution){
			solution = checkDiagonals(magicSquare);
		}
		//false if no winning combination true otherwise
		return solution;
	}
	
	private static boolean checkDiagonals(int[] magicSquare) {
		return ((magicSquare[0]+magicSquare[4]+magicSquare[8]==15) || (magicSquare[2]+magicSquare[4]+magicSquare[6]==15));
	}
	
	private static boolean checkColumns(int[] magicSquare) {
		boolean solution = false;
		int sum = 0;
		for(int col=0;col<3;col++){
			if(!solution){
				for(int row=col; row<=6+col;row+=3){
					sum+=magicSquare[row];
				}
				//if the sum of a column is 15 then there are 3 in a column
				if(sum==15){
					solution = true;
				}
				sum = 0;
			}
		}
		return solution;
	}
	
	private static boolean checkRows(int[] magicSquare) {
		boolean solution = false;
		int sum = 0; 
		for(int row=0;row<3;row++){
			if(!solution){
				for(int col=0+row*3; col<3+row*3;col++){
					sum+=magicSquare[col];
				}
				//if the sum of a row is 15 then there are 3 in a row
				if(sum==15){
					solution = true;
				}
				sum = 0;
			}
		}
		return solution;
	}
}
