// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Gas Station — Java JUnit 5 Tests

package greedy.gasstation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GasStationTest {

    private GasStation solver;

    @BeforeEach
    void setUp() { solver = new GasStation(); }

    @Test
    void testExample1() {
        assertEquals(3, solver.canCompleteCircuit(
            new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }

    @Test
    void testExample2NoSolution() {
        assertEquals(-1, solver.canCompleteCircuit(
            new int[]{2,3,4}, new int[]{3,4,3}));
    }

    @Test
    void testSingleStationEnough() {
        assertEquals(0, solver.canCompleteCircuit(new int[]{5}, new int[]{4}));
    }

    @Test
    void testSingleStationExact() {
        assertEquals(0, solver.canCompleteCircuit(new int[]{1}, new int[]{1}));
    }

    @Test
    void testSingleStationNotEnough() {
        assertEquals(-1, solver.canCompleteCircuit(new int[]{1}, new int[]{2}));
    }

    @Test
    void testStartAtLast() {
        assertEquals(3, solver.canCompleteCircuit(
            new int[]{1,1,1,4}, new int[]{2,2,2,1}));
    }

    @Test
    void testStartAt0() {
        assertEquals(0, solver.canCompleteCircuit(
            new int[]{3,1,1}, new int[]{1,2,2}));
    }
}
