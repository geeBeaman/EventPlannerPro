public class EventData extends Alarm
{
	Alarm alarm;
	String name, location, description;
	
	public EventData(int[] t, String n, String l, String d)
	{
		super(t);
		name = n;
		location = l;
		description = d;
	}
}