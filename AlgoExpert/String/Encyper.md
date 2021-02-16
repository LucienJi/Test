# Caesar Cypher Encryptor

1. 对输入的string 中的每个char 进行key 位平移，然后输出新string
2. 核心是理解char 和int 的等价，vector<char> 到string 的转化

# Code

```cpp
using namespace std;

char getLetter(char letter,int key);
string caesarCypherEncryptor(string str, int key) {
  // Write your code here.
	vector<char> res;
	key = key%26; // 关键，，这里容易忘
	for(int i =0;i<str.length();i++){
		res.push_back(getLetter(str[i],key));
	}
  return string(res.begin(),res.end());
}

char getLetter(char letter, int key){
	int new_letter = letter + key;
	if(new_letter <= 122){
		return new_letter;
	}else{
		return 96 + new_letter%122;
	}
}
```