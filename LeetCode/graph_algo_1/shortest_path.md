# 最短路径问题

## Dijkstra 算法

1. 核心想法：根本目的是计算出初始点
类似动归 + 贪婪
    > d(x,y) = min(d(x,v) + w(v,y),v 访问过的node，且为y的邻居)

2. pseudo code
```cpp
// Input: Graph G, weight w, source node x
// Output: 所有node到x的最短距离

初始化 priority_queue Q, key是d(y), node y 到 source x 的距离
初始化路径记录 P,(parent), P[x] 返回的是到达x之前的上一个node
Q.push(x,0) // 自己放自己是 0 距离
Q.push(y,+OO) 所有没有访问过的节点
while(!Q.empty()){
    v,d[v] = Q.front();
    for y in N(v):
        Q.decreasekey(y,d[v] + w(v,y)); // 可能不用减小，
        if Q.decreaseKey_successful:
            p[y] = v // 说明 v 是到达y最近的邻接点

}

```

## A* 算法

1. 比dijkstra的算法好在可能会更快的找到目标，因为dijkstra是要遍历所有的结点
2. 改进处：更改优先队中的key，原先是 d[y],单纯是距离source node 的距离，现在是：
    > f(n) = g(n) + h(n)
    
    > g(n): 从source node 到达n点的代价，也是我们要的最小的那个

    > h(n): 启发式代价，可以是 n 点距离目标的欧氏距离，曼哈顿距离等等，值越小(说明里目标越近，因此优先级越高)

3. Pseudo Code

```cpp
* 初始化open_set和close_set；
    // 注意 close_set 中的节点是完全确定好的，不需要重新更新key值
* 将起点加入open_set中，并设置优先级为0（优先级最高）；
* 如果open_set不为空，则从open_set中选取优先级最高的节点n：
    * 如果节点n为终点，则：
        * 从终点开始逐步追踪parent节点，一直达到起点；
        * 返回找到的结果路径，算法结束；
    * 如果节点n不是终点，则：
        * 将节点n从open_set中删除，并加入close_set中；
        * 遍历节点n所有的邻近节点 m：
            * 如果邻近节点m在close_set中，则：
                * 跳过，选取下一个邻近节点

            tentative_cost = g[n] + weight(n,m)
            // 接下来需要更新 在closet 中的key值和父节点
            * 如果m已经在open_set中，则：观察是否需要改变g值（g值是真实cost，h值的存在是决定访问顺序）：
                * if (tentative_cost < g[m]) // 已有g值
                    * tentative_is_better = true
                * else:
                    * tentative_is_better = false
                
            * 如果邻近节点m也不在open_set中，则：
                * tentative_is_better = true
                
            * 如果 tentative_is_better == true:
                // 我们要修改 y 的key，或者假如 y 这个新的未访问node
                * parent[m] = n
                * g[n] = tentative_cost
                * f[n] = g[n] + h[n]
                * open_set.push(y),y
```

## 
