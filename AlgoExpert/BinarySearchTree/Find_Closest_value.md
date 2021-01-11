# 描述
输入：一颗BST，和一个target value
输出：距离target 最近的树中元素

# 核心思想

1. BST 的特点：假如target 和 node 比较大小，发现 target 小于 node，那么根据 BST 的特性，我们没有必要去查询node 右侧的树，因此直接删掉一半的选项
2. 我刚开始遇到的疑惑：我知道怎么下降，但是有可能出现
    > 父节点 比 子节点 更加接近target，难道我要回溯上去？
    > 答案是，记录下你的 closest node 和目前 最小的差值 就行，然后一直往下走，直到node 变成 null
    > 只用constant 的space 就能实现记录信息

# Code
1. Iterative 版，不会增加额外的空间开销
```cpp
## Average O(lgN),worsest case O(N): one branch tree
## iterative : space O(1)
class BST {
public:
  int value;
  BST *left;
  BST *right;

  BST(int val);
  BST &insert(int val);
};



int findClosestValueInBst(BST *tree, int target) {
  // Write your code here.
	int min_difference = std::numeric_limits<int>::max();
	int closest_value = tree->value;
	BST* node = tree;
	int value;
	while(node!=NULL){
		value = node->value;
		
		if(abs(target-value) < min_difference){
			min_difference = abs(target-value);
			closest_value = value;
		}
		if(target < value){
			node = node->left;
			
		}else if(target>value){
			node = node->right;
		}else{
			return closest_value;
		}
		
	}
  return closest_value;
}

```

2. recursive 版，简单

```cpp
## Average O(lgN),worsest case O(N): one branch tree
## iterative : space O(1)
class BST {
public:
  int value;
  BST *left;
  BST *right;

  BST(int val);
  BST &insert(int val);
};



int findClosestValueInBst(BST *tree, int target) {
  // Write your code here.
	int min_difference = std::numeric_limits<int>::max();
	int closest_value = tree->value;
	BST* node = tree;
	int value;
	while(node!=NULL){
		value = node->value;
		
		if(abs(target-value) < min_difference){
			min_difference = abs(target-value);
			closest_value = value;
		}
		if(target < value){
			node = node->left;
			
		}else if(target>value){
			node = node->right;
		}else{
			return closest_value;
		}
		
	}
  return closest_value;
}

```