# Randomized Algo

## 基础知识

1. 什么是randomized algo ？ 和以往的O(f(N)) 有啥差别？
    > random 的特性在于算法的执行过程中，你的操作对象是随机,因此面对同样的输入，你也会得到不同的输出，但是，面对不同的输入，基本不存在worst case 的差别。你的复杂度不取决你的输入。

    > 以快排为例，快排的复杂度取决于输入的情况，我们说以输入的分布去计算一个复杂度的期望，这不是随机算法的核心思想，随机算法的核心在于不假设输入是什么，我所计算的期望是对于我的操作过程中引入的随机性。

2. 重要的不等式

通常随机算法会得到性质：
> P( r 次操作对于 n 这个量级的输入 失败的概率) <= f(r,n)

* Markov 不等式：
    > P(X > a) < E(X)/a

* Chebyshev 不等式：
    > P( |X - E(X)|  > a ) < Var(x)/a^2

* Azuma 不等式：
    > X = f(x1,x2,x3,,,xn)

    > P( X - E(X) > a) < exp(-2a^2/ sum(ci)), ci 是f(xi) 的波动的值域


## 基础案例和分析

### Maximum Cut in Graph

1. 对于一个 G = (V,E),选择一个 S 节点集，从而产生 V\E , 对于连接这两个集合的边的数量就是cut

2. NP complete

3. Code： 复杂度永远 N
```code
S = {}
for v in V:
    if random == 1:
        S = S + v
return S
```

    
4. 性能分析

    * 首先定义 e(S) 是cut，我们考虑的是 E(e(S)),考虑的是期望值（也可以考虑 Prob ，你可以用Markov直接联系）

    > 定理：算法的 *期望结果* 至少是0.5 approximation algo

    * 假如存在最优解 e*, 那么我们就去考虑：(u,v)属于 e* ，那么他被选到的概率是多少？
        * P(选 u 不选v + 选 v 不选 u) = 0.5
        * 每条边都可以 0.5 的概率选到，因此 E(e(S))= 0.5 * |E| >= 0.5 *e

    > 推论：任何一个图，最大割的值一定大于 m/2，因为算法的均值是 m/2

    > Derandomization: 不再是随机放，而是准备 A，B 两个集合，将xi 放入我邻居少的那个集合。

### Randomized Selection

1. 选出 第k个最小的数——divide and conquer的经典解答：找到a[p],然后区分为 严格小于 a[p] 和严格大于a[p] 的两个集合，然后重复调用 找到第 k’ 小的

2. 原本 a[p] 每次最好要均分，但是这很难，现在：randomly 选一个， 期望复杂度 T(n) <= 4n

    * T(n) <= max(T(n,k)) ,n 个数，找第 k 个
    * T(n,k) <= n-1(compare) + a[p] 在j位的概率 * max（T(j-1),T(n-j))
    * 再加上 induction，先用 递归式找到一个 T(n) <= c*n 然后 证明

    * intuitive 的解法：每次选p，你有 1/2 的概率下一次只用 3/4 n长度的


## 实际案例

### Minimum Cut

1. 总所周知：这是最大流 + 从source节点所有可达到节点 这套方法是 optimal

2. 随机算法:
```
while |V| > 2:
    randomly choose edge e in E
    contract G = G/e
return the corresponding to the two remaining vertices of G
```
* contract e = (u,v) == 将u，v捏在一起，delete u，v之间所有的edge，然后其余的edge是连在 节点 uv 上

* contract 必然是会产生 multi-edge 的

* a minimual cut C remains minimal if contract edge in C

3. 问题：
    * 假设最优解的 C  = cost = k，求算法第一步选到 edge in C 的概率，再求找到最优解C的概率
    > 重点：假如最小割是 k， 那么说明每个node 的 degree 至少是 k ，否则我们可以孤立一个node

    > 重点：假设每个点的deg 是k，那么我们可以得到，边数至少是 k*n/2, 这个除2不要忘记


    *  求证？ run n*(n-1)*ln(n)/2  次得到最优解的概率至少是 1-1/n
        * 这题是说，，连续使用取最优解的讨论

        * 连续运行，取逆的操作常见，常用万能不等式:
            * 1 - x <= exp(-x), for all x


> Probability amplification: 多次调用 == 提升最优解概率

### Randomwalk + 2-SAT

1. 2-SAT
    * φ = C1 and C2 and,,,Cm
    * Ci = L1 or L2 (最多2个，因为 2-Sat)
    * Li = Xj , 或者 !Xj

2. random walk

```code
choose a = {0,1}^n

while φ(a) = 0:
    select arbitrarily a C which C(a) = 0
    select randomly Xi in C occuring in C
    flip ai (换Xi)

return a

```
* 这可能是死循环！！！

* 假设我们考虑的 2-SAT 有唯一解 s

3. 定义 Hamming Distance d(x,y) = sum(对位不同)
    * di = random walk i 次之后，a 和 s 的hamming distance
    * 证明：
        * P(di+1 = 0 | di = 0) = 1
        * P(di+1 = n-1 | di = n) = 1
        * P(di+1 = j-1 | di = j) >= 0.5
        * P(di+1 = j+1 | di = j) <= 0.5
    

4. 定义 P(a,i) = probability of di = 0 starting from a

    * 证明：假如 d(a,s) = d(b,s) => P(a,i) = P(b,i)
    > induction on i


5. 定义 ha = expection of starting from a and i 次iteration 就达成了 di = 0\
同样的，hj = expected iteration starting from d0(a,s) = j, 初始状态差 j 

    * 证明：
        * h0 = h0
        * hn = 1 + hn-1
        * hj = 1 + 0.5 (hj-1 + hj+1)
        * hj = hj-1 + 2*(n-j) + 1

    > one step analysis in Chain Markov

6. 证明： 
    * walksat， 2n^2 iteration = 解出来的概率是 0.5
    * 2kn^2 iteration = 1 - 1/2^k

### Matrix Product Verification

1. 验证 A * B = C， 大家都是 n*n 的矩阵
    > 这里我反思一下，为啥要用随机{0,1}向量去验证？ D = AB - C， 假如是非零举证，全1向量可能正好是相加为0的情况。也就是说，正是因为引入的随机性，所以我们这个算法不会卡在 A,B,C 的特殊情况上。

    > 将分类讨论的情况看成是 **条件概率** ！！！

2. Code

```code
choose r = {0,1} ^n // n 维向量

compute x = B*r，y = A*x, z = C*r
if y == z:
    return true

```

