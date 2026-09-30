// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Flood Fill — Java JUnit 5 Tests

package graphs.floodfill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FloodFillTest {

    private FloodFill solver;

    @BeforeEach
    void setUp() {
        solver = new FloodFill();
    }

    @Test
    void testBasic3x3() {
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] result = solver.floodFill(image, 1, 1, 2);
        assertArrayEquals(new int[][]{{2,2,2},{2,2,0},{2,0,1}}, result);
    }

    @Test
    void testSameColorNoOp() {
        int[][] image = {{0,0,0},{0,0,0}};
        int[][] result = solver.floodFill(FloodFill.copyImage(image), 0, 0, 0);
        assertArrayEquals(new int[][]{{0,0,0},{0,0,0}}, result);
    }

    @Test
    void testSinglePixel() {
        int[][] image = {{1}};
        assertArrayEquals(new int[][]{{5}}, solver.floodFill(image, 0, 0, 5));
    }

    @Test
    void testIsolatedSeed() {
        int[][] image = {{1,0,1}};
        assertArrayEquals(new int[][]{{3,0,1}}, solver.floodFill(image, 0, 0, 3));
    }

    @Test
    void testEntireGridSameColor() {
        int[][] image = {{1,1},{1,1}};
        assertArrayEquals(new int[][]{{9,9},{9,9}}, solver.floodFill(image, 0, 0, 9));
    }
}
