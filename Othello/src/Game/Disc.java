package Game;

import Enum.ColorDisc;

public class Disc 
{
	private final char BLACK_DISC = 'B';
	private final char WHITE_DISC = 'W';
	
	private ColorDisc _color;
	
	//constructor
	public Disc(ColorDisc color)
	{
		_color = color;
	}
	
	//getter
	public ColorDisc GetColorDisc()
	{
		return _color;
	}
	
	public char GetColorDiscChar()
	{
		if(_color == ColorDisc.Black)
		{
			return BLACK_DISC;
		}
		else
		{
			return WHITE_DISC;
		}
	}
	public void FlipDisc()
	{
		if(_color == ColorDisc.Black)
		{
			_color = ColorDisc.White;
		}
		else
		{
			_color = ColorDisc.Black;
		}
	}
	public void PrintDisc()
	{
		if(_color == ColorDisc.Black)
		{
			System.out.println(BLACK_DISC);
		}
		else
		{
			System.out.println(WHITE_DISC);
		}
	}
}
