# 描述 Graph
1. G = (V,E), vertice(node) + edge, node数 n，edge数 m
2. degree of node: d(v) = |N(v)|, 
    > sum(d(v)) = 2*m  
    > degree 之和为两倍边数

3. connected 是一个equivalence relation（reflexive, symmetric,transitive）
4. forest = graph without cycle
5. tree = connected forest, 我也理解为 forest 中的 connected component 是tree
6. tree 的等价性质：
    > G is a tree = cycle-free + connected

    > G is minimally connected(删除任意edge 就不connected，注意是 删除edge！！！)

    > G is maximally cycle-free(加上任意edge 都会出现cycle)

    > G 有n个node，且有n-1个edge，同时connected（或者cycle-free）


# 基础算法：广度优先搜索 BFS

[先是基于无权图，计算得到所有点到源节点的最短距离]
1. 核心特征：在发现所有距离源节点为 k 距离的节点后，才会去探索 k+1 的节点
2. 常见简单Implementation是使用 queue FIFO
3. 节点需要被附上属性来表明是否有被搜索过
4. Pseudocode：

```cpp
//初始化所有节点信息：
for v in V:
    v.state = no_visit
    v.distance = -1
    v.parent = NULL

//放入源节点 s
s.state = on_visit
s.distance = 0
s.parent = s

queue.push(s)

//榨干queue
while(!queue.empty()):
    v = queue.pop()
    for f in N(v):
        // use list implementation
        if f.state == no_visit:
            f.state = on_visit
            f.distance = v.distance + 1
            f.parent = v
            queue.push(f)

        v.state = end_visit
```
5. 上述代码有几点需要注意：
    * 用while 循环不变式可以得到， **queue中的元素都是 on_visit**的
    * on_visit表明：自生距离已经算好，但是自身的邻居还没有完全遍历过（邻居可以是no_visit，也可以是 end_visit）
    * 复杂度 O(V+E),V 来源于初始化，E来源于每个点的边都会遍历一次

6. 重要的Lemme：
    * 最短路径lemme:
        > 若u，v 相连，则  d(s,u) <= d(s,v)+1
    * BFS 得到的 v.distance 值 >= d(s,v) (数归法)
    * Queue 中的点最多只有两个不同的distance 也就是 d 和 d+1，换句话说：
        对首.distance <= 队尾.distance + 1,
        并且，vi.d <= vi+1.d
    * 最终可以得到，BFS 一定是得到最短距离（反证法，假设了直接前驱结点这个概念，只用+1，并且也是parent点就能达到正在研究点）

# 基础算法：深度优先搜索 DFS
[和BFS不一样，这不是只用于单source node，不是构建最短距离，而是构建深度优先森林]
1. 核心思想：DFS 总是对 **最近** 发现的节点近似探索，直到这个节点的所有**后代节点**都被探索完，才会回到该节点
2. 和BFS不同，BFS能对source node出发能到达的所有节点构建广度优先树，但是DFS可以对全图（没有到不了的）构建一个森林！
3. 颜色和时间戳：
    * 未探索：白色 ； 探索中：灰色 ； 探索结束：黑色
        * 用颜色 这个tag来标记state的状态可以很好地看清亲属关系
    * 时间戳：发现时标记，探索结束时再标记

4. Pseudo Code
```cpp
//初始化所有节点的颜色和状态

DFS(G):
for v in V:
    v.state = no_visit
    v.time_on = 0
    v.time_off = 0
    v.parent = NULL
//初始化时间戳
time = 0


for v in V:
    if v.state == no_visit
    DFS_Visit(v)


// 辅助递归函数

DFS_Visit(v):
    // 第一次发现
    time+=1
    v.state = on_visit
    v.time_on = time
    for u in N(v):
        if u.state = no_visit
            u.parent = v
            DFS_Visit(u)

    //结束所有后代的探索
    // 改变改变状态，前进时间戳，标记结束时间
    v.state = end_visit
    time+=1
    v.time_off = time

```

