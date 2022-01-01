// https://practice.geeksforgeeks.org/problems/count-pairs-with-given-sum5022/1#
// A question in between unique and non unique pairs

/*
    Mai kitne k saath pair bna sakti hu ?
    
    Khud ko chor k, apne saare complements k saath ek ek baar pair bna sakti hu
    Par agar aisa sab koi karne lgega then same pairs ko hum baar baar count kar lenge, 
    but if we use HM and iterate from one end, we can overcome this duplicate false counting
    
    How ?? See Below !!
    
    Example : [3, 3, 2, 3]   target = 6
    
    Think like moving from left -> right in HM,
    So, apne se pehle waale ko he aaj tak explore kiye ho, 
    toh apne se piche walo k saath bna lo pair
    Pehla 3(at 0th idx) kisi k saath nai bna paaega, didn't find any comp as of now
    Dusra 3(at 1st idx) ek apne se pehle 3 k saath pair bna paega, found 1 comp
    2 apna compliment nai dhoondh paaega toh khud ko add kar dega
    Tisra 3(at 3nd idx) apne se pehle dono 3 k saath pair bna lega, found 2 comp
*/

class Solution {
    
    int getPairsCount(int[] nums, int n, int target) {
        
        // {Element, freq}
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i < n; i++) {
            int comp = target - nums[i];
            if(map.containsKey(comp)) {
                count += map.get(comp); // Adding compliments "so far" frequency in count               // Important line
            }
          
            // Tumhare saath tumse aage wala elements pair bna sakta h islye khud ko v add kar do
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return count;
    }
}
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
