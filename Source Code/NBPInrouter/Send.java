import java.util.*;
import java.net.*;
import java.io.*;

public class Send extends TimerTask
{
	Tim ti;
	Tim1 t1;
	Thread t;
	InetAddress i;
	In_Frame Fra;
	String str="";
	public Send(In_Frame Fra1)
	{
		Fra=Fra1;
		ti=new Tim();
		t1=new Tim1();
		try
		{
			i=InetAddress.getLocalHost();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		if(!Fra.msg.isEmpty())
		{
			try
			{
				try
				{
					Thread.sleep(Fra.tot*Fra.msg.size());
				}
				catch(Exception e){}
				Socket soc1=new Socket("localhost",7799);
			ObjectOutputStream oos=new ObjectOutputStream(soc1.getOutputStream());
				str=i.getHostName()+"~"+"localhost"+"|"+ti.calculateTime()+"|f";
				for(int i=0;i<Fra.len.size();i++)
				{
					str+="|"+Fra.sour.get(i)+"~"+Fra.des.get(i)+"~"+Fra.len.get(i);
				}
				str+="|"+t1.calculateTime();
				Fra.Ing_data.append("---------------------------------------------------------------\n");
				Fra.Ing_data.append(str);
       			       Fra.Ing_data.append("\n"+"---------------------------------------------------------------\n");
				oos.writeObject(str);
				B b=new B(Fra);

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				Fra.l=t1.calculateTime();
				Socket soc1=new Socket("localhost",7799);
				ObjectOutputStream oos=new ObjectOutputStream(soc1.getOutputStream());
				str=i.getHostName()+"~"+"localhost"+"|"+ti.calculateTime()+"|"+"There is no message";
				Fra.Ing_data.append("---------------------------------------------------------------\n");
				Fra.Ing_data.append(str);
				Fra.Ing_data.append("\n"+"---------------------------------------------------------------\n");
				oos.writeObject(str);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}