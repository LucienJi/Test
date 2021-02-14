# Stable Marriage
1. 问题描述：
    * 两个 Set G，F
    * g in G, g 有linear preference， f1 <g f2
    * f in F 也有

    * 什么是 matching？ ： edge set （u,v） ，u in G，v in F, 且没有相交
        
        * perfect matching: 就是对的，每个g 和 f 都出现了一次
        * stable mathching:  for all (f,g) in M, (f',g') in M, 不存在 可以形成更稳定的 （f,g'）， 也就是说 g' >f g and f >g' f'

2. 如何寻找算法？
    * 现有问题的另一种描述
    * generic method： Divide and Conquer & DP & Greedy

## Gale-Shapley Algo
1. 有一种 greedy + iterative improvment

```python 
M = {}

while(存在 g not in M){
    for f in g.preference list: ##降序查看 , f the most preferred woman not asked yet for g
        if(f not in M){
            M = M + （g,f)
        }else if ((f,g') in M){
            if f prefer g rather g'
            remove (f,g') in M
            make (f,g) in M
        }
}
return M
```

## 分析基础： Invariant

> Invariant1: Woman Improve : 每次迭代，女性的满意程度（couple 所处于的 order）只会上升.
因为只有更好的partner才会使f更改

> Invariant2: Each man ask each woman at most once: 女性两种：有couple，没couple的。有couple的，只会要更好的，所以问完一次后，假如形成couple，之后又遇见了，那么她遇见了更好的他。

> Invariant3: M always is a matching: 这是用 loop invariant 来证明，（A）初始是 M = {}, 合法matching. (B) 假设在while loop 开始时，invariant3 满足的，也就是说 M 是matching。 (C) 新加入的（g,f）,其中g原本不在 M中，f 要么单身，要么刚分手。所以成立

> Invariant4: 假如 (f,g) in M,那么 for all f' >g f， 这些 f' 都跟了更好的男人。 既然f,g 在一起了，而 f'更有吸引力，所以g一定是先询问的f'，但是最终f,g 在一起了，说明f'当时拒绝了g。

1. Termination
    > Lemma 1: 最多 n^2 次，因为 invariant 2

2. Correctness：
    > Lemma 2: 代码完结 且 perfect mathching（没说是stable）： 因为 Invariant 3 和 Lemma 1

    > Lemma 3: Stable. Invariant 4 导致我们永远找不到instable 的例子

3. Complexity:
    > O(N^2)


## 其他案例

### Stable marriage variant

1. n 个 candidate，m 个 company， sum(ni) for i in m = n,也能形成perfect matching

2. 简单idea：照抄原版，但是 company 自己也按list preference 来安排自己加进来的 candidate，每次新加candidate，都删除那个做不prefer的。 **缺点，这是你需要重新证明correctness**

3. 棒棒的reduction：
    > 复制 company 使得其凑满 n 个 ，然后 candidate的preference 也扩充到 n 个，这样就变成了 普通的 stable mathing

### Incomplete preference

1. 拓展有关preference的定义:
    1. 在preference 中加入自己，因此 unstable的比较中也会代入自己，可能 (f,f) 比 (f,g) 更加合适

2. 性质：
    1. 假如 M 是个stable matching，人们不会和比自己差的人couple。 证明用最简单的反证法。
    2. 可以使用原先代码：
        * terminate 还是一样的，每个proposal只会发生一次
        * matching 和 stable 都还是相同的，但是你的排序list不是满的，所以可以不perfect


## K-sum 问题

1. 问题描述：给定目标 s,给定 array S

2. 找到index 满足： si +++ sn = s

3. 2 - sum 问题：
    
    * 快排 + 滑窗 O(NlgN)
    ```code
    sort(S)
    i = 0
    j = s.size - 1
    while i < j:
        if S[i] + S[j] == s, return i,j
        
        else if S[i] + S[j] < s :
            i ++
        else :
            j --
    return False
    ```

4. 3 - sum 问题
    * 很重要的点：这个判断是 S[i] 是否参与在triple中，假如S[N],,S[i] 都测试过了，那么接下来的测试只需要测 S[0:i-1]的

    * 大致 是O(N^2)

```
sort(S)
for(int i = N;i>1;i--):
    if 2-sum(s - S[i]) return true

return false
```

5. 4 - sum 问题（难了很多）

    * 对 n 个元素 任取2个，得到 n*(n-1)/2 个 triplet，(si+sj,i,j)

    * 将 n 个 元素的 4-sum 问题转化为 n^2 的 2-sum 问题，排序 O(N^2lgN^2)

    * 使用2-sum在新S'上，但是要check，不可以取两个（i，j），(m,n)，indice 不能有重复

