# Selection Sort

1. 很简单，就是提一下这个概念，从 i = 0 到 n-1， j = i+1 到n
2. 特征：交换位置只发生：n-1次！

# Code

```cpp
#include <vector>
using namespace std;

vector<int> insertionSort(vector<int> array) {
  // Write your code here.
	for(int i =0;i<array.size()-1;i++){
		int min_index = i;
		for(int j = i+1;j<array.size();j++){
			if(array[j] < array[min_index]){
				min_index = j;
			}
		}
		int tmp = array[i];
		array[i] = array[min_index];
		array[min_index] = tmp;
	}
  return array;
}

```