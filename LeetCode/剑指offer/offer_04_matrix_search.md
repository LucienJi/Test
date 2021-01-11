## 描述

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

 

示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。


## CPP

1. 将矩阵转45度，就发现这是一颗二叉搜索树

```cpp
class Solution {
public:
    bool findNumberIn2DArray(vector<vector<int>>& matrix, int target) {
        if(matrix.size()==0 || matrix[0].size()==0){
            return false;
        }
        int n = matrix.size()-1;
        int m = matrix[0].size()-1;
        int j = 0;
        int i = n;
        while(i>=0 &&j<=m){
            if(target == matrix[i][j]){
                return true;
            }else if(target < matrix[i][j]){
                i--;
            }else{
                j++;
            }
        }
        return false;
        

    }
};

```