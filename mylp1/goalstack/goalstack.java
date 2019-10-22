import java.util.*;

class goalstack
{
	static state start,stop,curr;
	static ArrayList<String> step=new ArrayList<>();
	static Stack<String> stack=new Stack<>();
	public static void main(String str[])
	{
		int b=4;
		String s1="(on b a)^(ontable c)^(ontable d)^(ontable a)^(clear c)^(clear d)^(clear b)";
		String s2="(on c a)^(on b d)^(ontable a)^(ontable d)^(clear b)^(clear c)";
		start=new state(b,s1);
		stop=new state(b,s2);
		curr=new state(b,s1);
		
		//start.display();
		//stop.display();
		calculate(s2);
	}
	public static void calculate(String goal)
	{
		stack.push(goal);
		System.out.println("push "+goal);
		while(!stack.isEmpty())
		{
			String temp=stack.pop();
			System.out.println("pop"+temp);
			if(temp.contains("^"))
			{
				if(curr.check(temp)==0)
				{
					String t[]=temp.split("['^']+");
					for(int i=t.length-1;i>=0;i--)
					{
						System.out.println("push "+t[i]);
						stack.push(t[i]);
					}
				}
			}
			else if(temp.contains("ontable") || temp.contains("clear"))
			{
				String t[]=temp.split("[() ]+");
				if(curr.check(temp)==0)
				{
					int i=curr.checktop(t[2].charAt(0));
					if(i!=-1)
					{
						stack.push("(unstack "+Character.toString((char)(i+97))+" "+t[2].charAt(0)+")");
						stack.push("(on "+Character.toString((char)(i+97))+" "+t[2].charAt(0)+")"+"^"+"(clear "+Character.toString((char)(i+97))+")");
						stack.push("(clear "+Character.toString((char)(i+97))+")");
						stack.push("(on "+Character.toString((char)(i+97))+" "+t[2].charAt(0)+")");
					}
				}
			}
			else if(temp.contains("on"))
			{
				String t[]=temp.split("[() ]+");
				if(curr.check(temp)==0)
				{
					stack.push("(stack "+t[2].charAt(0)+" "+t[3].charAt(0)+")");
					stack.push("(clear "+t[2].charAt(0)+")^(clear "+t[3].charAt(0)+")");
					stack.push("(clear "+t[2].charAt(0)+")");
					stack.push("(clear "+t[3].charAt(0)+")");
				}
			}
			else if(temp.contains("stack") || temp.contains("unstack"))
			{
				step.add(temp);
				curr.perform(temp);
			}
			else
			{
				System.out.println("else loop "+temp);
			}
		}
		System.out.println(step);
	}
}

class state
{
	int b;
	int on[][];
	int ontable[];
	int clear[];
	
	state(int b,String s)
	{
		this.b=b;
		on=new int[b][b];
		ontable=new int[b];
		clear=new int[b];
		setState(s);	
	}
	
	public void setState(String s)
	{
		String sarr[]=s.split("['^']+");
		for(int i=0;i<sarr.length;i++)
		{
			//System.out.println(" "+sarr[i]+" ");
			String t[]=sarr[i].split("[() ]+");
			for(int j=0;j<t.length;j++)
			{
				//System.out.print(t[j]+" - ");
				if(t[1].equals("ontable"))
				{
					ontable[(int)t[2].charAt(0)%97]=1;
				}
				if(t[1].equals("on"))
				{
					on[(int)t[2].charAt(0)%97][(int)t[3].charAt(0)%97]=1;
				}
				if(t[1].equals("clear"))
				{
					clear[(int)t[2].charAt(0)%97]=1;
				}
			}
			//System.out.println();
		}
	}
	
	public void display()
	{
		System.out.println("\n ontable ");
		for(int i=0;i<b;i++)
		{
			System.out.print(" "+ontable[i]);
		}
		System.out.println("\n on");
		for(int i=0;i<b;i++)
		{
			
			for(int j=0;j<b;j++)
			{
				System.out.print(" "+on[i][j]);
			}
			System.out.println(" ");
		}
		System.out.println("\n clear");
		for(int i=0;i<b;i++)
		{
			System.out.println(" "+clear[i]);
		}
	}
	
	public int check(String s)
	{
		String sarr[]= s.split("['^']");
		int flag=0;
		for(int i=0;i<sarr.length;i++)
		{
			String t[]=sarr[i].split("[() ]+");
			if(t[1].equals("on") && on[(int)t[2].charAt(0)%97][(int)t[3].charAt(0)%97]==1)
			{
				flag=1;
			}
			else if(t[1].equals("ontable") && ontable[(int)t[2].charAt(0)%97]==1)
			{
				flag=1;
			}
			else if(t[1].equals("clear") && clear[(int)t[2].charAt(0)%97]==1)
			{
				flag=1;
			}
			else
			{
				return 0;
			}
		}
		return 1;
	}
	public int checktop(char c)
	{
		for(int i=0;i<b;i++)
		{
			if(on[i][(int)c%97]==1)
			{
				return i;
			}
		}
		return -1;
	}
	public void perform(String s)
	{
		String t[]=s.split("[() ]+");
		if(t[1].equals("stack"))
		{
			on[(int)t[2].charAt(0)%97][(int)t[3].charAt(0)%97]=1;
			clear[(int)t[2].charAt(0)%97]=1;
			clear[(int)t[3].charAt(0)%97]=0;
			ontable[(int)t[2].charAt(0)%97]=0;
		}
		else if(t[1].equals("unstack"))
		{
			ontable[(int)t[2].charAt(0)%97]=1;
			clear[(int)t[2].charAt(0)%97]=1;
			clear[(int)t[3].charAt(0)%97]=1;
			on[(int)t[2].charAt(0)%97][(int)t[3].charAt(0)%97]=0;
		}
		else
		{
			System.out.println("perform "+s);
		}	
	}	
}
