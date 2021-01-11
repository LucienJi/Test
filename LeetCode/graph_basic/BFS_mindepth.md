# 描述
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明：叶子节点是指没有子节点的节点。
# 核心思想
1. 注意这是问你深度，所所以只要访问到leaf就直接输出 depth
2. 注意数据结构queue，不仅可以存储node，还可以存储node 的深度（两个值），push的时候将子节点的深度也加上，这样根据queue的特性一定是先访问到最短的depth的那一层的leaf


# Code
1. 改进的queue版

```cpp
class Solution {
public:
    int minDepth(TreeNode *root) {
        if (root == nullptr) {
            return 0;
        }

        queue<pair<TreeNode *, int> > que;
        que.emplace(root, 1);
        while (!que.empty()) {
            TreeNode *node = que.front().first;
            int depth = que.front().second;
            que.pop();
            if (node->left == nullptr && node->right == nullptr) {
                return depth;
            }
            if (node->left != nullptr) {
                que.emplace(node->left, depth + 1);
            }
            if (node->right != nullptr) {
                que.emplace(node->right, depth + 1);
            }
        }

        return 0;
    }
};
```

2. 不会一下子记录层数，所以用双queue交替保存复杂版

```cpp
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {

public:
    int height = 0;
    bool isleaf(TreeNode* node){
        if(node->left == NULL && node->right == NULL){
            return true;
        }else{
            return false;
        }
    }
    int minDepth(TreeNode* root) {
        queue<TreeNode*> q1;
        queue<TreeNode*> q2;

        if(root==NULL)return 0;
        q1.push(root);
        TreeNode* tmp;
        while((!q1.empty())||(!q2.empty())){
            if(q2.empty()){
                while(!q1.empty()){
                    tmp = q1.front();
                    if(isleaf(tmp)){
                        return height+1;
                    }else{
                        if(tmp->left!=NULL)q2.push(tmp->left);
                        if(tmp->right!=NULL)q2.push(tmp->right);
                    }
                    q1.pop();
                }
                height++;

            }else{
                while(!q2.empty()){
                    tmp = q2.front();
                    if(isleaf(tmp)){
                        return height+1;
                    }else{
                        if(tmp->left!=NULL)q1.push(tmp->left);
                        if(tmp->right!=NULL)q1.push(tmp->right);
                    }
                    q2.pop();
                }
                height++;
            }

        }
        return height;

    }
};
```