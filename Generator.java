import java.util.*;
import java.math.*;

public class Generator 
{
	public static void main(String args[])
	{
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter the number of factors");
		int n=input.nextInt();
		
		BigInteger factors[]=new BigInteger[n];
		//factors[] stores prime factors of the given number.
		BigInteger factor=null;
		BigInteger b=null;
		
		System.out.println("Enter the value of p");
		BigInteger p=input.nextBigInteger();
		//p stores mod value which will be used to calculate Sqmod while checking a number for generator.
		
		int d,generator=1;
		BigInteger count=new BigInteger("2");
		
		
		//Reading factors of p-1
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter factor "+(i+1));
			factors[i]=input.nextBigInteger();
		}
		
		//Checking numbers from 2 to p-1 for generators
		while(true)
		{
			//Computing generator values for all factors
			for(int j=0;j<n;j++)
			{
				//compute g^(p-1)/factor and check if it is equal to 1
				factor=factors[j];
				b=sqmod(count,(p.subtract(new BigInteger("1"))).divide(factor),p);
				//b stores value of g^(p-1)/factor. 
				
				if(b.equals(new BigInteger("1")))
				{
					break;
				}
				//if b=1, break loop and check next number for value of g
			}
			
			d=b.compareTo(new BigInteger("1"));
			//if b != 1 after checking all factors, we have found the generator.
			if(d==1)
			{
				generator=count.intValue();
				break;
			}
			count=count.add(new BigInteger("1"));
			
		}
	
		
		{
		System.out.println("Generator is:"+generator);
		}
		
		
	}
	
	//function to calculate SqMod
	public static BigInteger sqmod(BigInteger base,BigInteger power,BigInteger mod)
	{
		BigInteger A=new BigInteger("1");
		BigInteger B=new BigInteger("0");
		boolean flag=true;
		
		while(flag)
		{
			B=power.mod(new BigInteger("2"));
			if(B.equals(new BigInteger("1")))
			{
				A=(A.multiply(base)).mod(mod);
			}
			
			power=(power.subtract(B).divide(new BigInteger("2")));
			
			if(power.compareTo(BigInteger.ZERO)>0)
			{
				base=base.multiply(base).mod(mod);
			}
			else
			{
				flag=false;
			}
		}
		
		return A;
	}

}
