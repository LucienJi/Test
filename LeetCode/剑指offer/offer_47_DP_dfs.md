## 描述
在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？

 

示例 1:

输入: 
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 12
解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物

## CPP

1. 乍一看和 offer55一样简单，dfs递归方程 + 终结条件

2. 代码正确，但是！！！超时了，，能不用递归就不用递归！！
```cpp
class Solution {
public:
    vector<vector<int>> grid;

    int maxValue(vector<vector<int>>& grid) {
        this->grid = grid;
        return recur(0,0);

    }

    int recur(int i,int j){
        int m = this->grid.size();
        int n = grid[0].size();
        if(i==m-1 && j==n-1)return this->grid[i][j];
        if(i==m-1 && j<n-1){
            return grid[i][j] + recur(i,j+1);
        }
        if(i<m-1 && j==n-1){
            return grid[i][j] + recur(i+1,j);
        }
        return grid[i][j] + max(recur(i+1,j),recur(i,j+1));
    }
};
```

3. 动归解法

grid[i][j] = 最大的 可能的方向的值 + 自身的值
```cpp
class Solution {
public:
    

    int maxValue(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        for(int i =0;i<m;i++){
            for(int j =0;j<n;j++){
                if(i==0 && j==0){
                    continue;
                }
                else if(i==0){
                    grid[i][j] += grid[i][j-1]; 
                }
                else if(j==0){
                    grid[i][j] += grid[i-1][j];
                }else{
                    grid[i][j] +=max(grid[i-1][j],grid[i][j-1]);
                }
            }
        }
        return grid[m-1][n-1];

        

    }

   
};
```