## 描述
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。

例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。

给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

## CPP

1. 动归的核心1：
    1. 描述问题：a[i] ：以 nums[i] 及其之前元素结尾的最长摇摆序列
    2. 选择：（增加一个元素，不会减少已有序列的长度）up[i] 是以nums[i]上升结尾的最长序列，down[i] 反之
    3. 子问题以及父问题如何依赖子问题：
        a. 需要比较 nums[i] > nums[i-1] (末尾上升)
        b. 比较好理解的是：末位上升，则 up[i] = down[i-1] +1
        c. 比较骚气的是：末尾上升， down[i] = down[i-1], 因为任何一个以 nums[i] 结尾的下降序列，必能以 nums[i-1] 结尾，而且有相同效果

```cpp
class Solution {
public:
    int wiggleMaxLength(vector<int>& nums) {
        int n = nums.size();
        if(n==0)return 0;
        if(n==1)return 1;
        vector<int> down(n);
        vector<int> up(n);
        down[0] = up[0] = 1;
        for(int i =1;i<n;i++){
            if(nums[i]>nums[i-1]){
                up[i] = down[i-1]+1;
                down[i] = down[i-1];
            }else if(nums[i]<nums[i-1]){
                up[i] = up[i-1];
                down[i] = up[i-1] +1;
            }else{
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return max(down[n-1],up[n-1]);

    }
};
```