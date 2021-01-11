# 描述
在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。

如果小镇的法官真的存在，那么：

1. 小镇的法官不相信任何人。
2. 每个人（除了小镇法官外）都信任小镇的法官。
3. 只有一个人同时满足属性 1 和属性 2 。
给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。

如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。


# 核心思想

1. 如何将问题转化为node 的度的衡量
2. 法官的定义转化为图语言：
    > outgoing = N-1

    > ingoing = 0

# Code

```cpp
class Solution {
public:
    int findJudge(int N, vector<vector<int>>& trust) {
        int count[N];
        for(int i = 0;i<N;i++){
            count[i] = 0;
        }
        for(auto i :trust){
            count[i[1]-1]++;
            count[i[0]-1]--;
        }
        int res = -1;
        int num = 0;
        for(int i = 0;i<N;i++){
            if(count[i]==N-1){
                num++;
                res = i+1;
            }
        }
        if(num!=1)return -1;
        return res;

    }
};
```

