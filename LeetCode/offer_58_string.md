## 描述
输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

 

示例 1：

输入: "the sky is blue"
输出: "blue is sky the"
示例 2：

输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
示例 3：

输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

## CPP

1. `auto i :string ` i 可以是 string 诶
2. 重新清空string 用 `s = ""`

```cpp
class Solution {
public:
    string reverseWords(string s) {
        if(s.size()==0)return s;

        vector<string> count;
        string tmp;
        for(auto i :s){
            if(i==' '){
                if(tmp.size()>0){
                    count.push_back(tmp);
                }
                tmp = "";
            }else{
                tmp += i;
            }


        }
        if(tmp.size() >0){
            count.push_back(tmp);
        }
        string res;
        if(count.size()>0){
            res+=count[count.size()-1];
            for(int i = count.size()-2 ;i>=0;i--){
                res+=" ";
                res+=count[i];
            }
        }else if(count.size()==1){
            res = count[0];
        }else{
            res = "";
        }

        return res;


    }
};

```
