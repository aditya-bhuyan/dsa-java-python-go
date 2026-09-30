# Gas Station

## Problem Statement

There are `n` gas stations along a circular route. You are given two integer arrays `gas` and `cost` where:
- `gas[i]` = amount of gas at station `i`.
- `cost[i]` = amount of gas needed to travel from station `i` to station `i+1`.

You have a car with an **unlimited gas tank**. You begin the journey with an **empty tank** at one of the gas stations.

Given `gas` and `cost`, return the **starting station's index** if you can travel around the circuit **once in the clockwise direction**, otherwise return `-1`.

If a solution exists, it is **guaranteed to be unique**.

**LeetCode:** [134. Gas Station](https://leetcode.com/problems/gas-station/)  
**Difficulty:** Medium  
**Topic Tags:** Array, Greedy

---

## Examples

### Example 1
```
Input:  gas  = [1, 2, 3, 4, 5]
        cost = [3, 4, 5, 1, 2]
Output: 3
Explanation: Start at station 3 (0-indexed).
  3→4: tank = 4-1 = 3; travel costs 1 → tank = 3
  4→0: tank = 3+5-2 = 6
  0→1: tank = 6+1-3 = 4
  1→2: tank = 4+2-4 = 2
  2→3: tank = 2+3-5 = 0  ← arrives back with 0 fuel ✓
```

### Example 2
```
Input:  gas  = [2, 3, 4]
        cost = [3, 4, 3]
Output: -1
Explanation: Total gas (9) < total cost (10) → no solution.
```

---

## Constraints

- `n == gas.length == cost.length`
- `1 <= n <= 10⁵`
- `0 <= gas[i], cost[i] <= 10⁴`

---

## Understanding the Problem

At each station, the **net surplus** is `diff[i] = gas[i] - cost[i]`. We need to find a starting station where we can maintain a non-negative running sum of `diff` all the way around.

### Key Observations
1. If `sum(gas) < sum(cost)`, no solution exists (not enough total fuel).
2. If a valid solution exists, it is unique.
3. **Greedy insight:** If the running sum from start `s` becomes negative at station `k`, then **none of the stations between `s` and `k`** can be the answer — they would inherit an even worse tank state from `s`. So we can jump the start to `k+1`.

### Visual
```
diff = gas - cost:
  [1-3, 2-4, 3-5, 4-1, 5-2] = [-2, -2, -2, +3, +3]

Running from index 0:
  0: tank=-2 < 0 → try start=1
  1: tank=-2 < 0 → try start=2
  2: tank=-2 < 0 → try start=3
  3: tank=+3; 4: tank=3+3=6; (wrap) 0: 6-2=4; 1: 4-2=2; 2: 2-2=0 ✓
  start=3, total=-2-2-2+3+3=0 ≥ 0 → valid ✓
```

---

## Approach 1: Brute Force

**Idea:** For every starting station `i`, simulate the full circuit. If we complete it without the tank going negative, return `i`.

```
for start = 0 to n-1:
    tank = 0
    for step = 0 to n-1:
        j = (start + step) % n
        tank += gas[j] - cost[j]
        if tank < 0: break
    else:
        return start
return -1
```

**Time:** O(n²)  **Space:** O(1)

---

## Approach 2: Greedy — Single Pass (Optimal)

**Idea:**
- Maintain `total` = sum of all `diff[i]` (to detect if any solution exists).
- Maintain `current` = running tank from the current candidate `start`.
- Whenever `current < 0`, the current start is invalid → set `start = i + 1` and reset `current = 0`.
- After the loop, if `total >= 0`, return `start`; else return `-1`.

### Algorithm
```
total = current = start = 0
for i = 0 to n-1:
    diff = gas[i] - cost[i]
    total   += diff
    current += diff
    if current < 0:
        start = i + 1
        current = 0
return start if total >= 0 else -1
```

### Why It Works
- `total < 0` → sum of all gas < sum of all cost → impossible.
- `total >= 0` → a solution exists. The greedy argument: if `current` goes negative at index `k`, every station from the previous `start` through `k` fails as a starting point (because starting at any intermediate index inherits less fuel). So `k+1` is the earliest possible new candidate.

### Dry Run — Example 1
```
gas  = [1, 2, 3, 4, 5]
cost = [3, 4, 5, 1, 2]
diff = [-2,-2,-2, 3, 3]

total=0, current=0, start=0

i=0: diff=-2, total=-2, current=-2 < 0 → start=1, current=0
i=1: diff=-2, total=-4, current=-2 < 0 → start=2, current=0
i=2: diff=-2, total=-6, current=-2 < 0 → start=3, current=0
i=3: diff=+3, total=-3, current=3
i=4: diff=+3, total=0,  current=6

total=0 >= 0 → return start=3 ✓
```

### Dry Run — Example 2
```
gas  = [2,3,4], cost = [3,4,3]
diff = [-1,-1,+1]

total after loop = -1 < 0 → return -1 ✓
```

---

## Complexity Analysis

| Approach | Time | Space |
|---|---|---|
| Brute Force | O(n²) | O(1) |
| Greedy | O(n) | O(1) |

---

## Edge Cases

| Case | Expected |
|---|---|
| Single station, `gas >= cost` | `0` |
| Single station, `gas < cost` | `-1` |
| All diffs equal 0 | `0` (any start works; return 0) |
| Solution at the very last station | `n-1` |
| Only one station with surplus | That station's index |

---

## Interview Discussion Points

1. **Why is the solution unique?** — If two valid starting points existed, both could complete the circuit, but that contradicts the single-pass greedy analysis (the surplus wouldn't cancel out). The problem guarantees uniqueness.
2. **Why not try each candidate separately?** — The greedy proof shows that once `current` goes negative at `k`, all stations `start..k` are disqualified simultaneously — no need to test them individually.
3. **Does the order of the reset matter?** — Yes. We must always check `current < 0` after adding `diff[i]`, not before.
4. **Follow-up: What if the circuit can be traveled clockwise or counter-clockwise?** → Run the greedy in both directions, return either valid start.

---

## Common Mistakes

- Not returning `-1` when `total < 0` (incorrectly returning `start` even when no circuit is possible).
- Resetting `start = i` instead of `start = i + 1` after a negative `current`.
- Forgetting to reset `current = 0` when advancing `start`.
- Using `current <= 0` instead of `current < 0` — a zero tank is still valid (you can move to the next station with 0 gas if cost is 0).

---

## Key Takeaways

- **Greedy insight:** A negative running sum tells you not just that the current start fails, but that *every* start in the current segment fails — enabling an O(n) skip.
- The pattern "running sum + reset on negative" also appears in Kadane's Algorithm (Maximum Subarray).
- Always separate the "feasibility check" (`total >= 0`) from the "candidate tracking" (`current` and `start`).

---

## Next Problem

➡️ [Assign Cookies](../assign-cookies/README.md)
