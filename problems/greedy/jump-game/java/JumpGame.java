// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Jump Game (LeetCode 55)
// Approach: Greedy — Track Maximum Reach

package greedy.jumpgame;

/**
 * Solution for Jump Game.
 *
 * <p>Strategy: Single left-to-right scan, maintaining {@code maxReach} —
 * the farthest index reachable from any previously visited position.
 * If the current index exceeds {@code maxReach}, return false.
 *
 * <p>Time:  O(n)
 * Space: O(1)
 */
public class JumpGame {

    /**
     * Returns true if the last index of nums is reachable from index 0.
     *
     * @param nums array where nums[i] = max jump length from index i
     * @return true if last index is reachable
     */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            if (i + nums[i] > maxReach) maxReach = i + nums[i];
        }
        return true;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        JumpGame solver = new JumpGame();

        int[][] inputs = {
            {2, 3, 1, 1, 4},
            {3, 2, 1, 0, 4},
            {0},
            {1, 0},
            {0, 1},
            {1, 1, 1, 1},
        };
        boolean[] expected = {true, false, true, true, false, true};

        System.out.println("============================================================");
        System.out.println("Jump Game");
        System.out.println("============================================================");
        for (int i = 0; i < inputs.length; i++) {
            boolean result = solver.canJump(inputs[i]);
            System.out.printf("Test %d: result=%b (expected %b) → %s%n",
                i + 1, result, expected[i], result == expected[i] ? "PASS" : "FAIL");
        }
    }
}
