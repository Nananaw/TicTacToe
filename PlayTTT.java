import java.util.Scanner;

public class PlayTTT
{
  public static void main(String[] args)
  {
    char player = validateFirst();
    TicTacToe game = new TicTacToe(player);
    game.printBoard();
    while (game.checkWin()==0 && !game.isFull())
    {
      if (player == 'O')
      {
        game.computerAlter();
        game.printBoard();
        if (game.checkWin()!=0 || game.isFull())
          break;
        game.alterBoard();
        game.printBoard();
      }
      else
      {
        game.alterBoard(); 
        game.printBoard();
        if (game.checkWin()!=0 || game.isFull())
          break;
        game.computerAlter();
        game.printBoard();
      }
    }
    game.printWinner(game.checkWin());
  }
  
  //lets player choose going first or second
  public static char validateFirst()
  {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter first or second: ");
    String a=sc.nextLine();
    a=a.trim();
    if (a.toLowerCase().equals("first"))
    {
      return 'X';
    }
    else if (a.toLowerCase().equals("second"))
    {
      return 'O';
    }
    else
    {
      System.out.println("Not valid, try again.");
      return validateFirst();
    }
  }
}


