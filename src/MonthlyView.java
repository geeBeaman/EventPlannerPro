import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * CalendarView is the view part of the mvc pattern. Its duty is to display the
 * calendar. This class IS-A JPanel and IS-A ChangeListener.
 * @author TeamSharks
 * 
 */
public class MonthlyView extends JPanel implements ChangeListener
{
	private Model model;
	private GregorianCalendar currDate, temp;
	private JPanel monthViewPanel, dayViewPanel;
	private JPanel dayDayPanel = new JPanel();
	private JPanel daysOftheWeekPanel = new JPanel();
	private JPanel dateLabelOfMonthPanel = new JPanel();
	private JLabel dayLabel;
	private JLabel daysOftheWeekLabel;
	private JLabel dateLabelOfMonth = new JLabel("");
	private JLabel dateLabelOfYear = new JLabel("");
	private JButton[] dayButton;
	private int firstdayoftheweek;
	private int daysInMonth;
	private int daynumber;
	private Color babyTeal = new Color(142, 229, 238);
	private Color lightBlue = new Color(176, 226, 255);
	private Border dayBorder = new LineBorder(babyTeal, 1);
	Font railWayBigBold = new Font("Raleway-Regular", Font.BOLD, 26);
	Font railWayMed = new Font("Raleway-Regular", Font.PLAIN, 20);
	Font railWay = new Font("Raleway-Regular", Font.PLAIN, 12);
	Font railWayHuge = new Font("Raleway-Regular", Font.PLAIN, 100);

	private int[] ghettoCurrDate; // Holds today's date (Derick)

	DailyView dv = new DailyView(null);

