### 描述
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

 

示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]

### CPP
```cpp
class Solution {
public:
    vector<int> singleNumbers(vector<int>& nums) {

        vector<int> res;
        int a= 0; 
        int b= 0;
        int num = 0;
        
        for(int i = 0;i<nums.size();i++){
            num ^=nums[i];                 // 异或运算：所有出现两次的数字都会被消掉，剩下的是两个独立数字的异或结果，但是我们还不知道谁是谁
        }
        int first_different_pos = 1;
        while((first_different_pos & num) == 0){
            first_different_pos<<=1;     // 既然这两个数字不一样，那么一定有1位上在异或后是1，也就是2进制上不相同
        }
        for (int i = 0;i<nums.size();i++){
            if((nums[i]&first_different_pos) == 0){  // 根据我们找到的这一位，我们可以将所有的数字再重新分组，重新异或一遍
                a^=nums[i];
            }else{
                b^=nums[i];
            }

        }
        res.push_back(a);
        res.push_back(b);
        return res;


    }
};


```

### 重要的分析：位运算
1. 位逻辑运算符：

      & （位   “与”）  and
      ^  （位   “异或”）  1^1 = 0,1^0 =1
      |   （位    “或”）   or
      ~  （位   “取反”）
2. 移位运算符：
      <<（左移）
      >>（右移）

3. 位运算的优先级很低很低很低！！！！！！！！！！！！！！！！比如在楼上的if语句里，你不加括号，答案是错的！！！！！
