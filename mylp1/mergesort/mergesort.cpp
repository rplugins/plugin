#include <iostream>
#include <cstdlib>
#define n 1000000
using namespace std;


void merge(int a[],int s,int mid,int end)
{
	int s2=mid+1;
	int c=s;
	int temp[n];
	//cout<<" s:"<<s<<" m:"<<mid<<" e:"<<end<<" ";
	int i=0;
	while(s<mid+1 && s2<end+1 )
	{
		if(a[s]>a[s2])
		{
			temp[i]=a[s2];
			s2++;
			i++;
		}
		else
		{
			temp[i]=a[s];
			s++;
			i++;
		}
	}
	while(s<mid+1)
	{
		temp[i]=a[s];
		s++;
		i++;
	}
	while(s2<end+1)
	{
		temp[i]=a[s2];
		s2++;
		i++;
	}
	//cout<<i<<" s:"<<s<<" s2:"<<s2<<endl;
	for(int j=0;j<i;j++)
	{
		a[c+j]=temp[j];
		//cout<<temp[j]<<"  ";
	}
	//cout<<endl;
}

void mergesort(int a[],int s,int e)
{
	if(s<e)
	{
		int mid=(s+e)/2;
		#pragma omp parallel sections
		{
			#pragma omp section
			{	
				mergesort(a,mid+1,e);
			}
			#pragma omp section
			{
				mergesort(a,s,mid);
			}
		}
		merge(a,s,mid,e);
	}
	
}

int main()
{
	int a[n];
	srand(clock());
	for(int i=0;i<n;i++)
	{
		a[i]=rand()%n;
	}
	clock_t start=clock();
	mergesort(a,0,n-1);
	clock_t stop=clock();
	
	for(int i=0;i<n;i++)
	{
		cout<<i+1<<" "<<a[i]<<endl;
	}
	cout<<"time: "<<stop-start<<endl;
}
