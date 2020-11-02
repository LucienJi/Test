## 描述
从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

 

例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回：

[3,9,20,15,7]

## CPP

1. queue
2. 一层层填装，刚好按层输出

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
    
    vector<int> levelOrder(TreeNode* root) {
        vector<int> res;

        if(root == NULL)return res;
        queue<TreeNode*> q;
        res.push_back(root->val);
        if(root->left != NULL){
            q.push(root->left);
        }
        if(root->right!= NULL){
            q.push(root->right);
        }

        while(!q.empty()){
            TreeNode* tmp = q.front();
            res.push_back(tmp->val);
            if(tmp->left != NULL){
                q.push(tmp->left);
            }
            if(tmp->right!= NULL){
                q.push(tmp->right);
            }
            q.pop();

        }

        return res;


    }
};
```