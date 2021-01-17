# Two Number Sum
1. 乍一看简单题
2. 给一个数列，一个target，目标是找到数列里面可以相加等于target的两个

# 核心思想

1. 方法一：第一反应，排个序，然后滑窗缩小范围 O(NlgN)
2. 方法二：确实没有想到O(N)的方法，主要是很少会想到用 hashmap来存储数据，而且更加厉害的是，只用一边循环，不用先存target-num，再找num
假如 a + b = target, a 在 b 之前。 遇到a，你访问 target - a，答案是没有，因为b还没有被遇到，但是没关系，存下a，下次target - b的时候就有了（查对偶，放自己） == (查自己，放对偶)

# Code

1. Hashmap
```cpp
#include <vector>
using namespace std;

vector<int> twoNumberSum(vector<int> array, int targetSum) {
  // Write your code here.
	
	unordered_set<int> nums;
	for(int num:array){
		int potential = targetSum - num;
		if(nums.find(potential)!=nums.end()){
			return vector<int>{potential,num};
		}else{
			nums.insert(num);
		}
	}
  return {};
}

```
2. Sort
```cpp
#include <vector>
using namespace std;

vector<int> twoNumberSum(vector<int> array, int targetSum) {
  // Write your code here.
	sort(array.begin(),array.end());
	int left = 0;
	int right = array.size()-1;
	while(left<right){
		if(array[left]+array[right] < targetSum){
			left++;
		}else if(array[left]+array[right] > targetSum){
			right--;
		}else{
			return vector<int>{array[left],array[right]};
		}
	}
	
  return {};
}
```
