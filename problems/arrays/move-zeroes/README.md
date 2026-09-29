# Move Zeroes

## Problem Statement

Given an integer array `nums`, move all the zeros to the end of it while maintaining the relative order of the non-zero elements.

**Note:** You must do this **in-place** without making a copy of the array.

## Problem Details

- **Input:** An array of integers (may contain zeros)
- **Output:** Modify array in-place to move all zeros to the end
- **Time Complexity:** O(n) - single pass through the array
- **Space Complexity:** O(1) - in-place modification

## Examples

### Example 1
```
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
```

### Example 2
```
Input: nums = [0]
Output: [0]
```

### Example 3
```
Input: nums = [1, 2, 3]
Output: [1, 2, 3]
No zeros to move
```

## Algorithm Approach

### Two Pointer Technique (Optimal)
Use two pointers: one tracking the position for the next non-zero element, and one scanning through the array.

**Algorithm:**
1. Initialize pointer `pos = 0` to track position for next non-zero
2. Iterate through array:
   - If element is non-zero, move it to position `pos` and increment `pos`
3. Fill remaining positions with zeros

**Time Complexity:** O(n) - single pass  
**Space Complexity:** O(1) - no extra space

## Key Observations

1. Only non-zero elements need to be moved forward
2. Relative order of non-zero elements must be maintained
3. In-place requirement means can't use extra arrays
4. Two-pointer technique efficiently handles this

## Edge Cases

- Array with no zeros: return as-is
- Array with all zeros: return as-is
- Array with single element (zero or non-zero)
- Empty array
