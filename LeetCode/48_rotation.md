##48. 旋转图像
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

```cpp
class Solution {
public:                       #######很简单的一道题  时间复杂度 n^2，空间复杂度就是原来矩阵 n^2
    void rotate(vector<vector<int>>& matrix) {
        int pos1=0,pos2=matrix.size()-1;
        int add,temp;
        while(pos1<pos2){     #####每次对一圈的元素做旋转
            add = 0;
            while(add < pos2-pos1){########每次动一个元素，顺时针，temp储存临时量
                temp = matrix[pos2-add][pos1];
                matrix[pos2-add][pos1] = matrix[pos2][pos2-add];
                matrix[pos2][pos2-add] = matrix[pos1+add][pos2];
                matrix[pos1+add][pos2] = matrix[pos1][pos1+add];
                matrix[pos1][pos1+add] = temp;
                add++;
            }
            pos1++;
            pos2--;
        }

    }

```
## 分析一下
这题很简单。。。
};
