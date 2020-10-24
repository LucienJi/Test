## 描述
把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

 

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

 

示例 1:

输入: 1
输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]

## CPP
1. 动归，找到转移方程
单单看第 nn 枚骰子，它的点数可能为 1 , 2, 3, ... , 6，因此投掷完 n 枚骰子后点数 j 出现的次数，可以由投掷完 n−1 枚骰子后，对应点数 j-1, j-2, j-3, ... , j-6 出现的次数之和转化过来。
2. 下限每次加一。上限每次加六

```cpp
class Solution {
public:
    vector<double> twoSum(int n) {
        int d[n*6+1];
        memset(d, 0, sizeof(d));
        for(int i =1;i<=6;i++){
            d[i] = 1;
        }
        for(int j = 2;j<=n;j++){
            for(int k = j*6;k>=j*1;k--){
                d[k] = 0;
                for(int comp = 1;comp<=6;comp++){
                    if((k-comp)<=j-2){     // key line!! 这一步，，一直卡主，为什么是j-2？？
                        break;
                    }
                    d[k]+=d[k-comp];
                }
            }
        }
        int all = pow(6, n);
        vector<double> ret;
        for (int i = n; i <= 6 * n; i ++) {
            ret.push_back(d[i] * 1.0 / all);
        }
        return ret;

    }
};
```