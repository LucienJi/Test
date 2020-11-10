## 描述

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

 

示例 1：

输入：target = 9
输出：[[2,3,4],[4,5]]
示例 2：

输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]


## CPP

1. 滑动窗口的重要性质是：窗口的左边界和右边界永远只能向右移动
2. 窗内内容太小：右边界右移
3. 窗内内容太大：左边界右移
4. 从[1,]开始，直到左边界达到一定返回就退出

```cpp
class Solution {
public:
    vector<vector<int>> findContinuousSequence(int target) {
        vector<vector<int>> res;
        int i = 1;
        int j = 2;
        int sum = i;
        while(i<=target/2){
            if(sum<target){
                sum+=j;
                j++;
                
            }else if(sum > target){
                sum-=i;
                i++;
            }else{
                vector<int> tmp_res;
                for(int k = i;k<j;k++){
                    tmp_res.push_back(k);
                }
                res.push_back(tmp_res);
                sum-=i;
                i++;

            }
        }
        return res;

    }
};
```
