package dp.coinchange;

// =============================================================================
// File    : CoinChangeTest.java
// Author  : Aditya Bhuyan
// Date    : 2026-07-29
// Problem : Coin Change (LeetCode #322)
// =============================================================================

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoinChangeTest {

    private CoinChange solver;

    @BeforeEach
    void setUp() {
        solver = new CoinChange();
    }

    @Test
    void testExample1() {
        assertEquals(3, solver.coinChange(new int[]{1, 2, 5}, 11));
    }

    @Test
    void testImpossible() {
        assertEquals(-1, solver.coinChange(new int[]{2}, 3));
    }

    @Test
    void testZeroAmount() {
        assertEquals(0, solver.coinChange(new int[]{1}, 0));
    }

    @Test
    void testSingleCoinExact() {
        assertEquals(1, solver.coinChange(new int[]{5}, 5));
    }

    @Test
    void testSingleCoinMultiple() {
        assertEquals(3, solver.coinChange(new int[]{3}, 9));
    }

    @Test
    void testImpossibleLarger() {
        assertEquals(-1, solver.coinChange(new int[]{5, 10}, 3));
    }

    @Test
    void testLargeAmount() {
        assertEquals(4, solver.coinChange(new int[]{1, 5, 10, 25}, 100));
    }

    @Test
    void testLargeCoins() {
        assertEquals(20, solver.coinChange(new int[]{186, 419, 83, 408}, 6249));
    }
}
