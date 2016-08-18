package Game;

import Enum.ColorDisc;
import Enum.LocationType;

public class Game 
{
	private final int STARTER_THREE = 3;
	private final int STARTER_FOUR = 4;
	private final int BOARD_SIZE = 8;
	private Player _playerOne;
	private Player _playerTwo;
	private Player _currentPlayer;
	private Board _board;
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.SetUp();
		game.StartGame();
	}
	
	public void SetUp()
	{
		_playerOne = new Player(ColorDisc.Black);
		_playerTwo = new Player(ColorDisc.White);
		_currentPlayer = _playerOne;
		_board = new Board(_playerOne, _playerTwo);
		SetBoard(_board);
	}
	public void StartGame()
	{
		while(!IsEndGame())
		{
			_board.PrintBoard();
			Location loc = _currentPlayer.GetInput();
			boolean IsEmpty = false;
			do
			{
				IsEmpty = IsEmpty(loc.getRow(), loc.getColumn());
				
				if(!IsEmpty)
				{
					System.out.println("Option is already on a disc, please re-select coordinates");
					loc = _currentPlayer.GetInput();
				}
			}
			while(!IsEmpty);
			
			CheckAllDirectionsResponse response = CheckAllDirections(loc.getRow(), loc.getColumn(), _currentPlayer);
			ProcessMove(loc.getRow(), loc.getColumn(), response);
			PrintPlayerScore();
		}
	}
	
	public boolean IsEndGame()
	{
		CheckAllDirectionsResponse responseOne = null;
		CheckAllDirectionsResponse responseTwo = null;
		
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				responseOne = CheckAllDirections(i, j, _playerOne);
				responseTwo = CheckAllDirections(i, j, _playerTwo);
			}
		}
		
		if(_board.GetScore() == 64
				|| !responseOne.CheckIfAnyLocationValid() && !responseTwo.CheckIfAnyLocationValid())
		{
			return true;
		}
		else
		{
			return false;
		}

	}
	
	private void SetBoard(Board board)
	{
		board.SetSquare(STARTER_THREE, STARTER_THREE, new Disc(ColorDisc.Black));
		board.SetSquare(STARTER_FOUR, STARTER_FOUR, new Disc(ColorDisc.Black));
		board.SetSquare(STARTER_FOUR, STARTER_THREE, new Disc(ColorDisc.White));
		board.SetSquare(STARTER_THREE, STARTER_FOUR, new Disc(ColorDisc.White));
	}
	
	public void UpdateCurrentPlayer()
	{
		if(_currentPlayer == _playerOne)
		{
			_currentPlayer = _playerTwo;
		}
		else
		{
			_currentPlayer = _playerOne;
		}
	}
	
	public void PrintPlayerScore()
	{
		_board.GetScore();
	}
	
	//checking if square is empty or not
	public boolean IsEmpty(int row, int column)
	{
		Disc disc = _board.GetSquare(row, column);
		//Check if player input is already on a disc
		if(disc != null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	//Helper method to check all directions
		public CheckAllDirectionsResponse CheckAllDirections(int row, int column, Player currentPlayer)
		{
			CheckAllDirectionsResponse response = new CheckAllDirectionsResponse();
			//conditions to check if the direction is not out of grid
			if(row > 0)
			{
				if(this.CheckByLocation(row, column, LocationType.Up, currentPlayer))
				{
					response.isUpValid = true;
				}
			}
			if(row < 7)
			{
				if(this.CheckByLocation(row, column, LocationType.Down, currentPlayer))
				{
					response.isDownValid = true;
				}
			}
			if(column > 0)
			{
				if(this.CheckByLocation(row, column, LocationType.Left, currentPlayer))
				{
					response.isLeftValid = true;
				}
			}
			if(column < 7)
			{
				if(this.CheckByLocation(row, column, LocationType.Right, currentPlayer))
				{
					response.isRightValid = true;
				}
			}
			if(row > 0 && column > 0)
			{
				if(this.CheckByLocation(row, column, LocationType.northWest, currentPlayer))
				{
					response.isNorthWestValid = true;
				}
			}
			if(row > 0 && column < 7)
			{
				if(this.CheckByLocation(row, column, LocationType.northEast, currentPlayer))
				{
					response.isNorthEastValid = true;
				}
			}
			if(row < 7 && column > 0)
			{
				if(this.CheckByLocation(row, column, LocationType.southWest, currentPlayer))
				{
					response.isSouthWestValid = true;
				}
			}
			if(row < 7 && column < 7)
			{
				if(this.CheckByLocation(row, column, LocationType.southEast, currentPlayer))
				{
					response.isSouthEastValid = true;
				}
			}
			return response;
		}
		
		public boolean CheckByLocation(int row, int column, LocationType type, Player currentPlayer)
		{
			boolean result = false;
			Location loc = new Location(row, column, type);
			Location dirLoc = loc.nextLocation();
			
			if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()) != null)
			{
				//PlayerOne = black disc
				if(currentPlayer.GetColorDisc() == ColorDisc.Black)
				{
					//if black disc, no need to check
					if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.Black)
					{
						result = false;
					}
					else
					{
						//if white disc, continue checking direction
						while(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()) != null && _board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.White)
						{
							dirLoc = dirLoc.nextLocation();
							
							//if direction is all white, not valid move to flip
							if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()) == null)
							{
								result = false;
							}
							//if end disc is black, then valid move
							else if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.Black)
							{
								result = true;
							}
						}
					}
					return result;
				}
				//PlayerTwo = white disc
				else
				{
					//if white disc, then not valid move
					if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.White)
					{
						result = false;
					}
					//if black disc, continue checking direction
					while(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()) != null &&_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.Black)
					{
						dirLoc = dirLoc.nextLocation();
						
						//if direction is all black, not valid move to flip
						if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()) == null)
						{
							result = false;
						}
						//if end disc is white, then valid move
						else if(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.White)
						{
							result = true;
						}
					}
					return result;
				}
			}
			return result;
		}
		
		//flip the discs
		public void FlipDiscs(int row, int column, LocationType type)
		{
			Location loc = new Location(row, column, type);
			Location dirLoc = loc.nextLocation();
			//PlayerOne
			if(_currentPlayer.GetColorDisc() == ColorDisc.Black)
			{
				//flip the white into black
				while(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.White)
				{
					_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).FlipDisc();
					_board.PrintBoard();
					dirLoc = dirLoc.nextLocation();
				}
			}
			//PlayerTwo
			else
			{
				while(_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).GetColorDisc() == ColorDisc.Black)
				{
					_board.GetSquare(dirLoc.getRow(),dirLoc.getColumn()).FlipDisc();
					dirLoc = dirLoc.nextLocation();
				}
			
			}
		}
		//if all valid moves, place down the disc
		public void ProcessMove(int row, int column, CheckAllDirectionsResponse response)
		{
			if(response.CheckIfAnyLocationValid())
			{
				Disc disc = new Disc(_currentPlayer.GetColorDisc());
				_board.SetSquare(row, column, disc);
				
				//flip adjacent discs
				if(response.isUpValid)
				{
					FlipDiscs(row, column, LocationType.Up);
				}
				if(response.isDownValid)
				{
					FlipDiscs(row, column, LocationType.Down);
				}
				if(response.isLeftValid)
				{
					FlipDiscs(row, column, LocationType.Left);
				}
				if(response.isRightValid)
				{
					FlipDiscs(row, column, LocationType.Right);
				}
				if(response.isNorthEastValid)
				{
					FlipDiscs(row, column, LocationType.northEast);
				}
				if(response.isNorthWestValid)
				{
					FlipDiscs(row, column, LocationType.northWest);
				}
				if(response.isSouthEastValid)
				{
					FlipDiscs(row, column, LocationType.southEast);
				}
				if(response.isSouthWestValid)
				{
					FlipDiscs(row, column, LocationType.southWest);
				}
				
				UpdateCurrentPlayer();
			}
			else
			{
				System.out.println("Not a valid move");
			}
		}	
		
}
