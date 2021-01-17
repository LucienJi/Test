# BinarySearching
1. 顾名思义：二分搜索，看到什么问题可以logN，很有可能就是利用二分
2. 还有经典的 指数搜索O(log(L))

# 核心思想
1. 对于quasi sorted的数，每次利用中值都能消除一般的可能

# Code

1. 必须使用 `i<=j` ,否则无法发现 target 在边界上的情况
```cpp
#include <vector>
using namespace std;

int binarySearch(vector<int> array, int target) {
  // Write your code here.
	
	int i = 0;
	int j = array.size();
	int mid;
	if(j==0)return -1;
	
	while(i<=j){
		mid = i+(j-i)/2;
		if(array[mid]<target){
			i = mid+1;
		}else if(array[mid]>target){
			j = mid-1;
		}else{
			return mid;
		}
	
	}
  return -1;
}
```