## 描述

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;
    
    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/
```

## CPP

1. 不同于普通的复制列表，只要让复制者比被复制者慢跑一点，就可以更上了

```cpp
while cur:
            node = Node(cur.val) # 复制节点 cur
            pre.next = node      # 新链表的 前驱节点 -> 当前节点
            # pre.random = '???' # 新链表的 「 前驱节点 -> 当前节点 」 无法确定
            cur = cur.next       # 遍历下一节点
            pre = node 

```

2. 其实就是跑2遍，利用hashmap
```cpp
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;
    
    Node(int _val) {
        val = _val;
        next = NULL;
        random = NULL;
    }
};
*/
class Solution {
public:
   
    Node* copyRandomList(Node* head) {
        Node* cur = head;
        unordered_map<Node*,Node*> map;
        while(cur!=NULL){
            Node* new_node = new Node(cur->val);
            map[cur] = new_node;
            cur=cur->next;
        }
        cur = head;
        while(cur!=NULL){
            Node* tmp = map[cur];
            tmp->next = map[cur->next];
            tmp->random = map[cur->random];
            cur=cur->next;
        }

        return map[head];

        
    }
};
```

