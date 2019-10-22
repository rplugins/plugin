import java.util.*;

class crypt
{
    static String s1,s2,s3;
    static ArrayList<Character> uchar=new ArrayList<>();
    static ArrayList<ArrayList<Integer>> perm=new ArrayList<>();
    static int no[]={0,1,2,3,4,5,6,7,8,9};
    public static void main(String str[])
    {
        System.out.println("Crypt");

        s1="send";
        s2="more";
        s3="money";

        addtolist(s1);
        addtolist(s2);
        addtolist(s3);


        for(int i=0;i<uchar.size();i++)
        {
            System.out.println(uchar.get(i));
        }

        System.out.println(uchar.size());
        permute(0);
        System.out.println(perm.size());

        calculate();
    }

    public static void calculate()
    {
        for(int i=0;i<perm.size();i++)
        {
            HashMap<Character,Integer> hm=new HashMap<>();
            for(int j=0;j<uchar.size();j++)
            {
            	hm.put(uchar.get(j),perm.get(i).get(j));
	    	}
	    	
	    	int no1=findint(s1,hm);
	    	int no2=findint(s2,hm);
	    	int no3=findint(s3,hm);
	    	
	    	//System.out.println(no1+"+"+no2+"="+no3);
	    	if((no1+no2)==no3 && s1.length()==String.valueOf(no1).length() && s2.length()==String.valueOf(no2).length())
	    	{
	    		System.out.println(no1+"+"+no2+"="+no3);
	    		return;
			}
		
        }
    }
    public static int findint(String s,HashMap<Character,Integer> hm)
    {
    	String temp="";
    	for(int i=0;i<s.length();i++)
    	{
    		temp=temp+hm.get(s.charAt(i));
    	}
    	return Integer.parseInt(temp);
	}
    public static void permute(int size)
    {
        if(size==no.length)
        {
            ArrayList <Integer>al=new ArrayList<>();
            for(int i=0;i<no.length;i++)
            {
                al.add(no[i]);
            }
            perm.add(al);
        }
        else if(size<no.length)
        {
            for(int i=size;i<no.length;i++)
            {
                int temp= no[size];
                no[size]=no[i];
                no[i]=temp;
                permute(size + 1);
                temp= no[size];
                no[size]=no[i];
                no[i]=temp;
            }
        }
    }
    public static void addtolist(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            if(isunique(str.charAt(i))==0)
            {
                uchar.add(str.charAt(i));
            }
        }
    }
    public static int isunique(Character c)
    {
        for(int i=0;i<uchar.size();i++)
        {
            if(uchar.get(i)==c)
            {
                return 1;
            }
        }
        return 0;
    }
}
