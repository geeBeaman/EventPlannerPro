import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Model has all the data relating to the Calendar.
 * 
 * @author naghmeh
 *
 */
public class Model
{
	private GregorianCalendar currentDate;
	private int currentDay;
	private int currentMonth;
	private int currentYear;
	private ArrayList<ChangeListener> listeners;
	private HashMap<GregorianCalendar, ArrayList<Event>> events;
	private ArrayList<Event> currentDayEvents;

	private MONTHS[] arrayOfMonths = MONTHS.values();
	private DAYS[] arrayOfDays = DAYS.values();

	ArrayList<EventData> eventData;

	/**
	 * This is the constructor and the constructor initializes the data and if
	 * there exists a file containing events it will call load.
	 */
	public Model()
	{
		currentDate = new GregorianCalendar(); // current date
		listeners = new ArrayList<ChangeListener>();
		events = new HashMap<GregorianCalendar, ArrayList<Event>>();

		eventData = new ArrayList<EventData>();
		new EventReader().read(eventData);

		File file = new File("event.ser");
		if (file.exists())
			load();
	}

	/**
	 * Loads the events into the data structure.
	 */
	private void load()
	{
		System.out.println("Reading Objects...");
		Event[] listOfEvents = null;
		FileInputStream fileIn;
		try
		{
			fileIn = new FileInputStream("event.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			int num = in.readInt();
			listOfEvents = new Event[num];
			for (int i = 0; i < num; i++)
			{
				Event e = (Event) in.readObject();
				listOfEvents[i] = e;
			}
			in.close();
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		events.clear();
		for (Event e : listOfEvents)
		{
			this.createEvent(e);
		}
	}

	/**
	 * Gets the Current Date that user is currently viewing.
	 * 
	 * @return the current GregorianCalendar Date
	 */
	public GregorianCalendar getCurrentDate()
	{
		return (GregorianCalendar) currentDate.clone();
	}

	/**
	 * Gets the current Day number.
	 * 
	 * @return the number representing the current day
	 */
	public int getCurrentDay()
	{
		currentDay = currentDate.get(Calendar.DATE);
		return currentDay;
	}

	/**
	 * Gets the current Month.
	 * 
	 * @return the enum value of the current Month
	 */
	public MONTHS getCurrentMonth()
	{
		currentMonth = currentDate.get(Calendar.MONTH);
		return arrayOfMonths[currentMonth];
	}

	/**
	 * Gets the day of the week. ie. Sunday, Monday, ...etc
	 * 
	 * @return the String value of the current day of the week.
	 */
	public String getCurrentDayoftheWeek()
	{
		int day = currentDate.get(Calendar.DAY_OF_WEEK);
		DAYS dayString = arrayOfDays[day - 1];
		return dayString.name();
	}

	/**
	 * Gets the current year.
	 * 
	 * @return the int value of the current year
	 */
	public int getCurrentYear()
	{
		currentYear = currentDate.get(Calendar.YEAR);
		return currentYear;
	}


	/**
	 * Changes the current Day to the previous day and notifies all the change
	 * listeners.
	 */
	public void prevDay()
	{
		currentDate.add(Calendar.DATE, -1);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(event);
		}

	}
	
	public void prevMonth()
	{
		currentDate.add(Calendar.MONTH, -1);
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener l : listeners)
		{
			l.stateChanged(event);
		}
				
	}

