import java.util.*;

class Tim
{
	public String calculateTime()
	{
		Calendar c;
		int hr,min,sec;
		c=Calendar.getInstance();
		hr=c.get(Calendar.HOUR);
		min=c.get(Calendar.MINUTE);
		sec=c.get(Calendar.SECOND);
		String str=hr+":"+min+":"+sec;
		return str;
	}

}
