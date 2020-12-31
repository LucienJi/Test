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
        * rank 来实现平衡数，记录高度，永远让小树的root接在大树的root下，只有两棵树相同高度时，总高度才会加1
        * 根据上述的操作，假如高度是k时，那么这个partition至少有 2^k 个element，因此我们的find 复杂度能降到O(mlogm)

## 重要的 Lemme

1. > G = (V,E) is connected, if G contains a cycle and e = {a,b} in this cycle,then G-e is still connected
    
    > 也就是说一个有 cycle 且 connected 的图在删除cycle中的一个edge 一定还是connected的

    > 联想：n-1 edge 和 connected = tree ， 加一个edge 再减一个edge就能形成一个不一样的tree

2. > Extendable: 形容 edge set F, 就是说存在 （V，F') 是minimum spanning tree，那么F extendable 就是 F in F'

    > F is extendable，then V = A + B 且 F 交 E(A,B) = 空集，其中 E(A,B)是联通两个partition的edge集合，也就是说F目前只在一个partition中，找到 e = min(E(A,B)) 那么F+e 依旧extendable

