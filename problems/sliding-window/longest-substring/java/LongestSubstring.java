// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Longest Substring Without Repeating Characters (LeetCode 3)
// Approach: Variable Sliding Window + Index Map

package slidingwindow.longestsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * Solution for Longest Substring Without Repeating Characters.
 *
 * <p>Strategy: Variable sliding window with an index map (char → last index).
 * For each character at right:
 * - If it was seen at index >= left, jump left = seen[c] + 1.
 * - Update seen[c] = right.
 * - Update best = max(best, right - left + 1).
 *
 * <p>Time:  O(n)
 * Space: O(min(n, |Σ|))
 */
public class LongestSubstring {

    /**
     * Returns the length of the longest substring without repeating characters.
     *
     * @param s input string
     * @return length of longest valid substring
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (seen.containsKey(c) && seen.get(c) >= left) {
                left = seen.get(c) + 1;
            }
            seen.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        LongestSubstring solver = new LongestSubstring();

        String[] inputs = {"abcabcbb", "bbbbb", "pwwkew", "", "a", "abcdefg"};
        int[] expected = {3, 1, 3, 0, 1, 7};

        System.out.println("============================================================");
        System.out.println("Longest Substring Without Repeating Characters");
        System.out.println("============================================================");
        for (int i = 0; i < inputs.length; i++) {
            int result = solver.lengthOfLongestSubstring(inputs[i]);
            System.out.printf("Test %d: s=%s → %d (expected %d) %s%n",
                i + 1, inputs[i].isEmpty() ? "\"\"" : "\"" + inputs[i] + "\"",
                result, expected[i], result == expected[i] ? "PASS" : "FAIL");
        }
    }
}
