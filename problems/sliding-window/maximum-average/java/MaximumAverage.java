// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Maximum Average Subarray I (LeetCode 643)
// Approach: Fixed Sliding Window

package slidingwindow.maximumaverage;

/**
 * Solution for Maximum Average Subarray I.
 *
 * <p>Strategy: Fixed sliding window of size k.
 * Compute the initial window sum, then slide right by adding nums[right]
 * and subtracting nums[right-k]. Track the maximum sum.
 *
 * <p>Time:  O(n)
 * Space: O(1)
 */
public class MaximumAverage {

    /**
     * Returns the maximum average of any contiguous subarray of length k.
     *
     * @param nums integer array
     * @param k    window size
     * @return maximum average value
     */
    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        int maxSum = sum;
        for (int right = k; right < nums.length; right++) {
            sum += nums[right] - nums[right - k];
            if (sum > maxSum) maxSum = sum;
        }
        return (double) maxSum / k;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        MaximumAverage solver = new MaximumAverage();

        int[][] testNums = {
            {1, 12, -5, -6, 50, 3},
            {5},
            {-1, -12, -5, -6},
            {0, 4, 0, 3, 2}
        };
        int[] ks = {4, 1, 2, 1};
        double[] expected = {12.75, 5.0, -6.0, 4.0};

        System.out.println("============================================================");
        System.out.println("Maximum Average Subarray I");
        System.out.println("============================================================");
        for (int i = 0; i < testNums.length; i++) {
            double result = solver.findMaxAverage(testNums[i], ks[i]);
            boolean ok = Math.abs(result - expected[i]) < 1e-5;
            System.out.printf("Test %d: k=%d → %.5f (expected %.5f) %s%n",
                i + 1, ks[i], result, expected[i], ok ? "PASS" : "FAIL");
        }
    }
}
