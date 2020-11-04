## 描述

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

 

参考以下这颗二叉搜索树：

     5
    / \
   2   6
  / \
 1   3
示例 1：

输入: [1,6,3,2,5]
输出: false
示例 2：

输入: [1,3,2,6,5]
输出: true

## CPP方法一：递归分治
1. 后序遍历定义： [ 左子树 | 右子树 | 根节点 ] ，即遍历顺序为 “左、右、根” 。
2. 二叉搜索树定义： 左子树中所有节点的值 << 根节点的值；右子树中所有节点的值 >> 根节点的值；其左、右子树也分别为二叉搜索树。


根据二叉搜索树的定义，可以通过递归，判断所有子树的 正确性 （即其后序遍历是否满足二叉搜索树的定义） ，若所有子树都正确，则此序列为二叉搜索树的后序遍历。
递归解析：
终止条件： 当 i \geq ji≥j ，说明此子树节点数量 \leq 1≤1 ，无需判别正确性，因此直接返回 truetrue ；
递推工作：
划分左右子树： 遍历后序遍历的 [i, j][i,j] 区间元素，寻找 第一个大于根节点 的节点，索引记为 mm 。此时，可划分出左子树区间 [i,m-1][i,m−1] 、右子树区间 [m, j - 1][m,j−1] 、根节点索引 jj 。
判断是否为二叉搜索树：
左子树区间 [i, m - 1][i,m−1] 内的所有节点都应 << postorder[j]postorder[j] 。而第 1.划分左右子树 步骤已经保证左子树区间的正确性，因此只需要判断右子树区间即可。
右子树区间 [m, j-1][m,j−1] 内的所有节点都应 >> postorder[j]postorder[j] 。实现方式为遍历，当遇到 \leq postorder[j]≤postorder[j] 的节点则跳出；则可通过 p = jp=j 判断是否为二叉搜索树。
返回值： 所有子树都需正确才可判定正确，因此使用 与逻辑符 \&\&&& 连接。
p = jp=j ： 判断 此树 是否正确。
recur(i, m - 1)recur(i,m−1) ： 判断 此树的左子树 是否正确。
recur(m, j - 1)recur(m,j−1) ： 判断 此树的右子树 是否正确。



```cpp


class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        if(postorder.empty()) return true;
        int len = postorder.size();
        return recursion(postorder, 0, len - 1);
    }
    bool recursion(vector<int>& postorder, int left, int right)
    {
        if(left >= right) return true; // 没有左子树（比如[5,4,3,2,1]），true
        int root = postorder[right];
        int pos = left; // 必须要设置一个 pos 变量保存 left，不能直接操作 left！
        for(; pos<right; ++pos)
        {
            // 找到右子树起点
            if(postorder[pos] > root) break;
        }
        for(int j=pos; j<right; ++j)
        {
            // 如果右子树部分有小于 root 的，false
            if(postorder[j] < root) return false;
        }
        // 看左子树是否也是后序遍历。如果前面不设置 pos 的话，这里就会是 (postorder,0,left-1)，会严重超时
        if(!recursion(postorder, left, pos - 1)) return false; 
        if(!recursion(postorder, pos, right - 1)) return false;// 看右子树是否也是后序遍历
        return true;
    }

};
```