## 描述

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

 

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

## CPP

```cpp
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        if(s.size()==0){
            return 0;
        }
        unordered_map<char,int> dic;  // 记录存放，，其实可以用<char,bool>
        queue<char> q1;       // 用一个queue来存放当前正在考虑的字符串
        int res = 0;
        int max_help = 0;
        int length_q2 = 0;   // 遇到重复时：dequeue，知道把重复的元素也de了，同时计数，同时在hashmap中剔除相应出现
        char ele,tmp;

        for(int i = 0;i<s.size();i++){
            tmp = s[i];
            q1.push(tmp);
            if(dic[tmp]==0){
                dic[tmp]++;
                if(res > q1.size()){
                    res = res;
                }else{
                    res = q1.size();
                }
            }else{
                while(q1.front()!=tmp){
                    ele = q1.front();
                    length_q2++;
                    dic[ele] = 0;
                    q1.pop();
                }
                ele = q1.front();
                length_q2 ++;
                dic[ele] = 1;
                q1.pop();
                if(q1.size()>length_q2){
                    max_help = q1.size();
                    res = max(res,max_help);
                }else{
                    max_help = length_q2;
                    res = max(res,max_help);
                }

                length_q2 = 0;
            }

        }
        return res;

    }
};
```

## 分析
1. 首先是queue来描述连续字符串，灵感来源于动归的最大和，任务分成两步：找到已s[i]结尾的最长段的值；记录最大值
2. 用了hashmap，空间套不掉O(N),但是查询就很快了，时间上，平均下来，，也是O（N）吧？