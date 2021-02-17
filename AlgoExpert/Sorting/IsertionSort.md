# Insertion Sort

1. 核心概念是：调整所有逆序对； 调整 第一个循环 i，使的array[0:i] 排序好，然后不断增加 i

# Code

```cpp
#include <vector>
using namespace std;

vector<int> selectionSort(vector<int> array) {
  // Write your code here.
	
	for(int i = 1;i<array.size();i++){
		int j = i;
		while(j>0 && array[j]<array[j-1]){
			swap(array[j],array[j-1]);
			j--;
		}
	}
	
  return array;
}

```