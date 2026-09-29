# Rotate Array

## Problem Statement

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

You must solve it **in-place** with O(1) extra space.

## Problem Details

- **Input:** An array of integers and a rotation amount k
- **Output:** Modify array in-place to rotate right by k steps
- **Time Complexity:** O(n) - optimal approach
- **Space Complexity:** O(1) - in-place modification

## Examples

### Example 1
```
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
```

### Example 2
```
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
```

### Example 3
```
Input: nums = [1], k = 0
Output: [1]
```

## Algorithm Approach

### Reversal Technique (Optimal)
Reverse the array in segments to achieve rotation in-place.

**Algorithm:**
1. Normalize k: `k = k % n` (handle k > n)
2. Reverse the entire array
3. Reverse the first k elements
4. Reverse the remaining n-k elements

**Example with nums = [1,2,3,4,5,6,7], k = 3:**
- After step 2 (reverse all): [7,6,5,4,3,2,1]
- After step 3 (reverse first 3): [5,6,7,4,3,2,1]
- After step 4 (reverse last 4): [5,6,7,1,2,3,4]

**Time Complexity:** O(n) - three passes  
**Space Complexity:** O(1) - no extra space

## Alternative Approaches

### Brute Force Rotation
Rotate one step at a time (not recommended for large k).

**Time Complexity:** O(n * k)  
**Space Complexity:** O(1)

### Using Extra Array
Copy elements to new positions using extra array.

**Time Complexity:** O(n)  
**Space Complexity:** O(n) - violates constraint

## Key Observations

1. Rotation by k is equivalent to moving last k elements to front
2. Multiple rotations can overflow, so normalize k modulo n
3. Reversal technique is elegant and efficient
4. Important to handle edge cases (k > n, k = 0, single element)

## Edge Cases

- k = 0: no rotation needed
- k >= n: need to normalize k
- Array with single element: no effect
- k = n: full rotation returns to original
- Empty array: handle gracefully
