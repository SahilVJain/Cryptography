import java.util.*;

public class NumericRep 
{
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the text");
		String text=input.nextLine();
		
		String message=text.replaceAll("\\s+", "");
		
		System.out.println("Enter the block size");
	    int block_size=input.nextInt();
		
		String block="";
		
		boolean flag=true;
		
		int start=0,end=block_size,power=block_size-1,num=0,count=1,val=0;
		
		char x;
		
		char map[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		int mapnum[]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		
		int convert[]=new int[block_size];
		int convert1[]=null;
		
			while(flag)
			{
				block="";
				num=0;
				power=block_size-1;
			if(end<message.length())
			{
				block=block+message.substring(start,end);
			}
			else
			{
				block=block+message.substring(start);
				convert1=new int[block.length()];
				val=1;
				power=block.length()-1;
				flag=false;
			}
			
			start+=block_size;
			end+=block_size;
			
			if(val==0)
			{
			for(int j=0;j<block_size;j++)
			{
				x=block.charAt(j);
				
				for(int k=0;k<26;k++)
				{
					if(x==map[k])
					{
						convert[j]=mapnum[k];
						break;
					}
				}
			}
			}
			else
			{
				for(int j=0;j<block.length();j++)
				{
					x=block.charAt(j);
					
					for(int k=0;k<26;k++)
					{
						if(x==map[k])
						{
							convert1[j]=mapnum[k];
							break;
						}
					}
				}
			}
			
			if(val==0)
			{
			for(int j=0;j<block_size;j++)
			{
				num=num+(convert[j]*(int)Math.pow(26, power));
				power--;
			}
			}
			else
			{
				for(int j=0;j<block.length();j++)
				{
					num=num+(convert1[j]*(int)Math.pow(26,power));
					power--;
				}
			}
		
		
		
		System.out.println("\nThe compressed number generated for block "+count+" is:"+num);
		count++;
		
	}
		
	}
}
