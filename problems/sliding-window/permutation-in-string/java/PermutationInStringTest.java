// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Permutation in String — Java JUnit 5 Tests

package slidingwindow.permutationinstring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PermutationInStringTest {

    private PermutationInString solver;

    @BeforeEach
    void setUp() { solver = new PermutationInString(); }

    @Test
    void testBasicTrue() { assertTrue(solver.checkInclusion("ab", "eidbaooo")); }

    @Test
    void testBasicFalse() { assertFalse(solver.checkInclusion("ab", "eidboaoo")); }

    @Test
    void testSingleCharPresent() { assertTrue(solver.checkInclusion("a", "ab")); }

    @Test
    void testSingleCharAbsent() { assertFalse(solver.checkInclusion("z", "ab")); }

    @Test
    void testS1LongerThanS2() { assertFalse(solver.checkInclusion("abc", "ab")); }

    @Test
    void testS1EqualsS2() { assertTrue(solver.checkInclusion("abc", "abc")); }

    @Test
    void testDuplicatesInS1Satisfied() { assertTrue(solver.checkInclusion("aa", "aab")); }

    @Test
    void testDuplicatesNotSatisfied() { assertFalse(solver.checkInclusion("aa", "ab")); }

    @Test
    void testAdcInDcda() { assertTrue(solver.checkInclusion("adc", "dcda")); }
}
