// https://www.lintcode.com/problem/607/

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
