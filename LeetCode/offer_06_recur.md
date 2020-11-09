## 描述
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：

输入：head = [1,3,2]
输出：[2,3,1]

## cpp

1. 这个例子充分说明了 递归的本质就是一个stack

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
    vector<int> reversePrint(ListNode* head) {
        vector<int> res;
        if(head==NULL)return res;
        rec(head,res);
        return res;

    }
    void rec(ListNode* head,vector<int>& res){
        if(head==NULL){
            return;
        }

        rec(head->next,res);
        res.push_back(head->val);

    }
};
```