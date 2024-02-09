// https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

class Solution {
    public int numPairsDivisibleBy60(int[] advertisements) {
        int N = 60;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 0;

        for (int duration : advertisements) {
            int remainder = duration % N;
            int complement = (N - remainder) % N;

            count += frequencyMap.getOrDefault(complement, 0);

            frequencyMap.put(remainder, frequencyMap.getOrDefault(remainder, 0) + 1);
        }
        return count;
    }
}
