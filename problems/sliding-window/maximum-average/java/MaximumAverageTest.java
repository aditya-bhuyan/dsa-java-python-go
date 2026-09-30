// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Maximum Average Subarray I — Java JUnit 5 Tests

package slidingwindow.maximumaverage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaximumAverageTest {

    private MaximumAverage solver;
    private static final double EPS = 1e-5;

    @BeforeEach
    void setUp() { solver = new MaximumAverage(); }

    @Test
    void testExample1() {
        assertEquals(12.75, solver.findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4), EPS);
    }

    @Test
    void testSingleElement() {
        assertEquals(5.0, solver.findMaxAverage(new int[]{5}, 1), EPS);
    }

    @Test
    void testAllNegatives() {
        assertEquals(-1.5, solver.findMaxAverage(new int[]{-3,-2,-5,-1}, 2), EPS);
    }

    @Test
    void testKEqualsN() {
        assertEquals(8.0 / 3, solver.findMaxAverage(new int[]{4,0,4}, 3), EPS);
    }

    @Test
    void testK1MaxValue() {
        assertEquals(4.0, solver.findMaxAverage(new int[]{0,4,0,3,2}, 1), EPS);
    }

    @Test
    void testAllSame() {
        assertEquals(2.0, solver.findMaxAverage(new int[]{2,2,2,2}, 2), EPS);
    }
}
