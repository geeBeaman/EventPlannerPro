/**
 * @author TeamSharks
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlarmView extends MonthlyView
{
	final static Integer[] HOURS = new Integer[]{12,1,2,3,4,5,6,7,8,9,10,11};
	final static String[] MINUTES = new String[]{
		"00","01","02","03","04","05","06","07","08","09",
		"10","11","12","13","14","15","16","17","18","19",
		"20","21","22","23","24","25","26","27","28","29",
		"30","31","32","33","34","35","36","37","38","39",
		"40","41","42","43","44","45","46","47","48","49",
		"50","51","52","53","54","55","56","57","58","59"
		};
	final static String[] MERIDIEM = new String[]{"AM","PM"};
	
	static Alarm alarm;
	
	Time time;
	int[] date;

	public AlarmView(Model m)
	{
		super(m);
	}

	// public Alarm(int[] t)
	// {
	// time = new Time(t[3]);
	// date = new int[]
	// { t[0], t[1], t[2] };
	// }TEST

	public static void AddAlarmGUI()
	{
		final Color babyTeal = new Color(142, 229, 238);
		Font railWayBig = new Font("Raleway-Regular", Font.PLAIN, 26);
		Font railWay = new Font("Raleway-Regular", Font.PLAIN, 12);

		java.io.InputStream is = DailyView.class
				.getResourceAsStream("Raleway-Regular.ttf");
		try
		{
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			railWay = font.deriveFont(Font.PLAIN, 12);
			railWayBig = font.deriveFont(Font.PLAIN, 26);

		} catch (FontFormatException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// UPPER GUI
		JPanel upperPannel = new JPanel();
		upperPannel.setLayout(new GridLayout(2, 2));
		upperPannel.setBackground(Color.WHITE);

		// UPPER LEFT
		JPanel upperLeftPannel = new JPanel();
		upperLeftPannel.setBackground(Color.WHITE);
		upperLeftPannel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperPannel.add(upperLeftPannel, BorderLayout.WEST);

		JLabel eventLabel = new JLabel(" Set Alarm");
		eventLabel.setFont(railWayBig);
		eventLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperLeftPannel.add(eventLabel, BorderLayout.WEST);

		// UPPER RIGHT
		JPanel upperRightPannel = new JPanel();
		upperRightPannel.setBackground(Color.WHITE);
		upperRightPannel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperPannel.add(upperRightPannel, BorderLayout.EAST);

		final JButton removeButton = new JButton("-");
		removeButton.setFont(railWayBig);
		removeButton.setBackground(babyTeal);
		removeButton.setPreferredSize(new Dimension(80, 28));
		removeButton.setOpaque(true);
		removeButton.setBorderPainted(false);
		upperRightPannel.add(removeButton);

		removeButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				removeButton.setBackground(Color.RED);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				removeButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				removeButton.setBackground(Color.GREEN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		}// end anonymous inner class
				);

		final JButton saveButton = new JButton("Save");
		saveButton.setFont(railWay);
		upperRightPannel.add(saveButton);

		saveButton.setBackground(babyTeal);
		saveButton.setPreferredSize(new Dimension(80, 28));
		saveButton.setOpaque(true);
		saveButton.setBorderPainted(false);

		saveButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				saveButton.setBackground(Color.CYAN);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				saveButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				saveButton.setBackground(Color.GREEN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		}// end anonymous inner class
				);

		// UPPER BOTTOM LEFT
		JPanel upperBottomLeftPannel = new JPanel();
		upperBottomLeftPannel.setBackground(babyTeal);
		upperBottomLeftPannel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperPannel.add(upperBottomLeftPannel, BorderLayout.SOUTH);

		// UPPER BOTTOM RIGHT
		JPanel upperBottomRightPannel = new JPanel();
		upperBottomRightPannel.setBackground(babyTeal);
		upperBottomRightPannel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		upperPannel.add(upperBottomRightPannel, BorderLayout.SOUTH);

		// LOWER GUI
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new GridLayout(4, 0));
		lowerPanel.setBackground(Color.WHITE);

		JPanel extraSpacePanel1 = new JPanel();
		extraSpacePanel1.setBackground(Color.WHITE);
		extraSpacePanel1.setLayout(new FlowLayout(2, 2, 2));
		lowerPanel.add(extraSpacePanel1);

		JPanel TimeDatePanel = new JPanel();
		TimeDatePanel.setBackground(Color.WHITE);
		TimeDatePanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(TimeDatePanel);

		JLabel timeLabel = new JLabel("Time: ");
		timeLabel.setFont(railWay);
		TimeDatePanel.add(timeLabel);

		JPanel eventTimeComboBoxes = new JPanel();
		eventTimeComboBoxes.setBackground(Color.WHITE);
		eventTimeComboBoxes.setLayout(new GridLayout(1, 3, 6, 0));

		JComboBox<Integer> eventTimeHour = new JComboBox<Integer>(
				EventView.HOURS);
		JComboBox<String> eventTimeMinute = new JComboBox<String>(
				EventView.MINUTES);
		JComboBox<String> eventTimeMeridiem = new JComboBox<String>(
				EventView.MERIDIEM);
		eventTimeComboBoxes.add(eventTimeHour);
		eventTimeComboBoxes.add(eventTimeMinute);
		eventTimeComboBoxes.add(eventTimeMeridiem);

		TimeDatePanel.add(eventTimeComboBoxes);

		// JTextField timeTextField = new JTextField(25);
		// TimeDatePanel.add(timeTextField);

		JPanel extraSpacePanel = new JPanel();
		extraSpacePanel.setBackground(Color.WHITE);
		extraSpacePanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(extraSpacePanel);

		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(railWay);
		TimeDatePanel.add(dateLabel);

		JTextField dateTextField = new JTextField(25);
		TimeDatePanel.add(dateTextField);

		JPanel eventCancelPanel = new JPanel();
		eventCancelPanel.setBackground(Color.WHITE);
		eventCancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		final JButton cancelEventButton = new JButton("Cancel");
		cancelEventButton.setFont(railWay);
		eventCancelPanel.add(cancelEventButton);

		cancelEventButton.setBackground(babyTeal);
		cancelEventButton.setPreferredSize(new Dimension(80, 28));
		cancelEventButton.setOpaque(true);
		cancelEventButton.setBorderPainted(false);

		cancelEventButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				cancelEventButton.setBackground(Color.RED);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				cancelEventButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				cancelEventButton.setBackground(Color.CYAN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		});

		// CLOSES THE ALARM VIEW FRAME
		cancelEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{

			}
		});

		lowerPanel.add(eventCancelPanel);

		// Create and set up the window.

		JFrame frame = new JFrame("Event Planner Pro");
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);

		frame.add(upperPannel, BorderLayout.NORTH);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	// public void setTime(/* String s */)
	// {
	//
	// int t = 0; for(int i = 0; i < s.length(); i++) { if(s.charAt(i) >= 48 &&
	// s.charAt(i) <= 57) { t *= 10; t +=
	// Character.getNumericValue(s.charAt(i))-48; } } time = new Time(t);
	//
	// }

	// Returns time for editing in EventView
	// Might not have to be a string
	public String getTime()
	{
		return time.toString();
	}
}
