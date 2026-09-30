// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Minimum Window Substring (LeetCode 76)
// Approach: Variable Sliding Window + Match Counter

package slidingwindow.minimumwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution for Minimum Window Substring.
 *
 * <p>Strategy: Sliding window with a match counter.
 * - need[c] = required frequency of c from t.
 * - required = distinct chars in t.
 * - Expand right; when have[c] == need[c], matches++.
 * - When matches == required, shrink left recording minimum window.
 *
 * <p>Time:  O(m + n)
 * Space: O(|Σ|)
 */
public class MinimumWindow {

    /**
     * Returns the minimum window substring of s containing all chars of t.
     *
     * @param s source string
     * @param t target string
     * @return minimum window, or "" if none exists
     */
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int required = need.size();
        Map<Character, Integer> have = new HashMap<>();
        int matches = 0, left = 0;
        int bestLen = s.length() + 1, bestLeft = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            have.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && have.get(c).equals(need.get(c))) {
                matches++;
            }
            while (matches == required) {
                if (right - left + 1 < bestLen) {
                    bestLen = right - left + 1;
                    bestLeft = left;
                }
                char lc = s.charAt(left);
                have.merge(lc, -1, Integer::sum);
                if (need.containsKey(lc) && have.get(lc) < need.get(lc)) {
                    matches--;
                }
                left++;
            }
        }
        return bestLen > s.length() ? "" : s.substring(bestLeft, bestLeft + bestLen);
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        MinimumWindow solver = new MinimumWindow();

        String[][] tests = {
            {"ADOBECODEBANC", "ABC", "BANC"},
            {"a", "a", "a"},
            {"a", "aa", ""},
            {"aa", "aa", "aa"},
            {"ab", "b", "b"},
        };

        System.out.println("============================================================");
        System.out.println("Minimum Window Substring");
        System.out.println("============================================================");
        for (int i = 0; i < tests.length; i++) {
            String result = solver.minWindow(tests[i][0], tests[i][1]);
            boolean ok = result.equals(tests[i][2]);
            System.out.printf("Test %d: s=%s t=%s → %s (expected %s) %s%n",
                i + 1, tests[i][0], tests[i][1],
                result.isEmpty() ? "\"\"" : result,
                tests[i][2].isEmpty() ? "\"\"" : tests[i][2],
                ok ? "PASS" : "FAIL");
        }
    }
}
