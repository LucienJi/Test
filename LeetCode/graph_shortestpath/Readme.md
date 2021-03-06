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

3. 循环不变式，证明Dijkstra的正确性
    * Loop invariant: 每个在V0的点都是已经v.d = d(s,v)
    * 初始化：成立，因为V0是空集
    * 保持：假设V0里面的已经是好了的，现在看 u 是在准备加入的点，u 有没有已经被一个到位的点v relax过了，如果有，那么根据relax 定理，他也就到位了，但是有没有可能是一个乳臭未干的node去relax他呢？不可能只有加入过V0才能relax别人，所以u的前置节点必定到位 y.d = d(s,y) < d(s,u) <= u.d ，所以y.d一定是提前被选出来的
    * O(Elg(V))

4. PseudoCode

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
## DAG-Shortest-Path: 有向无环图 + 单源最短路径 + 可以负边
1. 相当于Dijkstra差不多，前者没有负边，这个不能有环
2. 对边进行拓扑排序（因为有向无环图），依次进行relax操作
3. 为什么拓扑排序？假如存在 u 到 v 的一条路，那么在拓扑排序中，u一定在v前面,因此这个顺序的relax能保证一路收敛
4. DAG 算法的应用：PERT，关键路径 = 有向无环图中最长的路 = 所有可执行的工作的最长时长
    * 小trick是将权重换成负数，那么最小路径 = 最长时间




## Floyd-Warshall: 所有距离对 + 可以负边

* 土办法: 无负边的话，V 次 Dijkstra O(vElgE), 有负边的话 V 次 bellman O(V VE)
* 这个办法在稀疏图的情况下，显著优于土办法 

1. DP 中 knapsack 的思想
    * 换成人话：考虑所有点集的子集{1,,,k},节点对（i，j）路径P只使用{1,,k}中的节点，那么就研究路径P和节点k的关系，是否使用了节点k

    * 从 i 到 j ：只用前 k-1 node， 或者 ，使用了第k个node（到达 k ，从 k 再次出发，两段路中途不用 k，只用k-1）；假如没用那么P中的所有中间节点都是{1,,k-1};假如是了，那么根据无环性质，所以只会经过k一次，那么k之前就是只用{1,,k-1},k之后只用{1,,k-1}

    * dk(i,j) = min(dk-1(i,j) , dk-1(i,k) + dk-1(k,j))

2. 路径：前驱结点矩阵（单源节点可以只用parent点来解决）
前驱结点
```python
#初始化
Path(i,j,0) = null if i==j or wij==inf else i
Path(i,j,k) = Path(i,j,k-1) if d(i,j,k-1) <= d(i,k,k-1) + d(k,j,k-1)  ## 走k节点不划算
Path(i,j,k) = Path(k,j,k-1) if d(i,j,k-1) > d(i,k,k-1) + d(k,j,k-1)   ## 走k

```

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
1. 问题简述：
    * 一笔画出所有的边 edge，同时也是cycle
    * 定理：
        > 一个connected  graph 能有eulerian cycle == 所有的node 的degree 是偶数
        > 一个有 enlerian cycle 的图，那么只用 O(m) 就能计算出这个环


## Hamilton Cycle NP 困难的问题
1. 问题简述：
    * 一笔画完所有的点，同时也是cycle
    * NP-complete

2. 经典案例：TSP(traveling salesman problem)
    * 一笔走完所有的城市，不带重复
    * 总距离最少

3. 稍微简化一点的TSP：metric TSP
    * 在TSP的要求上，对Graph再加上一点要求，让求解更加简单一点
    * d(i,j) <= d(i,k) + d(k,j)
        * 基于 metric TSP，我们有一个给力的定理；short-cut TH：
        > 对于 metric TSP，假设cycle C 包含了每个顶点至少一次，那么必定能在O(|C|)时间内计算出一个C
    ' 也是一个环，但是每个顶点只包含正好一次！！

4. Pseudo Code 近似系数2 的 MST-based 版本（基于最小生成树）
```python
## 将输入的 G 看做一个 complete graph
1. 利用 prim、krustal算法O(ElgE)得到 MST ，取名为 T
2. 将T中的edge double，保证这是一个拥有eulerian cycle 的图
3. 计算一个 eulerian cycle C（问题是 eulerian 保证每个边都有，这就意味着有些节点走了不止一遍）
4. 使用 short-cutting 方法，计算一个 hamiltonian H
```


* 最值得注意就是，在 Metric TSP中，我们计算的 MST 的权重和，其实是TSP权重和的lower bound，最终的解一定比 MST的要好

5. Pseudo Code with 近似系数 1.5

```python
## 将输入的 G 看做一个 complete graph
1. 利用 prim、krustal算法O(ElgE)得到 MST ，取名为 T
2. 将T中的有奇数edge 的node 拎出来，做一个 perfect matching，要求是这是总的weight和最小的matching，相当于让这些个odd nodes 自己内部解决了，保证这是一个拥有eulerian cycle 的图
3. 计算一个 eulerian cycle C（问题是 eulerian 保证每个边都有，这就意味着有些节点走了不止一遍）
4. 使用 short-cutting 方法，计算一个 hamiltonian H
```


# 经典问题
1. 欧拉环路 ； 欧拉通路
> 无向图 有 欧拉环路 == 每个节点都有偶数degree + 联通

> 有向图 有 欧拉环路 == 每个节点的入度=出度 + 属于一个 strong connected component（强连通分量）

> 无向图 有 欧拉通路 == 联通 + 只有两个node有奇数degree

> 有向图 有 欧拉环路 == 一个node 入-出 = 1 + 一个node 出-入 = 1 + 属于一个 strong connected component + 其余节点的入度 = 出度

2. handshaking lemma
    > 所有无向图：奇度的node的个数是偶数

## 找欧拉环路

1. 先是找circuit，未必完全遍历所有edge
    * 重要的性质：在欧拉图中的walk都可以拓展成一个circuit(not necessary eulerian)

* Pseudo Code： FindCircuit(G,v)
```python
#input: G and v
res = {v}
node = v
while(!(v.d+ == v.d-  and v.d+ == 0)){
    e = Find_one_of_the_rest_edge(node)
    res = res + {e}
    node = e.other_node
    res = res + {node}

}
return res


```
2. Pseudo Code：Euler(v)

```python
res = [] ## supposed to be linked list
while G.isnotEmpty():
    circuit = FindCircuit(G,v)
    if res is empty:
        res = circuit
    else:
        pre_v  =v.prev
        tmp = pre_v.next
        pre_v.next = circuit.first
        circuit.last.next = tmp

    Delete_all_isolated_node(G)

    v = one_of_the_rest_node(G)

```
3. Complexity
    * graph: double linked list:
        * No need to search a certain node: just the first neighbor on store O(1)
        * Delete an edge, each out-neighbors not only point to next out-neighbors, also point to the corresponding in-neighbor
        * Deletion in linked list O(1)
    * FindCircuit : O(|res|)
    * Insert circuit by linked list O(1)




## 中国邮递员问题
1. 遍历所有的边至少一次，并且权重和最小，最终回到出发点
2. 不管是不是欧拉图，欧拉环路都是中国邮递员问题的lowerbound
3. 假如是欧拉环路：复杂度O(V) 找到环路就行了
4. 假如是棵树 （E=V-1），复杂度也是O(V),因为每条边必须走2次
