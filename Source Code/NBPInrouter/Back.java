import java.io.*;
import java.net.*;
import java.util.*;

class Back extends Thread
{

	In_Frame rf;
	ServerSocket ss;
	int fir,sec,thir,i=0;

	//RateControl rc=new RateControl(this);



	Back(In_Frame obj1)
	{
		rf=obj1;
		start();
	}

	public void run()
	{
		try
		{
			ss=new ServerSocket(777);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		while(true)
		{
			try
		    {
				//System.out.println("back accept");
				Socket soc=ss.accept();
				ObjectInputStream ois=new ObjectInputStream(soc.getInputStream());
		    	String instring=(String) ois.readObject();
		    	rf.Ing_data.append("---------------------------------------------------------------\n");
		    	rf.Ing_data.append(instring+"\n");
		    	rf.Ing_data.append("---------------------------------------------------------------"+"\n");
				int fir=instring.indexOf('|');
				int sec=instring.indexOf('|',fir+1);
				int thir=instring.indexOf('|',sec+1);
				if(thir>0)
				{
					String inside=instring.substring(sec+1,instring.length());
					//System.out.println("inside1 :"+inside);
					int len=0;
					do
					 {
						 i++;
							int las=inside.lastIndexOf('|');
							//System.out.println("inside2 :"+las);
							if(las>0)
							{
								int in=inside.indexOf('|');
								int nex=inside.indexOf('|',in+1);
								//System.out.println("inside2 :"+in);
								//System.out.println("inside2 :"+nex);
								//System.out.println("inside2 :"+las);
								//System.out.println(inside.substring(in+1,nex));
								rf.tot+=Integer.parseInt(inside.substring(in+1,nex));
								if(i==1){rf.egg=rf.tot+"";}
								inside=inside.substring(nex+1,las+1);
								//System.out.println("inside21 :"+inside);
								len=inside.length();
								//System.out.println("inside22 :"+inside.length());
							}
							else
							{
								len=0;
							}
					}while(len>0);
				}
		   	}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}
	}
}