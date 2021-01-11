## 描述

从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

 

示例 1:

输入: [1,2,3,4,5]
输出: True
 

示例 2:

输入: [0,0,1,2,5]
输出: True

## CPP

```cpp
class Solution {
public:
    bool isStraight(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        int zeros = 0;
        int pre = 0;
        int next;
        for(int i = 0;i<nums.size();i++){
            if(nums[i]==0){
                zeros++;
            }else{
                break;
            }
        }
        if(zeros>=4) return true;
        for(int i = zeros;i<nums.size()-1;i++){
            pre = nums[i];
            next = nums[i+1];
            if((next==pre)||((next-pre-1)>zeros)){
                return false;
            }
            zeros-=next-pre-1;
        }
        return true;

    }
};
```
