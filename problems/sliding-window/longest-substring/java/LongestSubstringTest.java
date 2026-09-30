// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Longest Substring Without Repeating Characters — Java JUnit 5 Tests

package slidingwindow.longestsubstring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringTest {

    private LongestSubstring solver;

    @BeforeEach
    void setUp() { solver = new LongestSubstring(); }

    @Test
    void testAbcabcbb() { assertEquals(3, solver.lengthOfLongestSubstring("abcabcbb")); }

    @Test
    void testAllSame() { assertEquals(1, solver.lengthOfLongestSubstring("bbbbb")); }

    @Test
    void testPwwkew() { assertEquals(3, solver.lengthOfLongestSubstring("pwwkew")); }

    @Test
    void testEmpty() { assertEquals(0, solver.lengthOfLongestSubstring("")); }

    @Test
    void testSingleChar() { assertEquals(1, solver.lengthOfLongestSubstring("a")); }

    @Test
    void testAllUnique() { assertEquals(7, solver.lengthOfLongestSubstring("abcdefg")); }

    @Test
    void testSpaces() { assertEquals(1, solver.lengthOfLongestSubstring("  ")); }
}
