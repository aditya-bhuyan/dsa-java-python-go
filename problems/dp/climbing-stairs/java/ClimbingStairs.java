package dp.climbingstairs;

// =============================================================================
// File    : ClimbingStairs.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Climbing Stairs (LeetCode #70)
//
// Approach : Space-Optimised Dynamic Programming (Fibonacci)
// ----------
//   Observation: f(n) = f(n-1) + f(n-2)  (1-step or 2-step choices)
//   Instead of an O(n) array we maintain only two rolling variables.
//
// Dry Run  : n = 5
//   prev2=1(f1), prev1=2(f2)
//   i=3 → curr=1+2=3   prev2=2, prev1=3
//   i=4 → curr=2+3=5   prev2=3, prev1=5
//   i=5 → curr=3+5=8   prev2=5, prev1=8
//   return 8  ✓
//
// Complexity:
//   Time  : O(n)
//   Space : O(1)
// =============================================================================

public class ClimbingStairs {

    /**
     * Returns the number of distinct ways to climb n stairs
     * taking 1 or 2 steps at a time.
     *
     * @param n number of stairs (1 ≤ n ≤ 45)
     * @return count of distinct ways
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int prev2 = 1; // f(1)
        int prev1 = 2; // f(2)
        for (int i = 3; i <= n; i++) {
            int curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        ClimbingStairs solver = new ClimbingStairs();
        int[] cases = {1, 2, 3, 4, 5, 10, 20, 45};
        System.out.println("=== Climbing Stairs ===");
        for (int n : cases) {
            System.out.printf("climbStairs(%2d) = %d%n", n, solver.climbStairs(n));
        }
    }
}
