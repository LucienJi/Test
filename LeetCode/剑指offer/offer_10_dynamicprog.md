## 描述
一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：

输入：n = 2
输出：2
示例 2：

输入：n = 7
输出：21

## CPP

1. 凡是用递归的，，，麻烦用多年数学知识思考一下，复杂度exp啊

2. 原因出在recursif反复计算已经算过的内容
- 因此可行的方法是：采用列表记录你己经算过的
- 记忆递归

3. 用动态规划的思想，，去解决0，1，2，3。。。。

```cpp

class Solution {
public:

    //int helper(int n){
    //    if(n==0)return 1;
    //    if(n==1)return 1;
    //    if(n==2)return 2;
    //    return (helper(n-2)+helper(n-1));
   //}
    
    int numWays(int n) {
        if(n==0)return 1;
        if(n==1)return 1;
        if(n==2)return 2;
        int res;
        int first = 1;
        int second = 2;
        while(n>2){
            res = (first + second)%1000000007;
            first = second;
            second = res;
            n--;
        }
        return res;


    }
};
```
