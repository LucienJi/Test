## 描述
给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。

你需要返回给定数组中的重要翻转对的数量。

示例 1:

输入: [1,3,2,3,1]
输出: 2


## CPP

1. 方法：分治 + 归并排序 : *核心思想* 在分治过程中，同时排序，利用排好序的两组数组来计算额外翻转对
    > Input: array, left,right
    > Output: nums_reversed_pair, sorted_array
    > if(right - left <3){
        calculate the reverse num 
        inplace sort and return num
    }else{
        res1 = algo(array, left,left + right/2);
        res2 = algo(array, left + rigth/2+1, rigth);
        int i = left;
        int j = left+right/2 + 1;
        int extra_res = 0;
        while( i <=  left + right/2 && j< right){
            if(array[i] > 2*array[j]){
                那么array[i] 之后的都可以做到这件事；
                因此，extra_res += left + right/2 - i + 1;
                然后准备看右侧下一个；
                j++;
            }else{
                左侧不够大;
                i++;
            }
        }
        return res1 + res2 + extra_res;
    }

2. 
```cpp
class Solution {
public:
    int reversePairsRecursive(vector<int>& nums, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = reversePairsRecursive(nums, left, mid);
            int n2 = reversePairsRecursive(nums, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int j = mid + 1;
            while (i <= mid) {
                while (j <= right && (long long)nums[i] > 2 * (long long)nums[j]) j++;
                ret += (j - mid - 1);
                i++;
            }

            // 随后合并两个排序数组
            vector<int> sorted(right - left + 1);
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = nums[p2++];
                } else if (p2 > right) {
                    sorted[p++] = nums[p1++];
                } else {
                    if (nums[p1] < nums[p2]) {
                        sorted[p++] = nums[p1++];
                    } else {
                        sorted[p++] = nums[p2++];
                    }
                }
            }
            for (int i = 0; i < sorted.size(); i++) {
                nums[left + i] = sorted[i];
            }
            return ret;
        }
    }

    int reversePairs(vector<int>& nums) {
        if (nums.size() == 0) return 0;
        return reversePairsRecursive(nums, 0, nums.size() - 1);
    }
};



```