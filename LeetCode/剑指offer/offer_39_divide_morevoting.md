## 描述

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

 

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

 

示例 1:

输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
输出: 2


## CPP

1. 核心思想，众数 + 1，非众数-1，那么最后整个式子>0
2. 假设 i 位是众数，那么一路比下去，肯定最终是>0, 假如中途 =0了，没事，接下来的数中的众数就是全数列的众数
3. 假设第一位是众数，计数=1，假如在遍历过程中有0出现，说明之前都相互抵消了。那么剩余数组中必有众数，重复这一步。

```cpp
class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int n = nums.size();
        if(n == 1) return nums[0];

        int x = nums[0];
        int ct = 1;
        int i = 1;
        while(i<n){
            if(nums[i]==x){
                ct++;
                i++;
            }else{
                ct--;
                i++;
                if(ct==0){
                    x = nums[i];
                    i++;
                    ct =1;
                }
            }
        }
        return x;

    }
};
```