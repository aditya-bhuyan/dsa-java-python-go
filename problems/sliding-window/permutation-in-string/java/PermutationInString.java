// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Permutation in String (LeetCode 567)
// Approach: Fixed Sliding Window + Match Counter (int[26] arrays)

package slidingwindow.permutationinstring;

/**
 * Solution for Permutation in String.
 *
 * <p>Strategy: Fixed window of size len(s1) over s2.
 * Use int[26] frequency arrays (lowercase only) for O(1) per step.
 * Track matches = chars with have[c] == need[c].
 * Return true when matches == required.
 *
 * <p>Time:  O(m + n)
 * Space: O(1) — fixed 26-char alphabet
 */
public class PermutationInString {

    /**
     * Returns true if any permutation of s1 is a substring of s2.
     *
     * @param s1 pattern string
     * @param s2 text string
     * @return true if permutation found
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] need = new int[26];
        int[] have = new int[26];

        for (char c : s1.toCharArray()) need[c - 'a']++;

        int required = 0;
        for (int v : need) if (v > 0) required++;

        int matches = 0;
        int k = s1.length();

        for (int right = 0; right < s2.length(); right++) {
            int c = s2.charAt(right) - 'a';
            have[c]++;
            if (have[c] == need[c]) matches++;

            if (right >= k) {
                int lc = s2.charAt(right - k) - 'a';
                if (have[lc] == need[lc]) matches--;
                have[lc]--;
            }

            if (matches == required) return true;
        }
        return false;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        PermutationInString solver = new PermutationInString();

        String[][] tests = {
            {"ab", "eidbaooo", "true"},
            {"ab", "eidboaoo", "false"},
            {"a", "ab", "true"},
            {"abc", "bbbca", "true"},
            {"adc", "dcda", "true"},
        };

        System.out.println("============================================================");
        System.out.println("Permutation in String");
        System.out.println("============================================================");
        for (int i = 0; i < tests.length; i++) {
            boolean result = solver.checkInclusion(tests[i][0], tests[i][1]);
            boolean expected = Boolean.parseBoolean(tests[i][2]);
            System.out.printf("Test %d: s1=%s s2=%s → %b (expected %b) %s%n",
                i + 1, tests[i][0], tests[i][1], result, expected,
                result == expected ? "PASS" : "FAIL");
        }
    }
}
