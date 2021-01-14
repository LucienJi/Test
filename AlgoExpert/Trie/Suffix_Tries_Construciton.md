# Suffix_Tries_Construction
1. 啥是前缀树？一种用于搜索substring的高效数据结构
2. 大致构成：
    * 以string初始化，形成一颗multitree O(N^2)
    * 功能 contain，判断string 中是否有 substring O(L),子字符串长度

3. 举个例子
```py
## 输入 babc
## 树就该是
          handle
          / \   \
         b   a   c
        / \   \   \
       a   c   b   *
      /     \   \
     b       *   c
    /             \
   c               *
  /
 * 
  2
给定的树 B：

```