package dp.houserobber;

// =============================================================================
// File    : HouseRobberTest.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : House Robber (LeetCode #198)
// =============================================================================

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseRobberTest {

    private HouseRobber solver;

    @BeforeEach
    void setUp() {
        solver = new HouseRobber();
    }

    @Test
    void testExample1() {
        assertEquals(4, solver.rob(new int[]{1, 2, 3, 1}));
    }

    @Test
    void testExample2() {
        assertEquals(12, solver.rob(new int[]{2, 7, 9, 3, 1}));
    }

    @Test
    void testSingleHouse() {
        assertEquals(5, solver.rob(new int[]{5}));
    }

    @Test
    void testTwoHouses() {
        assertEquals(10, solver.rob(new int[]{3, 10}));
    }

    @Test
    void testAllEqual() {
        assertEquals(8, solver.rob(new int[]{4, 4, 4, 4}));
    }

    @Test
    void testDescending() {
        assertEquals(12, solver.rob(new int[]{9, 5, 3, 1}));
    }

    @Test
    void testAlternating() {
        assertEquals(27, solver.rob(new int[]{1, 9, 1, 9, 1}));
    }

    @Test
    void testEmpty() {
        assertEquals(0, solver.rob(new int[]{}));
    }
}
