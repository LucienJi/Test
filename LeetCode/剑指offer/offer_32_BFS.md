## 描述

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [20,9],
  [15,7]
]

## CPP

1. 每一层一个vector 说明了这是 BFS 而不是DFS
2. 因为隔层更换顺序，所以选择使用 两个 stack 分别记录，由于 stack 的先进后出就符合了更换顺序的要求
3. 值的注意的是：读取左右child 的顺序在每一层是不同的是不同的

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
    vector<vector<int>> levelOrder(TreeNode* root) {
        
        vector<vector<int>> res;
        if(root == NULL) return res;
        stack<TreeNode*> left;
        stack<TreeNode*>right;
        left.push(root);
        bool done = false;
        while(!done){
            if(!left.empty() && right.empty()){
                vector<int> tmp_res;
                while(!left.empty()){
                    TreeNode* tmp = left.top();
                    left.pop();
                    tmp_res.push_back(tmp->val);
                    if(tmp->left != NULL)right.push(tmp->left);
                    if(tmp->right != NULL)right.push(tmp->right);

                }
                res.push_back(tmp_res);
            }else if(!right.empty() && left.empty()){
                vector<int> tmp_res;
                while(!right.empty()){
                    TreeNode* tmp = right.top();
                    right.pop();
                    tmp_res.push_back(tmp->val);
                    if(tmp->right != NULL) left.push(tmp->right);
                    if(tmp->left != NULL) left.push(tmp->left);
                }
                res.push_back(tmp_res);
            }else{
                done = true;
            }

        }

        return res;

    }
};

```