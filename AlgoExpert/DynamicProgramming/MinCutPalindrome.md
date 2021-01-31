# Palindrome Partitioning Min Cuts

1. 问题描述：给出一个string，问最少割几下，可以将这个string分割成是palindrome 的substring
2. 举例：
    1. Input: `noonabbad`
    2. Output: 2 cuz: `noon|abba|d`

# 核心思想，精彩好题

1. 首先substring的palindrome问题，肯定可以再用DP利用 n*n 表格在O(N^2)内求出来，老DP了
```
palindrome[i][i] ==true
palindrome[i][j] == palindrome[i+1][j-1] && string[i]==string[j]

```
2. 如何用bottom-up的写法实现这个DP？

**常用的遍历的操作**：起源是 palindrome[i][i],特征是长度为1，遍历长度
```
for(int length = 2;length < string.length()+1;length++){
    for(int i = 0;i + length -1 < string.length();i++){
        DP_transfer
    }
}
```


3. 假设你有所有substring的信息，怎么去求min cut？ DP 有用吗？其实还是暴力的
    > 从考虑 string[0:i] 到string[0:i+1] 有没有依赖关系？

    > 假如palindrome[0][i+1] = true，那么cuts[i+1] = 0

    > 假如不是，那么 cuts[i+1] = cuts[i] + 1 ? 单独把 i + 1 这个字符切出来？然后单独考虑 前 i 个？

    > 假如 string[O:i+1] 不构成 palindrome，那么得到， i+1 不在 以 0 开头的palindrome里，但是还有可能在以j开头的 palindrome里

    >  cuts[i+1] = cuts[j-1] + 1 (palindrome[j][i] = true)

# Code

```cpp
#include <vector>
using namespace std;

int palindromePartitioningMinCuts(string string) {
  // Write your code here.
	vector<vector<bool>> palindromes(string.length(),vector<bool>(string.length(),false));
	for(int i = 0;i<string.length();i++){
		palindromes[i][i] = true;
	}
	
	for(int length = 2;length <= string.length();length++){
		for(int i = 0;i<string.length()-length + 1;i++){
			int j = i+length-1;
			if(length == 2){
				palindromes[i][j] = (string[i]==string[j]);
			}else{
			palindromes[i][j] = (string[i]==string[j]) && palindromes[i+1][j-1];}
		}
	}
	vector<int> cuts(string.length(),INT_MAX);
	for(int i = 0;i<string.length();i++){
		if(palindromes[0][i]){
			cuts[i] = 0;
		}else{
			cuts[i] = cuts[i-1] + 1;
			for(int j = 1;j<i;j++){
				if(palindromes[j][i] && (cuts[j-1] + 1 < cuts[i])){
					cuts[i] = cuts[j-1] + 1;
				}
			}
		}
	}
	
	return cuts[string.length()-1];
}
```
    