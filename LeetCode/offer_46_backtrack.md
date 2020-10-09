## 描述
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

 

示例 1:

输入: 12258
输出: 5
解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 

提示：

0 <= num < 231


## CPP -Backtrack

```cpp

class Solution {
public:

    int backtrack(int rest){   // 主要backtrack函数：体现了决策树的思想？认为返回的是，以rest为root的决策树的leaf个数
        if (rest ==0){
            return 0;
        }
        if(rest<10){
            return 1;
        }
        if(rest < 26){
            return 2;
        }

        // 前三个判断水输入条件判断 == 决策树的 leaf node
        // 尝试递归表示，每次都有两种可能，单个；单个加双个
        // 决策树的可选选项：
        // 取2个，还是取1个
        int candidate2 = rest%100;
        int candidate1 = rest%10;
        // tricky: 5006,506,此时的 06和6代表一种决策
        if (candidate2<26 && candidate2 >0 && candidate1!=candidate2){
            //双children情况
            return backtrack(rest/10) + backtrack(rest/100);
            
        }else{
            //单child
            return backtrack(rest/10);
        }
        

    }
    int translateNum(int num) {
        if(num==0)return 1;     // tricky,,  单独一个零是不能用 backtrack的？
        return backtrack(num);



    }
};

```

## 复杂度分析
关键参数：位数 n

1. 空间的，，基本原地，但是有了recursive，，所以可能大一点？ O(1),或者O(n)
2. 时间的，，`O(n) < O(n-1) + O(n-2)`，，，所以是多少呢？
