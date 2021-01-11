# 单源最短路径问题
1. 路径问题具有明显地 DP 性质：具有最优子结构（子路径也是最短路径），利用子结构定义下一级
    * d(s,v) = min(d(s,u) + w(u,v), u in N_(v))

2. 那为什么普通的DP没有解决这个问题？
    * 子问题相互依赖！ d(s,v) 依赖 d(s,u), 但是d(s,u) 也可能依赖于 d(s,v)
    * 因此我们定一个特定的顺序

3. 重要的SP 问题的Lemme：
    > 最短路径的子路径也是最短路径

    > 拥有负权重的环路没有最短路径

    > 所有最短路径都是简单路径（不存在环路，正环路也不会有）

    > 复杂度O(1) 的 松弛操作 relaxation: `if v.d > u.d + w(u,v) then v.d = u.d + w(u,v)`,
    通过对**边**进行松弛操作，我们能得到 dijkstra 和 bellman-ford 算法

    > 松弛收敛：s -> v, 假设 u 是 v 是直接前置节点，假如在给 (u,v) 进行松弛之前，u.d = d(s,u),那么松弛后，v 也达到了最短距离
    


## Dijkstra： 单源 + 无负边

1. 如何解决DP的访问顺序？构建一个 V0 由已经确定下来距离的nodes组成的集合，根据松弛收敛的效果，可以不断通过拓展 V0 来实现
2. 怎么扩展我们的 V0
> v in V\V0, v 满足 min(d(s,u)+ w(u,v), u in V0 and u is neighbor of v)
> 并且此时，d(s,v) = min(d(s,u)+ w(u,v), u in V0 and u is neighbor of v)

3. PseudoCode

*Dijkstra 算法其实是greedy算法，每次都从 V - V0 这个集合里挑选距离自己最近的*

```python
##Initialiasation,只有source node是0，其余全是无穷
V0 = {}
Q = priority_queue ## 储存未访问的所有节点
Q = V  ## 装入所有的nodes

while Q is not empty:
    s = Q.pop() ## 距离V0最近点
    V0 = V0 + {s}
    for n in Neighbor(s):
        relax(s,n)  ## 根据 s,n 和边值来进行 松弛，因为s是松弛过的最短距离计算者，因此n点也被松弛成了最短距离目前拥者

```

其实很自然会有一个问题，那就是 n 具有很多node，比如没有进V0的节点，难道不可以是他们relax才是真值嘛？
不会，因为v是PQ中第一个出来的，被人relax过了


## Bellman-Ford 单源 + 可有负边

1. 可以有负边有什么影响？
    * 1->3 = 5 
    * 1->2 = 8, 2->3 = -4
    * 所以 1-> 3 = 4 而不是5，但是Dijkstra就会优先选择 1->3 这个通道


2. 因此需要新的排序方法：
*Knapsack*

    * 将节点index {1,2,,,n},考虑一条最短路径，最多只用了k个node(k 步的)
    * 最后的解肯定是看 最多用 n 个node 的那组，但是现在有了遍历的根据

    * 转移方程：
        > d0(s,s) = 0

        > d0(s,v) = inf

        > dk(s,v) = min( dk-1(s,v), dk-1(s,u)+w(u,v), for all u in N(v))

        > 用最多k node 从s到v = 其实k-1 node 就够了， 或者 k-1 先走 + 多走一步

3. Pseudocode 很粗暴

```python
n = |V| 
##  先初始化为 d(s) = 0, 其余是 inf

for i in range(n):
    for u in V:
        for v in N(u):
            relax(u,v)


```
* 外循环 ：n ；内循环使用聚合分析：所有 out-edge 之和，|E|
* O(VE)

4. Bellman-Ford 也让我们可以检查是否有 *负权重环*， 因为n次后（每个node 都被做了 n次relax，该收敛的都收敛），假如有人没收敛，（存在还能减小的的情况，你就凉了）

```python
# 先bellman

for v in V:
    for u in N(v):
        if v.d < u.d + w(u,v): ##还能降！！！
            return FALSE

return TRUE
```

## Floyd-Warshall: 所有距离对 + 可以负边

* 土办法: 无负边的话，V 次 Dijkstra O(vElgE), 有负边的话 V 次 bellman O(V VE)
* 这个办法在稀疏图的情况下，显著优于土办法 

1. DP 中 knapsack 的思想
    * 从 i 到 j ：只用前 k-1 node， 或者 ，使用了第k个node（到达 k ，从 k 再次出发，两段路中途不用 k，只用k-1）
    * dk(i,j) = min(dk-1(i,j) , dk-1(i,k) + dk-1(k,j))

2. 答案用 matrix 来装

3. Pseudocode

```python
# initializaion
d[i,j] = inf
d[i,i] = 0   ## 这是 d0
for e in E:  
    d[i,j] = w(i,j) if e = (i,j) ## d1

## 接下来是 bottom-up 的方法计算

for k in range(n):  ## 每次最多用k
    for i in range(n):
        for j in range(n):
            d[i,j] = min ( d[i,j], d[i,k] + d[k,j])

```
4. 因为和 bellman 类似的性质，我们也能顺便得到negative cycle 判断，不用额外运行，只需要查一下 d[i,i],因为，d[i,i]在0步时就应该fix了，值就是0，假如经过最多n个nodes，你还能减少，就说明，绕了一圈负环

## Eulerian Cycles 不难的问题

## Hamilton Cycle NP 困难的问题







