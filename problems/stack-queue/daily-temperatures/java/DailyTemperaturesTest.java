package stackqueue.dailytemperatures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DailyTemperaturesTest {

    private final DailyTemperatures solution = new DailyTemperatures();

    @Test
    void testExample1() {
        assertArrayEquals(
                new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                solution.waitDays(new int[]{73, 74, 75, 71, 69, 72, 76, 73})
        );
    }

    @Test
    void testAllIncreasing() {
        assertArrayEquals(
                new int[]{1, 1, 1, 0},
                solution.waitDays(new int[]{30, 40, 50, 60})
        );
    }

    @Test
    void testAllDecreasing() {
        assertArrayEquals(
                new int[]{0, 0, 0, 0},
                solution.waitDays(new int[]{60, 50, 40, 30})
        );
    }

    @Test
    void testSingleElement() {
        assertArrayEquals(
                new int[]{0},
                solution.waitDays(new int[]{50})
        );
    }

    @Test
    void testAllSame() {
        assertArrayEquals(
                new int[]{0, 0, 0},
                solution.waitDays(new int[]{50, 50, 50})
        );
    }

    @Test
    void testTwoIncreasing() {
        assertArrayEquals(
                new int[]{1, 0},
                solution.waitDays(new int[]{30, 60})
        );
    }
}
