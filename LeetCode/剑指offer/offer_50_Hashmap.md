## 描述

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

示例:

s = "abaccdeff"
返回 "b"

s = "" 
返回 " "

## CPP
1. `unordered_map<A,B>`
2. char 单字符好像是基础类，所以可以直接输出，不用申明

```cpp
class Solution {
public:
    char firstUniqChar(string s) {
        
        if(s == ""){
            return ' ';
        }
        unordered_map<char,int> hashmap;
        queue<char> q;
        for(int i = 0;i<s.size();i++){
            if(hashmap[s[i]]!=0){
                hashmap[s[i]]++;
            }else{
                q.push(s[i]);
                hashmap[s[i]] = 1;
            }

        }
        char tmp;
        while(!q.empty()){
            tmp = q.front();
            if(hashmap[tmp]==1){
                return tmp;
            }else{
                q.pop();
            }
        }

        return ' ';

    }
};
```