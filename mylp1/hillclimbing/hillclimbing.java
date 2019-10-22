import java.util.*;

class board
{
	static int n=128;
	int b[];
	board()
	{
		this.b=new int[n];
	}
	
	int h()
	{
		int count=0;
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if(b[i]==b[j])
				{
					count+=1;
				}
				else if((b[i]-(i-j))==b[j])
				{
					count+=1;
				}
				else if((b[i]+(i-j))==b[j])
				{
					count+=1;
				}
			}
		}
		return count;
	}
	void display()
	{
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(b[j]==i)
				{
					System.out.print("Q ");
				}
				else
				{
					System.out.print("* ");
				}
			}
			System.out.println("");
		}
	}
}

class hillclimbing
{
	public static void main(String str[])
	{
		System.out.println("hillclimbing");
		board  start = new board();
		for(int i=0;i<board.n;i++)
		{
			start.b[i]=(int)(Math.random()*(double)board.n);
		}
		start.display();
		System.out.println();
		System.out.println(start.h());
		
		while(start.h()!=0)
		{
			board temp=hillclimb(start);
			if(temp.h()<start.h())
			{
				start=temp;
			}
			else if(temp.h()==start.h())
			{
				start.b[(int)(Math.random()*(double)board.n)]=(int)(Math.random()*(double)board.n);
			}
			else
			{
				System.out.println("error");
			}
			
			start.display();
			System.out.println(start.h());
			System.out.println();
		}
	}
	public static board hillclimb(board s)
	{
		s.display();
		System.out.println(s.h());
		System.out.println();
		if(s.h()==0)
		{
			return s;
		}
		board t=new board();
		for(int i=0;i<board.n;i++)
		{
			t.b[i]=s.b[i];
		}
		
		for(int i=0;i<board.n;i++)
		{
			for(int j=0;j<board.n;j++)
			{
				if(t.b[i]!=j)
				{
					int temp=t.b[i];
					t.b[i]=j;
					if(t.h()<s.h())
					{
						board t2= hillclimb(t);
						if(t2.h()==0)
						{
							return t2;
						}
					}
					t.b[i]=temp;
				}
			}
		}
		return t;
	}
}

