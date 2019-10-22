import time;
import multiprocessing;
import math;
from itertools import chain
points={
		0:[(2.0,1.5),(2.5,1.0),(2.0,1.8),(1.8,1.6),(2.0,1.5)],
		1:[(1.5,2.0),(1.0,2.0),(1.7,2.0),(1.8,2.0),(1.2,2.0)]
		}
distance=[]

p=(1.8,1.8)


def myfun(group):
	global points,p
	distance=[]
	for feature in points[group]:
		ed=math.sqrt(((p[0]-feature[0])**2)+((p[1]-feature[1])**2))
		distance.append((ed,group))
	return distance 
def classifypoint(p,k=3):
	global points,distance
	
	mypool=multiprocessing.Pool()
	
	distance = mypool.map(myfun,(group for group in points))
	distance = list(chain.from_iterable(distance));
	print(distance)
	distance=sorted(distance)[:k]
	print(distance)
	
	c0=0
	c1=0
	for i in distance:
		if(i[1]==0):
			c0+=1;
		else:
			c1+=1;
	if(c1>c0):
		return 1;
	else:
		return 0;
def main():
	print("KNN")
	print(classifypoint(p))


if __name__ == '__main__':
	main()
