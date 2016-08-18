package Game;

import java.util.NoSuchElementException;

import Enum.LocationType;


/**
 *  Title: class Location
 *  Description: Directions to move for Board.java 
 *  and to access and change location.
 *  @version 1.0
 *  @Author Dephanie Ho
 *  @since 07-12-16
 */
public class Location 
{
	//instance variables
	private int _row;
	private int _col;
	private LocationType _type;
	
	//constructors
	public Location(int row, int col) 
	{
		_row = row;
		_col = col;
	}

	public Location(int row, int col, LocationType type) 
	{
		_row = row;
	    _col = col;
	    _type = type;
	}
	  /** Getter to get the Location's row index 
	   *  @return row index
	   */
	  public int getRow() 
	  {
	    return _row;
	  }
	  /** Getter to get the Location's column index 
	   *  @return column index
	   *////        
	  public int getColumn() 
	  {
	    return _col;
	  }
	  
	  public Location nextLocation()
	  {
		  switch(_type)
		  {
		  	case Up:
		  		return up();
		  	case Down:
		  		return down();
		  	case Left:
		  		return left();
		  	case Right:
		  		return right();
		  	case northWest:
		  		return northWest();
		  	case northEast:
		  		return northEast();
		  	case southWest:
		  		return southWest();
		  	case southEast:
		  		return southEast();
		  	default:
		  		throw new NoSuchElementException();
		  }
	  }
	  
	  /**
	   * @return location when moved up on the board
	   */
	  private Location up()
	  {
		  return new Location(_row-1, _col, _type);
	  }
	  /**
	   * @return location when moved down on the board
	   */
	  private Location down()
	  {
		  return new Location(_row+1, _col, _type);
	  }
	  /**
	   * @return location when moved left on the board
	   */
	  private Location left()
	  {
		  return new Location(_row, _col-1, _type);
	  }
	  /**
	   * @return location when moved right on the board
	   */
	  private Location right()
	  {
		  return new Location(_row, _col+1, _type);
	  }
	  //Diagonals
	  private Location northWest()
	  {
		  return new Location(_row-1, _col-1, _type);
	  }
	  
	  private Location northEast()
	  {
		  return new Location(_row-1, _col+1, _type);
	  }
	  
	  private Location southWest()
	  {
		  return new Location(_row+1, _col-1, _type);
	  }
	  
	  private Location southEast()
	  {
		  return new Location(_row+1, _col+1, _type);
	  }
	  
	  
	}
