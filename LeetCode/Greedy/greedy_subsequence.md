## 描述
给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

## CPP
1. 其实好像是双指针

```cpp
class Solution {
public:
    bool isSubsequence(string s, string t) {
        int n1 = s.size();
        int n2 = t.size();

        int i = 0;
        int j = 0;
        while(i<n1 && j<n2){
            if(s[i]==t[j]){
                i++;
                j++;
            }else{
                j++;
            }
        }
        if(i==n1){
            return true;
        }else{
            return false;
        }
    }
};
```