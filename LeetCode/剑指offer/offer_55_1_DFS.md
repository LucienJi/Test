## 描述

输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：

给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

## CPP

1. 写好递归公式： height(root) = 1 + max(height(leftchild),height(rightchild));

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int maxDepth(TreeNode* root) {
        if(root==NULL)return 0;
        return down_height(root);

    }
    int down_height(TreeNode* node){
        if(node->left==NULL && node->right == NULL){
            return 1;
        }
        if(node->left==NULL){
            return 1 + down_height(node->right);
        }
        if(node->right==NULL){
            return 1 + down_height(node->left);
        }
        return 1 + max(down_height(node->left),down_height(node->right));
        
    }
};
```