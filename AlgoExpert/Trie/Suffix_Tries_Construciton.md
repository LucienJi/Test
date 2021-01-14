# Suffix_Tries_Construction
1. 啥是前缀树？一种用于搜索substring的高效数据结构
2. 大致构成：
    * 以string初始化，形成一颗multitree O(N^2)
    * 功能 contain，判断string 中是否有 substring O(L),子字符串长度

3. 举个例子
```py
## 输入 babc
## 树就该是
          handle
          / \   \
         b   a   c
        / \   \   \
       a   c   b   *
      /     \   \
     b       *   c
    /             \
   c               *
  /
 * 
  2
给定的树 B：

```

# Code
1. 大致数据结构：
    * Node 是基础单位，是一个字符串 到 Node 的字典，因为是多叉树所以你不知道会用到多少开销，因此是使用字典
    * 构造方法就是反复遍历
```cpp
#include <unordered_map>
using namespace std;

// Do not edit the class below except for the
// populateSuffixTrieFrom and contains methods.
// Feel free to add new properties and methods
// to the class.
class TrieNode {
public:
  unordered_map<char, TrieNode *> children;
};

class SuffixTrie {
public:
  TrieNode *root;
  char endSymbol;

  SuffixTrie(string str) {
    this->root = new TrieNode();
    this->endSymbol = '*';
    this->populateSuffixTrieFrom(str);
  }

  void populateSuffixTrieFrom(string str) {
    // Write your code here.
		int n = str.size();
		char tmp;
		TrieNode* tmpnode;
		for(int i = 0;i<n;i++){
			tmpnode = root;
			if(tmpnode->children.find(str[i]) == tmpnode->children.end()){
				TrieNode* node = new TrieNode();
				tmpnode->children.insert({str[i],node});
			}
			tmpnode = tmpnode->children[str[i]];
			for(int j = i+1;j<n;j++){
				if(tmpnode->children.find(str[j]) == tmpnode->children.end()){
					TrieNode* node = new TrieNode();
					tmpnode->children.insert({str[j],node});

				}
				tmpnode = tmpnode->children[str[j]];
			}
			
			tmpnode->children.insert({this->endSymbol,NULL});
			
		}
  }

  bool contains(string str) {
    // Write your code here.
		
		int n = str.size();
		TrieNode* tmpnode = root;
		for(int i = 0;i<n;i++){
			if(tmpnode->children.find(str[i])!= tmpnode->children.end()){
				tmpnode = tmpnode->children[str[i]];
			}else{
				return false;
			}
		}
		
    return tmpnode->children.find(this->endSymbol)!=tmpnode->children.end();
  }
};
```