#include<iostream>
#include<omp.h>
#define n 10
using namespace std;
double average(double a,double b)
{
	return a+b;
}

int main()
{
	double a[n];
	for(int i=0;i<n;i++)
	{
		a[i]=i;
	}
	double avgp=0;
	
	#pragma omp declare reduction(avg:double:omp_out = average(omp_out,omp_in))
	#pragma omp parallel for reduction(avg:avgp)
	for(int i=0;i<n;i++)
	{
		avgp=average(avgp,(double)a[i]/n);
	}
	
	cout<<avgp<<endl;
}
