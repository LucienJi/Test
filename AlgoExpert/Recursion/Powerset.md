# Powerset
1. 给定一个数集，给出它所有子集的集合
2. 给{1,2,3} , 输出 {{},{1},{1,2},{1,3},{2,3},{2},{3},{1,2,3}}

# 核心思想
1. 递归的思想，还是假设你有一个你暂时还没有的，确实很像DP
2. 查看 {1,2,3} 的powerset 时，等价于，在所有 {1,2}的powerset 的基础上，逐一加上{3},再加上这些集合

# Code
1. Recursion 版
2. 记得辅助函数需要提前申明
3. O(N*2^N)

```cpp
#include <vector>
using namespace std;

vector<vector<int>> powersethelper(vector<int> array , int idx);

vector<vector<int>> powerset(vector<int> array) {
  // Write your code here.
	
  return powersethelper(array,array.size()-1);
}

vector<vector<int>> powersethelper(vector<int> array, int idx){
	if(idx < 0){
		return vector<vector<int>> {{}};
	}
	int added_element = array[idx];
	vector<vector<int>> prev_subset = powersethelper(array,idx-1);
	int n = prev_subset.size();
	for(int i = 0;i<n;i++){
		vector<int> tmpvec = prev_subset[i];
		vector<int> tobe_added = tmpvec;
		tobe_added.push_back(added_element);
		prev_subset.push_back(tobe_added);
		
	}
	return prev_subset;
	
}
```

