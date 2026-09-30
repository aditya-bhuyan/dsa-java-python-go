// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Assign Cookies (LeetCode 455)
// Approach: Greedy — Sort + Two Pointers

package greedy.assigncookies;

import java.util.Arrays;

/**
 * Solution for Assign Cookies.
 *
 * <p>Strategy: Sort both arrays ascending. Use two pointers to greedily
 * assign the smallest sufficient cookie to the least greedy unsatisfied child.
 * Always advance the cookie pointer; only advance the child pointer on a match.
 *
 * <p>Time:  O(n log n + m log m)
 * Space: O(1)
 */
public class AssignCookies {

    /**
     * Returns the maximum number of children that can be made content.
     *
     * @param g greed factors of children
     * @param s sizes of available cookies
     * @return count of content children
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0, cookie = 0;
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) child++;
            cookie++;
        }
        return child;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        AssignCookies solver = new AssignCookies();

        int[][][] inputs = {
            {{1, 2, 3}, {1, 1}},
            {{1, 2},    {1, 2, 3}},
            {{10, 9, 8, 7}, {5, 6, 7, 8}},
            {{1, 2, 3}, {}},
        };
        int[] expected = {1, 2, 2, 0};

        System.out.println("============================================================");
        System.out.println("Assign Cookies");
        System.out.println("============================================================");
        for (int i = 0; i < inputs.length; i++) {
            // Clone to avoid mutating test data
            int[] gCopy = inputs[i][0].clone();
            int[] sCopy = inputs[i][1].clone();
            int result = solver.findContentChildren(gCopy, sCopy);
            System.out.printf("Test %d: result=%d (expected %d) → %s%n",
                i + 1, result, expected[i], result == expected[i] ? "PASS" : "FAIL");
        }
    }
}
