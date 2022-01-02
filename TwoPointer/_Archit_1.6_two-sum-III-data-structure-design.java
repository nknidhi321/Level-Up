// https://www.lintcode.com/problem/607/

// Using HashMap
// TC : O(N)
// SC : O(N)

public class TwoSum {

    // {Element, freq}
    public static HashMap<Integer, Integer> map; // A DS is required to store elements

    public TwoSum() { // Create own constructor
        map = new HashMap<>(); // Initializing set
    }

    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    public boolean find(int target) {       
        for(Map.Entry<Integer, Integer> entrySet : map.entrySet()) {
            int key = entrySet.getKey();
            int freq = entrySet.getValue();
            int comp = target - key;

            if(key == comp) { // Duplicate element case
                if(freq >= 2) return true;  // The comp or key should exist >= 2 times, then only pair can be formed
                else return false; // Else pair cannot be formed
            }
            // Non duplicate elements
            else if(map.containsKey(comp) && freq >= 1) return true;
        }        
        return false;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Two Pointer
// Using ArrayList
// TC : O(NlogN)
// SC : O(N)

public class TwoSum {

    public static List<Integer> list; // A DS is required to store elements
    public static boolean isSorted = false; // And to apply 2 pointer you need to confirm if it is sorted or not

    public TwoSum() { // Create own constructor
        list = new ArrayList<>(); // Initializing list
        isSorted = true; // Assuming List of 0 size is by default sorted
    }

    public void add(int number) {
        list.add(number);
        isSorted = false; // Whenever you a number at end, elements are no more sorted
    }

    public boolean find(int target) {
        // Sort before applying two pointer
        if(!isSorted) {
            Collections.sort(list);
            isSorted = true;
        }

        // Two pointer
        int start = 0, end = list.size() - 1;
        while(start < end) {
            int sum = list.get(start) + list.get(end);
            if(sum == target) return true;
            else if(sum < target) start++;
            else end--;
        }
        return false;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX

// Using TreeSet // Used TreeSet because it is by default sorted
// Removes duplicate elements
// The following Code fails when duplicate values comes in
// Example [0, 0, 1]    target = 0    // To overcome this duplicate issue use HM and store freq of each element like above
// Below Code is correct if you are sure there won't exist any duplicate elements

public class TwoSum {

    // Using TreeSet because it is by default sorted 
    public static TreeSet<Integer> set; // A DS is required to store elements

    public TwoSum() { // Create own constructor
        set = new TreeSet<>(); // Initializing set
    }

    public void add(int number) {
        set.add(number);
    }

    public boolean find(int target) {

        // To iterate over a set using 2 pointer,
        // copying all elements of TreeSet in nums array 
        // There's no other alternative in set to iterate 
        int[] nums = new int[set.size()];
        Iterator itr = set.iterator();
        int i = 0;
        while(itr.hasNext()) {
            nums[i++] = (int)itr.next();
        }

        // TreeSet => Set is sorted

        // Two Pointer
        int start = 0, end = nums.length - 1;
        while(start < end) {
            int sum = nums[start] + nums[end];
            if(sum == target) return true;
            else if(sum < target) start++;
            else end--;
        }
        return false;
    }
}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
