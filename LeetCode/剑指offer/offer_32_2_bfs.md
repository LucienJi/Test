## 描述
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

 

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
  [9,20],
  [15,7]
]

## CPP

1. 原本我是用两个queue来回接某一层的元素，后来发现这样用一个计数就可以轻易解决了

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

        if(root == NULL)return res;
        queue<TreeNode*> q;
        vector<int> init;
        init.push_back(root->val);
        res.push_back(init);
        if(root->left != NULL){
            q.push(root->left);
        }
        if(root->right!= NULL){
            q.push(root->right);
        }
        int tmp_length;
        while(!q.empty()){
            tmp_length = q.size();
            vector<int> tmp_res;
            for(int i =0;i<tmp_length;i++){
                TreeNode* tmp = q.front();
                tmp_res.push_back(tmp->val);
                if(tmp->left != NULL){
                q.push(tmp->left);
            }
            if(tmp->right!= NULL){
                q.push(tmp->right);
            }
            q.pop();
            }
            res.push_back(tmp_res);
        }

        return res;

    }
};
```