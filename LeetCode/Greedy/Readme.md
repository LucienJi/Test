## 贪心算法

### 核心思想
1. 什么样的问题适用于贪心算法？贪心特征：
    * 具有最优子结构性质的问题（类似 动态规划）[最优解包含子问题的最优解]
    * 每次做出贪心选择后，只剩下一个子问题需要去求解
    * 贪心的选择可能会依赖于已经做好的选择，但是绝对不会依赖于没有解决的子问题，因此贪心方法一直是自顶向下（和bottom-up的DP不同）

2. 应该如何选择？(往这些方向凑，事实上，贪心的选择有很多，但是不会每个都能有最优解)
    * 直白一点，局部最优选择
    * No wrong = 这个决定的结果一定存在于某一个最优解中
    * No regret = 对后续的影响最小，最多阻遏后面一两个，别什么一下子pass了一堆别人
    * 考虑lower bound，利用lower bound 来设计选择，逼近bound

3. 假如是最优解，如何证明贪心的最优？其实也是设计步骤，按这个步骤设计，就是能得到最优的
    * 将问题转化为：做出一个选择后，只剩下一个子问题需要解决
    * 在作出贪心选择后，必然依旧存在最优解（贪心结果存在于众多最优解中）
    * 贪心选择后的子问题的最优解与先前的贪心选择组合后即可得到原问题最优解

    > 假设 S 是最优解，修改它，使S‘ 满足贪心选择的特征，同样依旧还是最优解

4. p-approximation 近似

    *  A(I) < p OPT(I)
    * I是instance of problem, 其中OPT(I)是最优算法能够得到的解（我们未必能知道OPT的值，但是在证明过程中我们可以假设个别数值和OPT的关系）

## 经典案例
### 活动选择问题
1. 给定n个活动的开始时间 s[i] 和结束时间e[i],要求得到安排出最多的兼容（无冲突）活动
2. 动归的思想：很显然有重叠子问题藏在里面：
    * 做出一个选择：选择 k 活动：c[i,j] = c[i,k] + c[k,j] + 1  [一个选择，两个子问题]
    * c[i,j] = i 活动结束之后，j 活动开始之前我们能排多少无冲突活动
    * 遍历所有可能的k c[i,j] = max(c[i,k]+c[k,j]+1)
3. 贪婪的思想：
    * 也是做出一个选择，但是动归是遍历选择，贪婪是目标明确的那个选择：[优先选择最早结束的活动] == [优先选择最晚开始的活动]
    * > 设 m 是 Sk 中结束最早的活动，那么Sk 的最优解中的某个最大兼容活动子集必定包含m ！！！将可行解中第一个结束任务替换成全局最早结束的那个任务，必能替换，同时保持最优性。
### 单一机器+多任务不同截止时间：最小化迟到时间
1. EDF 策略：earliest deadline first：最先处理最快过期的（类似于我的吃饭策略）

2. EDF O(NlgN) 最优解
    * > 这个的证明方法是：假设一个最优解 S，然后构造S'，使得他至少比S优。然后假设有一组 inversion 是不满足EDF 性质，然后我们改正它，然后计算 lateness 的减小。
### 任务安排--可分割任务版（但是不能两个机器同时做一个任务）
1. n 个 任务，m 个machine，任务j占据p[j]的时间，如何设计安排，使得最晚结束工作的机器的时间（Cmax）最短（minimize the latest machine load），订一个 额定功率。

2. 分析lower bound：
    * 每个machine 至少 sum(C) >= sum(p) 不然活干不完
    * Cmax >= max(p),因为不能同时执行同一个任务
    * Lower Bound = max(max(p),sum(p)/m)

3. McNaughton 解法以此填满每个机器，假如超支了，就匀给下一个机器，每个机器都是额定负载 lower bound
    > 需要证明这种额定功率能完成所有任务
    > 需要证明这种额定功率不会有overlap出现

### 任务安排--不可分割任务版（近似解，有效果保证）

