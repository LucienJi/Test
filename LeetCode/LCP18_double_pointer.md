## 题目描述
一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1


## C++

```cpp
class Solution {
public:
    int breakfastNumber(vector<int>& staple, vector<int>& drinks, int x) {
        const int mod = 1e9 + 7;
        int res = 0;
        sort(staple.begin(), staple.end());                 // 必须排序，不然超时
        sort(drinks.begin(), drinks.end());
        int j = drinks.size() - 1;                          // 倒序，j 从大到小计数
        for (int i = 0; i < staple.size(); i++) {
            while (j >= 0 && staple[i] + drinks[j] > x) j--;    // 排除所有相加 大于 x的
            if (j == -1) break;
            res += j + 1;                                   // 一次性加上 剩余的i
            res %= mod;      //myt：这个取余可以拿出来？
        }
        
        return res;
    }
};

```
## Java

```java
class Solution {
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int cnt=0;
        int m=staple.length,n=drinks.length;
        int i=0,j=n-1;
        while(i<m&&j>=0){
            if(staple[i]+drinks[j]<=x){
                cnt=(cnt+j+1)%1000000007;
                i++;
            }else{
                j--;
            }
        }
        return cnt%1000000007;
    }
}

```

## 分析一下
1. 这是 双指针的 思想，假如暴力计算 复杂度在 `O(nm)`,  myt：为什么要叫双指针  感觉这个思想没有很高深啊
2. 但是现在这样的话，，可能可以压缩到 `O(n)` ?   myt：最优情况是 O（n）
3. 但是最坏情况就是 每一个数都大于x，必须全部遍历 nm次    myt：是的
