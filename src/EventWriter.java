import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EventWriter
{
	public EventWriter()
	{
	}
	
	public void write(ArrayList<EventData> e)
	{
		String save = "";
		for(EventData w: e)
		{
			save += w.date[0]+" "+w.date[1]+" "+w.date[2]+" "+w.time.getTimecode()+" "+w.name+" "+(char)176+" "+w.location+" "+(char)176+" "+w.description+" "+(char)176;
			if(w.alarm != null)
				save += " "+w.alarm.date[0]+" "+w.alarm.date[1]+" "+w.alarm.date[2]+" "+w.alarm.time.getTimecode();
			if(e.indexOf(w) < e.size()-1)
			save += "\n";
		}
		
		try
		{
			FileWriter eWriter = new FileWriter(new File(System.getProperty("user.dir")+"/src/events.epp"),false);
			eWriter.write(save);
			eWriter.close();
		}
		catch (IOException f)
		{
			f.printStackTrace();
		}
	}
}