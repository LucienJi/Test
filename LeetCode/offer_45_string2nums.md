## 描述

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

 

示例 1:

输入: [10,2]
输出: "102"
示例 2:

输入: [3,30,34,5,9]
输出: "3033459"

## CPP


```cpp
class Solution {

public: 
    struct{
        bool operator()(string a,string b) const {  // 记得是const！~这是你的比较结果
            return ((a+b) < (b+a));
        }
    }f;         // 注意，structure的命名放在前面居然识别不了，，，

    string minNumber(vector<int>& nums) {
        string res;
        vector<string> num_str;
        for(int i =0;i<nums.size();i++){
            num_str.push_back(to_string(nums[i]));
        }
        sort(num_str.begin(),num_str.end(),f);

        for(int i = 0;i<num_str.size();i++){
            res+=num_str[i];
        }

        return res;


    }
};
```

## What can we learn?

1. 如何用cpp struct来写lambda表达式，真香，注意命名就好了
2. `to_string()`后的比较规则，‘3’ < '30',,,因此还是要重新定义比较，不能直接用字符串比较

