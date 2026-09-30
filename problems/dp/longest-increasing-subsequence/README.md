# Longest Increasing Subsequence

## Problem Statement

Given an integer array `nums`, return the **length of the longest strictly increasing subsequence**.

A **subsequence** is a sequence derived from the array by deleting some (or no) elements without changing the relative order of the remaining elements.

**LeetCode:** [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)  
**Difficulty:** Medium  
**Topic Tags:** Array, Binary Search, Dynamic Programming

---

## Examples

### Example 1
```
Input:  nums = [10, 9, 2, 5, 3, 7, 101, 18]
Output: 4
Explanation: The LIS is [2, 5, 7, 101] or [2, 3, 7, 101] — length 4.
```

### Example 2
```
Input:  nums = [0, 1, 0, 3, 2, 3]
Output: 4
Explanation: [0, 1, 2, 3] — length 4.
```

### Example 3
```
Input:  nums = [7, 7, 7, 7]
Output: 1
Explanation: Strictly increasing — no two equal elements can both be in the LIS.
```

---

## Constraints

- `1 <= nums.length <= 2500`
- `-10⁴ <= nums[i] <= 10⁴`

---

## Understanding the Problem

We are looking for the **longest chain** of elements where each is strictly greater than the previous, and elements are taken in their original left-to-right order (but not necessarily contiguous).

### Key Observations

1. Subsequence ≠ subarray — elements don't need to be adjacent.
2. "Strictly increasing" means equal values cannot both be included.
3. There may be multiple LIS of the same maximum length.

### Visual
```
nums = [10, 9, 2, 5, 3, 7, 101, 18]
Index:   0  1  2  3  4  5   6    7

LIS candidates:
  [10, 101]          length 2
  [9, 101]           length 2
  [2, 5, 7, 101]     length 4 ← optimal
  [2, 3, 7, 101]     length 4 ← optimal
  [2, 5, 7, 18]      length 4 ← optimal
```

---

## Approach 1: Brute Force

Generate all 2ⁿ subsequences, keep only increasing ones, return the maximum length.

**Time:** O(2ⁿ × n)  **Space:** O(n)

---

## Approach 2: DP — O(n²)

**Idea:** `dp[i]` = length of the LIS ending at index `i`.

```
dp[i] = 1 + max(dp[j] for all j < i where nums[j] < nums[i])
        default dp[i] = 1 (just nums[i] itself)
```

```
dp = [1] * n
for i = 1 to n-1:
    for j = 0 to i-1:
        if nums[j] < nums[i]:
            dp[i] = max(dp[i], dp[j] + 1)
return max(dp)
```

### Dry Run (nums = [10, 9, 2, 5, 3, 7, 101, 18])
```
dp = [1, 1, 1, 1, 1, 1, 1, 1]

i=1 (9):  no j<1 with nums[j]<9 → dp[1]=1
i=2 (2):  no j<2 with nums[j]<2 → dp[2]=1
i=3 (5):  j=2(2<5): dp[3]=max(1,dp[2]+1)=2
i=4 (3):  j=2(2<3): dp[4]=max(1,dp[2]+1)=2
i=5 (7):  j=2(2<7): dp[5]=2; j=3(5<7): dp[5]=3; j=4(3<7): dp[5]=3
i=6 (101):j=0,1,2,3,4,5 all < 101: dp[6]=max(dp)+1=4
i=7 (18): j=0..5 many <18: dp[7]=max over those = dp[5]+1=4

dp = [1, 1, 1, 2, 2, 3, 4, 4]
max(dp) = 4 ✓
```

**Time:** O(n²)  **Space:** O(n)

---

## Approach 3: DP + Binary Search — O(n log n)

**Idea:** Maintain a `tails` array where `tails[i]` = smallest tail element of all increasing subsequences of length `i+1`.

