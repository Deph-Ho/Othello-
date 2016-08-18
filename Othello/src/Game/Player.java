package Game;

import java.util.Scanner;

import Enum.ColorDisc;

public class Player 
{
	private ColorDisc colorDisc;
	
	//constructor
	public Player(ColorDisc colorDisc)
	{
		this.colorDisc = colorDisc;
	}
	
	public ColorDisc GetColorDisc()
	{
		return this.colorDisc;
	}
	
	
	//Methods
	public Location GetInput()
	{
		boolean validUserInput = false;
		int[] coordinates;
		
		System.out.println(this.colorDisc + " turn");
		System.out.println("Please enter the coordinates (row, column)"
							+ " of where you want to put the disc.");
		System.out.println("Range: (0,0) - (7,7)");
		
		Scanner userInput = new Scanner(System.in);
		
		//check for valid input
		do
		{
			coordinates = new int[2];
			//store row and column in array
			for(int i = 0; i < coordinates.length; i++)
			{
				coordinates[i] = userInput.nextInt();
				validUserInput = coordinates[i] >= 0 && coordinates[i] < 8;
				if(!validUserInput)
				{
					System.out.println("Option is out of bounds, please re-select coordinates");
				}
			}
			
		}
		while(!validUserInput);
		
		//userInput.close();
		Location loc = new Location(coordinates[0], coordinates[1]);
		return loc;
	}
}
