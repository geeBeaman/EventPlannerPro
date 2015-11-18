import java.awt.Image;
import java.awt.Toolkit;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;


public class DockIcon {{
	try {
	     ClassLoader cl = this.getClass().getClassLoader();
	     ImageIcon programIcon = new ImageIcon(cl.getResource("res/test.png"));
	     setIconImage(programIcon.getImage());
	  } catch (Exception whoJackedMyIcon) {
	     System.out.println("Could not load program icon.");
	  }
}

private void setIconImage(Image image) {
	// TODO Auto-generated method stub
	
}}