5. 为什么要用时间戳和颜色标记？对于图的结构探索
    * 节点 u 是 节点 v 的后代 == u 是在 v.state=on_visit 时被发现的
    * 时间戳的括号化定理：
        * (x,(y,(z,z),y),x) 类似的样子
        * 可以根据时间戳来判断两个节点 u ，v的关系 [u.time_on,u.time_off] , [v.time_on,v.time_off]
            * 完全分离：在深度优先森林中，这俩在两颗树里
            * 包含关系，被包含的那个是另一个的后代

    * 白色路径定理：
        * v 是 u 的后代 == 在发现u的时候（也就是 u.time_on的时候），存在一条path从u到v，path上所有的node 都是 no_visit

6. 边的分类
    * 树边：常见的通过探索 （DFS_Visit）
    * 后向边: 从后代节点到祖先节点的
    * 前向边: 从祖先节点到后代节点，但是没有被探索，也就是说有别的后代节点，提前探索了这个节点，你去的时候黄花菜都凉了
    * 横向边: 所有其他边，比如指向其他树，非祖先，非后代

7. state 和 边分类，在第一次遇到 边(u,v)时，v的状态就表明了这条的类型
    * v.state = no_visit : 树边
    * v.state = on_visit : 后向边
    * v.state = end_visit: 前向边
    * 其余是横向边

    * PS. 无向图中只有树边和后向边

## DFS 应用：拓扑排序
1. 对有向无环图实行拓扑排序，得到一个sequence v1,v2,v3,,,vk,满足：
    > vi 一定是 vi+1 的祖先或者，无关节点，总之能完成类似**有前置要求的任务安排**
    > 如果图中有边 (u,v), 则u.end_time > v.end_time

2. Pseudo Code
```cpp
// Topological Sort
// 调用先前写的DFS
DFS(G)
创建列表：以time_end 作为降序排列
（每次有vertice 是 time_end 时，就将vertice 放在list的front上）
```

## DFS 应用：强连通分量（SCC）
1. SCC 的定义: 任意(u,v) ，存在path 从 u 到 v，也存在path 从 v 到 u
2. 将(G,E) reduce 到 (G_scc,E_scc),(这是一个有向无环图，原图必定有环)
3. 算法核心是两次调用DFS，同时需要使用 图转置
4. Pseudo Code
```cpp
DFS(G),重点标记 time_end
//图转置
/*安装第一次DFS的time_end 的结果，以降序排列（也就是拓扑排序的结果顺序）
作为第二次DFS的访问顺序*/
DFS(G_t)

每次访问完一个树，这个树就是一个SCC

```
5. 为什么能够成立？
    > Recap: 分量图（SCC） 是一个有向无环图
    > Topo Sort 只能作用于 有向无环图图
    > 第二次DFS时按照降序访问，其实是按照拓扑排序的结果访问 分量图中的等效节点
    > 在访问G_t 时，从一个 连通分量只能是通向 一个已经被访问过的强连通分量，因此，不会放在同一颗树里
# 用图语言去描述算法问题

## Interval Scheduling 问题
原先这个问题（没有冲突时间的任务安排）使用greedy的“先选最早结束无冲突的”（“最晚开始无冲突的”），现在可以用图语言重新描述：
* e = {i,j} 存在说明任务 Ai Aj 是有冲突的，因此不是邻居的node则是可以安排在一起的
* 问题被转化为了：寻找最多的独立node集，集合中的node相互都无领边
* **independent set interval graph 问题**

## Assignment problem 

X： candidate 集合，Y: company 集合，适配 X中员工进入Y,每个员工 x 都有自己合适的公司 y
* e = {x,y} 说明 x 可以进 y 公司，
* 构建 **bipartite Graphs** , node 分成两类，candidate 和 company 而candidate和company之间是没有edge的
* 目标：从bipartite graphs中找到 **maximum matching**
    * 就是 edge set：任何edge e,f 交际为0，没有共享的node

1. Greedy 解法：遍历edge，假如e 的加入不会破坏matching的要求就加入
    > 性能保证 M > 0.5 M*
