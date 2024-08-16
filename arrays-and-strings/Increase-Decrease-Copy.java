// https://codeforces.com/problemset/problem/1976/B

/*
      You are given two integer arrays: array 𝑎 of length 𝑛 and array 𝑏 of length 𝑛+1
      You can perform the following operations any number of times in any order:
      
      choose any element of the array 𝑎 and increase it by 1
      choose any element of the array 𝑎 and decrease it by 1
      choose any element of the array 𝑎 copy it and append the copy to the end of the array 𝑎
  
      Your task is to calculate the minimum number of aforementioned operations (possibly zero) required to transform
      the array 𝑎 into the array 𝑏. It can be shown that under the constraints of the problem, it is always possible.
      
      Input
      The first line contains a single integer 𝑡(1≤𝑡≤104) — the number of test cases.
      
      Each test case consists of three lines:
      
      the first line contains a single integer 𝑛(1≤𝑛≤2⋅105);
      the second line contains 𝑛 integers 𝑎1,𝑎2,…,𝑎𝑛(1≤𝑎𝑖≤109);
      the third line contains 𝑛+1 integers 𝑏1,𝑏2,…,𝑏𝑛+1(1≤𝑏𝑖≤109).
      Additional constraint on the input: the sum of 𝑛 over all test cases doesn't exceed 2⋅105
      
      Output
      For each test case, print a single integer — the minimum number of operations (possibly zero) required to transform
      the array 𝑎 into the array 𝑏
      
      Example
      3
      1
      2
      1 3
      
      2
      3 3
      3 3 3
      
      4
      4 2 1 2
      2 1 5 2 3
      
      OutputCopy
      3
      1
      8

*/


// Approach
// You have to make only one copy operation as there is only one extra element in b
// So, you will see if your last element of b lies in the range of (a[i] to b[i]) or (b[i] to a[i]) for every respective i
// If yes take +1 operation in the name of copying that element
// If it doesn't lie in the range just take the minimum of Math.abs(a[i] - lastElementOfb) and +1 for copying 
// means either you will do +1+1+1... to reach untill b or -1-1-1.. to reach until b


public class IncDecCopy {
  
    public static int incDecCopy(int n, int[] a, int[] b) {
        int totalOps = 0;
        int minCopyOps = Integer.MAX_VALUE;
        
        for(int i = 0; i < n; i++) {
            if (b[b.length - 1] <= a[i] && b[b.length - 1] >= b[i]) { // Lies between the range, Copy while decreasing Ex: 4 to 2
                minCopyOps = 1;
            } else if (b[b.length - 1] >= a[i] && b[b.length - 1] <= b[i]) { // Lies between the range, Copy while increasing Ex: 2 to 4 
                minCopyOps = 1;
            } else {
                minCopyOps = Math.min(minCopyOps, Math.abs(a[i] - b[b.length - 1]) + 1); // Lies out of the range, Take each step +/- to to go from a to b and +1 to copy
            }
            totalOps += Math.abs(a[i] - b[i]); // Take each step +/- to to go from a to b 
        }

        if(n == 0) minCopyOps = 0;

        totalOps += minCopyOps;
        return totalOps;
    }
  
}
