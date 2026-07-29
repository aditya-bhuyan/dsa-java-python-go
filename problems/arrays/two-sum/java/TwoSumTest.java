package problems.arrays.twosum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TwoSumTest {

    private final TwoSum solution = new TwoSum();

    @Test
    void testBasicExample() {
        assertArrayEquals(
                new int[]{0,1},
                solution.twoSum(new int[]{2,7,11,15},9)
        );
    }

    @Test
    void testSecondExample() {
        assertArrayEquals(
                new int[]{1,2},
                solution.twoSum(new int[]{3,2,4},6)
        );
    }

    @Test
    void testDuplicateValues() {
        assertArrayEquals(
                new int[]{0,1},
                solution.twoSum(new int[]{3,3},6)
        );
    }

    @Test
    void testNegativeNumbers() {
        assertArrayEquals(
                new int[]{2,4},
                solution.twoSum(new int[]{-1,-2,-3,-4,-5},-8)
        );
    }

    @Test
    void testMixedNumbers() {
        assertArrayEquals(
                new int[]{0,2},
                solution.twoSum(new int[]{-3,4,3,90},0)
        );
    }

    @Test
    void testZeros() {
        assertArrayEquals(
                new int[]{0,3},
                solution.twoSum(new int[]{0,4,3,0},0)
        );
    }

    @Test
    void testMinimumArray() {
        assertArrayEquals(
                new int[]{0,1},
                solution.twoSum(new int[]{5,8},13)
        );
    }

    @Test
    void testNoSolution() {
        assertThrows(
                IllegalArgumentException.class,
                () -> solution.twoSum(new int[]{1,2,3},100)
        );
    }
}