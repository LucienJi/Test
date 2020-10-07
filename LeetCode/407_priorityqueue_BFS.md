## 描述
给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水




## CPP

```cpp
#include <queue>
class Node{         // 我思考了很久，怎么在cpp里做元组，发现，我可以用类啊 >~<
public:

    int x,y,h;
    Node(int _x = 0,int _y=0,int _h = 1){
        x = _x;
        y = _y;
        h = _h;

    }
    friend bool operator<(const Node& a,const Node&b){
        return a.h > b.h;  // 这一步说明了，这是反的优先堆，大的在上面
    }
};

class Solution{

public:
    int L,W;
    int dir_x[4] = {-1,0,1,0};
    int dir_y[4] = {0,1,0,-1};      // 方便BFS，往周围探索
    bool inGrid(int x,int y){
        return x>0 && y>0 && x<L &&y<W;
    }

    int trapRainWater(vector<vector<int>>& heightMap) {
        int rain = 0;
        L = heightMap.size();
        W = heightMap[0].size();
        if (L<3 || W < 3) return 0;

        vector<vector<int>> visit = vector(L,vector<int>(W));
        priority_queue<Node> pq;                  // 都是谁能够加入 pq？ BFS 探索到合法在边界内且，且被前者进行了比较的。
                                                // 并不是一圈一圈往里缩，而是用 pq 每次找最高的墙，然后灌水，同化周围的墙
        for(int i=0;i<L;i++){                    // 所有的边界点都不可能被灌水，所以我们直接visite，然后从站在最高边界点，向内灌水（左右都是visit，不会再看了）
            visit[i][0] = visit[i][W-1] = 1;
            pq.push(Node(i,0,heightMap[i][0]));
            pq.push(Node(i,W-1,heightMap[i][W-1]));

        }
        for(int j=0;j<W;j++){
            visit[0][j] = visit[L-1][j] = 1;
            pq.push(Node(0,j,heightMap[0][j]));
            pq.push(Node(L-1,j,heightMap[L-1][j]));
        }
        while(!pq.empty()){
            Node tmp = pq.top();
            pq.pop();
            for(int i = 0;i<4;i++){
                int neigh_x = tmp.x + dir_x[i];
                int neigh_y = tmp.y + dir_y[i];
                if(inGrid(neigh_x,neigh_y) && !visit[neigh_x][neigh_y]){
                    rain += max(0,tmp.h-heightMap[neigh_x][neigh_y]);

                    pq.push(Node(neigh_x,neigh_y,max(tmp.h,heightMap[neigh_x][neigh_y])));  // 为什么要有max高度，应为，加过水的空格，当被填满的状态
                    visit[neigh_x][neigh_y] = 1;  // 会存在重复的情况，因此需要避免，比如，上下都记录中间的。为什么是优先堆，因为
                                                // 假如矮子先访问高个子，结论是不能加水，但是高个子先访问其实是可以加水的。因此必须高的先访问

                }
                
            }

        }
        return rain;



    }
};
```

## 简述

1. 考了优先堆，，想不起来就完蛋了
2. 考了BFS的思想：
3. 字节跳动级别的难题