# MinMaxStack
1. 一种数据结构，stack，但是可以用O(1)的代价查看最小值和最大值
2. 我的Implementation借助了deque，比较作弊，但是比用map的简单很多，空间开销2/3

# 核心概念
1. 如何利用stack LIFO的性质记录最大最小值
2. stack 的添加和删除会影响到deque记录的最大最小值
3. 利用辅助的deque记录部分有序数据：
    * 使用deque是合法的，假定加入一个中间值，deque不会记录下这个，但是也不会
    * 因为，min 和 max 在stack top之下，所以 删除这个 中间值，不会影响minmax

# Code

1. 双开头 queue
2. 注意检查 deque的size 以及， deque中必须假如相同的元素，pop掉了一个最大值，可能还有相同的最大值
```cpp
using namespace std;

// Feel free to add new properties and methods to the class.
class MinMaxStack {
public:
	deque<int> minmax = {};
	vector<int> s = {};
  int peek() {
    // Write your code here.
		
    return s.back();
  }

  int pop() {
    // Write your code here.
		int res = s.back();
		if(res == minmax.front()){
			minmax.pop_front();
		}else if(res == minmax.back()){
			minmax.pop_back();
		}
		
		s.pop_back();
    return res;
  }

  void push(int number) {
    // Write your code here.
		s.push_back(number);
		if(minmax.size()==0){
			minmax.push_back(number);
			return;
		}
		if(number>=minmax.back()){
			minmax.push_back(number);
		}else if(number<=minmax.front()){
			minmax.push_front(number);
		}
		
		return;
		
  }

  int getMin() {
    // Write your code here.
    return minmax.front();
  }

  int getMax() {
    // Write your code here.
    return minmax.back();
  }
};

```

