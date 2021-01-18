# BranchSum

1. 输入一棵树，在每一个leaf节点输出 根节点到此leaf节点的路径和

# 核心思想

1. 简单题：recursion的写法，将父节点sum传参给子节点，假如子节点是叶节点，那么不再传参，直接加入result
2. Time and Space O(N) and O(N)
3. recursion + 传参

# Code

```cpp
using namespace std;

// This is the class of the input root. Do not edit it.
class BinaryTree {
public:
  int value;
  BinaryTree *left;
  BinaryTree *right;

  BinaryTree(int value) {
    this->value = value;
    left = NULL;
    right = NULL;
  }
};

void branchSumshelper(vector<int>& res,int parentsum,BinaryTree *node){
	if(node->left == NULL && node->right == NULL){
		res.push_back(node->value + parentsum);
		return;
	}
	if(node->left != NULL){
		branchSumshelper(res,parentsum+node->value,node->left);
	}
	if(node->right != NULL){
		branchSumshelper(res,parentsum + node->value,node->right);
		
	}
	return;
	
}
vector<int> branchSums(BinaryTree *root) {
  // Write your code here.
	vector<int> res;
	branchSumshelper(res,0,root);
  return res;
}

```