## 描述

给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）

以这种方式修改数组后，返回数组可能的最大和

## CPP
1. 贪婪选择：每次更改最负的；假如没有负的，就更改绝对值最小的
2. 实现方法，有排序一次，然后一边改，一边比较
3. 动态维护一个数组，每次都是直接弹出最小最小的（KlogN）的优先列


```cpp
class Solution {
public:
    int largestSumAfterKNegations(vector<int>& A, int K) {
        sort(A.begin(),A.end());
        int n = A.size();
        int i = 0;
        while(K>0){
            if(A[i]<0){
                A[i] = -A[i];
                K--;
                if(i+1<n && A[i+1]<=0){
                    i++;
                }else if(i+1<n && A[i+1]>=0 && A[i+1]<A[i]){
                    i++;
                }
            }else{
                A[i]=-A[i];
                K--;
            }

        }
        int res = 0;
        for(int i = 0;i<n;i++){
            res +=A[i];
        }
        return res;

    }
};
```