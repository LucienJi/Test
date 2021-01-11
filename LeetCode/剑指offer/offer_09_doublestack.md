### 描述
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

 

示例 1：

输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]



### CPP
```cpp
#include<stack>
class CQueue {
private:
    stack<int> head;
    stack<int> tail;

public:
    CQueue() {
    }
    
    void appendTail(int value) {
        tail.push(value);

    }
    
    int deleteHead() {
        int res,tmp;
        if(head.empty() && tail.empty()){
            return -1;
        }
        if(!head.empty()){
            res = head.top();
            head.pop();
            return res;
        }
        while(!tail.empty()){
            tmp = tail.top();
            tail.pop();
            head.push(tmp);
        }
        res = head.top();
        head.pop();
        return res;

    }
};

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue* obj = new CQueue();
 * obj->appendTail(value);
 * int param_2 = obj->deleteHead();
 */

 ```

 ### 分析
 1. 这题太教科书了。最大的作用就是c++ stack入门!
