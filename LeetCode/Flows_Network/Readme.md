# Flows in Network

1. 基础描述
    > G = (V,E,s,t,c)
    
    > s: source, t: target, c: capacity

    > flow  f: E -> R, 对单个边我们赋予一个流

    > 流量限制：f(e) < c(e) ； 流量守恒： 流入节点u f(v,u) = 流出节点u f(u,v)

    > 目标函数：总流量 v(f) = 所有流出source的flow f(s,u)


# Ford-Fulkerson algorithm

1. 贪婪，但是不能直接在原图上贪婪，每次都要增加，但是要在残差图上找增广路径

### 残差网络 Residual Network

1. 原图G，给出一个流 f，f表明了所有的edge上的流值。 G 原图上不能出现双向流，只能是单向！！！
2. 得到Gf, Gf 可以有双向流：
    > 重新定义capacity forward edges, unused capacity： cf(u,v) = [c(u,v) - f(u,v)]+
        

    > 增加新的edge backward deges, allowing to reduce the existing flow: cf(v,u) = f(u,v)

### 增广路径 Augmenting Flows

1. 在 residual network 上找一个 simple path == 每个节点只能过一次
2. 对于这个path P 我们找到一个 bottleneck c(p) = min(capacity in Path)
3. 原图G得到f，得到Gf残差网络，找到增广路径P，找到bottleneckc，f上加上P路径，增量是bottleneck
4. PseudoCode：

```python
## Ford-Fulkerson Algo
## 目标是输出一个字典，key edge； value flow
## 复杂度比较怪 O(m*fmax(G)),它取决于最后的最大流值
for e in E:
    f(e) = 0
while Gf.havePath():
    P = Gf.path()
    f(e) = f(e) + Augment(P,e)
return f

```
5. 补充一下为什么终结： 
    1. 每次augmenting 会让流 +1， 流有上限所以会终结
    2. 假如初始是整数，每次都是+1，所有一直都是整数


### 最小割 Minimum Cut

1. 如何定义Cut？
    > Cut 是定一个 edge set E(A,B)，这个 set 分割了两个 nodes set，A B 是V的partition

2. 重要的定义:
    > capacity(A,B) = sum(c(e), e in E(A,B))
    
    > f(A,B) = 从A流出，流入B ！！！！！ f(A,B) <= c(A,B)

    > Lemme: v(f) = f(A,B) 任意割 ！！！

    > 再推一下： 对于任意割，对于任意流： v(f) <= capacity(A,B)。 f 取决于我们怎么分配，但是 capacity(A,B) 只是基于原图的（找到最小割只是找到了最大流的值，你并没有找到最大流）

    > 根据 f(A,B) 我们可以得到 FoFu代码的正确性:假如Gf没有augmenting path那么f就是最大流
        
        > 关键idea是：理由残差图构建一个 partition ，A = source 出发能够到达。 B = 就是到不了

3. Theorem: 最大流最小割
> G: fmax(G) = Cmin(G), 左侧是流值，右侧是capacity

4. 如何将 最大流算法转化为最小割结果：
    > 在最终的残差图中，使用bfs，存储所有的能够到达的点
    
    > 带着这些点，回到原图G，他们对外的边集合就是最小cut（显然，他们对外流的边必定满流，因为在残差图中他们不能再拓展）

# Dinitz-Edmonds-Karp Algorithm
1. 一种优化方案，使得构建残差图的次数bounded
2. 核心思想：每次在残差网络类选择**最小路径的增广路径**， 只用遍历O(m*n),因此最终的量是O(m**2*n)
3. 简明的证明idea：最短路径的特征是什么？相邻节点之间的距离一定是1
> 从source开始，残差网络中d(u) 在迭代过程中，不会减小！因为要么部分节点被删除，当然也会被重新加回

> 假设 a 轮，(u,v)在Path里，然后saturated了，下一次Gf里(u,v)不在了. 
> 假设 b 轮，（u，v）重新加回，也就是说，Gf里选择了（v，u）

> d_b(u) = d_b(v) + 1 >= d_a(v) + 1 >= d_a(u) +1 +1 

> 每个点可以被重加回来，但是每次dis都会升高，又因为dis有上限，所以被重新加回来有限。

# Maximum Mathings in Bipartite Graphs

1. 任务目的：X，Y两个集合每个集合内部没有edge， X，Y 之间的edge (x,y) 表明x和y可以匹配。 找到一个最大M = subset of edge，使得M内的edge没有共享节点。

2. 利用Matching问题，构建一个等价的最大流问题
    > 增加 source 节点，(s,x) for x in X

    > 增加 target 节点，(y,t) for y in Y

    > 所有的edge的capacity都是 1

    > 最终的流量 f(V) = |M|

3. Perfect Matching：M 都包含了每个节点
4. Regular Graph：图里的节点的degree 相同

# Circulation: Multiple Demands and Lower Bounds on the Flow

1. 问题描述： 给定 G = (V,E,d,l,c)
    > d 定义在节点上：每个节点 流入 - 流出 = d， 假如d是负的，就说明这是供给端
    
    > l 是定义在边上，f(e) >= l(e),流的最小值

2. 乍一看有点莫名，感觉没有source？其实不然，所有拥有 负demande的节点都可以理解为source

3. 转换为最大流问题 + 重要性质：
    > circulation 有解 == sum of all demand = 0

    > 在without lower bound的情况下，简单转换到最大流：
        
        > 增加一个 source ，增加 (s,v) ,v 拥有负demand，capacity = -demand，反正得是正的
        > 增加一个 target，增加(v,t),v 拥有正demand，capacity = demand
    
    > 很重要的一点，假如这题有解，那么 看一下source出去的总量 == 总的正demand

