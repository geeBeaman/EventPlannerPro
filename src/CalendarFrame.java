import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * CalendarFrame is a frame that displays the window with its components.
 * 
 * @author naghmeh
 *
 */
public class CalendarFrame extends JFrame
{
	private Model model;
	private ControllerPanel controller;
	private MonthlyView view;

	/**
	 * CalendarFrame constructor initializes the model, view, and controller.
	 * Sets the window size and the layouts of its components.
	 */
	public CalendarFrame()
	{
		model = new Model();
		controller = new ControllerPanel(model);
		view = new MonthlyView(model);
		model.attach(view);

		this.setSize(1500, 700);
		add(controller, BorderLayout.NORTH);
		add(view, BorderLayout.CENTER);
		setBackground(Color.WHITE);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// pack();
		setVisible(true);
	}
}
