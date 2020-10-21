## 描述
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1

## CPP

```cpp
class Solution {
public:
    int helper(int i,int j,int k,int m,int n,vector<vector<bool>>& visit){
        
        int sum=0;
        int tmp1 = i;
        int tmp2 = j;
        while(!(tmp1==0 && tmp2==0)){
            sum +=tmp1%10;
            sum +=tmp2%10;
            tmp1/=10;
            tmp2/=10;
        }
        if(i<m && j<n && sum<=k && !visit[i][j]){
            visit[i][j] = true;
            return 1+helper(i+1,j,k,m,n,visit) + helper(i,j+1,k,m,n,visit);  // 这句话就是出卖了我不是典型BFS
        }else{

            return 0;
        }
    }
    int movingCount(int m, int n, int k) {
        vector<vector<bool>> visit(m,vector<bool>(n));


        return helper(0,0,k,m,n,visit);


    }
};
```

1. 如何利用vector<vector<bool>> 来标记已经访问过的
2. 我这个感觉和正版BFS有点差别，，更像是动归的打表

3. 用BFS的思想：
    > 需要引入一个 queue 用来记录 matrix[i][j] 访问后 新可能的 坐标放进queue，将所有可能的都一下子全放进
    > 再根据需要（优先堆，单纯的先进先出），所以不会一条道走到黑，还是一层层向外探索