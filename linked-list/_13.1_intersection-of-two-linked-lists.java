// https://leetcode.com/problems/intersection-of-two-linked-lists/

// Using size of LL and travelling the extra diff of the LL

```
public class Solution {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = size(headA);
        int sizeB = size(headB);
               
        ListNode longerHead = null;
        ListNode shorterHead = null;
        int diff = Math.abs(sizeA - sizeB);
        if(sizeA >= sizeB) {longerHead = headA; shorterHead = headB;}
        else {longerHead = headB; shorterHead = headA;}
        
        
        // Jitne ka difference that longerHead ko utne se pehle he badha do
        int count = 0;
        while(count < diff) {
            longerHead = longerHead.next;
            count++;
        }
        
        // Ab dono LL equal size k hai
        while(shorterHead != null) {
            if(shorterHead == longerHead) return shorterHead; // Ab jaha dono meet kr gaya that is your ans
            shorterHead = shorterHead.next;
            longerHead = longerHead.next;
        }
        return null; // Nai meet kiya toh null
    }
    
    public static int size(ListNode head) {
        ListNode curr = head;
        int size = 0;
        while(curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }
    
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using HashSet by adding nodes to the set, when false then that is the intersection node
// Worst case would be when there would not be any intersection
// Time complexity : O(M+N) 
// Space complexity : O(M+N)

```
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<ListNode>();
        while(headA != null || headB != null){
            if(headA != null){
                if(!set.add(headA))
                    return headA;
                headA= headA.next;
            }
            if(headB != null){
                if(!set.add(headB))
                    return headB;
                headB= headB.next;
            }
        }
        return null;
    }
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

/*
    Algo
    ----
    Iterate dummyA and dummyB to the next element, together in a single loop, if any node comes out to be null, assign the other nodes head in that dummy node,
    like if dummyA is null assign dummyA = headB, now at some point both dummyA and dummyB will be at equal distace from the merging node and 
    again iterate for dummyA == dummyB, if yes then return node else if there won't be any merging point then there will be a point 
    when both dummyA and dummyB will have null in it together. Then loop will break so return null.
    
    https://www.youtube.com/watch?v=u4FWXfgS8jw
    Worst Time complexity: O(2M)
    Space complexity: O(1)
*/

Reason : Our operations in first iteration will help us counteract the difference.

```
public class Solution {
    
    public ListNode getIntersectionNode(ListNode h1, ListNode h2) {
        
        ListNode t1 = h1;
        ListNode t2 = h2;
        
		// This diff will be balanced
        while(t1 != t2) {
            t1 = t1.next;
            t2 = t2.next;
            if(t1 == t2) return t1;
            if(t1 == null) t1 = h2;
            if(t2 == null) t2 = h1;
        }
        return t1; // If 1st node is the intersection pt.
    }
        
}
```

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
