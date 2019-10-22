import java.util.*;
import java.io.*;

class vertex
{
	vertex parent;
	int idx;
	int child[];
	int height;
	int h;
	vertex(int idx,int cost,int child) {
	this.idx=idx;
	this.h = cost;
	this.child=new int[child]; 
	}
	
	int h()
	{
		return h+height;
	}
}

class astar
{
	static ArrayList <Integer> close=new ArrayList<>();
	static PriorityQueue<vertex> open=new PriorityQueue<>(1000,new Comparator<vertex>(){
		public int compare(vertex a,vertex b)
		{
			return a.h()-b.h();
		}
	});
	public static void main(String str[])
	{
		System.out.println("BFS");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number of vertices");
		int n=sc.nextInt();
		vertex vx[] = new vertex[n];
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter h and no of child of vertice "+i+"  = ");
			int h = sc.nextInt();
			int nochild=sc.nextInt();
			vx[i]=new vertex(i,h,nochild);
			for(int j=0;j<nochild;j++)
			{
				System.out.println("Enter child of vertice "+i+"  = ");
				vx[i].child[j]=sc.nextInt();
			}
		}
		
		System.out.println("Enter start and end vertice  = ");
		int start=sc.nextInt();
		int stop=sc.nextInt();
		open.add(vx[start]);
		while(open.size()!=0 && start!=stop)
		{
			vertex node=open.poll();
			start=node.idx;
			System.out.println("Cost -> "+node.h);
			close.add(node.idx);
			for(int i=0;i<node.child.length;i++)
			{
				int flag=0;
				if((!open.contains(vx[node.child[i]]))  && (!close.contains(node.child[i])) )
				{
					vx[node.child[i]].parent=node;
					vx[node.child[i]].height=node.height+1;
					open.add(vx[node.child[i]]);
				}
			}
			
			displayopen();
		}
		System.out.println("close list "+close);
		System.out.println("Root from start and end");
		displayroute(vx[stop]);
				
	}
	public static void displayroute(vertex r)
	{
		if(r.parent==null)
		{
			System.out.println(r.idx);
			return;
		}
		else
		{
			displayroute(r.parent);
			System.out.println(r.idx);
		}
	}
	public static void displayopen()
	{
		Iterator<vertex> itr = open.iterator();
		while(itr.hasNext())
		{
			vertex node=itr.next();
			System.out.println("vertex => "+node.idx+" h => "+node.h);
		}
	}
}
