# Bit Manipulation

## Table of Contents

1. [Introduction](#introduction)
2. [Binary Representation](#binary-representation)
3. [Bitwise Operators](#bitwise-operators)
4. [Common Bit Tricks](#common-bit-tricks)
5. [Two's Complement & Negative Numbers](#twos-complement--negative-numbers)
6. [Bit Masks](#bit-masks)
7. [Complexity Analysis](#complexity-analysis)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

**Bit manipulation** operates directly on the binary representation of integers. It is used to:
- Replace expensive arithmetic with O(1) bitwise ops
- Encode sets of flags in a single integer (bitmask)
- Solve problems involving subsets, XOR properties, counting bits
- Implement fast multiply/divide by powers of 2

Bit manipulation often leads to the most efficient solutions for certain problem classes — O(1) space, O(1) per operation.

---

## Binary Representation

Every integer is stored as a sequence of bits (0 or 1). For a 32-bit signed integer:

```
Decimal 13  →  Binary 0000 0000 0000 0000 0000 0000 0000 1101
                       ^MSB                            bit 3210^
                       Bit 3 = 1, Bit 2 = 1, Bit 1 = 0, Bit 0 = 1
                       8 + 4 + 0 + 1 = 13
```

### Bit Positions (0-indexed from right)

```
n = 42 = 0b101010

Position: 5  4  3  2  1  0
Bit:      1  0  1  0  1  0

Value:   32  0  8  0  2  0  =  42
```

---

## Bitwise Operators

| Operator | Symbol | Description | Example (a=5=101, b=3=011) |
|---|---|---|---|
| AND | `&` | 1 only if both bits are 1 | `5 & 3 = 001 = 1` |
| OR | `\|` | 1 if either bit is 1 | `5 \| 3 = 111 = 7` |
| XOR | `^` | 1 if bits differ | `5 ^ 3 = 110 = 6` |
| NOT | `~` | Flip all bits | `~5 = ...11111010 = -6` |
| Left Shift | `<<` | Shift bits left, fill 0 | `5 << 1 = 1010 = 10` |
| Right Shift | `>>` | Shift bits right | `5 >> 1 = 010 = 2` |

### Truth Tables

```
AND:         OR:          XOR:
0 & 0 = 0    0|0 = 0      0^0 = 0
0 & 1 = 0    0|1 = 1      0^1 = 1
1 & 0 = 0    1|0 = 1      1^0 = 1
1 & 1 = 1    1|1 = 1      1^1 = 0   ← XOR: same bits cancel
```

---

## Common Bit Tricks

### Check if bit i is set
```
(n >> i) & 1 == 1
```
```
n=13=1101,  i=2:  (13>>2) & 1 = 11 & 1 = 1  ✓ (bit 2 is set)
n=13=1101,  i=1:  (13>>1) & 1 = 110 & 1 = 0  (bit 1 is not set)
```

### Set bit i
```
n | (1 << i)
```

### Clear bit i
```
n & ~(1 << i)
```

### Toggle bit i
```
n ^ (1 << i)
```

### Check if n is a power of 2
```
n > 0 && (n & (n-1)) == 0
```
```
n=8=1000: 8 & 7 = 1000 & 0111 = 0  ✓ power of 2
n=6=0110: 6 & 5 = 0110 & 0101 = 4 ≠ 0  not a power of 2
```
**Why:** A power of 2 has exactly one bit set. `n-1` flips that bit and all lower bits. `n & (n-1)` = 0 iff exactly one bit was set.

### Turn off the lowest set bit
```
n & (n-1)
```
Use case: count set bits (Brian Kernighan's algorithm)
```
count = 0
while n != 0:
    n = n & (n-1)
    count++
```

### Get the lowest set bit
```
n & (-n)     ← equivalently: n & (~n + 1)
```
```
n=12=1100:  -12 = ...10100  →  12 & -12 = 0100 = 4
```

### XOR properties
```
x ^ x = 0        (same value cancels)
x ^ 0 = x        (XOR with 0 is identity)
x ^ y ^ x = y    (double XOR cancels)
```
**Use:** Find the single non-duplicate in an array where every other element appears twice.

### Multiply / divide by power of 2
```
n << k  =  n * 2^k
n >> k  =  n / 2^k  (arithmetic shift for signed; floor division)
```

### Swap without temp variable
```
a ^= b
b ^= a
a ^= b
```

### Count set bits (popcount)
```
# Brian Kernighan — O(set bits)
count = 0
while n: n &= n-1; count++

# Bit parallel — O(1) for 32-bit
n = n - ((n >> 1) & 0x55555555)
n = (n & 0x33333333) + ((n >> 2) & 0x33333333)
n = (n + (n >> 4)) & 0x0F0F0F0F
return (n * 0x01010101) >> 24
```

---

## Two's Complement & Negative Numbers

Computers store negative integers using **two's complement**:
```
-x  =  ~x + 1

Example:  -5 (32-bit)
+5 = 0000 0101
~5 = 1111 1010
-5 = 1111 1011   (add 1)
```

Key consequences:
- `~n = -(n+1)` — bitwise NOT of n equals negative n minus 1
- Right shift `>>` on signed integers fills with the **sign bit** (arithmetic shift)
- `n & (-n)` isolates the lowest set bit because `-n = ~n + 1`

---

## Bit Masks

A **bitmask** represents a set using a single integer — each bit corresponds to an element.

### Subset Enumeration
```
# All subsets of a set of n elements
for mask in range(1 << n):
    # mask's binary repr tells which elements are included
    for i in range(n):
        if mask & (1 << i):
            # element i is in this subset
```

### Bitmask DP pattern
```
# dp[mask] = answer for the subset represented by mask
dp = [0] * (1 << n)
dp[0] = base_case
for mask in range(1 << n):
    for i in range(n):
        if not (mask & (1 << i)):   # i not yet chosen
            new_mask = mask | (1 << i)
            dp[new_mask] = combine(dp[mask], cost(i, mask))
```

---

## Complexity Analysis

| Operation | Time | Space |
|---|---|---|
| Any single bitwise op | O(1) | O(1) |
| Count set bits (Kernighan) | O(k) where k = set bits | O(1) |
| Count set bits (parallel) | O(1) | O(1) |
| Enumerate all subsets of n elements | O(2ⁿ) | O(1) |
| Bitmask DP | O(n · 2ⁿ) | O(2ⁿ) |

---

## Language Implementations

### Go

```go
// Check if bit i is set
func isBitSet(n, i int) bool { return (n>>i)&1 == 1 }

// Count set bits (Kernighan)
func countBits(n int) int {
    count := 0
    for n != 0 { n &= n - 1; count++ }
    return count
}

// Single number (XOR trick)
func singleNumber(nums []int) int {
    res := 0
    for _, v := range nums { res ^= v }
    return res
}
```

### Java

```java
// Check power of 2
boolean isPowerOfTwo(int n) { return n > 0 && (n & (n - 1)) == 0; }

// Count set bits
int countBits(int n) {
    int count = 0;
    while (n != 0) { n &= n - 1; count++; }
    return count;
}

// Built-in
int bits = Integer.bitCount(n);
```

### Python

```python
# Check if bit i is set
def is_bit_set(n: int, i: int) -> bool:
    return (n >> i) & 1 == 1

# Count set bits
def count_bits(n: int) -> int:
    count = 0
    while n:
        n &= n - 1
        count += 1
    return count

# Python built-in
bits = bin(n).count('1')

# XOR trick — find single number
from functools import reduce
import operator
single = reduce(operator.xor, nums)
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Forgetting operator precedence: `n & mask == 0` evaluates as `n & (mask == 0)` | Always parenthesise: `(n & mask) == 0` |
| Right shift on negative signed int fills with 1s (sign extension) | Use unsigned right shift `>>>` in Java; mask with `& 0xFFFFFFFF` in Python |
| `~n` is `-(n+1)`, not `-n` | Know two's complement: `-n = ~n + 1` |
| Off-by-one when iterating bit positions (0-indexed vs 1-indexed) | Always use 0-indexed bit positions |
| Modifying the loop variable `n` when iterating bits | Use a copy: `tmp = n` while iterating |
| Python integers are arbitrary precision — `~n` gives a negative bigint | Mask: `n ^ 0xFFFFFFFF` for a 32-bit flip |

---

## Problems Covered

| Problem | Key Trick | LeetCode |
|---|---|---|
| Single Number | XOR all elements | #136 |
| Number of 1 Bits | Kernighan's n&(n-1) | #191 |
| Power of Two | `n & (n-1) == 0` | #231 |
| Counting Bits | DP + lowest bit trick | #338 |
| Reverse Bits | Shift and OR | #190 |
| Missing Number | XOR with indices | #268 |
