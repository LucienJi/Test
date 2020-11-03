## 描述

给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。

示例:

输入: [1,2,3,4,5]
输出: [120,60,40,30,24]

## CPP

1. 动态规划的思想总是用已经计算过的值来减少总体计算量
2. B中任何一个元素 = 其左边所有元素的乘积 * 其右边所有元素的乘积。
    > 一轮循环构建左边的 累乘积 并保存在B中，
    > 二轮循环 构建右边 累乘积 的过程，乘以B原先的积，并将最终结果保存

```cpp
class Solution {
public:
    vector<int> constructArr(vector<int>& a) {
        if(a.size()==0){
            vector<int> b;
            return b;
        }
        if(a.size()==1){
            vector<int> b ={a[0]};
            return b;
        }
        vector<int> b(a.size(),1);
        b[0] = 1;
        int tmp = 1;
        for(int i =0;i<a.size()-1;i++){
            tmp *= a[i];
            b[i+1] *= tmp;
        }
        tmp = 1;
        for(int i = a.size()-1;i>0;i--){
            tmp *=a[i];
            b[i-1] *=tmp;
        }
        return b;

    }
};
```

