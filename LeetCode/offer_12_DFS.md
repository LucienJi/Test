## 描述
请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。

[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]

但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

 

示例 1：

输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true
示例 2：

输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false

## CPP
1. 算法原理：
深度优先搜索： 可以理解为暴力法遍历矩阵中所有字符串可能性。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
剪枝： 在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回，称之为 可行性剪枝 。
2. 算法剖析：
递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
3. 终止条件：
返回 falsefalse ： ① 行或列索引越界 或 ② 当前矩阵元素与目标字符不同 或 ③ 当前矩阵元素已访问过 （③ 可合并至 ② ） 。
返回 truetrue ： 字符串 word 已全部匹配，即 k = len(word) - 1 。
4. 递推工作：
标记当前矩阵元素： 将 board[i][j] 值暂存于变量 tmp ，并修改为字符 '/' ，代表此元素已访问过，防止之后搜索时重复访问。
搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需一条可行路径） ，并记录结果至 res 。
还原当前矩阵元素： 将 tmp 暂存值还原至 board[i][j] 元素。
回溯返回值： 返回 res ，代表是否搜索到目标字符串。

ps： 可以用char[] 和string 相等诶


```cpp
class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int length = word.size();
        //char words[length+1];
        //strcpy(words, word.c_str());  
        for(int i = 0;i<board.size();i++){
            for(int j = 0 ;j<board[0].size();j++){
                if(dfs(i,j,0,board,word)){
                    return true;
                }

            }
        }
        return false;


    }

    bool dfs(int i,int j,int current_word_pos,vector<vector<char>>& board,string& word){
        if(i>=board.size()||i<0||j>=board[0].size()||j<0||board[i][j]!=word[current_word_pos])return false;
        if(current_word_pos==word.size()-1)return true;

        char tmp = board[i][j];
        board[i][j] = '/';
        bool res = dfs(i+1,j,current_word_pos+1,board,word) || dfs(i,j+1,current_word_pos+1,board,word)||dfs(i-1,j,current_word_pos+1,board,word)||dfs(i,j-1,current_word_pos+1,board,word);
        board[i][j] = tmp;
        return res;

    }
};
```