# Longest SubString Without Duplication

1. 输入 stirng，输出最长无重复sub

# 核心思想

1. 不停地动态维护index
2. 无重复：find O(1) 的hashmap
3. 但是hashmap的删除操作是O(N),所以尽量不要求删除

4. > 记录最长的beg 和 end，计入当前的开头beg_index
    > 新加入一个 i，  那么假如 没有重复，新长度就是 i - beg_index + 1
    
    > 小问题是，新加入后可能截断，假如j 是已经被存在hashmap的，也就是要用新的开头作为 beg_index

    > 最让我喜欢的就是，只有 j 在 beg_index 之后的情况才需要更改，因为我们不想删除之前的记

# Code

```cpp
using namespace std;

string longestSubstringWithoutDuplication(string str) {
  // Write your code here.
	unordered_map<char,int> store;
	int l = str.length();
	vector<int> indexs{0,1};
	int current_beg = 0;
	for(int i = 0;i < l;i++){
		char c = str[i];
		if(store.find(c)!=store.end()){
			// 说明已经出现过了,可能我们需要截断了，需要判断他是出现在current_beg的左侧还是右侧
			current_beg = max(store[c]+1,current_beg);
		}
		// 动态维护index数组也是技巧之一，每次新加一个元素，都去比较一次，因此在遇到截断时【start，cut，now】，也是只要比较
		// 也是只要比较cut 和 now
		if(indexs[1] - indexs[0] < i - current_beg + 1){
			// 这里index[1],始终是在+1位
			indexs = {current_beg,i+1};
		}
		
		store[c] = i;
	}
	// 记得substr是 首位 + 长度
	string res = str.substr(indexs[0],indexs[1]-indexs[0]);
	return res;
}
```