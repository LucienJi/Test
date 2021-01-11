# 描述
给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。

 

提示：

如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
所有的机场都用三个大写字母表示（机场代码）。
假定所有机票至少存在一种合理的行程。
所有的机票必须都用一次 且 只能用一次。
 

示例 1：

输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
输出：["JFK", "MUC", "LHR", "SFO", "SJC"]


# 核心思想

1. 类似于一笔画，根据条件得知，本体必能一笔画
2. 转述问题：有向图的欧拉通路计算方式
3. 补充关于欧拉图的信息：
    * 通过图中所有边恰好一次且行遍所有顶点的通路称为**欧拉通路**。

    * 通过图中所有边恰好一次且行遍所有顶点的回路称为**欧拉回路**。

    * 具有欧拉回路的无向图称为**欧拉图**。

    * 具有欧拉通路但不具有欧拉回路的无向图称为**半欧拉图**

    * 如果没有保证至少存在一种合理的路径，我们需要判别这张图是否是欧拉图或者半欧拉图，具体地：

    * 对于无向图 GG，GG 是欧拉图当且仅当 GG 是连通的且没有奇度顶点。

    * 对于无向图 GG，GG 是半欧拉图当且仅当 GG 是连通的且 GG 中恰有 2 个奇度顶点。

    * 对于有向图 GG，GG 是欧拉图当且仅当 GG 的所有顶点属于同一个强连通分量且每个顶点的入度和出度相同。

    * 对于有向图 GG，GG 是半欧拉图当且仅当 GG 的所有顶点属于同一个强连通分量且

        * 恰有一个顶点的出度与入度差为 1；

        * 恰有一个顶点的入度与出度差为 1；

        * 所有其他顶点的入度和出度相同。

4. 再看我们此题：
    * 难度1：假如是欧拉环路：我们需要考虑字符顺序
    * 难度2：假如是欧拉通路：那么结尾必定是 入度 = 1，出度 = 0
    * 难度3：利用 删除边的技巧，保证在深度搜索下，最后一个一定是唯一的结尾，然后再回溯的过程中加入答案，这样的话回溯的过程也能体现顺序，而且根据PQ的实现，edge都是按照字符串比较大小的

5. Implementation 才是真的精彩：
    * 做一个 dictionary：从字符串转到 pq，pq 的比较方法是string
        * 这是比list adjacent更加方便的edge 储存方式
            * 寻找node 开销是 1
            * 在自己所有邻边中找到最小edge的开销是 o(log(deg(n)))
    * 不使用push_back,push等，而是用了 emplace_back,emplace_back,move，这样就节省了复制搬运的过程，emplace是直接根据内容重新创建

# Code

```cpp
class Solution {
public:
    unordered_map<string, priority_queue<string,vector<string>,std::greater<string>>> edges;
    vector<string> res;

    void dfs(const string&  cur){
        while(edges.count(cur) && edges[cur].size()>0){
            string tmp = edges[cur].top();
            // 删除此边，下次不会再用到了
            edges[cur].pop();
            dfs(move(tmp));
        }
        res.emplace_back(cur);
    }

    vector<string> findItinerary(vector<vector<string>>& tickets) {
        for(auto& i:tickets){
            // 记录下所有的边
            edges[i[0]].emplace(i[1]);

        }
        dfs("JFK");
        reverse(res.begin(),res.end());
        return res;

    }
};
```