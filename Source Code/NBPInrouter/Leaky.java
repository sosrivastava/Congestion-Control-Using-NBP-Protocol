import java.util.*;
import java.io.*;
import java.net.*;

class Leaky extends TimerTask
{
	static int f;
	static int t=5;
	static int lct=5;
	static int x=0;
	static long t1=2000;
	In_Frame Fral;
	Leaky(In_Frame Fra1)
	{
		Fral=Fral;
	}

	public void senddata()
	{
		if(Fral.msg.size()>0)
		{
			try
			{
				Socket soc1=new Socket("localhost",7799);
			ObjectOutputStream oos=new ObjectOutputStream(soc1.getOutputStream());
				String str=(String) Fral.msg.get(0);
				oos.writeObject(str);
				Fral.msg.removeElementAt(0);
				Fral.len.removeElementAt(0);
				Fral.des.removeElementAt(0);
				Fral.sour.removeElementAt(0);
				Fral.msg.trimToSize();
				Fral.len.trimToSize();
				Fral.des.trimToSize();
				Fral.sour.trimToSize();
				Fral.outp++;
				Fral.OutTxt.setText(Fral.outp+"");
				oos.flush();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void run()
	{
		while(true)
		{
			if(!Fral.msg.isEmpty())
			{
				try
				{
					System.out.println("t1 da :"+t1);
					Thread.sleep(t1);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				  f=x-(t-lct);
	    	      if(f<0)
	    	      {
		  	f=0;
	    	        x=f+Fral.I;
	    	        lct=t;
			senddata();
	    	        Fral.I--;
	    	      }
	    	      else
	    	      {
			x=f+Fral.I;
	    	        lct=t;
	    	        senddata();
	    	        Fral.I--;
	    	      }
	    	      t=t+5;
       		}
			else
			{
				cancel();
			}
		}
	 }
}

