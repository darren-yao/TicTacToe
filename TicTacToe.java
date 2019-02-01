/**
 * This class is a two-player tic tac toe game.
 * @author Darren Yao
 * @version v1.0
 * 
 * PATCH NOTES:
 * v1.0 implemented two-player tic tac toe game. 
 */
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicTacToe
{
    public static char[] board = new char[9]; 
    public static boolean turn = true; // TRUE = X, FALSE = O;
    public static void main(String[] args)
    {
        startGame();
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt())
        {
            int userInput = in.nextInt();
            if(checkLegalMove(userInput)) // checkLegalMove passed
            {
                move(userInput, turn);
                turn = !turn;
                checkGameEnd(board); // checks if a player won
            }
            else // checkLegalMove failed
            {
                System.out.println("Illegal move, please try again");
            }
        }
    }

    public static void startGame()
    {
        for(int i = 0; i < 9; i++) // initializing the board
        {
            board[i] = ' ';
        }
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

    public static void checkGameEnd(char[] board)
    {
        if(checkForWin(board) == 'X')
        {
            System.out.println("Player X has won");
            try
            {
                TimeUnit.SECONDS.sleep(5); 
            } 
            catch(Exception e){}
            System.exit(0);
        }                
        else if (checkForWin(board) == 'O')
        {
            System.out.println("Player O has won");
            try
            {
                TimeUnit.SECONDS.sleep(5); 
            } 
            catch(Exception e){}
            System.exit(0);
        }
        else // no player has won, continue
        {
            System.out.println(board[0] + "|" + board[1] + "|" + board[2] + "\n"
            + "_____" + "\n"
            + board[3] + "|" + board[4] + "|" + board[5] + "\n"
            + "_____" + "\n"
            + board[6] + "|" + board[7] + "|" + board[8] + "\n");

            if(turn) // X's turn
            System.out.println("X's turn. Please enter a square");
            else // O's turn
            System.out.println("O's turn. Please enter a square");
        }
    }

    public static char checkForWin(char[] board) // X = X Win, O = O Win, space = no win.
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
        else
            return ' ';
    }
}

