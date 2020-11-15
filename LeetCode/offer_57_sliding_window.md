## 描述

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
示例 2：

输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]

## CPP

1. 由于单调，就从两边朝中间缩小，通过和目标大小的比较，来决定窗口缩小的方向（隐藏了一个证明，消去状态其实是安全的）
2. 对撞双指针

```cpp
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        if(nums.size()==1){
            vector<int> res;
            return res;
        }
        int i = 0;
        int j = nums.size()-1;
        int tmp;
        vector<int> res;

        while(i<j){
            tmp = nums[i]+nums[j];
            if(tmp==target){
                res.push_back(nums[i]);
                res.push_back(nums[j]);
                return res;
            }else if(tmp<target){
                i++;
            }else{
                j--;
            }
        }
        return res;

    }
};
```