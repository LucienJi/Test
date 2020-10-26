## 描述

输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。


示例:
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

## CPP


1. 总的想法是：父节点前插在完成任务的子节点上传的向量，继续上传。

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
    bool isleaf(TreeNode* node){
        if(node->left == NULL && node->right == NULL){
            return true;
        }
        return false;
    }                                           // 判断叶子

    vector<vector<int>> find(TreeNode* node, int goal){
        if(isleaf(node)){
            vector<vector<int>> res;
            if(node->val == goal){
                vector<int> path;
                path.push_back(node->val);
                res.push_back(path);            // 唯一终点判断：goal == 叶子.val
                return res;
            }else{
                return res;
            }
        }else{
        
                vector<vector<int>> res;
                if(node->left != NULL){
                    vector<vector<int>> res1 = find(node->left,goal-node->val);  // 内部节点将任务下传给非空子节点， 注意如果叶节点没有完成任务，则上传空向量，父节点丢弃空向量，因为没有完成任务，所以该父节点也上传空向量
                                                                                // 这就导致我的算法经常申请空间，非常慢
                    for(auto i:res1){
                    if(i.size()!=0){
                        i.insert(i.begin(),node->val);
                        res.push_back(i);
                    }
                }
                }
                if(node->right != NULL){
                    vector<vector<int>> res2 = find(node->right,goal-node->val);
                    for(auto i:res2){
                    if(i.size()!=0){
                        i.insert(i.begin(),node->val);
                        res.push_back(i);
                    }
                }
                }
                return res;

            }

        }
    

    vector<vector<int>> pathSum(TreeNode* root, int sum) {
        vector<vector<int>> res;
        if(root == NULL)return res;
        res = find(root,sum);
        return res;

    }
};
```
