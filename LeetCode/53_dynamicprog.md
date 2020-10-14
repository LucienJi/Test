## 描述
最大子序和
给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6

## CPP

```cpp
class Solution {
public:
    
    int maxSubArray(vector<int>& nums) {
        if(nums.size() == 0)return 0;

        int precedence = 0;
        int res = nums[0];
        for(auto& x:nums){
            precedence = max(precedence + x, x);
            res = max(precedence,res);
        }
        return res;

    }
};
```

## 解析

1. 用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是
max(fi)
2. 动态规划的思想：f(i) = max(f(i-1)+ai,ai) :: 从0向后传：ai的角度上看，ai不想接一个负数，所以，，要么我自己开始，要么接着用你的



