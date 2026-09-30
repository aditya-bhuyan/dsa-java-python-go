package dp.climbingstairs;

// =============================================================================
// File    : ClimbingStairsTest.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Climbing Stairs (LeetCode #70)
// =============================================================================

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClimbingStairsTest {

    private ClimbingStairs solver;

    @BeforeEach
    void setUp() {
        solver = new ClimbingStairs();
    }

    @Test
    void testN1() {
        assertEquals(1, solver.climbStairs(1));
    }

    @Test
    void testN2() {
        assertEquals(2, solver.climbStairs(2));
    }

    @Test
    void testN3() {
        assertEquals(3, solver.climbStairs(3));
    }

    @Test
    void testN4() {
        assertEquals(5, solver.climbStairs(4));
    }

    @Test
    void testN5() {
        assertEquals(8, solver.climbStairs(5));
    }

    @Test
    void testN10() {
        assertEquals(89, solver.climbStairs(10));
    }

    @Test
    void testN20() {
        assertEquals(10946, solver.climbStairs(20));
    }

    @Test
    void testN45() {
        assertEquals(1836311903, solver.climbStairs(45));
    }
}