1.  List scheduling: 每次将task布置给machine负载最少的那台上（也是贪婪的想法~） Cmax < 2Cmax*
    > 这个怎么证明呢？利用一个特点，施加在最小负载机器上，因此那个决定最长时间的任务安排，在他安排时他的总时长小于平均值，额外加的那个值优势小于单个任务中最大，max < 均值 + 最大值 < 2*D_理论最优
2.  Longest processing time first(LPT): 先让task按照时长排降序，一次给到负载最少的那台上，Cmax < 4/3 Cmax
    

### 切棒子（非最优解）
1. n 长的棒子，p[i]表示长度i被卖的钱，咋切最多钱

2. 动归思想：
    * c[n] = max_{i<=n}(p[i] + c[n-i])

3. 贪婪思想：
```
Input: C = [[l1,p1],,,[ln,pn]]
Output: res = [[l1,n1],,,[l2,n2]]

ave_c = [p1/l1,,,pn/ln]
res = []
profit = 0
sort ave_c in decreasing order and at the same time, C receive the same operation
for (int i = 0;i < n;i++){
    [l,p] = C[i] 
    ni = int(n/l), choose ni as the biggest numbers of l which we could cut from the rest of bar n
    n = n - ni*l , rest of bar
    res.push([l,ni])
    profit += n1*p
}
return res,profit

```
* Complexity
    * sorting contribute O(NlgN) if we use mergesort
    * tarverse contribute(N) 
    * Final complexity is (NlgN)

* Optimal?( greedy(I) > 0.5 opt(I) )
    * there are some optimal cases: p[n] = n, p[j]<j for j < n
    *  thinking of the first piece you cut using Greedy :
        * max profit < p1/l1 *n
        * if the first piece 's length l1 > 1/2 * n, we can have only 1 l1,the max profit < p1/l1 *n, so if we take l1 > 1/2n , the profit we can gain > 1/2 * p1/l1*n > max profit
        * if the first piece 's length l1 < 1/2 * n, we will have several l1, same reasoning will be applied,ans since  n1 * l1 > 1/2, the profit we can gain > 1/2 * p1/l1 * n
* for each epsilon > 0, we can find some examples which make greedy < (1/2 + epsilon)opt
    * bar length: 1
    * p[0.5  + 0.5*epsilon] = 0.5 + epsilon ; so the ratio > 1
    * p[0.5] = 0.5 ; so the ratio = 1
    * p[0.5 - 0.5*epsilon] = 0; so the ratio = 0
    * greedy returns us: 0.5 + epsilon
    * opt returns: 1

* Optimizing the greedy method with O(N):
    * No need to sort, just a linear choice from C
        * if li < lj and pi/li > pj/li then i'll never use the lj 
        * create a list D to store in decreasing order the possible choice : we just supprime all the lj unqualified !, the  D is created in linear scan


### Set Cover 问题

1. 从 S1,,,Sm m家公司中选 I 家，使I家公司能够覆盖所有城市1~n，同时I的数量最少，每个Si都覆盖一定数量的城市

2. 贪婪思想：O(mn)
    * each iteration Si maximize the uncovered element
    ```

    while(there are still citys no covered){
        Si = max(Si & current_city - current_city)
        current_city = current_city & Si
        solution.append(Si)
    }
    ```

    * if input is in forme of matrix s[i,j] which means company i can cover city j:

        best idea: we don't have to maintain a list n, instead, after we chose the best company at each iteration , we substract 1 city from other company if the best company could cover the city

    ```
    Initialize n = [0,,,,0] means no city convered
    Initialize res = []
    while(sum(n)!=n){

        best_company = 0
        most_covered = 0
        for i = 1 to m:
            covered = sum(s[i] or n) - sum(n)
            if covered > most_covered:
                most_covered = covered
                best_company = i
        n = n or s[best_company]
        res.push(best_company)
    }
    ```
    * worst case: each (m^2)

    * if input is in form of list s1,s2,s3:
        we could implent priority_queue in which the best company comes first,
        but problem is how to measure the best company and substract 1, it's difficult to dynamically maintain the priority_queue



3. 非最优解：
    * 反例：
        * S1 = {1,2,3,4,5,6} ; S2 = {7,8,9} ; S3 = {10}
        * S3 = {1,2,3,9,10} ; S4 = {4,5,6,7,8}
        
