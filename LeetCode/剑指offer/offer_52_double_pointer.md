## 描述
输入两个链表，找出它们的第一个公共节点。



示例 1：



输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

## CPP

1. 列表1 = L1 + C；列表2 = L2 + C， 所以一共前进 L1 + L2 + C 两者就相遇了
2. Key point 是在于要输出null， 将null 看成common point！

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
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
        ListNode* node1 = headA;
        ListNode* node2 = headB;
        while(node1!=node2){
            if(node1 != NULL){
                node1 = node1->next;
            }else{
                node1 = headB;
            }

            if(node2!=NULL){
                node2 =  node2->next;
            }else{
                node2 = headA;
            }

        }
        return node1;
        
    }
};
```