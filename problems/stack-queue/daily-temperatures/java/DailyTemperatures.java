package stackqueue.dailytemperatures;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * ============================================================================
 * Problem: Daily Temperatures
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given temperatures[], return answer[] where answer[i] = number of days
 * to wait after day i for a warmer temperature (0 if none).
 *
 * Example
 * -------
 *
 * Input  : [73,74,75,71,69,72,76,73]
 * Output : [1,1,4,2,1,1,0,0]
 *
 * ============================================================================
 *
 * Approach — Monotonic Decreasing Stack
 * ----------------------------------------
 *
 * Store indices. Temperatures at stack indices are decreasing bottom→top.
 *
 * For each day i:
 *   While stack not empty AND temps[i] > temps[stack.peek()]:
 *       prevIdx = stack.pop()
 *       answer[prevIdx] = i - prevIdx
 *   stack.push(i)
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 *
 * ============================================================================
 */
public class DailyTemperatures {

    /**
     * Returns the wait-day count for each day.
     *
     * @param temperatures  Array of daily temperatures.
     * @return              Array of wait days.
     */
    public int[] waitDays(int[] temperatures) {

        int n = temperatures.length;
        int[] answer = new int[n];

        /*
         * Stack stores indices of "unanswered" days.
         * Temperatures at those indices are decreasing bottom → top.
         */
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            /*
             * Current day is warmer than the day at the top of the stack.
             * Pop and record the wait.
             */
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIdx = stack.pop();
                answer[prevIdx] = i - prevIdx;
            }

            stack.push(i);
        }

        /*
         * Remaining indices have no warmer future day.
         * answer[i] stays 0 (default).
         */
        return answer;
    }

    /**
     * Demonstrates the algorithm.
     */
    public static void main(String[] args) {

        DailyTemperatures solution = new DailyTemperatures();

        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println("============================================================");
        System.out.println("Daily Temperatures");
        System.out.println("============================================================");

        System.out.println("Input    : " + Arrays.toString(temps));
        System.out.println("Output   : " + Arrays.toString(solution.waitDays(temps)));
        System.out.println("Expected : [1, 1, 4, 2, 1, 1, 0, 0]");
    }
}
