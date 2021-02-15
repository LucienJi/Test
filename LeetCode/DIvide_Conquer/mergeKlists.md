# 合并 K 个list

1. 就是好奇，为什么用 DC 可以减少计算量

2. 假如遍历，假设 平均长 n，个数 k， 合并两个的复杂度是 n,累加起来就是 n + 2n + 3n +++ kn 
3. 假如二分， k/2 个 n ，k/4 个 2n，，， log2(n) * kn/2


# Code
```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* merge2List(ListNode* a,ListNode* b){
        if((!a) || (!b)) return a ? a : b;
        ListNode head, *tail = &head, *aPtr = a, *bPtr = b;

        while(aPtr && bPtr){
            if(aPtr->val < bPtr->val){
                tail->next = aPtr;
                aPtr = aPtr->next;
                tail = tail->next;
            }else{
                tail->next = bPtr;
                bPtr = bPtr->next;
                tail = tail->next;

            }
        }
        tail->next = (aPtr ? aPtr : bPtr);
        return head.next;}

/** 不用完全，，只用接一次
        while(aPtr){
            tail->next = aPtr;
            aPtr = aPtr->next;
            tail = tail->next;
        }
        while(bPtr){
            tail->next = bPtr;
            bPtr = bPtr->next;
            tail = tail->next;
        }

    }
*/  
    ListNode* merge(vector<ListNode*> lists, int l ,int r){
        if(l==r){
            return lists[l];
        }
        if(l>r){
            return nullptr;
        }
        int mid = (r - l)/2 + l;
        return merge2List(merge(lists,l,mid),merge(lists,mid+1,r));
    }
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        return merge(lists,0,lists.size()-1);

    }
};
```