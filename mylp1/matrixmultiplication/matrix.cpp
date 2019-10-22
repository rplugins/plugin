#include <iostream>
#include <cstdlib>
#include <omp.h>
#define n 10
using namespace std;
int main()
{
	int m[n][n];
	int m2[n][n];
	int a[n][n];
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			m[i][j]=1;
			m2[i][j]=2;
			a[i][j]=0;
		}
	}
	
	#pragma omp parallel for
	for(int i=0;i<n;i++)
	{
		#pragma omp parallel for
		for(int j=0;j<n;j++)
		{
			for(int k=0;k<n;k++)
			{
				a[i][j]=a[i][j]+(m[i][k]*m2[j][k]);
			}
		}
	}
	
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			cout<<a[i][j]<<" ";
		}
		cout<<endl;
	}
	return 0;
}
