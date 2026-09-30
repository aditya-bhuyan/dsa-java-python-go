// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Number of Islands — Java JUnit 5 Tests

package graphs.numberofislands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberOfIslandsTest {

    private NumberOfIslands solver;

    @BeforeEach
    void setUp() {
        solver = new NumberOfIslands();
    }

    private char[][] grid(String... rows) {
        char[][] g = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            g[i] = rows[i].toCharArray();
        }
        return g;
    }

    @Test
    void testSingleIsland() {
        char[][] g = grid("11110", "11010", "11000", "00000");
        assertEquals(1, solver.numIslands(g));
    }

    @Test
    void testThreeIslands() {
        char[][] g = grid("11000", "11000", "00100", "00011");
        assertEquals(3, solver.numIslands(g));
    }

    @Test
    void testAllWater() {
        char[][] g = grid("00", "00");
        assertEquals(0, solver.numIslands(g));
    }

    @Test
    void testAllLand() {
        char[][] g = grid("11", "11");
        assertEquals(1, solver.numIslands(g));
    }

    @Test
    void testSingleLandCell() {
        char[][] g = grid("1");
        assertEquals(1, solver.numIslands(g));
    }

    @Test
    void testSingleWaterCell() {
        char[][] g = grid("0");
        assertEquals(0, solver.numIslands(g));
    }

    @Test
    void testDiagonalAreSeparate() {
        char[][] g = grid("10", "01");
        assertEquals(2, solver.numIslands(g));
    }

    @Test
    void testAlternatingRow() {
        char[][] g = grid("10101");
        assertEquals(3, solver.numIslands(g));
    }

    @Test
    void testNullGrid() {
        assertEquals(0, solver.numIslands(null));
    }

    @Test
    void testEmptyGrid() {
        assertEquals(0, solver.numIslands(new char[0][]));
    }
}
