## 描述
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5


## CPP

1. 经典的Divide？：但是用了更优秀的选pivot才能是divide吧？
2. pivot value + quasi sort + 问题reduce 到 k-nl-1
```cpp
class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        if(k==0){
            return nums[0];
        }
        if(nums.size()==1){
            return nums[0];
        }
        int index = nums.size()/2;
        int tmp = nums[index];
        vector<int> right;
        vector<int> left;
        for(int i =0;i<index;i++){
            if(nums[i]>tmp){
                left.push_back(nums[i]);
            }else{
                right.push_back(nums[i]);
            }
        }
        for(int i = index+1;i<nums.size();i++){
            if(nums[i]>tmp){
                left.push_back(nums[i]);
            }else{
                right.push_back(nums[i]);
            }
        }
        int nl = left.size();
        if(nl==k-1){
            return tmp;
        }
        if(nl>=k){
            return findKthLargest(left, k);
        }else{
            return findKthLargest(right, k-nl-1);
        }
        

    }
};
```