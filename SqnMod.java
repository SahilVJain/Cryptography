import java.util.*;
import java.math.*;

public class SqnMod 
{
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the base");
		BigInteger u=input.nextBigInteger();
		
		System.out.println("Enter the power");
		BigInteger m =input.nextBigInteger();
		
		System.out.println("Enter the value of p");
		BigInteger p=input.nextBigInteger();
		
		//Seed for 1st iteration
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
				System.out.println(A);
				flag=false;
			}
		}
	}
}
