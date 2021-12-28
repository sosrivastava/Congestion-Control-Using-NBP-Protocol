import java.net.*;
import java.io.*;

class Back extends Thread
{
	String bef,ins,aft,all;
	Tim ti=new Tim();
	Back()
	{

	}

	Back(String str,int st,int nex,Get g)
	{
		try
		{
			Socket soc=new Socket("localhost",888);
			bef=str.substring(0,st);
			ins="~localhost|"+ti.calculateTime();
			aft=str.substring(nex,str.length());
			all=bef+ins+aft+"(Backward Feedback)";
			g.jta.append("---------------------------------------------------------------\n");
			g.jta.append(all+"\n");
			g.jta.append("---------------------------------------------------------------"+"\n");
			ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(all);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}