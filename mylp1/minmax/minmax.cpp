#include <iostream>
#include <omp.h>
#include <cstdlib>
#define n 10
using namespace std;

double average(double a,double b)
{
	return a+b;
}
int main()
{
	int a[n];
	for(int i=0;i<n;i++)
	{
		a[i]=rand()%n;
	}
	
	int sum=0;
	for(int i=0;i<n;i++)
	{
		sum+=a[i];
	}
	
	int min=999999;
	for(int i=0;i<n;i++)
	{
		if(min>a[i])
		{
			min=a[i];
		}
	}
	
	int max=-1;
	for(int i=0;i<n;i++)
	{
		if(max<a[i])
		{
			max=a[i];
		}
	}
	
	double avg=0;
	for(int i=0;i<n;i++)
	{
		avg+=(double)a[i]/n;
	}
	
	int sump=0;
	#pragma omp parallel for reduction(+:sump)
	for(int i=0;i<n;i++)
	{
		sump+=a[i];
	}
	
	int minp=999999;
	#pragma omp parallel for reduction(min:minp)
	for(int i=0;i<n;i++)
	{
		if(minp>a[i])
		{
			minp=a[i];
		}
	}
	
	
	int maxp=-1;
	#pragma omp parallel for reduction(max:maxp)
	for(int i=0;i<n;i++)
	{
		if(maxp<a[i])
		{
			maxp=a[i];
		}
	}
	
	double avgp=0.0;
	#pragma omp declare reduction(avg:double:omp_out=average(omp_out,omp_in))
	#pragma omp parallel for reduction(avg:avgp)
	for(int i=0;i<n;i++)
	{
		avgp=average(avgp,(double)a[i]/n);
	}
	cout<<"sum "<<sum<<":"<<sump<<endl;
	cout<<"max "<<max<<":"<<maxp<<endl;
	cout<<"min "<<min<<":"<<minp<<endl;
	cout<<"avg "<<avg<<":"<<avgp<<endl;	
}
