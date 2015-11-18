public class Time
{
	String meridiem;
	int hour, min;
	
	private int timecode;	//Used for storing in the file of stored events
	
	public Time(int t)
	{
		timecode = t;
		
		hour = t/100;
		min = t%100;
		
		meridiem = (hour < 12) ? "AM" : "PM";
		if(hour > 12)
			hour -= 12;
			
	}
	
	public int getTimecode()
	{
		return timecode;
	}
	
	@Override
	public String toString()
	{
		return hour+":"+((min < 9) ? "0" : "")+min+" "+meridiem;
	}
}