// https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/trees/convert-sorted-dll-to-bst/ojquestion

import java.util.*;

class Main {
  public static Scanner scn = new Scanner(System.in);

  public static class Node {
    int val = 0;
    Node left = null;
    Node right = null;

    Node(int val) {
      this.val = val;
    }
  }
  
  
  // left : prev, right : next
  
  //=====================================================================================================
   
   // Code for 1st middle, only getPrevAndMid() code would be changing, rest conversion is same for both
   // Here 1st midde is asked in result, not mentioned, but observed by test failures
   // NOTE : Both for 1st && 2nd middle, it would definitely be a valid BST,
   // but BST won't be identical obviously.
   
   public static Node SortedDLLToBST(Node head) {
        if(head == null) return null;
            
        Node[] PrevAndMid = getPrevAndMid(head); // {Prev, 1ndMid}
        Node prev = PrevAndMid[0]; // Prev
        Node mid = PrevAndMid[1];  // 1nd mid
        Node forw = mid.right;
        
        // Breaking the links of the midNode for Divide and Conquer, to create 2 seperate list
        if(prev != null) prev.right = null;
        if(forw != null) forw.left = null;
        mid.right = mid.left = null;
        
        // 1st half list
        // Jab sirf ek he node hoga then
        // agar head ko he pass kr diya then infinite call lg jaaegi
        // So agar prev null hai => Sirf ek he node hai, so pass the 1st half list as null
        mid.left = SortedDLLToBST(prev != null ? head : null); 
        
        // 2nd half List
        // Incase sirf 1 he node hua then forw toh null he rahega, so pass the 2nd half as forw/null 
        mid.right = SortedDLLToBST(forw);
        
        return mid;
    }
    
    
    // Returns prev and 1nd mid Node
    public static Node[] getPrevAndMid(Node head) {
        Node slow = head;
        Node fast = head;
        Node prev = null;
        
        while(fast.right != null && fast.right.right != null) {
            prev = slow;
            slow = slow.right;
            fast = fast.right.right; 
        }
    
        return new Node[] {prev, slow};
    }
  //======================================================================================================


//   //=====================================================================================================
//   // Code for 2nd middle, only getPrevAndMid() code would be changing, rest conversion is same for both

//   public static Node SortedDLLToBST(Node head) {
//         if(head == null) return null;
            
//         Node[] PrevAndMid = getPrevAndMid(head); // {Prev, 2ndMid}
//         Node prev = PrevAndMid[0]; // Prev
//         Node mid = PrevAndMid[1];  // 2nd mid
//         Node forw = mid.right;
        
//         // Breaking the links of the midNode for Divide and Conquer, to create 2 seperate list
//         if(prev != null) prev.right = null;
//         if(forw != null) forw.left = null;
//         mid.right = mid.left = null;
        
//         // 1st half list
//         // Jab sirf ek he node hoga then
//         // agar head ko he pass kr diya then infinite call lg jaaegi
//         // So agar prev null hai => Sirf ek he node hai, so pass the 1st half list as null
//         mid.left = SortedDLLToBST(prev != null ? head : null); 
        
//         // 2nd half List
//         // Incase sirf 1 he node hua then forw toh null he rahega, so pass the 2nd half as forw/null 
//         mid.right = SortedDLLToBST(forw);
        
//         return mid;
//     }
    
    
//     // Returns prev and 2nd mid Node
//     public static Node[] getPrevAndMid(Node head) {
//         Node slow = head;
//         Node fast = head;
//         Node prev = null;
        
//         while(fast != null && fast.right != null) {
//             prev = slow;
//             slow = slow.right;
//             fast = fast.right.right; 
//         }
    
//         return new Node[] {prev, slow};
//     }
//   //==================================================================================================
  
   

  public static void display(Node node) {
    if (node == null)
      return;

    StringBuilder sb = new StringBuilder();
    sb.append((node.left != null ? node.left.val : "."));
    sb.append(" -> " + node.val + " <- ");
    sb.append((node.right != null ? node.right.val : "."));

    System.out.println(sb.toString());

    display(node.left);
    display(node.right);

  }

  public static Node makeList(int n) {
    Node dummy = new Node(-1);
    Node prev = dummy;
    while (n-- > 0) {
      Node node = new Node(scn.nextInt());
      prev.right = node;
      node.left = prev;
      prev = prev.right;
    }

    Node head = dummy.right;
    head.left = dummy.right = null;

    return head;
  }

  public static void main(String[] args) {
    Node head = makeList(scn.nextInt());

    head = SortedDLLToBST(head);
    display(head);
  }

}
