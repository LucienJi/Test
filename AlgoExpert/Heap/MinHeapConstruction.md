# MinHeapConstruction

1. 首先最小堆是就是实现 priorityqueue的一种数据结构
2. 是一个complete tree，因此我们可以用 vector，array去实现它
3. 在于 `siftUp` ,`siftDown`,`buildheap`
    * 父节点：floor(i-1)/2
    * 子节点：i*2+1; i*2+2
    * siftup: 比父节点小，就和父节点交换， O(log(N))
    * siftDown: 先比较两个子节点，找到更小的那个，这个更小的其实就是带换位的node，然后和它比较， *注意，一定要和更小的node比较，否则heap性不保证*
    * buildheap：最神奇的操作！！从heap的倒数第二层开始，使用siftdown，这样能确保 O(N) 而不是 NlgN 的复杂度（这是从低往下用siftup的结果）
    * 重点好药check 访问是否有效

# Code
1. `int rightchild = 2*currentIdx + 2 <= endIdx ? 2*currentIdx + 2:-1;` 这句话简明扼要
2. `int leftchild = 2*currentIdx+1;`
	`while(leftchild <= endIdx){`
    因为完备性，所以不会存在有right 而没有left的情况，

```cpp
#include <vector>
using namespace std;

// Do not edit the class below except for the buildHeap,
// siftDown, siftUp, peek, remove, and insert methods.
// Feel free to add new properties and methods to the class.
class MinHeap {
public:
  vector<int> heap;

  MinHeap(vector<int> vector) { heap = buildHeap(vector); }

  vector<int> buildHeap(vector<int> &vector) {
    // Write your code here.
		int firstparent = (vector.size()-2)/2;
		for(int cur = firstparent;cur>=0;cur--){
			siftDown(cur,vector.size()-1,vector);
		}
    return vector;
  }

  void siftDown(int currentIdx, int endIdx, vector<int> &heap) {
    // Write your code here.
		int leftchild = 2*currentIdx+1;
		int next;
		while(leftchild <= endIdx){
			int rightchild = 2*currentIdx + 2 <= endIdx ? 2*currentIdx + 2:-1;
			if(rightchild!=-1 && heap[rightchild]<heap[leftchild]){
					next = rightchild;
			}else{
					next = leftchild;
			}
			if(heap[currentIdx] > heap[next] ){
				swap(heap[currentIdx],heap[next]);
				currentIdx = next;
				leftchild = 2*currentIdx+1;
			}else{
				return;
			}
			
		}
		
  }


  void siftUp(int currentIdx, vector<int> &heap) {
    // Write your code here.
		int parent = (currentIdx-1)/2;
		while(currentIdx > 0 && heap[currentIdx] < heap[parent]){
			swap(heap[currentIdx],heap[parent]);
			currentIdx = parent;
			parent = (currentIdx - 1)/2;
		}
  }

  int peek() {
    // Write your code here.
	
    return heap[0];
  }

  int remove() {
    // Write your code here.
		swap(heap[0],heap[heap.size()-1]);
		int v = heap.back();
		heap.pop_back();
		siftDown(0,heap.size()-1,heap);
    return v;
  }

  void insert(int value) {
    // Write your code here.
		heap.push_back(value);
		siftUp(heap.size()-1,heap);
  }
};
```