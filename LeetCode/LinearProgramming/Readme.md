# Linear Programming

## 标准式

1. 目标函数 + 约束
```
Object:  max cTx

constrains: 
    Ax <= b
    Ax >= b
    Ax = b

```

2. LP 的问题标准式 = 目标函数是 min， 约束只有小于等于且，变量大于零

    1. 目标函数 乘上符号就可以 min = max 互换了
    2. 映入新变量 xi1 xi2， 来代替变量 xi 
        > xi = xi1 - xi2, 且xi1>0,xi2>0
    3. 用小于等于和大于等于来同时作用等价于 等于
        > Ax = b  is equal to Ax <= b and Ax >= b
    
3. 经典解法：
    1. simplex : 单纯性法（在凸集内找局部最优顶点，由于凸集和线性规划的特征，局部最优顶点 == 全局最优顶点，全局最优顶点必定从属于最优集合）
    2. 椭圆法
    3. 内点法
## 经典问题的LP化

### SSSP: single source shortest path

1. S -> T 版本
```code
min: d[t]

ct:
    d[s] = 0
    d[v] <= d[u] + w(u,v) for all (u,v) in E
```
PS. u 可以是 v 的前驱结点，也可以不是

2. S -> 所有其他点

```code

max sum(d[v] v in V)

ct:
    d[s] = 0
    d[v] <= d[u] + w(u,v) for all (u,v) in E
```
**为什么是 max ？？**

> Intuitive ： d[v] == 所有从 s 到 v 的路径距离下限，我们要不断抬高下限，直到它满足所有路径都大于他，其中还有一条正好是他，因此没有办法继续抬高了

**如何证明我们的转述是对的？**
> 用 d[] 表示真实距离，d 表示所有我解出来的

> 1. 证明d[]是一组可行解 == 满足所有ct
又因为是 目标是 max，所以我们说，我们提升了最优解的下限，d* 必定大于等于d[]

> 2. 证明任意的 d 也就是可行解，都小于 d[]，因此d[]就是最大值。 具体证明还是 induction，对于任意真实路径 v0，v1，v2，，，vl，前l-1个点都满足 d[] 大，那么最后利用 ct 的条件 + 前驱结点的性质，易证

### Maximum Flow

1. 朴素最大流，LP不如FoFu 算法好
```
max sum(f(s,v)) all v in N+(s)

ct:
    sum(f(w,v))  w in N_(v) = sum(f(v,w)) w in N+(v)
    f(u,v) <= c(u,v)
    f(u,v) >=0

```

2. 最优成本流，这个就是LP更好，就是在每个edge增加一个单位成本，约束中增加额定流量，目标函数改为最小化成本


```
min sum(f(u，v)*a(u,v) ) all (u,v) in E

ct:
    sum(f(w,v))  w in N_(v) = sum(f(v,w)) w in N+(v)
    f(u,v) <= c(u,v)
    f(u,v) >=0
    sum(f(s,v)) all v in N+(s) = demand

```
3. 多商品流：引入多个商品 i，每个商品有自己的demand i，且edge上用共享的capacity，每个商品i自己也要守恒
关键在于：目标函数其实 写个 0 就好了，因为目的是找到可行解

## 整数线性规划 - NP

1. 举个例子：minimum vertex cover problem (最少节点数，覆盖所有的edge)

2. 表示方法很特殊 X = [0,1,1,1,0], 用binary表示哪些vertex会被选择

```
Min |X|1 = sum(xi)  i in V

ct:
    xi in {0,1}
    xu + xv >=1 for (u,v) in E
```
### ILP VS LP ? 什么时候可用LP 解决ILP？
1. 经典案例：Interval schedule

    1. 问题的LP 描述： Aj = [sj,tj] j 任务的 开始和结束时间； Jt = {j in [1,n], t in Aj} == 任务的集合，满足在任务 j 包含了 t 时刻，因此 Jt 任务是有交际

```
Max sum(xi)

ct:
    sum(xj) j in Jt
    xj in {0,1}

```

2. Linear Relaxtion
    > 将原先的约束条件 X in {0,1} , 改为 X in [0,1] ,因为更加宽松，所以 ILP 的解，一定属于 LP + relaxation 的解中

    > 换句话说：The Optimal solution values of ILP == Linear Relaxation 【只是value，你别问我解是多少】

3. 什么时候 Linear Relaxation 可以有 integer 解？
    > 当约束矩阵是 TU ： totally unimodular == 所有的子方阵的determinant in {-1,1,0} == 任意两列和的差值的绝对值不超过1

    > 简易版条件： consecutive-ones matrix ：，只有 0，1.且，每行的 1 是连着的


