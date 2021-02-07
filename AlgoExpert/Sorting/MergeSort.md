# Merge Sort

1. 顾名思义，归并排序
2. 无论练几次，总能报错

# 核心思想 + Implementation Trick

1. 分治法： 假设先排 (i,mid) 和 (mid+1,j),然后再让他们合并
2. 在mergesort时，什么是 base case ？
    > 可以是 j = i+1，多做一次判断

    > 但是，也可以是 i = j

    > 在merge的时候，已经会有比较了

3. 如何使用辅助数列？

```

merge(mainarray, helperarray,i,j) 在调用时，应该divide conquer时，应使用

merge(helperarray,mainarray,i,mid)
merge(helperarray,mainarray,mid+1,j) = 所以前面排序的是helperarray

domerge(array,helperarray,i,j) = 相当于将 helperarray 放进 array内

```
# Code

```cpp
#include <vector>
using namespace std;
void mergesorthelper(vector<int> *array, vector<int> *arrayhelper,int i,int j);
void merge(vector<int> *array,vector<int> *arrayhelper,int left,int right);
vector<int> mergeSort(vector<int> array) {
  // Write your code here.
	if(array.size()<=1){
		return array;
	}
	vector<int> helper = array;
	mergesorthelper(&array,&helper,0,array.size()-1);
	
  return array;
}

void mergesorthelper(vector<int> *array, vector<int> *arrayhelper,int i,int j){
	if(i == j){
		return;
	}
	
	int mid = (i+j)/2;
	mergesorthelper(arrayhelper,array,i,mid);
	mergesorthelper(arrayhelper,array,mid+1,j);
	merge(array,arrayhelper,i,j);
	return;
}

void merge(vector<int> *array,vector<int> *arrayhelper,int left,int right){
	int mid =  (right + left)/2;
	int i = left;
	int j = mid + 1;
	int k = left;
	
	while(i<= mid && j <= right){
		if(arrayhelper->at(i) <= arrayhelper->at(j)){
			array->at(k++) = arrayhelper->at(i++);
		}else{
			array->at(k++) = arrayhelper->at(j++);
		}
	}
	while(i<=mid){
		array->at(k++) = arrayhelper->at(i++);
	}
	while(j<=right){
		array->at(k++) = arrayhelper->at(j++);
	}
	
}

```