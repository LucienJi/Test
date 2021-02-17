# Youngest Common Ancestor

1. 输入root，child1，child2， 找到最小的公共节点

# Idea

1. 就是回溯然后相同就输出，但是需要提前做一步计算深度，让深的那个先跑一段路

# Code

```cpp
#include <vector>
using namespace std;

class AncestralTree {
public:
  char name;
  AncestralTree *ancestor;

  AncestralTree(char name) {
    this->name = name;
    this->ancestor = nullptr;
  }

  void addAsAncestor(vector<AncestralTree *> descendants);
};

int getDepth(AncestralTree *node, AncestralTree *root){
	int depth = 0;
	while(node != root){
		depth++;
		node = node->ancestor;
	}
	return depth;
}

AncestralTree *helper(AncestralTree *lownode,AncestralTree *highnode,int depth){
	while(depth>0){
		lownode = lownode->ancestor;
		depth--;
	}
	while(lownode != highnode){
		lownode = lownode->ancestor;
		highnode = highnode->ancestor;
	}
	return lownode;
	
}

AncestralTree *getYoungestCommonAncestor(AncestralTree *topAncestor,
                                         AncestralTree *descendantOne,
                                         AncestralTree *descendantTwo) {
  // Write your code here.
	int d1 = getDepth(descendantOne,topAncestor);
	int d2 = getDepth(descendantTwo,topAncestor);
	
	if(d1 > d2){
		return helper(descendantOne,descendantTwo,d1-d2);
	}else{
		return helper(descendantTwo,descendantOne,d2-d1);
	}
		
}
```