4. 比较复杂的情况：有了lower bound。 将有 l 的问题转换为 无lower bound 的情况
    > 首先 capacity(e) = capacity(e) - l(e)

    > 其次，d(v) = d(v) - L ; L(v) = sum of l(u,v) - sum of l(v,u)


# Application

## 1. Assignment Problem

    1. 类似 INF421 的project的安排：也就是不是 1 对 1 的matching，y 有一个自身容量 qy
    2. 加source 和target，source 的 capacity = 1，target 的capacity = qy

## 2. Project Planning
    
    1. 任务描述：X：需要购买的器材，x 有 cost ， Y：需要完成的任务，y 有revenue。 (x,y) 有边意味着y 需要x这个器材。
    2. 目的：最大化 sum（revenue - cost）
    3. 转化问题：最小割：最小化（cost - (total_revenue - 没能完成的任务)) = total_revenue - 最小化（cost + 没能完成的任务）
        > 最小化（cost + 没能完成的任务）

        > 增加 source，target，(s,x) 是cost， (y,t) 是revenue

        > Cut 这个选择的分割 A，B 两个partition， B = B^X + B^Y, 其中B^Y是所有能完成的任务，B^X是所有要买的，(s,x) x in B^X 是cut里的

        > A = A^X + A^Y, (y,t) y in A^Y 也都在Cut中

        > 凭啥最终隔成这样？让(x,y) capacity = infinity，这样就不会割中间了


## 3. Maximum Flow with Increased Edge Capacity

    1. 给定 G = (V,E,C,s,t) + f ，假设 f 已经是 maximum flow了
    2. 假设 pick 一个 edge e， increase 一个 unit 的capacity,得到G'
        1. 求证G' 的maximum flow 要么 v(f) or v(f) + 1
            > 设(u,v) 增加1，那我肯定看 residual network，Gf 中(u,v)+1,然后看bottleneck = min(0,1)
        
            > 另一种想法：此图的 minimum cut 最多+1

        2. 求在O(m+n)时间中找到新的maximum
            > 初步想法：怎么转为残差网路，主要是会有反向edge的出现，正向的只要在linkedlist，反向我假设有复杂的结构吧。那样的话，我BFS，存在唯一通路，然后一边bfs，一边给更新原图的流量：正向边+1，反向边-1
        3. 假如没有integrality的条件，那么我们还能有 1 的性质嘛？
            > 假如不是整数，怎么来 unit 1?

            > 假如全部划归到整数，那么 增加的量 [0,1],区间取决于划归的量，我觉得，1必定是被加在原来0上的

            > 但是假如最小单位是0.2， 然后 （0.1，1.1） ，这样的话，也不能增加，然后在 0.1 上增加 1,（1.1，1.1）,这样的话，就是增加 1。 但是问题是，0.1 是从哪里来的？？？？？？

            > 重点：非整数情况下，第二问的情况也不能保证，就是说，你可以同时多出来多条！更重要的是，非整数环境下我们没有 loop invariant！！！所以代码不一定能终结。
    


## 4. Assignment of Doctors

    1. 给定 n 个doctors，每个人有 Si 是医生 i 的可以加班的日期集合；
    每年有 k 个假期，每个假期有Dk 天，很迷惑的事是，，doctor的加班日期可以只是每个假期中的几天，而不是一定要加完所有这个假期的班。

    2. 任务1：尝试每天只安排一个医生 + 每个医生不能超过加班 c 天 + polynomial-time:
        > 这题很简单，不用考虑假期，只用考虑单独的日子

        > 医生 x， 日子 y, 增加 source s，capacity(s,x) = c, 增加 target t，capacity（y,t)=1,
        if x 可以在 y 加班，那么，(x,y) = 1

        > 假如 v(f) = sum(y)，那么有解

    3. 任务2： 尝试每天安排 p 个医生，其余同上：
        > 不动脑子的想法，增加(y,t) 的capacity 到 p，然后检查 v(f) == p*D

    4. 任务3：尝试每天安排 1 个医生，但是每个假期一个医生只能加班一次，说白了2周的圣诞你加班只能加一天
        > 我觉得只要增加给每个x 增加一套 zji, j是医生， i 是假期的种类，一个假期里有很多假日, capacity（x,zi) = 1 这保证了你每个假期只能放1天， (zi,y) = 1 if （x,y) = 1. 核心想法就是放置了一个阀门

        > check 的方法还是检查 v(f)

    5. 额外的想法：
        > 我们可以用circulation 的想法，给每个day 一个 demand 1，source = -d 
## 5. Image Segmentation（自动扣背景）

    1. 像素当成 node，有4个无向 neighbor，每个点有 a，b 两个值，分别代表foreground，background的概率，外加一个惩罚的项 p(i,j) 假如i，j是不同类别；假如让 n 划入 A 这个partition 比如是前景，那么得分a，否则得到b分，我们就有了一个目标函数

    2. 找一个割：最大化：q(A,B) = sum(a in A) + sum(b in B) - sum(p(i,j), i，j 不同类别)
        
        1. 先将最大化问题转化为最小化问题
            > 单纯只是转化为最小化并不难：sum(a in A) = sum(a+b in A) - sum(b in A)
            > 最终：q'(A,B) = min(sum(b in A) + sum(a in B) + sum(p(i,j),boundary))
            > 难的是，怎么和图联系起来！！！

        2. 将无向图改为有向图，然后reduction到minimum cut 问题

            > 初步想法：s -> 左边 N*N -> 右边 N*N -> t
            > capacity(ai) + capacity(bi) + 点点之间，相连就有


