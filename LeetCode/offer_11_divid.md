## 描述

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

示例 1：

输入：[3,4,5,1,2]
输出：1


## CPP

1. 蠢方法：trivial，但是注意不要让指针超出范围

```cpp
class Solution {
public:
    int minArray(vector<int>& numbers) {
        int i = 0;
        if(numbers.size()==0){
            return NULL;
        }
        if(numbers.size()==1){
            return numbers[0];
        }
        while( i<numbers.size()-1  && numbers[i]<=numbers[i+1]){
            i++;
        }

        if(i == numbers.size()-1){
            return numbers[0];
        }
        return numbers[i+1];

    }
};

```

2. 这是一个 quasi-sorted 的数列，自然可以二分

```cpp
class Solution {
public:
    int minArray(vector<int>& numbers) {
        int low = 0;
        int high = numbers.size() - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            }
            else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            }
            else {
                high -= 1;
            }
        }
        return numbers[low];
    }
};
```
