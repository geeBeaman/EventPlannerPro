import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class EventView
{
	// final attributes used for combo boxes (Derick)
	final static Integer[] HOURS = new Integer[]
	{ 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	final static String[] MINUTES = new String[]
	{ "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
			"23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
			"34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
			"45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55",
			"56", "57", "58", "59" };
	final static String[] MERIDIEM = new String[]
	{ "AM", "PM" };

	public static void createAndShowGUI(final Model m)
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

		// UPPER LEFT
		JPanel upperLeftPannel = new JPanel();
		upperLeftPannel.setBackground(Color.WHITE);
		upperLeftPannel.setLayout(new FlowLayout(FlowLayout.LEFT));
		upperPannel.add(upperLeftPannel, BorderLayout.WEST);

		JLabel eventLabel = new JLabel(" Event");
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
		saveButton.setPreferredSize(new Dimension(80, 28));
		saveButton.setFont(railWay);
		upperRightPannel.add(saveButton);

		saveButton.setBackground(babyTeal);
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
		lowerPanel.setLayout(new GridLayout(5, 0));

		JPanel eventNamePanel = new JPanel();
		eventNamePanel.setBackground(Color.WHITE);
		eventNamePanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(eventNamePanel);

		JLabel eventNameLabel = new JLabel("Event Name:");
		eventNameLabel.setFont(railWay);
		eventNamePanel.add(eventNameLabel);

		final JTextField eventNameField = new JTextField(33);
		eventNamePanel.add(eventNameField);

		JPanel eventDateTimePanel = new JPanel();
		eventDateTimePanel.setBackground(Color.WHITE);
		eventNamePanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(eventDateTimePanel);

		JLabel eventTimeLabel = new JLabel("Time: ");
		eventTimeLabel.setFont(railWay);
		eventDateTimePanel.add(eventTimeLabel);

		// EDIT: Changed time JTextField for drop-down "combo boxes" (Derick)
		JPanel eventTimeComboBoxes = new JPanel();
		eventTimeComboBoxes.setBackground(Color.WHITE);
		eventTimeComboBoxes.setLayout(new GridLayout(1, 3, 6, 0));

		final JComboBox<Integer> eventTimeHour = new JComboBox<Integer>(HOURS);
		final JComboBox<String> eventTimeMinute = new JComboBox<String>(MINUTES);
		final JComboBox<String> eventTimeMeridiem = new JComboBox<String>(MERIDIEM);
		eventTimeComboBoxes.add(eventTimeHour);
		eventTimeComboBoxes.add(eventTimeMinute);
		eventTimeComboBoxes.add(eventTimeMeridiem);

		eventDateTimePanel.add(eventTimeComboBoxes);

		JLabel eventDateLabel = new JLabel("Date: ");
		eventDateLabel.setFont(railWay);
		eventDateTimePanel.add(eventDateLabel);

		final JTextField eventDateField = new JTextField(19);
		eventDateTimePanel.add(eventDateField);

		JPanel eventAlarmLocationPanel = new JPanel();
		eventAlarmLocationPanel.setBackground(Color.WHITE);
		eventAlarmLocationPanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(eventAlarmLocationPanel);

		final JButton setAlarmButton = new JButton("Set Alarm");
		setAlarmButton.setPreferredSize(new Dimension(90, 28));
		setAlarmButton.setFont(railWay);
		eventAlarmLocationPanel.add(setAlarmButton);

		setAlarmButton.setBackground(babyTeal);
		setAlarmButton.setOpaque(true);
		setAlarmButton.setBorderPainted(false);

		setAlarmButton.addMouseListener(new MouseListener()
		{
			public void mouseEntered(MouseEvent evt)
			{
				setAlarmButton.setBackground(Color.CYAN);
			}// end mouse entered

			public void mouseExited(MouseEvent evt)
			{
				setAlarmButton.setBackground(babyTeal);
			}// end mouse exited

			public void mouseClicked(MouseEvent evt)
			{
				setAlarmButton.setBackground(Color.GREEN);
			}

			public void mousePressed(MouseEvent evt)
			{
			}

			public void mouseReleased(MouseEvent evt)
			{
			}
		}// end anonymous inner class
				);
		setAlarmButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				AlarmView.AddAlarmGUI();
			}
		});

		JLabel eventLocationLabel = new JLabel("Location:");
		eventLocationLabel.setFont(railWay);
		eventAlarmLocationPanel.add(eventLocationLabel);

		final JTextField eventLocationField = new JTextField(25);
		eventAlarmLocationPanel.add(eventLocationField);

		JPanel eventDescriptionPanel = new JPanel();
		eventDescriptionPanel.setBackground(Color.WHITE);
		eventDescriptionPanel.setLayout(new FlowLayout(10, 10, 10));
		lowerPanel.add(eventDescriptionPanel);

		JLabel eventDescriptionLabel = new JLabel("Description:");
		eventDescriptionLabel.setFont(railWay);
		eventDescriptionPanel.add(eventDescriptionLabel);

		final JTextField eventDescriptionField = new JTextField(33);
		eventDescriptionPanel.add(eventDescriptionField);

		JPanel eventCancelPanel = new JPanel();
		eventCancelPanel.setBackground(Color.WHITE);
		eventCancelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		lowerPanel.add(eventCancelPanel);

		final JButton cancelEventButton = new JButton("Cancel");
		cancelEventButton.setPreferredSize(new Dimension(80, 28));
		cancelEventButton.setFont(railWay);
		eventCancelPanel.add(cancelEventButton);

		cancelEventButton.setBackground(babyTeal);
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
		}// end anonymous inner class
				);

		// CLOSES THE EVENT VIEW FRAME
		cancelEventButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{

			}
		});

		// EDIT: Added ActionListener to saveButton (Derick)
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				int[] date;
				try
				{
					// Wait, why do we need date, since have to choose the day
					// to get to the EventView???
					date = new int[]
					{
							Integer.parseInt(eventDateField.getText()
									.substring(0, 2)),
							Integer.parseInt(eventDateField.getText()
									.substring(3, 5)),
							Integer.parseInt(eventDateField.getText()
									.substring(6)) };
				} catch (StringIndexOutOfBoundsException e)
				{
					System.out
							.println("ERROR: Invalid date. Event cannot be made.");
					System.out.println();
					return;
				}

				int time = (int) eventTimeHour.getSelectedItem()
						* 100
						+ Integer.parseInt((String) eventTimeMinute
								.getSelectedItem());
				if (eventTimeMeridiem.getSelectedItem().equals("PM"))
					time += 1200;

				int[] dateTime = new int[]
				{ date[0], date[1], date[2], time };
				m.eventData.add(new EventData(dateTime, eventNameField
						.getText(), eventLocationField.getText(),
						eventDescriptionField.getText()));

				// TEST
				System.out.println("Number of events: " + m.eventData.size());
				for (EventData e : m.eventData)
				{
					System.out.println(e.name + " " + e.time.toString() + "	"
							+ e.date[0] + "/" + e.date[1] + "/" + e.date[2]);
					System.out.println(e.location);
					System.out.println(e.description);
					System.out.println();
				}

				// EventWriter TEST
				EventWriter eWriter = new EventWriter();
				eWriter.write(m.eventData);
			}
		});

		// Create and set up the window.
		JFrame frame = new JFrame("Event Planner Pro");
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.WHITE);

		frame.add(upperPannel, BorderLayout.NORTH);
		frame.add(lowerPanel, BorderLayout.SOUTH);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
