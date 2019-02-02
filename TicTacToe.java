/**
 * This class is a two-player tic tac toe game.
 * @author Darren Yao
 * @version v1.0
 * 
 * PATCH NOTES:
 * Alpha 1.0: Implemented 2 player tic tac toe.
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToe
{
    public static char[] board = new char[9]; 
    public static int movesMade;
    public static boolean turn = true; // TRUE = X, FALSE = O;
    public static void main(String[] args)
    {
        startGame();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt())
        {
            int userInput = in.nextInt();
            if(checkLegalMove(userInput)) // Legal move
            {
                move(userInput, turn);
                turn = !turn;
                movesMade++;
                checkGameEnd(board); // checks if a player won
            }
            else // Illegal move
            {
                System.out.println("Illegal move, please try again");
            }
        }
    }
    /**
     * Starts and initializes the game, and gives instructions to the players.
     */
    public static void startGame()
    {
        for(int i = 0; i < 9; i++) // setting up the game board
        {
            board[i] = ' ';
        }
        movesMade = 0;
        System.out.println("At any time, you can type q to quit the game.");
        System.out.println("Position numbers for each square: ");
        System.out.println(
          1 + "|" + 2 + "|" + 3 + "\n"
        + "_____" + "\n"
        + 4 + "|" + 5 + "|" + 6 + "\n"
        + "_____" + "\n"
        + 7 + "|" + 8 + "|" + 9 + "\n");
        System.out.println("X's turn. Please enter a square");
    }

    /**
     * Checks if a move is legal.
     * @param input: The square number inputted by the player
     * @return boolean: true = legal; false = illegal.
     */
    public static boolean checkLegalMove(int input)
    {
        if(input < 1 || input > 9) // ERROR: Illegal square number
        {
            return false;
        }
        if(board[input-1] != ' ') // ERROR: Square is already occupied
        {
            return false;
        }
        return true;
    }

    /**
     * Makes a move (places an X or O on a square)
     * @param square: The square numer inputted by the player
     * @param player: The player whose turn it currently is
     */
    public static void move(int square, boolean player)
    {
        if(player)
        {
            board[square-1] = 'X';
        }
        else // if(!player)
        {
            board[square-1] = 'O';
        }
    }

    /**
     * Ends the game if one player has won or the game has ended in a tie.
     * @param board: the game board to be checked 
     */
    public static void checkGameEnd(char[] board)
    {
        if(checkForWin(board) == 'X')
        {
            printBoard(board);
            System.out.println("Player X has won");
            try
            {
                TimeUnit.SECONDS.sleep(5); 
            } 
            catch(Exception e){}
            System.exit(0);
        }                
        else if(checkForWin(board) == 'O')
        {
            printBoard(board);
            System.out.println("Player O has won");
            try
            {
                TimeUnit.SECONDS.sleep(5); 
            } 
            catch(Exception e){}
            System.exit(0);
        }
        else if(movesMade == 9 && checkForWin(board) == ' ')
        {   
            printBoard(board);
            System.out.println("Game has ended in a tie");
            try
            {
                TimeUnit.SECONDS.sleep(5); 
            } 
            catch(Exception e){}
            System.exit(0);
        }
        else // no player has won, continue
        {
            printBoard(board);
        }
    }

    /**
     * Prints the board. 
     * @param board: the board to be printed.
     */
    public static void printBoard(char[] board)
    {
        String output = (board[0] + "|" + board[1] + "|" + board[2] + "\n"
            + "_____" + "\n"
            + board[3] + "|" + board[4] + "|" + board[5] + "\n"
            + "_____" + "\n"
            + board[6] + "|" + board[7] + "|" + board[8] + "\n");

        if(movesMade < 9)
        {
            if(turn) // X's turn
            output = output.concat("X's turn. Please enter a square");
            else // O's turn
            output = output.concat("O's turn. Please enter a square");
        }
        System.out.println(output);
    }

    /**
     * Checks if one player has won.
     * @param board: the game board to be checked
     * @return char: X, O, or space depending on whether X won, O won, or no player won.
     */
    public static char checkForWin(char[] board) 
    {
        if((board[0] == board[1] && board[1] == board[2]) && (board[0] != ' ')) // win on top row
            return board[0];
        else if((board[3] == board[4] && board[4] == board[5]) && (board[3] != ' ')) // win on middle row
            return board[3];
        else if((board[6] == board[7] && board[7] == board[8]) && (board[6] != ' ')) // win on bottom row
            return board[6];
        else if((board[0] == board[3] && board[3] == board[6]) && (board[0] != ' ')) // win on left column
            return board[0];
        else if((board[1] == board[4] && board[4] == board[7]) && (board[1] != ' ')) // win on middle column
            return board[1];
        else if((board[2] == board[5] && board[5] == board[8]) && (board[2] != ' ')) // win on right column
            return board[2];
        else if((board[0] == board[4] && board[4] == board[8]) && (board[0] != ' ')) // win on top-left / bottom-right diagonal
            return board[0];
        else if((board[2] == board[4] && board[4] == board[6]) && (board[2] != ' ')) // win on top-right / bottom-left diagonal
            return board[2];
        else
            return ' ';
    }
}

