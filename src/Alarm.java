public class Alarm
{
	/**
	 * @author TeamSharks
	 */
	Time time;
	int[] date;
	
	public Alarm(int[] t)
	{
		time = new Time(t[3]);
		date = new int[]{t[0],t[1],t[2]};
	}
	
	public void setTime(/*String s*/)
	{
	/*	int t = 0;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) >= 48 && s.charAt(i) <= 57)
			{
				t *= 10;
				t += Character.getNumericValue(s.charAt(i))-48;
			}
		}
		time = new Time(t);	*/
	}
	
	// Returns time for editing in EventView
	// Might not have to be a string
	public String getTime()
	{
		return time.toString();
	}
}