	/**
	 * Changes the current day to the next day and notifies all the change
	 * listeners.
	 */
	public void nextDay()
	{
		currentDate.add(Calendar.DATE, 1);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(event);
		}
	}
	
	public void nextMonth()
	{
		currentDate.add(Calendar.MONTH, 1);
		ChangeEvent event = new ChangeEvent(this);
		for(ChangeListener l : listeners)
		{
			l.stateChanged(event);
		}
	}

	/**
	 * Changes the current day to the given day and it will notify all change
	 * listeners of a change.
	 * 
	 * @param i
	 *            is the given day number.
	 */
	public void changeDay(int i)
	{
		currentDate.set(Calendar.DATE, i);
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(event);
		}

	}

	/**
	 * Gets a copy of all the events.
	 * 
	 * @return a hashmap containing all the events.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<GregorianCalendar, ArrayList<Event>> getEvents()
	{
		return (HashMap<GregorianCalendar, ArrayList<Event>>) events.clone();
	}

	/**
	 * Creates the given event in the current day and notifies all the change
	 * listeners for a change.
	 * 
	 * @param e
	 *            is the given event
	 */
	public void createEvent(Event e)
	{
		if (events.isEmpty())
		{
			// Add the event to the calendar
			ArrayList<Event> eList = new ArrayList<Event>();
			eList.add(e);
			events.put(e.getStartingTime(), eList);
			System.out.println("created");
		} else
		{
			// Get all the keys in the hashmap
			Set<GregorianCalendar> keys = events.keySet();
			boolean state = false;
			for (GregorianCalendar key : keys)
			{
				if (key.get(Calendar.DATE) == e.getStartingTime().get(
						Calendar.DATE))
				{
					if (key.get(Calendar.MONTH) == e.getStartingTime().get(
							Calendar.MONTH))
					{
						if (key.get(Calendar.YEAR) == e.getStartingTime().get(
								Calendar.YEAR))
						{
							state = true;
							events.get(key).add(e);
						}
					}
				}
			}
			if (state == false)
			{
				// Add the event to the calendar
				ArrayList<Event> eList = new ArrayList<Event>();
				eList.add(e);
				events.put(e.getStartingTime(), eList);
				System.out.println("created");
			}
		}
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener l : listeners)
		{
			l.stateChanged(event);
		}
	}

	/**
	 * Gets all the events of the given date and sets them as the current day's
	 * events.
	 * 
	 * @param gc
	 *            is the given date.
	 */
	private void setThisDaysEvents(GregorianCalendar gc)
	{
		currentDayEvents = events.get(gc);
	}

	/**
	 * Gets all the events that are set in the current day.
	 * 
	 * @return an arraylist of all the events that are in the current day.
	 */
	public ArrayList<Event> getEventsOfToday()
	{
		if (findEvent() == true)
			return currentDayEvents;
		return currentDayEvents;
	}

	/**
	 * Checks to see whether there exists events for that particular day.
	 * 
	 * @param checkDate
	 *            is the given date to check
	 * @return true if there was an existing event in the given date, false if
	 *         there wasn't.
	 */
	public boolean findEvent(GregorianCalendar checkDate)
	{
		boolean state = false;
		if (events.isEmpty())
			return false; // there are no events whatsoever
		Set<GregorianCalendar> keys = events.keySet();
		for (GregorianCalendar key : keys)
		{
			if (key.get(Calendar.DATE) == checkDate.get(Calendar.DATE))
			{
				if (key.get((Calendar.MONTH)) == checkDate.get(Calendar.MONTH))
				{
					if (key.get(Calendar.YEAR) == checkDate.get(Calendar.YEAR))
					{
						state = true;
					}
				}
			}
		}
		return state;
	}

	/**
	 * Checks to see whether there are existing events in the current day.
	 * 
	 * @return true if there are existing events in the current day false if
	 *         there aren't.
	 */
	public boolean findEvent()
	{
		boolean state = false;
		if (events.isEmpty())
			return false; // there are no events in this date
		Set<GregorianCalendar> keys = events.keySet();
		for (GregorianCalendar key : keys)
		{
			if (key.get(Calendar.DATE) == currentDate.get(Calendar.DATE))
			{
				if (key.get((Calendar.MONTH)) == currentDate
						.get(Calendar.MONTH))
				{
					if (key.get(Calendar.YEAR) == currentDate
							.get(Calendar.YEAR))
					{
						state = true;
						setThisDaysEvents(key);

					}
				}
			}
		}
		return state;
	}

	/**
	 * Adds or attaches change listeners to the model.
	 * 
	 * @param c
	 *            is the change listener
	 */
	public void attach(ChangeListener c)
	{
		listeners.add(c);
	}

}
