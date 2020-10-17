## 描述
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

 

示例：

给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.


## CPP


1. Basic Idea: 首先计数，然后再跑一次
```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* getKthFromEnd(ListNode* head, int k) {
        if(head==NULL){
            return NULL;
        }
        int total = 0;
        ListNode* tmp = head;
        while(tmp->next != NULL){
            tmp = tmp->next;
            total++;
        }
        total++;
        tmp = head;
        total-=k;
        if(total==0){
            return tmp;
        }
        while(total-1>0){
            tmp = tmp->next;
            total--;
        }
        return tmp->next;
        

    }
};
```


2. 双指针版 : 后指针先跑 k-1 次，然后，前后一起跑，最后输出前指针


```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* getKthFromEnd(ListNode* head, int k) {

        ListNode* front = head;
        ListNode* rear = head;
        
        int i = 0;
        while(i<k-1 && rear->next != NULL){
            rear = rear->next;
            i++;
        }
       
        while(rear->next !=NULL){
            rear = rear->next;
            front = front->next;

        }
        return front;
    
    }
};
```

