# Max Subset Sum No adjacent
1. 输入一个数列
2. 求一个 子数列，要求子数列不相邻并且和最大

# 核心思想

1. 简单的 DP，找个转移方程
```cpp
// 其中，f[i] 表示 从 i 开始的这个问题的解
f[i] = max(array[i] + f[i+2], f[i+1])
f[n] = array[n]
f[n-1] = array[n-1]
```

# Code

1. Time O(N), Space O(N)

```cpp
#include <vector>
using namespace std;

int maxSubsetSumNoAdjacent(vector<int> array) {
  // Write your code here.
	int n = array.size();
	if(n==0)return 0;
	if(n==1)return array[0];
	if(n==2)return max(array[0],array[1]);
	int res[n];
	res[n-1] = array[n-1];
	res[n-2] = max(array[n-1],array[n-2]);
	
	for(int i = n-3;i>=0;i--){
		res[i] = max((array[i] + res[i+2]),res[i+1]);
	}
	
	
  return res[0];
}
```

2. Time O(N), Space O(1) : 仔细看你只用了2位

```cpp
current = max(first,second + array[i]);
second = first;
first = current;
```
