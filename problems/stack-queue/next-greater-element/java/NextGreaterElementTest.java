package stackqueue.nextgreaterelement;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NextGreaterElementTest {

    private final NextGreaterElement solution = new NextGreaterElement();

    @Test
    void testExample1() {
        assertArrayEquals(
                new int[]{-1, 3, -1},
                solution.findNextGreater(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})
        );
    }

    @Test
    void testExample2() {
        assertArrayEquals(
                new int[]{3, -1},
                solution.findNextGreater(new int[]{2, 4}, new int[]{1, 2, 3, 4})
        );
    }

    @Test
    void testAllIncreasingNums2() {
        assertArrayEquals(
                new int[]{2, 3},
                solution.findNextGreater(new int[]{1, 2}, new int[]{1, 2, 3})
        );
    }

    @Test
    void testAllDecreasingNums2() {
        assertArrayEquals(
                new int[]{-1, -1},
                solution.findNextGreater(new int[]{3, 1}, new int[]{3, 2, 1})
        );
    }

    @Test
    void testLastElementInNums2() {
        assertArrayEquals(
                new int[]{-1},
                solution.findNextGreater(new int[]{1}, new int[]{2, 1})
        );
    }

    @Test
    void testSingleElementQuery() {
        assertArrayEquals(
                new int[]{-1},
                solution.findNextGreater(new int[]{5}, new int[]{5, 4, 3, 2, 1})
        );
    }
}
