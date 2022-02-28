// https://nados.io/question/segregate-node-of-linkedlist-over-pivot-index?zen=true
// Pivot will be is a single idx

import java.util.*;

class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class ListNode {
    int val = 0;
    ListNode next = null;

    ListNode(int val) {
      this.val = val;
    }
  }

  
  //===========================================================================================================
  public static ListNode segregate(ListNode head, int pivotIdx) {
      ListNode smallDummy = new ListNode(-1); // Represents < pivotIdx head
      ListNode largeDummy = new ListNode(-1);  // Reprsents > pivotIdx head

      ListNode sp = smallDummy;  // Represents < pivotIdx iterator
      ListNode lp = largeDummy;   // Reprsents > pivotIdx iterator
      ListNode pA = null;       // pivotAddress ptr

      ListNode pivotAddress = getAddressOfPivot(head, pivotIdx);

      ListNode curr = head;
      while(curr != null) {
          if(curr == pivotAddress) {
            pA = curr;
          }
          else if(curr.val <= pivotAddress.val) {  // Only change from even odd segregation
              sp.next = curr;
              sp = sp.next;
          }
          else {
              lp.next = curr;
              lp = lp.next;
          }
          curr = curr.next;
      }
      
      // smallIterator(sp) k next me pivotAddress ko add karo and pivot k next me largeHead(largeDummy) k head ko add karo

      sp.next = lp.next = null; // Make sure to make it null, otherwise it will form a cycle
      
      sp.next = pA;  // smallIterator(sp) k next me pivotAddress 
      pA.next = largeDummy.next;  // pivotAddress k next me largeHead(largeDummy)
      
      return smallDummy.next; // return smallDummy [Actually smallDummy ka next since dummy node]
  }


  // pivot ek random idx hai, not last idx
  public static ListNode getAddressOfPivot(ListNode head, int pivotIdx) {
      ListNode curr = head;
      int idx = 0;
      while(curr.next != null) {
        if(idx == pivotIdx) return curr;
        idx++;
        curr = curr.next;
      }
      return null;
  }
  //==============================================================================================================================



  public static void printList(ListNode node) {
    while (node != null) {
      System.out.print(node.val + " ");
      node = node.next;
    }
  }

  public static ListNode createList(int n) {
    ListNode dummy = new ListNode(-1);
    ListNode prev = dummy;
    while (n-- > 0) {
      prev.next = new ListNode(scn.nextInt());
      prev = prev.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    int n = scn.nextInt();
    ListNode h1 = createList(n);
    int idx = scn.nextInt();
    h1 = segregate(h1, idx);
    printList(h1);
  }
}
