import java.io.*;
import java.util.*;
import java.net.*;

class RateControl extends Thread
{
	long currentRTT,currentTime,Ptimestamp,EbaseRTT,deltaRTT,QF,FOutRouterRate,Fphase;
	long RTTElapsed,ElastFeedbackTime,rateQuantum,MSS,EhopCount,FInRouterRate,flow;
	In_Frame Fra1;
	Date d=new Date();
	Tim1 tt=new Tim1();
	long ct;
	String eg;
	ServerSocket soc;
	long o;
	RateControl()
	{

	}


	RateControl(In_Frame Fra1)
	{
		this.Fra1=Fra1;
		currentRTT=0;
		EbaseRTT=10;
		RTTElapsed=0;
		QF=10;
		FInRouterRate=0;
		FOutRouterRate=0;
		ct=d.getTime();


		//ElastFeedbackTime=0;
		rateQuantum=0;
		Fphase=0;
		EhopCount=1;
		MSS=61;
		ElastFeedbackTime=0;

		flow=0;
		try
		{
			soc=new ServerSocket(3333);
			start();
		}
		catch(Exception e){}

	}

	public void run()
	{
		while(true)
		{
			try
			{
				Socket s=soc.accept();
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				eg=br.readLine();
				FInRouterRate=Long.parseLong(eg);
				currentTime=d.getTime();
				FOutRouterRate=Long.parseLong(Fra1.egg);
			}
			catch(Exception e){}

			currentRTT=d.getTime()-Fra1.l;
			if(currentRTT<EbaseRTT)
			EbaseRTT=currentRTT;
			deltaRTT=currentRTT-EbaseRTT;
			RTTElapsed=(currentTime-ElastFeedbackTime)/currentRTT;
			ElastFeedbackTime=currentTime;
			rateQuantum=Math.min(MSS/currentRTT,FOutRouterRate/QF);

			if((deltaRTT*FInRouterRate )<(MSS*EhopCount))
			{
				FInRouterRate=FInRouterRate*2;
				System.out.println("The InRouter rate is :"+FInRouterRate);
				if(Leaky.t1>=300) Leaky.t1-=300;
			}
			else
			{
				FInRouterRate=FInRouterRate-rateQuantum;
				System.out.println("The InRouter rate is :"+FInRouterRate);
				Leaky.t1+=1000;

			}
		}
	}
}