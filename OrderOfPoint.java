import java.math.*;
import java.util.*;

public class OrderOfPoint 
{
	static BigInteger xp,yp,xr,yr,xq,yq;
	
	public static void main(String args[])
	{
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter x coordinate");
		xp=input.nextBigInteger();
		xq=xp;
		
		System.out.println("Enter y coordinate");
		yp=input.nextBigInteger();
		yq=yp;
		
		System.out.println("Enter p");
		BigInteger p=input.nextBigInteger();
		
		System.out.println("Enter a");
		BigInteger a=input.nextBigInteger();
		
		int count=2;
		boolean flag=true;
		
		while(flag)
		{
			if(count==2)
			{
				AddToItself(p,a);
			}
			else
			{
				Add(p);
			}
			count++;
			if(xr.compareTo(new BigInteger("0"))==0 && yr.compareTo(new BigInteger("0"))==0)
			{
				System.out.println("Order is:"+(count-1));
				flag=false;
			}
			
		}
	}
	
	static void AddToItself(BigInteger p,BigInteger a)
	{
		BigInteger temp,B;
		if(yp.compareTo(new BigInteger("0"))==0)
		{
			xr=new BigInteger("0");
			yr=new BigInteger("0");
			return;
		}
		else
		{
			temp=FISH(yp.multiply(new BigInteger("2")),p);
			//B=((3*xp*xp + a)*temp)%p;
			B=((xp.multiply(xp).multiply(new BigInteger("3"))).add(a)).multiply(temp).mod(p);
			
			//xr=(B*B-2*xp)%p;
			xr=(B.multiply(B).subtract(xp.multiply(new BigInteger("2")))).mod(p);
			
			//yr=((B*modSub(xr,xp,p))-yp)%p;
			BigInteger temp1=modSub(xr,xp,p);
			yr=(B.multiply(temp1)).subtract(yp).mod(p);
			
			xq=xr;
			yq=yr;
		}
		return;
	}
	
	static void Add(BigInteger p)
	{
		if(xp.compareTo(xq)==0)
		{
			xr=new BigInteger("0");
			yr=new BigInteger("0");
			return;
		}
		else
		{
			BigInteger temp1;
			BigInteger temp=FISH(modSub(xq,xp,p),p);
			if(temp.compareTo(new BigInteger("0"))==0)
			{
				temp=new BigInteger("1");
			}
			BigInteger A=((modSub(yq,yp,p)).multiply(temp)).mod(p);
			
			//xr=(A*A-xp-xq)%p;
			xr=((A.multiply(A)).subtract(xp).subtract(xq)).mod(p);
			//yr=(A*(modSub(xr,xp,p))-yp)%p;
			//yr=modSub(yp,A*(modSub(xr,xp,p)),p)%p;
			yr=modSub(yp,modSub(xr,xp,p).multiply(A),p).mod(p);
			
			
			xq=xr;
			yq=yr;
		}

	}
	
	static BigInteger modSub(BigInteger x1,BigInteger x2,BigInteger p)
	{
		BigInteger temp=x2.subtract(x1);
		while(temp.compareTo(new BigInteger("0"))==-1)
		{
			if(temp.compareTo(new BigInteger("0"))==-1)
			{
				temp=temp.add(p);
			}
		}
		return temp;
	}
	
	static BigInteger FISH(BigInteger a,BigInteger p)
	{
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
		
		return result;

	}

}
