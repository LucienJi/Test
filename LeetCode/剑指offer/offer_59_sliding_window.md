## 描述

给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

## CPP

1. 新的数据结构耶：双向队列 deque
2. 要保证deque中只有滑窗内部的元素
3. 要保证deque中近似单调，front 大，back 小
4. front 元素担心是否会被删除，back元素担心是否比新加的元素小（因为没必要保留，只要有大的新加的元素，就轮不到他们作为最大数）

```cpp
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        if(nums.size()==0){
            vector<int> res;
            return res;
        }
        
        vector<int> res;
        deque<int> q;
        int n =nums.size();
        int left = 1-k;
        int right = 0;
        int tmp;
        while(right<k){
            tmp = nums[right];
            while(!q.empty() && q.back()<tmp){
                q.pop_back();
            }
            q.push_back(tmp);
            right++;
            left++;
        }
        res.push_back(q.front());
        while(right < n){
            tmp = nums[right];
            if(q.front()==nums[left-1]) q.pop_front();
            while(!q.empty() && q.back()<tmp) q.pop_back();
            q.push_back(tmp);
            res.push_back(q.front());
            right++;
            left++;

        }
        return res;

    }
};

```