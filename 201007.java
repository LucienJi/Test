/**2. 两数相加
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp1=l1;
        ListNode temp2=l2;
        ListNode result=new ListNode();
        ListNode a=result;
        int r=0;
        while((temp1!=null)&&(temp2!=null)){#############
            int k=temp1.val+temp2.val;
            if(k+r>=10){

                a.next=new ListNode(k+r-10);  #第一个while在两个数字都没到头情况下计算
                a=a.next;
                r=1;
            }
            else{
                a.next=new ListNode(k+r);
                a=a.next;
                r=0;                           #r来记录进位的情况
            }
            temp1=temp1.next;
            temp2=temp2.next;
        }################################################
        if(temp1==null){                        #如果第一个数到头了就算第二个
            while(temp2!=null){
                int k=temp2.val;
                if(k+r>=10){
                    a.next=new ListNode(k+r-10);#保持了原来的逆序计数，省的最后再倒一次
                    a=a.next;
                    r=1;
                }
                else{
                    a.next=new ListNode(k+r);
                    a=a.next;
                    r=0;
                }
                temp2=temp2.next;
            }
            if(r==1){
                a.next=new ListNode(1);
                a=a.next;
                r=0;
            }
        }
        else{                               #第二个数到头了就算第一个
            while(temp1!=null){
                int k=temp1.val;
                if(k+r>=10){
                    a.next=new ListNode(k+r-10);
                    a=a.next;
                    r=1;
                }
                else{
                    a.next=new ListNode(k+r);
                    a=a.next;
                    r=0;
                }
                temp1=temp1.next;
            }
            if(r==1){
                a.next=new ListNode(1);
                a=a.next;
                r=0;
            }
        }
        return result.next;
    }
}
