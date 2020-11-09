## 描述

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

 

示例:

输入: a = 1, b = 1
输出: 2


## CPP

1. 重要思路：
> 无进位加法 和 异或运算 规律相同，
> 进位 和 与运算 规律相同（并需左移一位）。因此，无进位和 n 与进位 c 的计算公式如下；
n=a^b
c=a&b<<1
然后有个骚操作就是 n+c 才等于 a+b
所以持续使用 add(n,c)!!直到 c = 0

​	
2. CPP 不支持负数的移位，所以只能

```cpp
class Solution {
public:
    int add(int a, int b) {
        int c;
        while(b!=0){
            c = (unsigned)(a&b)<<1;
            a = a^b;
            b = c;
            
        }
        return a;

    }
};
```
