package dp.longestincreasingsubsequence;

// =============================================================================
// File    : LongestIncreasingSubsequence.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Longest Increasing Subsequence (LeetCode #300)
//
// Two approaches:
//
// 1) Patience Sort / Binary Search  O(n log n) — lengthOfLIS()
//    Maintain a `tails` array where tails[i] = smallest tail element
//    of all increasing subsequences of length i+1.
//    For each num: binary search for its position in tails and replace.
//    Arrays.binarySearch returns -(insertion point)-1 for misses.
//
// Dry Run  : [10,9,2,5,3,7,101,18]
//    10 → tails=[10]
//     9 → tails=[9]
//     2 → tails=[2]
//     5 → tails=[2,5]
//     3 → tails=[2,3]
//     7 → tails=[2,3,7]
//   101 → tails=[2,3,7,101]
//    18 → tails=[2,3,7,18]   length=4  ✓
//
// 2) Classic DP  O(n²) — lengthOfLISDP()
//    dp[i] = length of LIS ending at index i
//
// Complexity:
//   lengthOfLIS   : Time O(n log n), Space O(n)
//   lengthOfLISDP : Time O(n²),      Space O(n)
// =============================================================================

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    /**
     * Patience-sort / binary-search approach — O(n log n).
     *
     * @param nums input array
     * @return length of longest strictly increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            // Arrays.binarySearch: returns index if found, else -(insertionPoint)-1
            int pos = Arrays.binarySearch(tails, 0, size, num);
            if (pos < 0) pos = -(pos + 1); // convert to insertion point
            tails[pos] = num;
            if (pos == size) size++;
        }
        return size;
    }

    /**
     * Classic DP approach — O(n²).
     *
     * @param nums input array
     * @return length of longest strictly increasing subsequence
     */
    public int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        LongestIncreasingSubsequence solver = new LongestIncreasingSubsequence();
        System.out.println("=== Longest Increasing Subsequence ===");
        int[] a = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("O(n log n): " + solver.lengthOfLIS(a));   // 4
        System.out.println("O(n²)     : " + solver.lengthOfLISDP(a)); // 4

        int[] b = {0, 1, 0, 3, 2, 3};
        System.out.println("O(n log n): " + solver.lengthOfLIS(b));   // 4
        System.out.println("O(n²)     : " + solver.lengthOfLISDP(b)); // 4

        int[] c = {7, 7, 7, 7, 7};
        System.out.println("O(n log n): " + solver.lengthOfLIS(c));   // 1
        System.out.println("O(n²)     : " + solver.lengthOfLISDP(c)); // 1
    }
}
