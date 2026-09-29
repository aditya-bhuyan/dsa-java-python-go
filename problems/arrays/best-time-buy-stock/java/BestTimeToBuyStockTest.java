import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BestTimeToBuyStockTest {

    private final BestTimeToBuyStock solver = new BestTimeToBuyStock();

    @Test
    public void testBasicCase1() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        assertEquals(5, solver.maxProfit(prices));
    }

    @Test
    public void testBasicCase2() {
        int[] prices = {7, 6, 4, 3, 1};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void testBasicCase3() {
        int[] prices = {2, 4, 1};
        assertEquals(2, solver.maxProfit(prices));
    }

    @Test
    public void testSingleElement() {
        int[] prices = {1};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void testTwoElements() {
        int[] prices = {1, 2};
        assertEquals(1, solver.maxProfit(prices));
    }

    @Test
    public void testAscendingPrices() {
        int[] prices = {1, 2, 3, 4, 5};
        assertEquals(4, solver.maxProfit(prices));
    }

    @Test
    public void testDescendingPrices() {
        int[] prices = {5, 4, 3, 2, 1};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void testAllSamePrices() {
        int[] prices = {5, 5, 5, 5};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void testMinMaxAtEnds() {
        int[] prices = {1, 5, 0, 4};
        assertEquals(4, solver.maxProfit(prices));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, solver.maxProfit(null));
    }

    @Test
    public void testEmptyArray() {
        int[] prices = {};
        assertEquals(0, solver.maxProfit(prices));
    }

    @Test
    public void testLargeNumbers() {
        int[] prices = {Integer.MAX_VALUE - 10, 1, Integer.MAX_VALUE};
        assertEquals(Integer.MAX_VALUE - 1, solver.maxProfit(prices));
    }

    @Test
    public void testBruteForceComparison() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        assertEquals(
            solver.maxProfit(prices),
            solver.maxProfitBruteForce(prices)
        );
    }

    @Test
    public void testBruteForceMultipleCases() {
        int[][] testCases = {
            {7, 6, 4, 3, 1},
            {2, 4, 1},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1}
        };

        for (int[] prices : testCases) {
            assertEquals(
                solver.maxProfit(prices),
                solver.maxProfitBruteForce(prices),
                "Brute force should match greedy for: " + java.util.Arrays.toString(prices)
            );
        }
    }
}
