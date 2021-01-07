# 描述
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
`vector<string> table = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};`
示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

# 核心思想
1. 其实就是DFS的基本运用，唯一不同的是，recap DFS的回溯条件是到底了，没有可继续探索node了，然后回到父节点再去探索另一侧
2. 这一次，如何利用计数的方式手动控制向下探索，还有如何记录节点信息

# Code
```cpp
class Solution {
public:
    string tmp;
    vector<string> res;
    vector<string> table = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        
    void DFS(int pos,string digits){
        if(pos == digits.size()){
            res.push_back(tmp);
            return;
        }
        
        int num = digits[pos] - '0';
        string words = table[num];
        for(int i = 0;i<words.size();i++){
            tmp.push_back(words[i]);       // 先放一个字母
            DFS(pos+1,digits);             // 准备放下一个字母，或者把和这个到底的word整个放到res中
            tmp.pop_back();                // Bear in mind that DFS 返回的条件是，没啥可以探索的了，也就是说，以这个字母开头的，没有什么可以再加的
        }
            
    }
    vector<string> letterCombinations(string digits) {
        if(digits.size() == 0){
            return res;
        }
        DFS(0,digits);
        return res;

    }
};
```