2. Augmenting Path 算法
    * 面对 bipartite graph，已有一个matching F，尝试扩张这个F 使其成为 M*(maximum matching) 找到一条path：
        * v0，e1,v1,e2,v2,,,ek,vk
        * k 是 odd
        * e2,e4,,ek-1 是属于 F的 （e1,e3,,是还没有被选中的）
        * v0,vk 还不属于F中的任意edge，也就是v0，vk还没有找到company，candidate（由于k是odd，所以都v0，vk隶属于不同的bipartite）
        * 增加方式是 F+P = F\{e2,e4,,,ek-1} + {e1,e3,,,ek},因此F+P中edge总数比F原先多一个


# 最小生成树 Minimum Spanning Trees

1. 问题描述
    * 给定 G = (V,E), w(e) weights for edges
    * 求subgraph T = (V,F) 是 tree 且 边的权重最小
    * 等效转化一下语言：我们要找一个有 n-1个边的connected graph 同时权重最小

## Greedy: Prim's algorithm
1. 核心思想：
    * 从 空集开始，维护一个connected component
    * 每次找到 cheapest outgoing edge 同时要确保不会破坏 cycle-free的特性

2. Pseudo Code
```cpp
Input: G, W
Output: minimum spanning tree T = (V,F)

初始 Vt = {v},随便选一个
while Vt != V:
    Find e : cheapest (outgoing edge) e = {x,y} such that  x in Vt while y not
    Vt = Vt + {y}
    F = F + {e}

return F

```

3. 真实Implementation
    * 难点1：找到cheapest outgoing edge：
        * cheapest： priority queue
        * outgoing edge：
            * 面对新node，查看所有领边e，检查另一侧是否已经在Vt中
    * 简单的pq 带来O(mlogm)

## Greedy: Kruskal's algorithm

1. 核心思想：
    * 巨直接！！！先选定所有node，不选边，这样就有相当于forest
    * 对所有边按照权重排序
    * 选择最小权重的边 e = {x,y}，check 是否会发生 cycle

2. Pseudo Code

```cpp
Input: G, W
Output: Minimum Spanning Tree

初始直接选定所有节点 Vt = V
对edge E 进行排序

for e in E:
    e = {x,y}
    if (x,y) 现在还没有联通：
        F = F+{e}
    else:
        pass
return F

```

3. 真实的Implementation
    * 难点1：check 新的edge是否会带来cycle（实话实说，这比带删除功能的pq好实现的多）
        * Union-Find structure
        * 面对 e = {x,y},检查一下 x,y是否已经联通，假如联通就下一条
    * 难点2：实现Union-Find结构式使用的小trick
        * path compression， 每次find的时候，都parent[v] = parent[parent[v]]
        * **重点是path compression的效果**，每次Union 和 find 的复杂度被降到了 quasiconstant， **O(ka(n)),a(n) inverse Acker,ann function**
        * rank 来实现平衡数，记录高度，永远让小树的root接在大树的root下，只有两棵树相同高度时，总高度才会加1
        * 根据上述的操作，假如高度是k时，那么这个partition至少有 2^k 个element，因此我们的find 复杂度能降到O(k) constant
        * 所以 kruskal的所有复杂度完全都留在了排序上

    * 两者的都需要PQ来做到quasi排序，事实上可以用 bucket sorting 假如你知道你的weight上限是多少，就可以用空间换时间

## 重要的 Lemme

1. > G = (V,E) is connected, if G contains a cycle and e = {a,b} in this cycle,then G-e is still connected
    
    > 也就是说一个有 cycle 且 connected 的图在删除cycle中的一个edge 一定还是connected的

    > 联想：n-1 edge 和 connected = tree ， 加一个edge 再减一个edge就能形成一个不一样的tree

2. > Extendable: 形容 edge set F, 就是说存在 （V，F') 是minimum spanning tree，那么F extendable 就是 F in F'

    > F is extendable，then V = A + B 且 F 交 E(A,B) = 空集，其中 E(A,B)是联通两个partition的edge集合，也就是说F目前只在一个partition中，找到 e = min(E(A,B)) 那么F+e 依旧extendable





# 经典案例

