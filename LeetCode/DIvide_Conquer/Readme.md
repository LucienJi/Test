# Divide & Conquer

1. 分治法的核心：
    * 将原问题分为 **独立** 两个问题
    * 最好分割得平均

2. 分治的分析：讨论
    * Induction:
        > T(n) = n + 2*T(n/2)

        > 猜一个答案，然后induction

    * Master theorem：
        > T(n) = kn ， 这是基础case

        > T(n) = a * T([n/b]) + f(n),  分割成 a 个小问题，每个小问题的规模是 n/b, 额外计算是 f(n)

        > 主要关注 分割 和 分割后规模 c = logb(a), 大于1说明分割得多，但是问题规模还是很大

        > 若 f(n) = O(n^d), d < c, T = O(N^c), [f(n) negligible]

        > 若 f(n) = O(n^c), 规模一致， T(n) = O(n^c * log(n))

        > 若 f(n) = O(n^d), d > c, T(n) = O(f)

## 实战案例

### 归并排序

1. Pseudocode
```
MergeSort(a):
    MergeSort(a[0:n/2])
    MergeSort(a[n/2:n])
    Merge(a[0:n/2], a[n/2:n])
```

2. 分析
    > Termination: MergeSort(a[0:n]) terminate 因为 ， MergeSort(a[0:n/2]) and MergeSort(a[n/2:n]) 都terminate

    > Correctness: induction, 同上述论述

    > Time Complexity: T(n) = 2*T(n/2) + n-1 
    * Master Theorem: c = 1, f(n) = n-1, O(NlgN)
    * Induction = 发现 T(n) = cnlgn, = 2 * cn/2 * lg(n/2) + n-1, 符合

### Karatsuba‘s Trick： 快速乘法

1. 问题描述：
    * a = [a1,a2,a3,,,an], b = [b1,b2,b3,,,bn], i 为 对应10进制的系数

    * 简单算法：对位乘法 + 加法 = O(N^2)

    * 看似厉害的DC ： a = [ai,ai+1,,,an] * 10^(i-1) + [a1,a2,,ai-1],但是划归为 4个 n/2 规模的乘法，c = 2, 但是额外操作 是 n， 所以主定理钙素我们取决c = 2，还是 O(N^2)

2. 快乘技巧：子问题分成3个
    * x = xa + xb, y = ya + yb

    * xy = xa * ya + xb*yb + ((xa + xb)*(ya + yb) - xa*ya - xb*yb) 

### Closest Points in the plane

1. 问题描述：
    * n 个点 （xi,yi), 然后找到距离最近的p,q

    * Core Idea: 分割一下plane，然后各取半平面的最近点和距离

    * 思考，还有cross line 的最近点，但是不用全部遍历，只用看比 subproblem 近的case

2. Pseudocode

```python
ClosestPair(P):
    # P 是个数列，数列里的内容是点(x,y)
    # Px 也是数列，数列是点，排序依据是 x 的大小
    Px = sort(P) according x
    Py = sort(P) according y

    return ClosestPairRec(Px,Py)
```

```python
ClosestPairRec(Px,Py):
    if 数量 < 3:
        硬算
    else:
        # 分割x轴
        Qx = Px[0:n/2]
        Rx = Px[n/2:n]
        # 从 Py 里找到对应的 y 轴
        Qy = Py[in Qx]
        Ry = Py[in Qy]

        (q0,q1) = ClosestPairRec(Qx,Qy)
        (p0,p1) = ClosestPairRec(Rx,Ry)

        # subproblem 的最近值
        eps = min(d(q0,q1),d(p0,p1))

        # 中间线
        l = Qx[n/2] 
        S = 从 Py 中 提取所有 x 值 in [l-eps,l+eps] # S 中的个数有限，S[0,1,,,n]中的值点 y 值是排序的！！
        for i in len(S)-1:
            s = S[i] # 可能的最近点 S[i+j] ,j < 7,不会很多, 只用考虑 j>0的，没必要看先前的点，因为一直向前看
            for j in range(min(7,len(S)-1)):
                record the min d(S[i],S[i+j])

        return 最小的距离的 pair

```

3. 分析

    * 排序O(NlgN)
    * 主要看额外操作，思考以一个点周围 eps 的距离画大正方形，所以最多只有 7 个点，额外操作 最多 7n
    * T(n) = 2*T(n/2) + c*n 
        > T(n) = O(NlgN)



### Selection Problem： Kth 问题

1. 从array 里跳出 kth 个数

2. 思想：
    * 排序 + 选择 = O(NlgN)

    * 根据pivot分成两个子问题

3. Pseudocode
```python
Selection(a,k):
    chose p in a # randomly 很好
    b,c =  split(a,p) # b 中只有纯小于p，c 中只有纯大于p
    if len(b) == k-1:
        return p
    elif len(b) < k-1:
        return Selection(c,k-len(b)-1)
    else:
        return Selection(b,k)
```

4. 分析

    * 假如能二分问题
        > T(n) = T(n/2) + n, 取决于 f(n) = n