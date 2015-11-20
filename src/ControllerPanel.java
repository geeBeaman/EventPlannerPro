import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * This is the controller part of the mvc pattern. The controller is responsible
 * for controlling the data flow.
 * 
 * @author Sharks
 *
 */
public class ControllerPanel extends JPanel
{
	private Model model;
	private JButton previousButton;
	private JButton nextButton;
	private JButton quitButton;
	private JButton createButton;

	/**
	 * Initializes the buttons and this class has a model. Also adds
	 * ActionListeners to the buttons.
	 * 
	 * @param m
	 */
	public ControllerPanel(Model m)
	{
		model = m;
		// // Creates the previous button
		JButton previousButton = new JButton();
		try
		{
			Image img = ImageIO.read(getClass().getResource("pre1.png"));
			previousButton.setIcon(new ImageIcon(img));
			Image newimg = img.getScaledInstance(50, 50,
					java.awt.Image.SCALE_SMOOTH);
			previousButton.setIcon(new ImageIcon(newimg));
		} catch (IOException ex)
		{
		}
		previousButton.setOpaque(false);
		previousButton.setBorderPainted(false);
		previousButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				model.prevMonth();

			}
		});

		// // Creates the next button
		JButton nextButton = new JButton();
		try
		{
			Image img = ImageIO.read(getClass().getResource("next1.png"));
			Image newimg = img.getScaledInstance(50, 50,
					java.awt.Image.SCALE_SMOOTH);
			nextButton.setIcon(new ImageIcon(newimg));
		} catch (IOException ex)
		{
		}
		nextButton.setOpaque(false);
		nextButton.setBorderPainted(false);
		nextButton.setPreferredSize(new Dimension(50, 50));
		nextButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				model.nextMonth();
			}
		});
		/*
		 * Button = new JButton("Quit"); quitButton.addActionListener(new
		 * ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * HashMap<GregorianCalendar, ArrayList<Event>> list =
		 * model.getEvents(); ArrayList<Event> ls = new ArrayList<Event>();
		 * Set<GregorianCalendar> keys = list.keySet(); for(GregorianCalendar
		 * key : keys) { ArrayList<Event> temp = list.get(key); for(int i = 0; i
		 * < temp.size(); i++) { ls.add(temp.get(i)); } } Collections.sort(ls);
		 * 
		 * 
		 * try { FileOutputStream fileOut = new FileOutputStream("event.ser");
		 * 
		 * ObjectOutputStream out = new ObjectOutputStream(fileOut);
		 * out.writeInt(ls.size()); for(Event event : ls) {
		 * out.writeObject(event); } out.close(); fileOut.close();
		 * System.out.println("Serialized data is saved in event.ser");
		 * }catch(IOException i) { i.printStackTrace(); } System.exit(0); } });
		 */
		// //Create "+" Icon using image
		// JButton createButton = new JButton();
		// try {
		// Image img = ImageIO.read(getClass().getResource("plus1.png"));
		// createButton.setIcon(new ImageIcon(img));
		// Image newimg = img.getScaledInstance(50, 50,
		// java.awt.Image.SCALE_SMOOTH ) ;
		// createButton.setIcon(new ImageIcon(newimg));
		// } catch (IOException ex) {
		// }
		// // createButton.setPreferredSize(new Dimension(400, 4));
		// createButton.setOpaque(false);
		// createButton.setBorderPainted(false);
		//
		// createButton.addActionListener(new
		// ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// JPanel dialogboxPanel = new JPanel();
		// // dialogboxPanel.setBackground(Color.RED);
		// dialogboxPanel.setLayout(new BorderLayout());
		// JTextField description = new JTextField("Untitled event", 50);
		// JPanel theInfoPanel = new JPanel();
		// theInfoPanel.setLayout(new FlowLayout());
		// JTextField theDate = new JTextField(10);
		// theDate.setEditable(false);
		// theDate.setText((model.getCurrentDate().get(Calendar.MONTH)+1) + "/"
		// + model.getCurrentDay()
		// + "/" + model.getCurrentYear());
		// JTextField startingT = new JTextField(7);
		// startingT.setText("00:00am");
		// JLabel toLabel = new JLabel("to");
		// JTextField endingT = new JTextField(7);
		// endingT.setText("00:00am");
		// theInfoPanel.add(theDate);
		// theInfoPanel.add(startingT);
		// theInfoPanel.add(toLabel);
		// theInfoPanel.add(endingT);
		//
		// // dialogboxPanel.add(description, BorderLayout.NORTH);
		// dialogboxPanel.add(theInfoPanel, BorderLayout.CENTER);
		//
		// int result = JOptionPane.showConfirmDialog(null,
		// dialogboxPanel,"Create Event", JOptionPane.OK_CANCEL_OPTION);
		// if (result == JOptionPane.OK_OPTION)
		// {
		// //get data
		//
		// //starting time
		// String[] start = startingT.getText().split(":");
		// int hourS = Integer.parseInt(start[0]);
		// String ampm = (start[1].substring(2, 3)).toLowerCase();
		// int minuteS = Integer.parseInt(start[1].substring(0, 1));
		// GregorianCalendar startingTime = new GregorianCalendar();
		// startingTime.set(model.getCurrentYear(),
		// (model.getCurrentDate().get(Calendar.MONTH)),
		// model.getCurrentDay(), hourS, minuteS);
		// if(ampm.equals("am"))
		// startingTime.set(Calendar.AM_PM, Calendar.AM);
		// else if(ampm.equals("pm"))
		// startingTime.set(Calendar.AM_PM, Calendar.PM);
		// //ending time
		// String[] end = endingT.getText().split(":");
		// int hourE = Integer.parseInt(end[0]);
		// String ampm2 = (end[1].substring(2, 3)).toLowerCase();
		// int minuteE = Integer.parseInt(end[1].substring(0, 1));
		// GregorianCalendar endingTime = new GregorianCalendar();
		// endingTime.set(model.getCurrentYear(),
		// (model.getCurrentDate().get(Calendar.MONTH)),
		// model.getCurrentDay(), hourE, minuteE);
		// if(ampm2.equals("am"))
		// endingTime.set(Calendar.AM_PM, Calendar.AM);
		// else if(ampm2.equals("pm"))
		// endingTime.set(Calendar.AM_PM, Calendar.PM);
		// Event ev = new Event(description.getText(), startingTime,
		// endingTime);
		// // we need to check time conflict here
		// if(checkConflict(ev) == true)
		// {
		// JOptionPane.showMessageDialog(dialogboxPanel,
		// "The event's time conflicts with an existing event! Please"
		// + " choose a different time.",
		// "Time Conflict Error",
		// JOptionPane.ERROR_MESSAGE);
		// }
		// else
		// model.createEvent(ev);
		// }
		// }
		// });

		add(previousButton);
		add(nextButton);

	}

	/**
	 * Checks whether there is a time conflict.
	 * 
	 * @param newe
	 *            is the new event that needs to be checked to see if it
	 *            conflicts with an existing event.
	 * @return true if there is conflict and false if there isn't.
	 */
	private boolean checkConflict(Event newe)
	{
		boolean state = false;
		boolean dateFound = false;
		HashMap<GregorianCalendar, ArrayList<Event>> listOfEvents = model
				.getEvents();
		if (listOfEvents.isEmpty())
		{
			return state;
		} else
		{
			// Get all the keys in the hashmap
			Set<GregorianCalendar> keys = listOfEvents.keySet();
			ArrayList<Event> existingDateArray = new ArrayList<Event>();

			for (GregorianCalendar key : keys)
			{
				if (key.get(Calendar.DATE) == newe.getStartingTime().get(
						Calendar.DATE))
				{
					if (key.get(Calendar.MONTH) == newe.getStartingTime().get(
							Calendar.MONTH))
					{
						if (key.get(Calendar.YEAR) == newe.getStartingTime()
								.get(Calendar.YEAR))
						{
							dateFound = true;
							existingDateArray = listOfEvents.get(key);
						}
					}
				}
			}
			if (dateFound == true)
			{
				for (Event existing : existingDateArray)
				{
					// check cases
					// case 1 : if new event's starting time overlaps the
					// existing event's starting time
					if (newe.compareTo(existing) == 0) // equal
					{
						System.out.println("1");
						return (state = true);
					}

					// case 2 : if new.starting < existing.ending
					else if (newe.getStartingTime().compareTo(
							existing.getEndingTime()) < 0
							&& newe.getStartingTime().compareTo(
									existing.getStartingTime()) > 0)
					{
						System.out.println("2");
						return (state = true);
					}

					// case 3 : if new.ending > existing.starting
					else if (newe.getEndingTime().compareTo(
							existing.getStartingTime()) > 0
							&& newe.getEndingTime().compareTo(
									existing.getEndingTime()) < 0)
					{
						System.out.println("3");
						return (state = true);
					}

				}
			}
		}
		return state;

	}
}
