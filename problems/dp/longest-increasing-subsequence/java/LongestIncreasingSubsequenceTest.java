package dp.longestincreasingsubsequence;

// =============================================================================
// File    : LongestIncreasingSubsequenceTest.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Longest Increasing Subsequence (LeetCode #300)
// =============================================================================

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestIncreasingSubsequenceTest {

    private LongestIncreasingSubsequence solver;

    @BeforeEach
    void setUp() {
        solver = new LongestIncreasingSubsequence();
    }

    // --- lengthOfLIS (O(n log n)) ---

    @Test
    void testExample1BinarySearch() {
        assertEquals(4, solver.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    @Test
    void testExample2BinarySearch() {
        assertEquals(4, solver.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    @Test
    void testAllSameBinarySearch() {
        assertEquals(1, solver.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }

    @Test
    void testSingleBinarySearch() {
        assertEquals(1, solver.lengthOfLIS(new int[]{5}));
    }

    @Test
    void testStrictlyIncreasingBinarySearch() {
        assertEquals(5, solver.lengthOfLIS(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testStrictlyDecreasingBinarySearch() {
        assertEquals(1, solver.lengthOfLIS(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void testEmptyBinarySearch() {
        assertEquals(0, solver.lengthOfLIS(new int[]{}));
    }

    // --- lengthOfLISDP (O(n²)) ---

    @Test
    void testExample1DP() {
        assertEquals(4, solver.lengthOfLISDP(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    @Test
    void testExample2DP() {
        assertEquals(4, solver.lengthOfLISDP(new int[]{0, 1, 0, 3, 2, 3}));
    }

    @Test
    void testAllSameDP() {
        assertEquals(1, solver.lengthOfLISDP(new int[]{7, 7, 7, 7}));
    }

    @Test
    void testEmptyDP() {
        assertEquals(0, solver.lengthOfLISDP(new int[]{}));
    }
}
