#include <iostream>
#include <omp.h>
#include <cstdlib>
#define N 1000
using namespace std;

int main()
{
	int a[N];
	
	srand(clock());
	for(int i=0;i<N;i++)
	{
		a[i]=rand()%N;
	}
	
	for(int i=0;i<N;i++)
	{
		cout<<a[i]<<endl;
	}
	cout<<endl;
	
	for(int i=0;i<N;i++)
	{
		int first=i%2;
		#pragma parellel omp for shared(a,first)
		for(int j=first+1;j<N;j+=2)
		{
			if(a[j]<a[j-1])
			{
				int temp=a[j];
				a[j]=a[j-1];
				a[j-1]=temp;
			}
		}
	}
	
	for(int i=0;i<N;i++)
	{
		cout<<a[i]<<endl;
	}	
}
