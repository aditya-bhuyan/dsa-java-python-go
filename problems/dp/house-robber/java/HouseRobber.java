package dp.houserobber;

// =============================================================================
// File    : HouseRobber.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : House Robber (LeetCode #198)
//
// Approach : Space-Optimised Dynamic Programming
// ----------
//   dp[i] = max money robbing up to house i
//         = max(dp[i-1],  dp[i-2] + nums[i])
//   Keep only two rolling variables: prev2 and prev1.
//
// Dry Run  : nums = [2, 7, 9, 3, 1]
//   prev2=0, prev1=2
//   i=1 (7) → curr=max(2, 0+7)=7   prev2=2,  prev1=7
//   i=2 (9) → curr=max(7, 2+9)=11  prev2=7,  prev1=11
//   i=3 (3) → curr=max(11,7+3)=11  prev2=11, prev1=11
//   i=4 (1) → curr=max(11,11+1)=12 prev2=11, prev1=12
//   return 12  ✓
//
// Complexity:
//   Time  : O(n)
//   Space : O(1)
// =============================================================================

public class HouseRobber {

    /**
     * Returns the maximum amount that can be robbed without
     * alerting the police (no two adjacent houses).
     *
     * @param nums array of non-negative integers representing house values
     * @return maximum money that can be robbed
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev2 = 0;
        int prev1 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curr = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        HouseRobber solver = new HouseRobber();
        System.out.println("=== House Robber ===");
        System.out.println(solver.rob(new int[]{1, 2, 3, 1}));       // 4
        System.out.println(solver.rob(new int[]{2, 7, 9, 3, 1}));    // 12
        System.out.println(solver.rob(new int[]{5}));                 // 5
        System.out.println(solver.rob(new int[]{}));                  // 0
    }
}
