# Best Time to Buy and Sell Stock

## Problem Statement

You are given an array `prices` where `prices[i]` is the price of a given stock on the i<sup>th</sup> day. You want to maximize your profit by choosing a single day to buy one stock and a different day in the future to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

**Constraint:** You must buy before you sell.

## Problem Details

- **Input:** An array of integers representing stock prices on consecutive days
- **Output:** An integer representing the maximum profit possible
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - constant extra space

## Examples

### Example 1
```
Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
```

### Example 2
```
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: No profit possible as prices only decrease.
```

### Example 3
```
Input: prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
```

## Algorithm Approach

### Greedy Approach (Optimal)
The key insight is to track the minimum price seen so far as we iterate through the array. For each price, we calculate the profit if we sold at that price, and keep track of the maximum profit.

**Algorithm:**
1. Initialize `min_price` to the first element and `max_profit` to 0
2. Iterate through prices from index 1:
   - Calculate profit as current price - min_price
   - Update max_profit if current profit is greater
   - Update min_price if current price is smaller
3. Return max_profit

**Time Complexity:** O(n) - single pass  
**Space Complexity:** O(1) - no extra space

### Brute Force Approach (Reference)
Check every pair of buy-sell days and find the maximum profit.

**Time Complexity:** O(n²)  
**Space Complexity:** O(1)

## Key Observations

1. We must buy before we sell (forward-looking problem)
2. We can only hold one stock at a time
3. We don't have to buy/sell if there's no profit
4. The greedy approach works because profit at any point depends only on the minimum price seen so far

## Edge Cases

- Array with single element: return 0
- Strictly decreasing prices: return 0
- Strictly increasing prices: return last - first
- All same prices: return 0
- Empty array: return 0
