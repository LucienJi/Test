## 描述

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

 

示例：

输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。

##

1. 类似快排的思想（按照target，区分大于target和小于target的数），双指针前后向中间移动，卡主并互换位置。
2. 要注意全是奇数或者全是偶数的情况

```cpp
class Solution {
public:
    vector<int> exchange(vector<int>& nums) {
        if(nums.size()==1){
            return nums;
        }
        int i = 0 ;
        int j = nums.size()-1;
        int tmp;
        while(j>i){
            while(nums[i]%2 == 1 && i<j){
                i++;
            }
            while(nums[j]%2==0 && i<j){
                j--;
            }
            if(i==j)return nums;
            tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
            i++;
            j--;

        }
        return nums;

    }
};
```