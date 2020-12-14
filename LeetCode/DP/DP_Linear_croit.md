## 描述
给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。

## CPP


1. 单串 dp[i] 线性动态规划最简单的一类问题，输入是一个串，状态一般定义为 dp[i] := 考虑[0..i]上，原问题的解，其中 i 位置的处理，根据不同的问题，主要有两种方式：

> 第一种是 i 位置必须取，此时状态可以进一步描述为 dp[i] := 考虑[0..i]上，且取 i，原问题的解；
> 第二种是 i 位置可以取可以不取(根据条件判断，比如这题)

2. 核心转移方程
dp[i]=max j(dp[j])+1

```cpp
class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        int n = nums.size();
        if(n==0)return 0;

        vector<int> res(n);
        for(int i =0;i<n;i++){
            res[i] = 1;
        }
        int ans = 1;
        for(int i = 1;i<n;i++){
            for(int j = 0;j<i;j++){
                if(nums[i]>nums[j]){
                    res[i] = max(res[i],res[j]+1);
                    if(res[i]>=ans){
                        ans = res[i];
                    }
                }
            }
        }
        return ans;

    }
};
```



