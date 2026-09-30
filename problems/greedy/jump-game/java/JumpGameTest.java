// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Jump Game — Java JUnit 5 Tests

package greedy.jumpgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JumpGameTest {

    private JumpGame solver;

    @BeforeEach
    void setUp() { solver = new JumpGame(); }

    @Test
    void testExample1Reachable() { assertTrue(solver.canJump(new int[]{2, 3, 1, 1, 4})); }

    @Test
    void testExample2Stuck() { assertFalse(solver.canJump(new int[]{3, 2, 1, 0, 4})); }

    @Test
    void testSingleElementZero() { assertTrue(solver.canJump(new int[]{0})); }

    @Test
    void testSingleElementNonZero() { assertTrue(solver.canJump(new int[]{5})); }

    @Test
    void testTwoElementsCanJump() { assertTrue(solver.canJump(new int[]{1, 0})); }

    @Test
    void testTwoElementsCannotJump() { assertFalse(solver.canJump(new int[]{0, 1})); }

    @Test
    void testAllOnes() { assertTrue(solver.canJump(new int[]{1, 1, 1, 1})); }

    @Test
    void testLargeJumpFromStart() { assertTrue(solver.canJump(new int[]{5, 0, 0, 0, 0})); }

    @Test
    void testLastElementZeroReachable() { assertTrue(solver.canJump(new int[]{2, 0, 0})); }

    @Test
    void testAllZerosLengthGt1() { assertFalse(solver.canJump(new int[]{0, 0, 0})); }
}
