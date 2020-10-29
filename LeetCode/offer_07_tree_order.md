## 描述
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7


## CPP

1. 自己版本，有点问题
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
    vector<int> preorder;
    unordered_map<int,int> dic;

    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        this->preorder = preorder;
        for(int i = 0;i<inorder.size();i++){
            dic[preorder[i]] = i;
        }

        return recur(0,0,inorder.size()-1);

    }
    TreeNode* recur(int root_index, int left, int right){
        if(left>right){
            return nullptr;
        }
        int r = this->preorder[root_index];
        int index = dic[r];
        TreeNode* root = new TreeNode(r);
        root->left = recur(index+1,left,index-1);
        root->right = recur(root_index+(index-left)+1,index+1,right);
        return root;


    }
};
```

2. 莫名其妙，，这个就不会有多余的null

```cpp
class Solution {
public:
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        this->preorder = preorder;
        for(int i = 0; i < inorder.size(); i++)
            dic[inorder[i]] = i;
        return recur(0, 0, inorder.size() - 1);
    }
private:
    vector<int> preorder;
    unordered_map<int, int> dic;
    TreeNode* recur(int root, int left, int right) { 
        if(left > right) return nullptr;                        // 递归终止
        TreeNode* node = new TreeNode(preorder[root]);          // 建立根节点
        int i = dic[preorder[root]];                            // 划分根节点、左子树、右子树
        node->left = recur(root + 1, left, i - 1);              // 开启左子树递归
        node->right = recur(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                            // 回溯返回根节点
    }
};
```