	/**
	 * This is the constructor which initializes the instance variables and the
	 * components. Also at last it will display the initial screen.
	 * 
	 * @param m
	 *            is the model and HAS-A relationship gets constructed here.
	 */
	public MonthlyView(Model m)
	{
		// CHANGING THE FONT AND SIZE
		java.io.InputStream is = DailyView.class
				.getResourceAsStream("Raleway-Regular.ttf");
		try
		{
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			railWay = font.deriveFont(Font.PLAIN, 12);
			railWayMed = font.deriveFont(Font.PLAIN, 20);
			railWayBigBold = font.deriveFont(Font.BOLD, 26);
			railWayHuge = font.deriveFont(Font.PLAIN, 100);

		} catch (FontFormatException e1)
		{
			e1.printStackTrace();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}

		// MAIN MONTH VIEW PANEL
		// --------------------------------------------------------------------
		this.setLayout(new FlowLayout());
		monthViewPanel = new JPanel();
		monthViewPanel.setBackground(Color.WHITE);
		model = m;

		ghettoCurrDate = new int[]
		{ MONTHS.valueOf(model.getCurrentMonth() + "").ordinal(),
				model.getCurrentDay(), model.getCurrentYear() }; // {month, day,
																	// year}
																	// (Derick)

		currDate = model.getCurrentDate();
		temp = new GregorianCalendar();
		temp.set(currDate.get(Calendar.YEAR), currDate.get(Calendar.MONTH), 1);
		firstdayoftheweek = temp.get(Calendar.DAY_OF_WEEK);
		daysInMonth = currDate.getActualMaximum(Calendar.DATE);
		dayButton = new JButton[31];
		

		// UPPER HALF PANEL
		// --------------------------------------------------------------------------
		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		upperPanel.setBackground(Color.WHITE);
		

		// CURRENT MONTH & YEAR PANEL

		dateLabelOfMonthPanel.setBackground(Color.WHITE);
		// JLabel dateLabelOfMonth = new JLabel("");
		// JLabel dateLabelOfYear = new JLabel("");
		dateLabelOfMonth.setText(model.getCurrentMonth() + " ");
		dateLabelOfYear.setText(Integer.toString(model.getCurrentYear()));
		dateLabelOfMonth.setFont(railWayHuge);
		dateLabelOfYear.setFont(railWayHuge);
		dateLabelOfMonth.setForeground(Color.BLACK);
		dateLabelOfYear.setForeground(babyTeal);
		dateLabelOfMonthPanel.add(dateLabelOfMonth);
		dateLabelOfMonthPanel.add(dateLabelOfYear);

		upperPanel.add(dateLabelOfMonthPanel, BorderLayout.CENTER);

		// PREVIOUS & NEXT BUTTON PANEL
		// JPanel previousNextButtonPanel = new JPanel(new GridLayout(1, 2));
		// JPanel previousButtonLeftPanel = new JPanel();
		// JButton previousButton = new JButton();
		// try
		// {
		// Image img = ImageIO.read(getClass().getResource("pre1.png"));
		// previousButton.setIcon(new ImageIcon(img));
		// Image newimg = img.getScaledInstance(50, 50,
		// java.awt.Image.SCALE_SMOOTH);
		// previousButton.setIcon(new ImageIcon(newimg));
		// } catch (IOException ex)
		// {
		// }
		// previousButton.setOpaque(false);
		// previousButton.setBorderPainted(false);
		// previousButton.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// model.prevMonth();;
		// }
		// });
		// dateLabelOfMonthPanel.add(previousButton);
		// previousButtonLeftPanel.add(previousButton);
		// previousNextButtonPanel.add(previousButtonLeftPanel, new
		// FlowLayout(FlowLayout.LEFT));
		// upperPanel.add(previousNextButtonPanel);
		//
		// // Creates the next button
		// JButton nextButton = new JButton();
		// try
		// {
		// Image img = ImageIO.read(getClass().getResource("next1.png"));
		// Image newimg = img.getScaledInstance(50, 50,
		// java.awt.Image.SCALE_SMOOTH);
		// nextButton.setIcon(new ImageIcon(newimg));
		// } catch (IOException ex)
		// {
		// }
		// nextButton.setOpaque(false);
		// nextButton.setBorderPainted(false);
		// nextButton.setPreferredSize(new Dimension(50, 50));
		// nextButton.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent e)
		// {
		// model.nextDay();
		// }
		// });

		// JPanel daysOftheWeekPanel = new JPanel();
		JLabel daysOftheWeek = new JLabel("");
		daysOftheWeekPanel.setLayout(new GridLayout());
		daysOftheWeekPanel.setBackground(babyTeal);

		String[] days =
		{ "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
				"SATURDAY" };
		for (int i = 0; i < days.length; i++)
		{
			final String label = days[i];
			JLabel dayLabel = new JLabel(label);
			dayLabel.setForeground(Color.BLACK);
			dayLabel.setBackground(lightBlue);
			dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
			daysOftheWeekPanel.add(dayLabel);

			dayLabel.setFont(railWayMed);
			daysOftheWeekPanel.add(dayLabel);
		}
		dayDayPanel.setLayout(new GridLayout(0, 7));
		for (int i = 1; i < firstdayoftheweek; i++)
		{
			JLabel dayLabell = new JLabel("");
			dayDayPanel.add(dayLabell);
		}
		for (int i = 0; i < dayButton.length; i++)
		{
			final int counter = i + 1;

			GregorianCalendar foo = new GregorianCalendar();
			foo.set(model.getCurrentYear(),
					model.getCurrentDate().get(Calendar.MONTH), i + 1);
			String star = "";
			if (model.findEvent(foo) == true)
				star = " *";
			dayButton[i] = new JButton(Integer.toString(i + 1) + star);
			dayButton[i].setOpaque(true);
			dayButton[i].setBorder(dayBorder);
			dayButton[i].setBackground(lightBlue);
			dayButton[i].setPreferredSize(new Dimension(205, 90));
			dayButton[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					daynumber = counter;
					model.changeDay(daynumber);
					DailyView.createAndShowDailyViewGUI();
				}
			});
		}
		for (int i = 0; i <= daysInMonth; i++)
		{
			dayDayPanel.add(dayButton[i]);
			dayButton[i].setPreferredSize(new Dimension(205, 90));
			dayButton[i].setBackground(Color.WHITE);
			dayButton[i].setHorizontalAlignment(SwingConstants.LEFT);
			dayButton[i].setVerticalAlignment(SwingConstants.NORTH);
			// set the current date to a certain color
			if (i + 1 == model.getCurrentDay())
			{
				dayButton[i].setBackground(lightBlue);
			}
		}
		dayViewPanel = new JPanel();
		dayViewPanel.setLayout(new BorderLayout());
		dayLabel = new JLabel();
		dayViewPanel.setBackground(Color.WHITE);

		dayLabel.setText(model.getCurrentDayoftheWeek() + " "
				+ (model.getCurrentDate().get(Calendar.MONTH) + 1) + "/"
				+ model.getCurrentDay());
		//JTextArea dayViewArea = new JTextArea(20,50);

		ArrayList<Event> listofE = new ArrayList<Event>();
		listofE = model.getEventsOfToday();
		if (listofE != null)
		{
			Collections.sort(listofE);
		}
		String s = "";

		for (int i = 0; i <= 24; i++)
		{
			if (i < 10)
			{
				s += ("0" + i);
				s += ("-------\n");

				if (listofE != null)
				{
					for (Event ev : listofE)
					{
						if (ev.getStartingTime().get(
								GregorianCalendar.HOUR_OF_DAY) == i)
						{
							s += ev.getTitle();
							s += " at "
									+ ev.getStartingTime().get(Calendar.HOUR)
									+ ":";
							if (ev.getStartingTime().get(Calendar.MINUTE) < 10)
							{
								s += "0"
										+ ev.getStartingTime().get(
												Calendar.MINUTE);
							} else
								s += ev.getStartingTime().get(Calendar.MINUTE);
							if (ev.getStartingTime().get(Calendar.AM_PM) == 0)
							{
								s += "AM";
							} else
								s += "PM";

							s += " to " + ev.getEndingTime().get(Calendar.HOUR)
									+ ":";
							if (ev.getEndingTime().get(Calendar.MINUTE) < 10)
							{
								s += "0"
										+ ev.getEndingTime().get(
												Calendar.MINUTE);
							} else
								s += ev.getEndingTime().get(Calendar.MINUTE);
							if (ev.getEndingTime().get(Calendar.AM_PM) == 0)
							{
								s += "AM";
							} else
								s += "PM";
						}
					}
				}

				s += "\n\n";
			} else
			{
				s += i;
				s += ("-------\n");

				if (listofE != null)
				{
					for (Event ev : listofE)
					{
						if (ev.getStartingTime().get(
								GregorianCalendar.HOUR_OF_DAY) == i)
						{
							s += ev.getTitle();
							s += " at "
									+ ev.getStartingTime().get(Calendar.HOUR)
									+ ":";
							if (ev.getStartingTime().get(Calendar.MINUTE) < 10)
							{
								s += "0"
										+ ev.getStartingTime().get(
												Calendar.MINUTE);
							} else
								s += ev.getStartingTime().get(Calendar.MINUTE);
							if (ev.getStartingTime().get(Calendar.AM_PM) == 0)
							{
								s += "AM";
							} else
								s += "PM";

							s += " to " + ev.getEndingTime().get(Calendar.HOUR)
									+ ":";
							if (ev.getEndingTime().get(Calendar.MINUTE) < 10)
							{
								s += "0"
										+ ev.getEndingTime().get(
												Calendar.MINUTE);
							} else
								s += ev.getEndingTime().get(Calendar.MINUTE);
							if (ev.getEndingTime().get(Calendar.AM_PM) == 0)
							{
								s += "AM";
							} else
								s += "PM";

						}
					}

				}
				s += "\n\n";
			}
		}
		/*
		 * dayViewArea.setText(s); dayViewScrollPane = new
		 * JScrollPane(dayViewArea); dayViewPanel.add(dayLabel,
		 * BorderLayout.NORTH); dayViewPanel.add(dayViewScrollPane,
		 * BorderLayout.CENTER);
		 */

		monthViewPanel
				.setLayout(new BoxLayout(monthViewPanel, BoxLayout.Y_AXIS));
		monthViewPanel.add(dateLabelOfMonthPanel);
		monthViewPanel.add(daysOftheWeekPanel);
		monthViewPanel.add(dayDayPanel);
		monthViewPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		dayDayPanel.setBackground(Color.WHITE);

		add(monthViewPanel);

	}

	/**
	 * When there is a change in the model stateChanged gets notified of a
	 * changeEvent and stateChanged is responsible for repainting the screen
	 * with the changes that has been applied.
	 */
	public void stateChanged(ChangeEvent c)
	{
		currDate = model.getCurrentDate();
		temp = (GregorianCalendar) currDate.clone();

		dateLabelOfMonth.setText(model.getCurrentMonth() + " ");
		dateLabelOfYear.setText(Integer.toString(model.getCurrentYear()));
		dateLabelOfMonth.setFont(railWayHuge);
		dateLabelOfYear.setFont(railWayHuge);
		dateLabelOfMonth.setForeground(Color.BLACK);
		dateLabelOfYear.setForeground(babyTeal);
		temp.set(currDate.get(Calendar.YEAR), currDate.get(Calendar.MONTH), 1);
		firstdayoftheweek = temp.get(Calendar.DAY_OF_WEEK);
		daysInMonth = currDate.getActualMaximum(Calendar.DATE);

		removeAll();
		monthViewPanel
				.setLayout(new BoxLayout(monthViewPanel, BoxLayout.Y_AXIS));
		monthViewPanel.add(dateLabelOfMonthPanel);
		monthViewPanel.add(daysOftheWeekPanel);
		dayDayPanel.removeAll();
		for (int i = 1; i < firstdayoftheweek; i++)
		{
			JLabel dayLabell = new JLabel("");
			dayDayPanel.add(dayLabell);
		}

		GregorianCalendar foo = new GregorianCalendar();
		for (int i = 0; i < daysInMonth; i++)
		{
			dayButton[i].setPreferredSize(new Dimension(205, 90));
			dayDayPanel.add(dayButton[i]);
			foo.set(model.getCurrentYear(),
					model.getCurrentDate().get(Calendar.MONTH), i + 1);
			String star = "";
			if (model.findEvent(foo) == true)
				star = " *";

			// set the current date to a certain color
			dayButton[i].setBackground(Color.WHITE);
			if (currDate.get(Calendar.MONTH) == ghettoCurrDate[0]
					&& i + 1 == ghettoCurrDate[1]
					&& model.getCurrentYear() == ghettoCurrDate[2])
				dayButton[i].setBackground(lightBlue);

			dayButton[i].setText(Integer.toString(i + 1) + star);

			dayButton[i].setForeground(Color.BLACK);

		}

		dayButton[model.getCurrentDay() - 1].setForeground(babyTeal);
		dayLabel.setText(model.getCurrentDayoftheWeek() + " "
				+ (model.getCurrentDate().get(Calendar.MONTH) + 1) + "/"
				+ model.getCurrentDay());

		// ArrayList<Event> listofE;
		// String s = "";
		// if (model.findEvent() == true) {
		//
		// listofE = model.getEventsOfToday();
		// Collections.sort(listofE);
		// for (int i = 0; i <= 24; i++) {
		// if (i < 10) {
		// s += ("0" + i);
		// s += ("-------\n");
		//
		// for (Event ev : listofE) {
		// if (ev.getStartingTime().get(
		// GregorianCalendar.HOUR_OF_DAY) == i) {
		// s += ev.getTitle();
		// s += " at "
		// + ev.getStartingTime().get(Calendar.HOUR)
		// + ":";
		// if (ev.getStartingTime().get(Calendar.MINUTE) < 10) {
		// s += "0"
		// + ev.getStartingTime().get(
		// Calendar.MINUTE);
		// } else
		// s += ev.getStartingTime().get(Calendar.MINUTE);
		// if (ev.getStartingTime().get(Calendar.AM_PM) == 0) {
		// s += "AM";
		// } else
		// s += "PM";
		//
		// s += " to " + ev.getEndingTime().get(Calendar.HOUR)
		// + ":";
		// if (ev.getEndingTime().get(Calendar.MINUTE) < 10) {
		// s += "0"
		// + ev.getEndingTime().get(
		// Calendar.MINUTE);
		// } else
		// s += ev.getEndingTime().get(Calendar.MINUTE);
		// if (ev.getEndingTime().get(Calendar.AM_PM) == 0) {
		// s += "AM";
		// } else
		// s += "PM";
		// }
		// }
		// s += "\n\n";
		// } else {
		// s += i;
		// s += ("-------\n");
		//
		// for (Event ev : listofE) {
		// if (ev.getStartingTime().get(
		// GregorianCalendar.HOUR_OF_DAY) == i) {
		// s += ev.getTitle();
		// s += " at "
		// + ev.getStartingTime().get(Calendar.HOUR)
		// + ":";
		// if (ev.getStartingTime().get(Calendar.MINUTE) < 10) {
		// s += "0"
		// + ev.getStartingTime().get(
		// Calendar.MINUTE);
		// } else
		// s += ev.getStartingTime().get(Calendar.MINUTE);
		// if (ev.getStartingTime().get(Calendar.AM_PM) == 0) {
		// s += "AM";
		// } else
		// s += "PM";
		//
		// s += " to " + ev.getEndingTime().get(Calendar.HOUR)
		// + ":";
		// if (ev.getEndingTime().get(Calendar.MINUTE) < 10) {
		// s += "0"
		// + ev.getEndingTime().get(
		// Calendar.MINUTE);
		// } else
		// s += ev.getEndingTime().get(Calendar.MINUTE);
		// if (ev.getEndingTime().get(Calendar.AM_PM) == 0) {
		// s += "AM";
		// } else
		// s += "PM";
		//
		// }
		// }
		// s += "\n\n";
		// }
		// }
		// dayViewArea.setText(s);
		//
		// } else {
		// for (int i = 0; i <= 24; i++) {
		// if (i < 10) {
		// s += ("0" + i);
		// s += ("-------\n");
		// s += "\n\n";
		// } else {
		// s += i;
		// s += ("-------\n");
		// s += "\n\n";
		// }
		// }
		// dayViewArea.setText(s);
		// }

		// dayViewPanel.add(dayLabel, BorderLayout.NORTH);
		// dayViewPanel.add(dayViewScrollPane, BorderLayout.CENTER);
		monthViewPanel.add(dayDayPanel);
		add(monthViewPanel, BorderLayout.CENTER);
		// add(dayViewPanel, BorderLayout.CENTER);
		repaint();
		revalidate();

	}
}
