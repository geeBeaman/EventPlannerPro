import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * This is an Event object that contains title of the event, starting and ending time of the event.
 * It is Comparable and Serializable.
 * @author naghmeh
 *
 */

public class Event implements Comparable<Event>, Serializable
{
	private String title;
	private GregorianCalendar startingTime;
	private GregorianCalendar endingTime;
	
	/**
	 * Constructs an event object.
	 * @param tl is the title of the event
	 * @param d is the starting time of the event
	 * @param tm is the ending time of the event
	 */
	public Event(String tl, GregorianCalendar d, GregorianCalendar tm)
	{
		setTitle(tl);
		setStartingTime(d);
		setEndingTime(tm);
	}
	
	/**
	 * Retrieves the title of the event.
	 * @return the title of the event
	 */
	public String getTitle()
	{
		return title;
	}
	/**
	 * Retrieves the starting time of the event.
	 * @return the starting time of the event
	 */
	public GregorianCalendar getStartingTime()
	{
		return startingTime;
	}
	
	/**
	 * Retrieves the ending time of the event.
	 * @return the ending time of the event
	 */
	public GregorianCalendar getEndingTime()
	{
		return endingTime;
	}
	
	/**
	 * Sets the title of this event.
	 * @param s is the title
	 */
	public void setTitle(String s)
	{
		title = s;
	}
	
	/**
	 * Sets the Starting time of this event.
	 * @param d is the date of the starting time
	 */
	public void setStartingTime(GregorianCalendar d)
	{
		startingTime = d;
	}
	
	/**
	 * Sets the ending time of this event.
	 * @param t is the date of the ending time
	 */
	public void setEndingTime(GregorianCalendar t)
	{
		endingTime = t;
	}
	
	/**
	 * Overrides the compareTo method and compares the starting time of two events.
	 * @return -1 if the first object is less than the second object, 0 if both are the same, and +1 if the
	 * second object is bigger than the first object
	 */
	public int compareTo(Event o)
	{
		return (this.getStartingTime().compareTo(o.getStartingTime()));
	}
	
}
