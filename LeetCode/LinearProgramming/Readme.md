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

1. 举个例子：
