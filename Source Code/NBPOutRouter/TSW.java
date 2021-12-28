import java.io.*;
import java.net.*;
class TSW
{
	static long winlength=1;
	static long avg_rate=0;
	static long T_Front=0;
	static long Byte_in_TSW=0;
	static long New_Byte=0;
	static long Packet_Size=0;
	static Tim1 t=new Tim1();

	TSW()
	{

	}

	TSW(long avg_rate)
	{
		avg_rate=avg_rate;
		//System.out.println("Const");
	}


	public void TimeSlide(long Packet_Size,long now)
	{
		Packet_Size=Packet_Size;
		Byte_in_TSW=avg_rate*winlength;
		New_Byte=Byte_in_TSW+Packet_Size;
		avg_rate=New_Byte*(now-T_Front+winlength);
		System.out.println("hai :"+Math.abs(avg_rate));
		String avg=Math.abs(avg_rate)+"";
		long avg1=Long.parseLong(avg.substring(0,14));
		T_Front=now;
		try
		{
			Socket soc=new Socket("localhost",3333);
			PrintWriter pw=new PrintWriter(soc.getOutputStream(),true);
			pw.println(avg1);
		}
		catch(Exception e){e.printStackTrace();}
	}
}