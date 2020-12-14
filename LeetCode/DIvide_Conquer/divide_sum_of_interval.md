## 描述

给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

说明:
最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。

示例:

输入: nums = [-2,5,-1], lower = -2, upper = 2,
输出: 3 
解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。


## CPP

1. 相同的分治，相同的在divide过程中使用merge
2. 不同的是，需要对区间和进行预处理 presum
3. 在合并两个分治结果时：我们需要在单边数组维护两个指针用于标识low 和high的范围，所有的指针只能往右，因此是O(N)

```cpp
class Solution {
public:
    int countRangeSumRecursive(vector<long>& sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) l++;
                while (r <= right && sum[r] - sum[i] <= upper) r++;
                ret += (r - l);
                i++;
            }

            // 随后合并两个排序数组
            vector<int> sorted(right - left + 1);
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = sum[p1++];
                    } else {
                        sorted[p++] = sum[p2++];
                    }
                }
            }
            for (int i = 0; i < sorted.size(); i++) {
                sum[left + i] = sorted[i];
            }
            return ret;
        }
    }

    int countRangeSum(vector<int>& nums, int lower, int upper) {
        long s = 0;
        vector<long> sum{0};
        for(auto& v: nums) {
            s += v;
            sum.push_back(s);
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.size() - 1);
    }
};

```