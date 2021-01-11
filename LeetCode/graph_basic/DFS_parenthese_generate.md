# 描述
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]

# 核心思想
1. 主要想法有两个：
    * 递归的结构不用去自己构造：保证 左括号永远比右括号先加上
    * 右括号的数量永远比左括号少

# Code
1. 不清楚为什么我的代码一直错

```cpp
/*
class Solution {
public:

    void generateParenthesishelper(int left,int right, string& cur,vector<string>& res,int object_size){
        if(cur.size()==object_size*2){
            res.push_back(cur);
            return;
        }else{
            if(left>0){
                cur.push_back('(');
                generateParenthesishelper(left-1, right, cur,res,object_size);
                cur.pop_back();
            }
            if(right>0 && right < left){
                cur.push_back('(');
                generateParenthesishelper(left, right-1, cur,res,object_size);
                cur.pop_back();
            }
        }
    }
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        string cur;
        generateParenthesishelper(n, n, cur,res,n);
        return res;


    }
*/

class Solution {
    void backtrack(vector<string>& ans, string& cur, int open, int close, int n) {
        if (cur.size() == n * 2) {
            ans.push_back(cur);
            return;
        }
        if (open < n) {
            cur.push_back('(');
            backtrack(ans, cur, open + 1, close, n);
            cur.pop_back();
        }
        if (close < open) {
            cur.push_back(')');
            backtrack(ans, cur, open, close + 1, n);
            cur.pop_back();
        }
    }
public:
    vector<string> generateParenthesis(int n) {
        vector<string> result;
        string current;
        backtrack(result, current, 0, 0, n);
        return result;
    }
};


```