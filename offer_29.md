### 描述
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：

输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]

### CPP
```cpp
class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> res;
        int beg1 = 0;
        int end1 = matrix.size();
        if(end1==0){
            return res;
        }
        int beg2 = 0;
        int end2 = matrix[0].size();
        int i,j;
        
        
        end1--;
        end2--;
        while((end1-beg1)>=0 && (end2-beg2)>=0){
            i = beg1;
            j = beg2;
            res.push_back(matrix[i][j]);
            while(j<end2){
                j++;
                res.push_back(matrix[i][j]);
            }
            while(i<end1){
                i++;
                res.push_back(matrix[i][j]);
            }
            while(j>beg2&&(end1-beg1)>0){
                j--;
                res.push_back(matrix[i][j]);
            }
            while(i>(beg1+1)&&(end2-beg2)>0){
                i--;
                res.push_back(matrix[i][j]);
            }
            beg1++;
            beg2++;
            end1--;
            end2--;
        }
        
        return res;

    }
};
```
### 描述

1. 难不难：不难，恶不恶心：恶心————用按圈的方法有太多小细节要注意
复杂度 O(mn),O(1)

2. 还有一种稍微简单一点，给出visit矩阵和全长m*n，这样的话就可以稍微方便一点？但是多开了空间