# Dynamic Programming

1. DP 核心： 
    > 寻找最优解的结构特征

    > 递归的定义最优解的值, 自底向上的计算方式

    > 利用计算出的信息构造最优解

2. 我们必须搞清楚 问题 + 子问题 的依赖关系

## 简单例子

### 切板
1. 总长 n，不同切割的单价 P[i]

2. 定义最优解： res[n] = max(res[i] + P[n-i]), for i = 0,1,2,,,n-1

3. 依赖关系是 res[n] 依赖于前面全部 res[0],,,res[n-1],

4. Attention: 不要用recursion，要用iteration
```python
rse[0] = 0
res[1] = p[1]
for(int i = 1;i<=n;i++){
    tmp_res = 0;
    for(int j = 0;j<i){
        if(res[j] + P[i-j] > tmp_res){
            tmp_res = res[j] + P[i-j];
        }
    }

    res[i] = tmp_res;
}

return tmp_res[n];
```

5. Attention: 一定要尝试 track solution！！
我要去记录： n 时我切多少，cut[n],然后，还剩 n - cut[n], 下次找 cut[n-cut[n]]

```python
rse[0] = 0
cut[0] = 0
res[1] = p[1]
cut[1] = 1

for(int i = 1;i<=n;i++){
    tmp_res = 0;
    for(int j = 0;j<i){
        if(res[j] + P[i-j] > tmp_res){
            tmp_res = res[j] + P[i-j];
            cut[i] = i-j
        }
    }

    res[i] = tmp_res;
}

return tmp_res[n],cut
```

### Matrix Multiplication

1. 给定 A* B *C *D *E.... 找一种乘法的结合律，可以最小的计算量

2. 转移方程： mul[i,j] : 从 0 ✖️到n 位，= min( mul[i,k-1] +  mul[k,j]) + ai * ak-1 * aj+1 for i = 0，，n; mul[i,i] = 0

3. 怎么记录solution？我猜测：sol[i,j] = k ，k 是上述那个最优风格， 举例 sol[0,n] = k，有一个括号：（）k位（），找sol[0,k] 和 sol[k,n]

### Knapsack 

1. n 个问题，有wi 重量 和 pi 的价值，我们有重量限制：Wmax!!! 我们拓宽任务的规模：原先只track 使用第几个物品，你还有track 可以使用的重量

2. 转移方程： res[i,w] = 只看前 i 个物品,我们用 w 的上限，要么拿 第i个，要么不拿：
    > res[i] = max(pi + res[i-1,w-wi], res[i-1,w])

3. Attention：记录最优解：sol[i,w] = 1，0  是指我现在拿的东西，假如是 0 在w 的限重下，我不拿 i 号物品

4. 再优化！！！！观察问题依赖图，你发现这是res[i,w]依赖于res[i-1,w],所以reduce 到 O(W)空间


## 经典案例

1. 给定 list a1,a2,a3,,,, S = sum(ai) even, 判断能否分成 2（升级版是 k 份）等大的subset

    * 惊了个大呆：此题就是 “ Reduction to a well-kown” problem,将问题转化为knapsack 问题，背包额度是S/2，每个元素 weight 是ai，profit 是ai
    * 为什么这样转化？ weight 保证你的解不会大于 S/2, 同时knapsack算法会最大化收益，因此会尽可能的得到接近S/2 的答案，去check最后的解是不是S/2
    * 转移方程: w[i,W] = max(w[i-1,W],ai + w[i-1,W-ai] if W-ai >0),第i的商品那还是不拿

2. 类似working efficiency 的问题:
    * 给定 x = [1,,,,n(n+1)/2], 判断是否存在 S subset of [1,,,,n]， sum(S) = x
    * 假如 x <= n，那么直接就是对的
    * 假如 x >= n,  a[i,x] = a[i-1,x] or a[i-1,x-i] , a[i,j] 表示使用 小于等于 i 的数 能够decompose x

3. 乱入的分治算法：
    * 给定 a1,a2,,,,,an, n 是 4 的倍数，询问，是否能够找到所有 rank [0.25n,0.75n] 只用 O(NlgN)的比较次数？
    * 先用 kth 算法，线性找到 n/4 和 3/4 n 的数，再加上linear scan，总时长 O(N)
    * 

4. DP with game 切披萨问题
    * n 个 不规则披萨，你先开始选，第一块任意选，但是第二块必须从缺口开始选，这就意味着，之后每一次，你和你的对手都有2种选择，求解最优选择（假设你的对手也是最优选择）
    * 牛逼轰轰的 算上对上的DP：
        * DP 的初始步骤，你的选择，以及它产生的子问题：
        * 披萨是个缺了的圆，假设，[Si,,,,Sk],你的选择是从Si，Sk 中选择一个：
            * v[Si,Sk] = max(Si + ,,,+ Sk -v[Si,Sk-1], Si+,,+Sk - v[Si+1,Sk])
            * 第一种是你拿了 Sk, 所以你的收益是，Si 到Sk 之和，减去，你对手面对 Si 到 Sk-1的收益总和