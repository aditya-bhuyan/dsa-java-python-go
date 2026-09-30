// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Minimum Window Substring — Java JUnit 5 Tests

package slidingwindow.minimumwindow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinimumWindowTest {

    private MinimumWindow solver;

    @BeforeEach
    void setUp() { solver = new MinimumWindow(); }

    @Test
    void testExample1() {
        assertEquals("BANC", solver.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    void testExactMatch() {
        assertEquals("a", solver.minWindow("a", "a"));
    }

    @Test
    void testNeedsTwo() {
        assertEquals("", solver.minWindow("a", "aa"));
    }

    @Test
    void testSandTEqual() {
        assertEquals("aa", solver.minWindow("aa", "aa"));
    }

    @Test
    void testSingleCharInT() {
        assertEquals("b", solver.minWindow("ab", "b"));
    }

    @Test
    void testTNotInS() {
        assertEquals("", solver.minWindow("abc", "d"));
    }

    @Test
    void testEmptyT() {
        assertEquals("", solver.minWindow("abc", ""));
    }

    @Test
    void testTLongerThanS() {
        assertEquals("", solver.minWindow("a", "ab"));
    }
}
