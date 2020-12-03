## 描述
给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。


示例：

输入：nums = [5,2,6,1]
输出：[2,1,1,0] 
解释：
5 的右侧有 2 个更小的元素 (2 和 1)
2 的右侧仅有 1 个更小的元素 (1)
6 的右侧有 1 个更小的元素 (1)
1 的右侧有 0 个更小的元素

## CPP

1. 分治的难度：同时进行mergesort，再难一点，用额外的index数组来记录被维护的下标：
    > merge的操作，同样地也施加在 index数组上，这样就算被排序了，也能给ans数组上正确的position进行操作了
    > 归并排序的部分，代码的边界条件需要斟酌

```cpp
class Solution {
public:
    void helper(vector<int>& nums, int left,int right, vector<int>& ans, vector<int>& index, vector<int>& helper1,vector<int>& helper2){
        if(left==right){
            ans[left] = 0;
            return;
        }else{
            int mid = left + (right-left)/2;
            helper(nums,left,mid,ans,index,helper1,helper2);
            helper(nums,mid+1,right,ans,index,helper1,helper2);
            int i = left;
            int j = mid + 1;
            while(i<=mid){
                while(j<=right && nums[i]>nums[j]){
                    j++;
                }
                ans[index[i]]+=j-mid-1;
                i++;
            }

            i = left;
            j = mid +1;
            int p= left;
            
            while(i<=mid || j<=right){
                if(i>mid){
                    helper1[p] = nums[j];
                    helper2[p] = index[j];
                    p++;
                    j++;
                }else if(j>right){
                    helper1[p] = nums[i];
                    helper2[p] = index[i];
                    p++;
                    i++;
                }else {
                    if(nums[i]<=nums[j]){
                    helper1[p] = nums[i];
                    helper2[p] = index[i];
                    p++;
                    i++;
                }else{
                    helper1[p] = nums[j];
                    helper2[p] = index[j];
                    p++;
                    j++;
                }
                }
            }
            for(int m = left;m<=right;m++){
                nums[m] = helper1[m];
                index[m] = helper2[m];
            }
        }

    }
    vector<int> countSmaller(vector<int>& nums) {

        int l = nums.size();
        vector<int> ans(l);
        if(l==0){
            return ans;
        }
        vector<int> helper1(l);
        vector<int> helper2(l);
        vector<int> index(l);
        for(int i = 0;i<l;i++){
            index[i] = i;
        }
        helper(nums,0,l-1,ans,index,helper1,helper2);
        return ans;


    }
};
```
