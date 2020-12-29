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

