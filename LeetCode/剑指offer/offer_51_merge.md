## 描述
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

 

示例 1:

输入: [7,5,6,4]
输出: 5

## CPP-超时版，复杂度O(nlogn)????

```cpp
class Solution {
public:
    int insert(vector<int>& num,int a){
        int i = 0;
        int j = num.size()-1;
        auto id = num.begin();

        if(a>num[0]){
            num.insert(id + 0,a);
            return 0;
        }
        if(a<num[j]){
            num.push_back(a);
            return j+1;
        }

        while(i<j){
            int mid = (i+j)/2;
            if(num[mid]>a){
                i = mid+1;
            }else{
                j = mid;
            }
        }
        num.insert(id+i,a);
        return i;

    }
    int reversePairs(vector<int>& nums) {
        if(nums.size()==0){
            return 0;
        }
        int i = 0;
        int j = nums.size()-1;
        int res = 0;
        int pos;
        vector<int> vec_help ;
        if (j == 0){
            return(0);
        }
        vec_help.push_back(nums[0]);

        for(i=1;i<=j;i++){
            res+=insert(vec_help,nums[i]);

        }
        return res;



    }
};
```

## CPP 不超时版：归并排序 + 分治思想

``` cpp
#include <vector>
#include <iostream>

class Solution {
public:

    // 分治算法 = 结束条件，如何利用假设结论
    int merge(std::vector<int>& nums,int left,int right,int mid,std::vector<int>& copy){
        int k;
        int res = 0;
        for(k = left;k<=right;k++){
            copy[k] = nums[k];
        }

        int i = left;
        int j = mid + 1;
        for(int l = left;l<=right;l++){
            if(i==mid+1){
                // 左侧提前全部放完，右侧多余的不会增加序列
                nums[l] = copy[j];
                j++;
            } else if(j==right+1){
                nums[l] = copy[i];
                i++;
            } else if(copy[i]<=copy[j]){
                nums[l] = copy[i];
                i++; // 正常序列，不用额外加1
            } else{
                nums[l] = copy[j];
                j++;
                res+=mid+1-i;
            }
        }
        return res;
    }

    int partition(std::vector<int>& nums,int left,int right,std::vector<int>& copy){

        if(left==right){
            return 0;   //结束条件：当分治只剩一个数时，是不用计算逆序对的
        }

        int mid = left + (right-left)/2 ;      // 这个方式可以避免大数溢出；同时向下取整，会取到left
        int left_reverse_pairs = partition(nums,left,mid,copy);
        int right_reverse_pairs = partition(nums,mid+1,right,copy);
        if(nums[mid]<nums[mid+1]){
            return left_reverse_pairs + right_reverse_pairs;    // 此时可以提前放出：数逆序对其实是一个将逆序对拆回来的过程，逆序对 = 左半个的逆序对 + 右半个的逆序对 + 归并时的逆序对
        }

        return left_reverse_pairs + right_reverse_pairs + merge(nums,left,right,mid,copy);



    }
    int reversePairs(std::vector<int>& nums) {
        int length = nums.size();
        if(length<2){
            return 0;
        }

        std::vector<int> copy(length);
        int i = 0;
        int j = length-1;
        int res = partition(nums,i,j,copy);
        return res;



    }
};

```

### 分析
1. 归并排序本身就是分治的体现，所以可以直接结合，
2. 将找逆序 用分治的眼光看
3. 从暴力解法中得到：可以从数组的排序中得到”记忆“，从而减少反复比较
4. 归并排序有几个小坑：
    - 全场共享一个copy，为了节约空间
    - merge时，必须`else if(copy[i]<=copy[j])`,这个小于等于号是merge稳定的条件