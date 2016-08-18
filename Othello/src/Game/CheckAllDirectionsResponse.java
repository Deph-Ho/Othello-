package Game;

public class CheckAllDirectionsResponse 
{
	public boolean isUpValid;
	
	public boolean isDownValid;
	
	public boolean isLeftValid;
	
	public boolean isRightValid;
	
	public boolean isNorthWestValid;
	
	public boolean isNorthEastValid;
	
	public boolean isSouthWestValid;
	
	public boolean isSouthEastValid;
	
	public boolean CheckIfAnyLocationValid()
	{
		if(isUpValid || isDownValid || isLeftValid || isRightValid 
			|| isNorthWestValid || isNorthEastValid || isSouthWestValid || isSouthEastValid)
		{
			return true;
		}
		return false;
	}
	
}
