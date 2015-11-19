/**
 * 
 * @author TeamSharks :)
 *
 */

public class Main// extends EventView
{
	public static void main(String[] args)
	{
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				CalendarFrame calendar = new CalendarFrame();
				System.err.println("This is a test");
				// createAndShowGUI();
			}
		});
	}
}
