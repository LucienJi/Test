## 描述

我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

 

示例:

输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
说明:  

1 是丑数。
n 不超过1690。

## CPP

1. 动态规划：Xn = min(Xa*2,Xb*3,Xc*5)
> Xa,Xb,Xc 初始状态在0
> 转移方程是：在更新Xn后，假如 Xa * 2 <= Xn ,那么Xa*2再也无法产生比Xn大的数字了，Xa指针向右
> 终止条件：vector size == n

```cpp
class Solution {
public:
    int min(int a,int b,int c){
        int min;
        if(a<b){
            min = a;
        }else{
            min = b;
        }
        if(min < c){
            return min;
        }else{
            return c;
        }
    }
    int nthUglyNumber(int n) {
        if(n==1){
            return 1;
        }
        vector<int> res;
        int num;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        res.push_back(1);
        while(res.size()<n){
            num = this->min(res[p2]*2,res[p3]*3,res[p5]*5);
            res.push_back(num);
            if(res[p2]*2<=num) p2++;
            if(res[p3]*3<=num) p3++;
            if(res[p5]*5<=num) p5++;
        }

        return res[res.size()-1];
        


    }
};
```