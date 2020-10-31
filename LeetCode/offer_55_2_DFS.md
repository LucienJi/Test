## 描述
输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

 

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
返回 false 


## CPP

1. 第一次遍历：计算各个节点高度，第二次遍历计算是否平衡
2. 找到平衡条件 balance(root) = |leftchild - rightchild| < 2 && balacne(leftchild) && balance(rightchild)

3. 补充技巧：可以通过剪枝来加速上传信息：遍历一次，要么 上传子树高度（说明默认子树也平衡），要么上传-1（说明子树非平衡），同理，根节点也选择上传高度或者-1

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
    bool isBalanced(TreeNode* root) {
        if(root==NULL)return true;
        int height = assign_height(root);
        return balance(root);

    }
    int assign_height(TreeNode* node){
        if(node->left==NULL && node->right==NULL){
            node->val = 1;
            return 1;
        }
        if(node->left == NULL){
            node->val = 1 + assign_height(node->right);
            return node->val;
        }
        if(node->right == NULL){
            node->val = 1 + assign_height(node->left);
            return node->val;
        }
        node->val = 1 + max(assign_height(node->left),assign_height(node->right));
        return node->val;
    }

    bool balance(TreeNode* node){
        if(node->left==NULL && node->right==NULL){
            return true;
        }
        if(node->left == NULL){
            return (node->right)->val <2 && balance(node->right);
        }
        if(node->right == NULL){
            return (node->left)->val < 2 && balance(node->left);
        }
        return abs((node->left)->val - (node->right)->val) < 2 &&balance(node->left) && balance(node->right);
    }
};
```