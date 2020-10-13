## 描述
数组中重复的数字
找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

示例 1：

输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3

## CPP ： 这是一个这要想起来Hashmap就可以做出来的

```cpp
class Solution {
public:
    int findRepeatNumber(vector<int>& nums) {

        unordered_map<int,bool> dic;
        for(int i = 0;i<nums.size();i++){
            if(dic[nums[i]]) return nums[i];
            else{
                dic[nums[i]] = true;
            }
        }
        return -1;

    }
};
```