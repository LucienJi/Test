# Palindrome

1. check 输入string是不是对称的
2. 要用漂亮的代码实现

# 方法
1. 维护双指针

# Code
1. 注意while 循环的条件
2. 注意return的判断，包含了奇偶的两种情况
```cpp
using namespace std;

bool isPalindrome(string str) {
  // Write your code here.
	int n = str.size();
	if(n<=1)return true;
	int i = 0;
	int j = n-1;
	while(j>i && str[i]==str[j]){
		j--;
		i++;
	}
  return j<=i;
}
```