- This array is always sorted.
- For each `num`, binary search for the leftmost position in `tails` where `tails[pos] >= num`, then replace `tails[pos] = num`.
- If `num` is larger than all tails, extend `tails`.
- Length of `tails` at the end = LIS length.

```
tails = []
for num in nums:
    pos = bisect_left(tails, num)   # first index where tails[pos] >= num
    if pos == len(tails):
        tails.append(num)           # extend: num is larger than all tails
    else:
        tails[pos] = num            # replace: maintain smallest possible tail
return len(tails)
```

### Why Replacing Keeps the Answer Correct

`tails` does **not** store an actual LIS — it stores the **optimal tail values** for each possible length. Replacing a tail with a smaller value never reduces the length, but opens up more extension possibilities for future elements.

### Dry Run (nums = [10, 9, 2, 5, 3, 7, 101, 18])
```
num=10: tails=[10]
num=9:  bisect_left([10],9)=0 → replace → tails=[9]
num=2:  bisect_left([9],2)=0  → replace → tails=[2]
num=5:  bisect_left([2],5)=1  → extend  → tails=[2,5]
num=3:  bisect_left([2,5],3)=1→ replace → tails=[2,3]
num=7:  bisect_left([2,3],7)=2→ extend  → tails=[2,3,7]
num=101:bisect_left([2,3,7],101)=3→extend→tails=[2,3,7,101]
num=18: bisect_left([2,3,7,101],18)=3→replace→tails=[2,3,7,18]

len(tails) = 4 ✓
```

**Time:** O(n log n)  **Space:** O(n)

---

## Complexity Analysis

| Approach | Time | Space | Notes |
|---|---|---|---|
| Brute Force | O(2ⁿ × n) | O(n) | All subsequences |
| DP O(n²) | O(n²) | O(n) | Two nested loops |
| DP + Binary Search | O(n log n) | O(n) | `tails` + bisect |

---

## Edge Cases

| Case | Expected |
|---|---|
| Single element `[5]` | 1 |
| All same `[7,7,7]` | 1 |
| Already sorted `[1,2,3,4]` | 4 |
| Reverse sorted `[4,3,2,1]` | 1 |
| Negative numbers `[-3,-2,-1,0]` | 4 |

---

## Interview Discussion Points

1. **Why does replacing in `tails` not break correctness?** — `tails[i]` always stores the minimum possible last element among all IS of length `i+1`. A smaller tail always gives more room for future extensions.
2. **Can you reconstruct the actual LIS (not just length)?** — Yes: store the predecessor index for each element during the O(n²) DP; then backtrack from the maximum `dp[i]` position.
3. **What makes this O(n log n) instead of O(n²)?** — Binary search reduces the inner "find the best j" step from O(n) to O(log n) per element.
4. **LIS vs Longest Common Subsequence?** — LCS compares two sequences; LIS works on one sequence with the constraint of increasing values.

---

## Common Mistakes

- Using `bisect_right` instead of `bisect_left` for strictly increasing (equal elements should replace, not extend).
- Returning `max(dp)` instead of `len(dp)` in the O(n²) approach — `dp` values aren't lengths from index 0, so `max(dp)` is correct; but in the `tails` approach, `len(tails)` is the answer.
- Confusing LIS (subsequence, not contiguous) with Longest Increasing Subarray (contiguous).
- Forgetting that `dp[i]` must be initialised to `1` (each element is an IS of length 1 by itself).

---

## Key Takeaways

- The O(n²) DP is intuitive and sufficient for `n ≤ 2500`; the O(n log n) `tails` approach handles `n` up to 10⁵+.
- `bisect_left` on the sorted `tails` array is the key insight for the optimal solution.
- The `tails` array gives the LIS **length** but not the LIS itself — reconstruction requires the O(n²) predecessor approach.
- LIS is a building block for the **patience sorting** algorithm used in some card games.

---

## Next Problem

➡️ [Back to DP Concepts](../../concepts/dynamic-programming.md)
