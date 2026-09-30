# Maximum Average Subarray I

## Problem Statement

You are given an integer array `nums` consisting of `n` elements, and an integer `k`.

Find a contiguous subarray whose **length is equal to `k`** that has the **maximum average value** and return this value. Any answer with a calculation error less than `10⁻⁵` will be accepted.

**LeetCode:** [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)  
**Difficulty:** Easy  
**Topic Tags:** Array, Sliding Window

---

## Examples

### Example 1
```
Input:  nums = [1, 12, -5, -6, 50, 3], k = 4
Output: 12.75000
Explanation: The subarray [12, -5, -6, 50] has average (12-5-6+50)/4 = 51/4 = 12.75
```

### Example 2
```
Input:  nums = [5], k = 1
Output: 5.00000
```

---

## Constraints

- `n == nums.length`
- `1 <= k <= n <= 10⁵`
- `-10⁴ <= nums[i] <= 10⁴`

---

## Understanding the Problem

We want the **maximum sum** (then divide by `k`) among all contiguous subarrays of length exactly `k`. Because `k` is fixed, this is the simplest form of sliding window — a fixed-size window that slides one step at a time.

### Key Observation
When the window moves one step to the right:
- Add the new right element.
- Subtract the element that just left the window on the left.
- No need to recompute the full sum each time → O(1) per step → O(n) overall.

---

## Approach 1: Brute Force

**Idea:** For every starting index `i`, compute the sum of `nums[i..i+k-1]` and track the maximum.

```
maxSum = -∞
for i = 0 to n-k:
    sum = 0
    for j = i to i+k-1:
        sum += nums[j]
    maxSum = max(maxSum, sum)
return maxSum / k
```

**Time:** O(n × k)  **Space:** O(1)  
Fails for large inputs (n = 10⁵, k = 10⁵ → 10¹⁰ ops).

---

## Approach 2: Fixed Sliding Window (Optimal)

**Idea:**
1. Compute the sum of the first window `nums[0..k-1]`.
2. Slide the window one step: `sum = sum + nums[right] - nums[right - k]`.
3. Track the maximum sum seen.
4. Return `maxSum / k`.

### Algorithm Steps
```
sum = sum(nums[0..k-1])
maxSum = sum
for right = k to n-1:
    sum += nums[right] - nums[right - k]
    maxSum = max(maxSum, sum)
return maxSum / k
```

### Dry Run
```
nums = [1, 12, -5, -6, 50, 3],  k = 4

Initial window [0..3]:
  sum = 1 + 12 + (-5) + (-6) = 2,  maxSum = 2

right = 4: add nums[4]=50, remove nums[0]=1
  sum = 2 + 50 - 1 = 51,  maxSum = 51

right = 5: add nums[5]=3, remove nums[1]=12
  sum = 51 + 3 - 12 = 42,  maxSum = 51

return 51 / 4 = 12.75 ✓
```

### ASCII Visualization
```
Index:  0    1    2    3    4    5
Value:  1   12   -5   -6   50    3
        [←————k=4————→]            sum=2
             [←————k=4————→]       sum=51  ← max
                  [←————k=4————→]  sum=42
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n × k) | O(1) |
| Sliding Window | O(n) | O(1) |

---

## Edge Cases

| Case | Notes |
|---|---|
| `k == n` | Entire array is the only window |
| `k == 1` | Answer is simply `max(nums)` |
| All negative numbers | Answer is the least-negative k-window |
| Window with all same values | Any window gives same average |

---

## Interview Discussion Points

1. **Why not divide by `k` at every step?** — Compare sums to avoid floating-point division in the hot loop; divide once at the end.
2. **Integer overflow?** — With n = 10⁵ and max value 10⁴, max sum = 10⁹ which fits in a 32-bit signed integer (max ~2.1 × 10⁹). Safe in Java/Go; Python has arbitrary precision.
3. **How does this generalise?** — Any fixed-window aggregation (max product, min XOR, …) follows the same template.

---

## Common Mistakes

- Using `nums[right - k - 1]` instead of `nums[right - k]` for the element being removed — off by one.
- Initialising `maxSum` to 0 (fails when all values are negative).
- Dividing the sum inside the loop on every iteration (wasteful; also introduces floating-point noise).

---

## Key Takeaways

- Fixed-size sliding window = **O(n)** by maintaining a running aggregate rather than recomputing from scratch.
- The remove index is always `right - k` (the element exactly `k` positions behind the new right element).
- This is the entry-point template for all sliding window problems.

---

## Next Problem

➡️ [Longest Substring Without Repeating Characters](../longest-substring/README.md)
