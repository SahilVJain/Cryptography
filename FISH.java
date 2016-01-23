import java.util.*;
import java.math.*;


public class FISH 
{
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the value of p"); //mod value
		BigInteger p=input.nextBigInteger();
		
		System.out.println("Enter the value of a"); 
		BigInteger a=input.nextBigInteger();
		
		BigInteger T=p;
		BigInteger B=a;
		BigInteger H=new BigInteger("2");
		BigInteger F=null;
		BigInteger result=null;
		
		
		int count=0,flag=0,i=0,res=2;
		//Counter count counts number of steps to check odd or even
		//F stores quotient 
		//H stores remainder
		//result stores final inverse calculated.
		
		BigInteger w[]=new BigInteger[20];
		//w[] stores values of Quotient obtained in Northern Stage and uses them in Southern Stage.
		//Counter i keeps a count of number of values in w[]
		
		//Northern Stage
		while(res>0)
		{
			F=T.divide(B);
			w[i++]=F;
			H=T.subtract(F.multiply(B));
			//H=T-F*B;
			count++;
			T=B;
			B=H;
			res=H.compareTo(new BigInteger("1"));
			
			
		}
		
		flag=count+1;
		T=new BigInteger("0");
		B=new BigInteger("1");
		
		//Southern Stage
		while(count!=0)
		{
			F=w[i-1];
			H=(F.multiply(B)).add(T);
			//H=F*B+T;
			count--;
			i--;
			T=B;
			B=H;
		}
		
		
		if(flag%2==0)
		{
			result=p.subtract(H);
			//if flag is even
		}
		else
		{
			result=H;
			//if flag is odd
		}
		
		System.out.println("Inverse is:"+result);
		
	}

}
