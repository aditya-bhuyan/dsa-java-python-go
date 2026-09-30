// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Gas Station (LeetCode 134)
// Approach: Greedy — Single Pass, Running Sum + Reset

package greedy.gasstation;

/**
 * Solution for Gas Station.
 *
 * <p>Strategy: Compute diff[i] = gas[i] - cost[i] on the fly.
 * Track {@code total} (overall feasibility sum) and {@code current} (running
 * tank from current candidate start). Whenever {@code current} goes negative,
 * reset to 0 and advance {@code start} to i + 1.
 * After the loop, return {@code start} if {@code total >= 0}, else -1.
 *
 * <p>Time:  O(n)
 * Space: O(1)
 */
public class GasStation {

    /**
     * Returns the starting station index for a complete clockwise circuit,
     * or -1 if no valid start exists.
     *
     * @param gas  gas available at each station
     * @param cost gas cost to travel to the next station
     * @return starting station index, or -1
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, current = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            current += diff;
            if (current < 0) {
                start = i + 1;
                current = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        GasStation solver = new GasStation();

        int[][] gases = {
            {1, 2, 3, 4, 5},
            {2, 3, 4},
            {5},
            {3, 1, 1},
        };
        int[][] costs = {
            {3, 4, 5, 1, 2},
            {3, 4, 3},
            {4},
            {1, 2, 2},
        };
        int[] expected = {3, -1, 0, 0};

        System.out.println("============================================================");
        System.out.println("Gas Station");
        System.out.println("============================================================");
        for (int i = 0; i < gases.length; i++) {
            int result = solver.canCompleteCircuit(gases[i], costs[i]);
            System.out.printf("Test %d: result=%d (expected %d) → %s%n",
                i + 1, result, expected[i], result == expected[i] ? "PASS" : "FAIL");
        }
    }
}
