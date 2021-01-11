## 描述

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

 

示例 1:

输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

## CPP

1. 保留当前的最小的价格和最大的收益
2. 收益 转移的条件是 当前价格与记录的最小价格之差大于最大收益
3. 最小价格随便转移
4. 
> 状态定义： 设动态规划列表 dp，dp[i] 代表以 prices[i] 为结尾的子数组的最大利润（以下简称为 前 i 日的最大利润 ）。
> 转移方程： 由于题目限定 “买卖该股票一次” ，因此前 i 日最大利润 dp[i] 等于前 i - 1 日最大利润 dp[i-1]dp[i−1] 和第 ii 日卖出的最大利润中的最大值。
前 i 日最大利润 = \max(前 (i-1) 日最大利润, 第 i 日价格 - 前 i 日最低价格)
*前i日最大利润=max(前(i−1)日最大利润,第i日价格−前i日最低价格)*

dp[i] = max(dp[i - 1], prices[i] - min(prices[0:i]))

初始状态： dp[0] = 0dp[0]=0 ，即首日利润为 00 ；
返回值： dp[n - 1]dp[n−1] ，其中 nn 为 dpdp 列表长度。


```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0;
        int min = 0;
        int l = prices.size();
        if(l==0){
            return 0;
        }
        for(int i = 0;i<l;i++){
            if(prices[i]>=prices[min]){
                if(prices[i]-prices[min] > res){
                    res = prices[i]-prices[min];
                }
            }else{
                min = i;
            }
        }

        return res;

    }
};
```