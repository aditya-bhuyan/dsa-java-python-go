class BestTimeToBuyStock:
    """
    Find the maximum profit from buying and selling stock once.
    
    Time Complexity: O(n) - single pass through the array
    Space Complexity: O(1) - constant extra space
    """
    
    @staticmethod
    def max_profit(prices):
        """
        Greedy approach: track minimum price seen so far.
        
        Args:
            prices: List of integers where prices[i] is the price on day i
        
        Returns:
            Maximum profit possible, or 0 if no profit can be achieved
        """
        if not prices or len(prices) < 2:
            return 0
        
        min_price = prices[0]
        max_profit = 0
        
        for price in prices[1:]:
            profit = price - min_price
            max_profit = max(max_profit, profit)
            min_price = min(min_price, price)
        
        return max_profit
    
    @staticmethod
    def max_profit_brute_force(prices):
        """
        Brute force approach - check all pairs (for reference/teaching)
        
        Time Complexity: O(n²)
        Space Complexity: O(1)
        """
        if not prices or len(prices) < 2:
            return 0
        
        max_profit = 0
        for i in range(len(prices)):
            for j in range(i + 1, len(prices)):
                profit = prices[j] - prices[i]
                max_profit = max(max_profit, profit)
        
        return max_profit
    
    @staticmethod
    def max_profit_one_liner(prices):
        """
        Functional approach using reduce (for educational purpose)
        """
        if not prices or len(prices) < 2:
            return 0
        
        from functools import reduce
        
        def update_profit(state, price):
            min_price, max_profit = state
            return (min(min_price, price), max(max_profit, price - min_price))
        
        _, result = reduce(update_profit, prices[1:], (prices[0], 0))
        return result


if __name__ == "__main__":
    # Test cases
    test1 = [7, 1, 5, 3, 6, 4]
    print(f"Test 1: {BestTimeToBuyStock.max_profit(test1)} (Expected: 5)")

    test2 = [7, 6, 4, 3, 1]
    print(f"Test 2: {BestTimeToBuyStock.max_profit(test2)} (Expected: 0)")

    test3 = [2, 4, 1]
    print(f"Test 3: {BestTimeToBuyStock.max_profit(test3)} (Expected: 2)")

    test4 = [1]
    print(f"Test 4: {BestTimeToBuyStock.max_profit(test4)} (Expected: 0)")

    test5 = [1, 2, 3, 4, 5]
    print(f"Test 5: {BestTimeToBuyStock.max_profit(test5)} (Expected: 4)")

    test6 = []
    print(f"Test 6: {BestTimeToBuyStock.max_profit(test6)} (Expected: 0)")
