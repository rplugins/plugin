#define n 10
#include <iostream>
#include <cstdlib>
#include <omp.h>
using namespace std;
int binary(int a[],int start,int end,int key)
{
	int mid=(start+end)/2;
	int s1,s2,e1,e2,m1,m2;
	s1=start;
	s2=mid+1;
	e1=mid;
	e2=end;
	int loc=-1;
	#pragma omp parallel sections
	{
	
	#pragma omp section
	{
	while(s1<=e1)
	{
		if(key<a[s1]&&key>a[e1])
		{
			s1=s1+e1;
			continue;
		}
		m1=(s1+e1)/2;
		if(a[m1]==key)
		{
			loc=m1;
			break;
		}
		else if(a[m1]>key)
		{
			e1=m1-1;
			continue;
		}
		else
		{
			s1=m1+1;
		}
	}
	
	}
	#pragma omp section
	{
	while(s2<=e2)
	{
		if(key<a[s2]&&key>a[e2])
		{
			s2=s2+e2;
			continue;
		}
		m2=(s2+e2)/2;
		if(a[m2]==key)
		{
			loc=m2;
			break;
		}
		else if(a[m2]>key)
		{
			e2=m2-1;
			continue;
		}
		else
		{
			s2=m2+1;
		}
	}
	}
	}
	return loc;
}
int main()
{
	int a[n];
	int key;
	for(int i=0;i<n;i++)
	{
		cout<<"enter "<<i<<" element of array :";
		cin>>a[i];
	}
	
	cout<<"enter the number to search : ";
	cin>>key;
	
	cout<<binary(a,0,n-1,key)<<endl;
	
	cout<<"enter the number to search : ";
	cin>>key;
	
	cout<<binary(a,0,n-1,key)<<endl;
}
