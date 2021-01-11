## 描述

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

## CPP

1. 排序链表： 节点应从小到大排序，因此应使用 *中序遍历* “从小到大”访问树的节点；
2. 双向链表： 在构建相邻节点（设前驱节点 prepre ，当前节点 curcur ）关系时，不仅应 pre.right = curpre.right=cur ，也应 cur.left = precur.left=pre 。
3. 循环链表： 设链表头节点 headhead 和尾节点 tailtail ，则应构建 head.left = tailhead.left=tail 和 tail.right = headtail.right=head 

> 什么是中序遍历？就是可以满足在排序二叉树中实现 小 中 大 的访问方式

```cpp
void dfs(TreeNode* root) {
    if(root == null) return;
    dfs(root->left); // 左
    print(root->val); // 根
    dfs(root->right); // 右
}

```
> 算法流程：
* dfs(cur): 递归法中序遍历；
    * 终止条件： 当节点 curcur 为空，代表越过叶节点，直接返回；
    * 递归左子树，即 dfs(cur.left) ；
    * 构建链表：
        * 当 prepre 为空时： 代表正在访问链表头节点，记为 headhead 。
        * 当 prepre 不为空时： 修改双向节点引用，即 pre.right = curpre.right=cur ， cur.left = precur.left=pre ；  [cur 永远指向当前最大的，pre就跟着cur更新]
        * 保存 curcur ： 更新 pre = curpre=cur ，即节点 curcur 是后继节点的 prepre ；
    * 递归右子树，即 dfs(cur.left) ；

* treeToDoublyList(root)：
特例处理： 若节点 rootroot 为空，则直接返回；
初始化： 空节点 prepre ；
转化为双向链表： 调用 dfs(root) ；
构建循环链表： 中序遍历完成后，headhead 指向头节点， prepre 指向尾节点，因此修改 headhead 和 prepre 的双向节点引用即可。
返回值： 返回链表的头节点 headhead 即可。

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;

    Node() {}

    Node(int _val) {
        val = _val;
        left = NULL;
        right = NULL;
    }

    Node(int _val, Node* _left, Node* _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
public:
    Node* pre;
    Node* cur;
    Node* head;
    void dfs(Node* root){
        if(root){
            dfs(root->left);
            if(pre==NULL){
                cur = root;
                head = root;
                pre = cur;
            }else{
                cur = root;
                cur->left = pre;
                pre->right = cur;
                pre = cur;
            }
            dfs(root->right);

        }
    }
    Node* treeToDoublyList(Node* root) {
        
        if(root == NULL){
            Node* tmp;
            return tmp;
        }
        dfs(root);
        head->left = cur;
        cur->right = head;
        return head;
        
    }
};
```