## 描述
硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)

示例1:

 输入: n = 5
 输出：2
 解释: 有两种方式可以凑成总金额:
5=5
5=1+1+1+1+1
示例2:

 输入: n = 10
 输出：4
 解释: 有四种方式可以凑成总金额:
10=10
10=5+5
10=5+1+1+1+1+1
10=1+1+1+1+1+1+1+1+1+1

## CPP
1. DP :转移方程
> 和切棒子类似，切棒子是第一刀切多少
> 这不能是第一元怎么分，而是使用什么钱币
> coins = {0,25,10,5,1}
> f(i,c) = f(i-1,c) + f(i,c-coins[i])
> 使用 i 种面值去实现 c 的表示法 = 只用 i-1 种面值去实现 c 的表示法 + 依旧使用 i 种面值，但是第 i 类面值用了一张
> 矩阵的初始化很重要

```cpp
class Solution {
private:
    static constexpr int mod = 1000000007;
    static constexpr int coins[5] = {0,25,10,5,1};
public:
    int waysToChange(int n) {
        int res[5][n+1];
        for(int i = 0;i<=n;i++){
            res[0][i] = 0;
        }
        for(int j = 1 ;j<5;j++){
            res[j][0] = 1;
        }
        for(int i = 1;i<5;i++){
            for(int j = 1;j<=n;j++){
                if(coins[i]<=j){
                    res[i][j] = (res[i-1][j]+res[i][j-coins[i]])%mod;
                }else{
                    res[i][j] = res[i-1][j];
                }
            }
        }
        return res[4][n];
    }
};

```