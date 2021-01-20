# Construction of Double Linked List


```cpp
using namespace std;

class Node {
public:
  int value;
  Node *prev;
  Node *next;

  Node(int value);
};

// Feel free to add new properties and methods to the class.
class DoublyLinkedList {
public:
  Node *head;
  Node *tail;

  DoublyLinkedList() {
    head = NULL;
    tail = NULL;
  }
	
  void setHead(Node *node) {
    // Write your code here.
		if(this->head == NULL){
			head = node;
			tail = node;
			return;
		}
		this->insertBefore(this->head,node);
  }

  void setTail(Node *node) {
    // Write your code here.
		if(this->tail==NULL){
			setHead(node);
		}else{
			insertAfter(this->tail,node);
		}
  }

  void insertBefore(Node *node, Node *nodeToInsert) {
    // 构造逻辑：假如这就是唯一的元素，啥也不做
		// 现再原list中删除这个node，这也是为什么让他完全 前后NULL的原因
		// 判断 是否是node是否是head，因为那样需要转换head
		if(this->head == nodeToInsert && this->tail == nodeToInsert){
			return;
		}
		this->remove(nodeToInsert);
		nodeToInsert->next = node;
		nodeToInsert->prev = node->prev;
		if(node->prev == NULL){
			this->head = nodeToInsert;
		}else{
			node->prev->next = nodeToInsert;
		}
		node->prev = nodeToInsert;
		
  }

  void insertAfter(Node *node, Node *nodeToInsert) {
    // Write your code here.
		if(this->head == nodeToInsert && this->tail == nodeToInsert){
			return;
		}
		this->remove(nodeToInsert);
		nodeToInsert->next = node->next;
		nodeToInsert->prev = node;
		if(node->next == NULL){
			this->tail = nodeToInsert;
		}else{
			node->next->prev = nodeToInsert;
		}
		node->next = nodeToInsert;
  }

  void insertAtPosition(int position, Node *nodeToInsert) {
    // Write your code here.
		if(position == 1){
			this->setHead(nodeToInsert);
			return;
		}
		Node* node = head;
		int ct = 1;
		while(node!=NULL && ct++ != position){
			node = node->next;
		}
		if(node!=NULL){
			this->insertBefore(node,nodeToInsert);
		}else{
			setTail(nodeToInsert);
		}
		
  }

  void removeNodesWithValue(int value) {
    // 这个是遍历，然后调用下方的辅助函数
		// 精彩之处在于，你需要提前保存一个这个节点的子节点复制，不然你删除了后，就没办法访问下家了
		Node* node = this->head;
		while(node!=NULL){
			Node* toremove = node;
			node = node->next;
			if(toremove->value == value){
				this->remove(toremove);
			}
		}
  }

  void remove(Node *node) {
    // 这一操作中的Node 已经假设是在 list中的，因此可以直接访问 prev next，而不是找 value
		if(node == this->head){
			this->head = this->head->next;
			}
		if(node == this->tail){
			this->tail = this->tail->prev;
		}
		removeNodeBindings(node);
  }

  bool containsNodeWithValue(int value) {
    // 代码的高级感
		Node* node = this->head;
		while(node!=NULL && node->value != value){
			node = node->next;
		}
    return node!=NULL;
  }
	
	void removeNodeBindings(Node* node){
		// 用来删除这node，这一步的意义是，明确这个node在list中，将它的前后node和它分离
		if(node->prev!=NULL){
			node->prev->next = node->next;
		}
		if(node->next!=NULL){
			node->next->prev = node->prev;
		}
		node->prev = NULL;
		node->next = NULL;
		
	}
};

```