4. 举例 Interval Schedule 的constraint matrix 其实就是TU：
    > 将 task 按照时间结束时间的顺序排

    > 假如 Jt = {1,2,3}, 那么有一个约束： x1 + x2 + x3 <= 1

### Approximation Solution & Integrality Gap

啥意思呢？说白了就是 LP 出来的解，四舍五入变成ILP的解，也有一定的性能保证

1. Minimum Vertex Cover 问题中四舍五入 能有 |y|1 <= |x|1

    > 首先描述构造的过程

    > 其次证明构造满足 ct

2. 什么是 Integrality gap P ？
    > 所有的近似解和最优解的最大比值 <= P

    > 换句话说，我只要找到一种特殊情况，比如比值是2，那么我就能说，integrality gap 至少是2

    > 假如我能证明这种放缩对所有输入都成立，那么我也就找到了一个 P


## LP 实战

### Modelling real-world problem

1. 情况描述：

三种商品 x1 , x2, x3
利润每千克：10，12，14
量产限制千克:20,30,30
单位产量限制：一天60，一天60，一天40

目最大化利润

```
max:  10 * x1 + 12 * x2 + 14 * x3

ct: 
    0 <= x1 <= 20
    0 <= x2 <= 30
    0 <= x3 <= 30

    x1 + x2 + 1.5*x3 < = 60

```



2. 情况描述: 我们学到了，引入新的变量会使得问题变简单
一种商品：利润每千克 10
每日需求:d1,d2,,,,d31
两个员工：日产20 kg 每天，工资 80欧每天（1 euro 0.25 kg）

额外策略1：加班：每人额外生产 0.2 * 20 = 4，加班费 = 80 * 0.2 * 1.5 = 24欧
额外策略2：存储：0.5 欧每千克

目的：每日量产必须大于等于 di,最小化总cost

```
min: 每日基本工资 + 额外产量工资 + 储存费
    20 *2 * 31 + sum( Xi - 40 )*6 + sum (Xi - di)*(32 - i)* 0.5

ct：
    Xi <= 48 ; 每日最大产量
    Xi >= 40
    sum(Xi - di) >= 0 for i = 1 to k, for k =1 to 31 
```

3. 凸多边形的最大内接圆
给定 n supporting line： y = ai * x + bi
前 k 根是多边形下端，后n-k是下端（这很重要，这定义了内部原点的代数距离的正负）
给定点(u,v) 和 y = ax + b ，代数距离 = (v - a*u -b)/sqrt(a^2+1)

```
点到直线： Ax + BY + c = 0 // (u,v)

d = (Au + Bv + c )/(A^2 + B^2)
求法线

```

增加一个variable r，从而避免使用 max min
尽管只max r，但是我们仍然使用 3个 variable
```
max: r

ct: r <= all d((u,v), li)
    r > 0
    (u,v) 在区域内 == （1，，k） d(点，lk) >=0 
                        (k+1,n) d(点，lk) <= 0


```


4. Matching Problem:

给定 N agent ci 的 N 项能力评分表pj
选择一种安排，最大化分数综合

 - 将问题转化为 图论（bipartie matchin）
    
```
Maximum Flow:(但是就不符合一个人选一个)

s - nodes: candidate + poste - t ,, 我尝试假如 circulation 和 demand因为没啥用

```

 - 将问题转化为 ILP

 ```
 变量： xij , 有 N*N variable

 max:  sum( cij * xij)
 ct:
    for all i: sum(xij) <= 1
    for all j: sum(xij) <= 1
    for all i,j xij in {0,1}

 ```

 - 核心问题：任意一个 bipartite graph, 假如 LP Relaxation 有解 == LP 的解中必定包含了 ILP 的最优解（有TU了呗）
    - LP - relaxation 的不同点， ILP 的话每个edge 要么选，要么不选，但是LP-relaxation后，就是最大流问题，那么只能LP就有capacity，未必完全选满
    - 证明1：假设LP最优解中至少有一条边是 non-integer，xe, 那么我们必定能在 图中找到cycle edge set，其中所有的edge的flow 值都是non-integer

    ```
    对于 LP -relaxation 的解，来说，假设有 e1,e2,e3 non-integer，最后一个node 不是 初始node，必定还会连着下一个
    ```
    - 魔改 LP 最优解 x* 变成 x_，给那个非整数cycle中的edge上的flow +- epsilon，（绕一圈的epsilon只和还是合法的）
        - 证明：x_ 是feasible：
            ```
            easy ,因为是cycle，所以总和加起来还是不变的，
            ```
        - 证明：x_ LP optimal
        - 证明：反复迭代，得到ILP optimal
        - 假如 N agent ！= N post， 我们还能构造嘛？



