public class BestTimeToBuyStock {

    /**
     * Find the maximum profit from buying and selling stock once.
     * 
     * Time Complexity: O(n) - single pass through the array
     * Space Complexity: O(1) - constant extra space
     * 
     * @param prices array of integers where prices[i] is the price on day i
     * @return maximum profit possible, or 0 if no profit can be achieved
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - minPrice;
            maxProfit = Math.max(maxProfit, profit);
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }

    /**
     * Brute force approach - check all pairs (for reference/teaching)
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static int maxProfitBruteForce(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        // Test cases
        int[] test1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test 1: " + maxProfit(test1) + " (Expected: 5)");

        int[] test2 = {7, 6, 4, 3, 1};
        System.out.println("Test 2: " + maxProfit(test2) + " (Expected: 0)");

        int[] test3 = {2, 4, 1};
        System.out.println("Test 3: " + maxProfit(test3) + " (Expected: 2)");

        int[] test4 = {1};
        System.out.println("Test 4: " + maxProfit(test4) + " (Expected: 0)");

        int[] test5 = {1, 2, 3, 4, 5};
        System.out.println("Test 5: " + maxProfit(test5) + " (Expected: 4)");

        int[] test6 = {};
        System.out.println("Test 6: " + maxProfit(test6) + " (Expected: 0)");
    }
}
