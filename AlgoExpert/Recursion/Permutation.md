# Permutation
1. 顾名思义，输入一个数组，然后输出它所有的permutation的可能

# 核心思想
1. 每一个permutation 都是由，array[i] + array剩下的数字的permutation组成
2. 但是光想到这里还没有recursion的反复使用同一种操作：剩下的数组也是 array2[i] + array2剩下的数字的permutation
3. Time O(N^2 N!) Space O(N*N!)

```cpp
#include <vector>
using namespace std;


void permutation(vector<int> vec,vector<int> perm,vector<vector<int>> *perms){
	if(vec.size() == 0 && perm.size()>0){
		perms->push_back(perm);
	}else{
		for(int i = 0;i<vec.size();i++){
			vector<int> newvec;
			newvec.insert(newvec.end(),vec.begin(),vec.begin()+i);  ## 这两步和我直接用erase好，因为你需要新的array，不能影响到先前的
			newvec.insert(newvec.end(),vec.begin()+i+1,vec.end());  ## 
			vector<int> newPerm = perm;
			newPerm.push_back(vec[i]);
			permutation(newvec,newPerm,perms);
		}
	}
	
}
vector<vector<int>> getPermutations(vector<int> array) {
  // Write your code here.
	vector<vector<int>> permutations;
	permutation(array,{},&permutations);
  return permutations;
}
```

# 另一种思想
1. 如何在不创建新array的前提下，在原有array下直接做permutation
2. **轮流让 i 和 i 之后的元素，担任第i位，然后i++**

```cpp

void permutationhelper(int i,vector<int> array,vector<vector<int>>* perms){
    for(int j = i;j<array.size();j++){
        swap(array[i],array[j]);
        permutationhelper(i+1,array,perms);
        swap(array[i],array[j]);
    }
}
```
