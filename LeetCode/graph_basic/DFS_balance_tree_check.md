# 描述
实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。


示例 1:
给定二叉树 [3,9,20,null,null,15,7]
    3
   / \
  9  20
    /  \
   15   7
返回 true 。
示例 2:
给定二叉树 [1,2,2,3,3,null,null,4,4]
      1
     / \
    2   2
   / \
  3   3
 / \
4   4
返回 false 。

# 核心思想

1. 递归的方式来计算每个节点的高度height
    > height(root) = max(height(left),height(right))+1

2. 为什么是DFS？
    * 因为每次都要先到leaf处，然后返回更新父节点信息
    * DFS 在回溯过程中更新接点高度保证了每个节点只访问一遍
    * 假如使用 全局变量 balance，则可以更加快的返回

# Code

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

    int height(TreeNode* node){
        int left_height = 0;
        int right_height = 0;
        if(node->left==NULL && node->right==NULL){
            return 1;
        }
        if(node->left != NULL){
            left_height = height(node->left);
        }
        if(node->right != NULL){
            right_height = height(node->right);
        }
        if(right_height==-1 || left_height==-1){
            return -1;
        }else{
            if(abs(right_height-left_height)>1){
                return -1;
            }else{
                return max(right_height,left_height)+1;
            }
        }

    }
    bool isBalanced(TreeNode* root) {
        if(root == NULL){
            return true;
        }

        if(height(root)==-1){
            return false;
        }else{
            return true;
        }

    }
};
```