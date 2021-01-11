## 描述

输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

 

示例 1：

输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
示例 2：

输入：arr = [0,1,2,1], k = 1
输出：[0]

## CPP

1. 巨简单操作：优先堆，quasi-sorted
```cpp
class Solution {
public:
    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        priority_queue<int> q;
        vector<int> res;
        if(k==arr.size()) return arr;
        if(k==0) return res;
        
        for(int i = 0;i<arr.size();i++){
            if(q.size()<k){
                q.push(arr[i]);
            }else{
                if(q.top()>arr[i]){
                    q.pop();
                    q.push(arr[i]);
                }
            }
        }
        while(!q.empty()){
            res.push_back(q.top());
            q.pop();
        }
        return res;

    }
};
```