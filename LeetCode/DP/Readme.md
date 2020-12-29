# Dynamic Programming



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