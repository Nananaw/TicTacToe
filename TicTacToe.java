import java.util.Scanner;

public class TicTacToe
{
  private char[][] board;
  private char player;
  private char computer;
  
  //constructor for game
  public TicTacToe(char player)
  {
    board = new char[3][3];
    this.player = player;
    if (player == 'O')
      computer = 'X';
    else
      computer = 'O';
    makeBoard();
  }
  
  //makes board and sets player and computer
  public void makeBoard()
  {
    for (int i=0; i<3; i++)
      for (int j=0; j<3; j++)
      board[i][j]=' ';
  }
  
  //prints board
  public void printBoard()
  {
    System.out.println("-------------");
    for (int i=0; i<3; i++)
    {
      System.out.print("| ");
      for (int j=0; j<3; j++)
      {
        System.out.print(board[i][j] + " | ");
      }
      System.out.println();
      System.out.println("-------------");
    }
  }
  
  //allows player to alter board
  public void alterBoard()
  {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter row: ");
    int row = sc.nextInt();
    System.out.print("Enter column: ");
    int column = sc.nextInt();
    while (board[row-1][column-1] != ' ')
    {
      System.out.println("Spot is taken or invalid.");
      System.out.print("Enter row: ");
      row = sc.nextInt();
      System.out.print("Enter column: ");
      column = sc.nextInt();
    }
    board[row-1][column-1]=player;
  }
  
  //checks to see if board is full
  public boolean isFull()
  {
    for (int i=0; i<3; i++)
    {
      for (int j=0; j<3; j++)
      {
        if (board[i][j]==' ')
        {
          return false;
        }
      }
    }
    return true;
  }
  
  //checks to see if game is won
  public int checkWin()
  {
    for (int i=0; i<3; i++)
    {
      if (board[i][0]==board[i][1] && board [i][1]==board[i][2])
      {
        if (board[i][0]==player)
        {
          return 10;
        }
        else if (board[i][0]==computer)
        {
          return -10;
        }
      }
    }
    for (int i=0; i<3; i++)
    {
      if (board[0][i]==board[1][i] && board [1][i]==board[2][i])
      {
        if (board[0][i]==player)
        {
          return 10;
        }
        else if (board[0][i]==computer)
        {
          return -10;    
        }
      }
      if ((board[1][1]==board[0][0] && board[1][1]==board[2][2]) || (board[1][1]==board[0][2] && board[1][1]==board[2][0]))
      {
        if (board[1][1]==player)
        {
          return 10;
        }
        else if (board[1][1]==computer)
        {
          return -10;
        }
      }
    }
    return 0;
  }
  
  public boolean getWinner(int checkwin)
  {
    if (checkwin>0)
    {
      return true;
    }
    else if (checkwin<0)
    {
      return true;
    }
    return false;
  }
  
    
  //sees who won game
  public boolean printWinner(int checkwin)
  {
    if (checkwin>0)
    {
      System.out.print("You won!");
      return true;
    }
    else if (checkwin<0)
    {
      System.out.print("Computer won!");
      return true;
    }
    System.out.print("Tie.");
    return false;
  }
  
  public void computerAlter()
  {
    if (board[1][1] == ' ')
    {
      board[1][1] = computer;
      return;
    }
    
    int bestMove = Integer.MIN_VALUE;
    int moveRow = 1;
    int moveCol = 1;
    for(int i=0; i<3; i++)
    {
      for(int j=0; j<3; j++)
      {
        if (board[i][j] == ' ')
        {
          board[i][j] = computer;
          if (minimax(0, false, Integer.MIN_VALUE, Integer.MAX_VALUE) > bestMove)
          {
            bestMove = minimax(0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
            moveRow = i;
            moveCol = j;
          }
          board[i][j] = ' ';
        }
      }
    }
    board[moveRow][moveCol] = computer;
  }
  
  public int minimax(int depth, boolean maximizingPlayer, int alpha, int beta)
  {
    if (checkWin()>0){return -10;}
    
    if (checkWin()<0){return 10;}
    
    if (isFull() && checkWin()==0){return 0;}
    
    
    if(maximizingPlayer)
    {
      int bestValue = Integer.MIN_VALUE;
      for (int i=0; i<3; i++)
      {
        for (int j=0; j<3; j++)
        {
          if (board[i][j] == ' ')
          {
            board[i][j] = computer;
            bestValue = Math.max(minimax(depth+1, false, alpha, beta)-depth, bestValue);
            alpha = Math.max(alpha, bestValue);
            board[i][j] = ' ';
            if (beta <=alpha)
              break;
          }
        }
      }
      return bestValue;
    }
    
    else
    {
      int bestValue = Integer.MAX_VALUE;
      for(int i=0; i<3; i++)
      {
        for(int j=0; j<3; j++)
        {
          if(board[i][j] == ' ')
          {
            board[i][j] = player;
            bestValue = Math.min(minimax(depth+1, true, alpha, beta)+depth, bestValue);
            beta = Math.min(beta, bestValue);
            board[i][j] = ' ';
            if (beta <= alpha)
              break;
          }
        }
      }
      return bestValue;
    }
  }
}
