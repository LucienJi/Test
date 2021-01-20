# DFS

1. 基础DFS，按照先左child的顺序，遍历所有的node

# 核心思想：

1. 对于所有的children，从左往右：
    先存 信息，
    然后对这个子node使用函数

# Code
```cpp
#include <vector>
using namespace std;

// Do not edit the class below except
// for the depthFirstSearch method.
// Feel free to add new properties
// and methods to the class.
class Node {
public:
  string name;
  vector<Node *> children;

  Node(string str) { name = str; }

  vector<string> depthFirstSearch(vector<string> *array) {
    // Write your code here.
		array->push_back(this->name);
		for(int i = 0;i<this->children.size();i++){
			dfshelper(this->children[i],array);
		}
		
    return *array;
  }
	void dfshelper(Node* node,vector<string>* array){
		array->push_back(node->name);
		for(int i = 0;i< node->children.size();i++){
			dfshelper(node->children[i],array);
		}
	}

  Node *addChild(string name) {
    Node *child = new Node(name);
    children.push_back(child);
    return this;
  }
};

```