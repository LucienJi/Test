## 描述
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

 

示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.



## CPP

1. 简单的，只要使用辅助stack就行了，每次将最小的元素存入
2. 经典的用空间换时间的方法，原先的min复杂度在O(n)，现在是常数


```cpp
class MinStack {
public:
    /** initialize your data structure here. */
    stack<int> mainstack;
    stack<int> minstack;

    MinStack() {

    }
    
    void push(int x) {
        mainstack.push(x);
        if(minstack.empty()){
            minstack.push(x);
        }else{
            if(x<=minstack.top()){
                minstack.push(x);
            }
        }


    }
    
    void pop() {
        if(!mainstack.empty() && !minstack.empty()){
            if(mainstack.top()==minstack.top()){
                mainstack.pop();
                minstack.pop();
            }else{
                mainstack.pop();
            }
        }

    }
    
    int top() {
        return mainstack.top();

    }
    
    int min() {
        return minstack.top();

    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(x);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->min();
 */

 ```