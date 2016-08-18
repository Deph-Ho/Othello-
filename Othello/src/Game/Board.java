package Game;

import Enum.ColorDisc;

public class Board 
{
	//instance variables
	private Disc[][] _board;
	final int BOARD_SIZE = 8;
	private final char EMPTY_SPACE = '*';
	
	//constructor
	public Board()
	{
		Initialize();
	}
	
	//start game
	private void Initialize()
	{
		_board = new Disc[BOARD_SIZE][BOARD_SIZE];
		
		//label all as empty
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				_board[i][j] = null;
			}
		}
	}
	
	public void SetSquare(int row, int column, Disc disc)
	{
		if(disc != null)
		{
			_board[row][column] = disc;
		}
	}
	
	public Disc GetSquare(int row, int column)
	{
		return _board[row][column];
	}
	
	public void PrintBoard()
	{
		System.out.println();
		
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				Disc disc = _board[i][j];
				char temp = EMPTY_SPACE;
				if(disc != null)
				{
					temp = disc.GetColorDiscChar();
				}
				System.out.print(" " + temp);
				
				if(j+1 >= BOARD_SIZE)
				{
					System.out.println();
				}
			}
		}
	}
	
	public int GetScore()
	{
		int bDisc = 0;
		int wDisc = 0;
		
		for(int i  = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				Disc disc = _board[i][j];
				if(_board[i][j] != null)
				{
					if(disc.GetColorDisc() == ColorDisc.Black)
					{
						bDisc++;
					}
					if(disc.GetColorDisc() == ColorDisc.White)
					{
						wDisc++;
					}
				}

			}
		}
		
		System.out.println("Player One Score: " + bDisc);
		System.out.println("Player Two Score: " + wDisc);
		return(bDisc + wDisc);
	}
}
