#include <iostream>
#include <omp.h>
#include <time.h>
#define N 100
using namespace std;
int main()
{
	int a[N],b[N],c[N];
	for(int i=0;i<N;i++)
	{
		a[i]=i;
		b[i]=i;
	}
	clock_t t1=clock();
	#pragma omp parallel for
	for(int i=0;i<N;i++)
	{
		c[i]=a[i]+b[i];
	}
	clock_t t2=clock();
		
	for(int i=0;i<N;i++)
	{
		cout<<c[i]<<endl;
	}
	cout<<"   time "<<t2-t1;
}
