import java.util.*;
import java.math.*;

public class ECPointTable
{
	static BigInteger a,p,xp,yp,xq,yq,xr,yr;
	public static void main(String args[])
	{
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter the value of p");
		p=input.nextBigInteger();
		
		System.out.println("Enter the value of a");
		a=input.nextBigInteger();
		
		System.out.println("Enter the value of b");
		BigInteger b=input.nextBigInteger();
		
		BigInteger count=new BigInteger("0");
		BigInteger y=new BigInteger("0");
		boolean flag=true;
		
		System.out.println("Points on EC are \n");
		
		while(flag)
		{
			y=(count.pow(3)).add(a.multiply(count)).add(b).mod(p);
			SQRT(count,p,y);
			count=count.add(new BigInteger("1"));
			
			if(count.compareTo(p)==0)
			{
				flag=false;
			}
		}
	}
	
	public static void SQRT(BigInteger x,BigInteger p,BigInteger a)
	{
		
		BigInteger r1=new BigInteger("0");
		BigInteger r2=new BigInteger("0");
		BigInteger xsp;
		BigInteger y,v1,v2;
		BigInteger temp=new BigInteger("0");
		BigInteger temp1=new BigInteger("0");
		
		xsp=a.mod(p);
		
		y=p.mod(new BigInteger("4"));
		
		if(y.compareTo(new BigInteger("3"))>0)
		{
			System.out.println("No Root");
			return;
		}
		else
		{
		    r1=SqnMod(a,p.add(new BigInteger("1")).divide(new BigInteger("4")),p);
			temp=p.subtract(r1);
			
			while(temp.compareTo(new BigInteger("0"))==-1)
			{
				if(temp.compareTo(new BigInteger("0"))==-1)
				{
					temp=temp.add(p);
				}
			}
			r2=temp;
			
			//verify
			v1=r1.pow(2).mod(p);
			v2=r2.pow(2).mod(p);
			
			if(r1.compareTo(r2)>0)
			{
				temp1=r1;
				r1=r2;
				r2=temp1;
			}
			
			if(v1.compareTo(xsp)==0)
			{
				System.out.print("("+x+","+r1+")");
				ScalarMult(x,r1);
				System.out.println();
			}
			if(v2.compareTo(xsp)==0)
			{
				System.out.print("("+x+","+r2+")");
				ScalarMult(x,r2);
				System.out.println();
			}
		}
		return;
	}
	
	public static BigInteger SqnMod(BigInteger u,BigInteger m,BigInteger p)
	{
        BigInteger A=new BigInteger("1");
		
		//b stores binary value of m
		BigInteger b=new BigInteger("0");
		boolean flag = true;
		
		while(flag)
		{
			//b=m mod p
			b=m.mod(new BigInteger("2"));
			
			//if b = 1 , multiply. Else i.e if b=0, skip
			if(b.equals(new BigInteger("1")))
			{
				A=(A.multiply(u)).mod(p);
			}
			
			//Update value of m. m=(m-b)/2
			m=(m.subtract(b)).divide(new BigInteger("2"));
			
			// if m > 0, continue, else print the answer i.e A
			if(m.compareTo(BigInteger.ZERO)>0)
			{
				u=u.multiply(u).mod(p);
			}
			else
			{
				flag=false;
			}
	     }
		return A;
	}
	
	static void ScalarMult(BigInteger x,BigInteger y)
	{
		xp=x;
		yp=y;
		int count=2;
		boolean flag=true;
		
		while(flag)
		{
			if(count==2)
			{
				AddToItself();
			}
			else
			{
				Add();
			}
			count++;
			if(xr.compareTo(new BigInteger("0"))==0 && yr.compareTo(new BigInteger("0"))==0)
			{
				System.out.print("\t("+xr+","+yr+")");
				flag=false;
			}
			
		}
	}
	
	static void AddToItself()
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
			
			System.out.print("\t("+xr+","+yr+")");
			
			xq=xr;
			yq=yr;
		}
		return;
	}
	
	static void Add()
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
			
			System.out.print("\t("+xr+","+yr+")");
			
			
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