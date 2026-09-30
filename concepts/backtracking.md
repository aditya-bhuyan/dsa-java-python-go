# Backtracking

## Table of Contents

1. [Introduction](#introduction)
2. [The Backtracking Template](#the-backtracking-template)
3. [Decision Tree Visualisation](#decision-tree-visualisation)
4. [Pruning](#pruning)
5. [Classic Backtracking Patterns](#classic-backtracking-patterns)
6. [Backtracking vs Dynamic Programming vs Brute Force](#backtracking-vs-dynamic-programming-vs-brute-force)
7. [Complexity Analysis](#complexity-analysis)
8. [Language Implementations](#language-implementations)
9. [Common Mistakes](#common-mistakes)
10. [Problems Covered](#problems-covered)

---

## Introduction

**Backtracking** is a depth-first recursive algorithm that builds candidates for a solution one piece at a time, and **abandons** (backtracks) a partial candidate as soon as it determines that it cannot lead to a valid complete solution.

It is the systematic way to explore all possibilities in a problem's solution space without generating them all upfront. Backtracking is the go-to technique for:

- **Combinatorial problems** — subsets, permutations, combinations
- **Constraint satisfaction** — Sudoku, N-Queens, crossword filling
- **Pathfinding on implicit graphs** — word search, maze solving
- **Parsing / expression building** — generate valid parentheses, IP addresses

**Core idea:** *"Try every choice at the current step. If a choice leads to a dead end, undo it (backtrack) and try the next choice."*

The key difference from plain brute force: backtracking **prunes** the search tree early — it never explores branches that are provably invalid.

---

## The Backtracking Template

Every backtracking solution follows the same skeleton:

```
result = []

def backtrack(path, choices):
    if is_solution(path):          # base case: path is a complete solution
        result.append(path.copy())
        return

    for choice in choices:
        if not is_valid(choice, path):   # pruning: skip invalid choices
            continue

        path.append(choice)        # 1. CHOOSE  — make the choice
        backtrack(path, next_choices(choice))  # 2. EXPLORE — recurse
        path.pop()                 # 3. UNCHOOSE — undo the choice (backtrack)

backtrack([], initial_choices)
return result
```

### The Three Steps — Choose, Explore, Unchoose

```
┌──────────────────────────────────────────────────────────┐
│  1. CHOOSE   — add the candidate to the current path     │
│  2. EXPLORE  — recurse deeper with the updated state     │
│  3. UNCHOOSE — remove the candidate (restore state)      │
└──────────────────────────────────────────────────────────┘
```

Steps 1 and 3 are mirror images. Whatever you do in step 1, you must undo in step 3. This keeps the shared state (path, visited set, grid cell) consistent across sibling branches.

---

## Decision Tree Visualisation

### Example — Subsets of {1, 2, 3}

```
                        []
              /          |          \
           [1]          [2]          [3]
          /    \          \
       [1,2]  [1,3]      [2,3]
         |
      [1,2,3]

Every node is a valid subset.
All 8 subsets: [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
```

### Example — Permutations of {1, 2, 3}

```
                              []
            /                  |                  \
          [1]                 [2]                 [3]
         /   \              /    \              /    \
      [1,2] [1,3]        [2,1] [2,3]        [3,1] [3,2]
        |     |            |     |            |     |
     [1,2,3][1,3,2]    [2,1,3][2,3,1]    [3,1,2][3,2,1]

6 leaf nodes = 6 permutations = 3!
```

At each level of the tree, one element is **chosen** and removed from the available choices for deeper levels.

---

## Pruning

**Pruning** eliminates branches before fully exploring them, dramatically reducing the search space.

### Types of Pruning

**1. Validity Pruning** — skip choices that immediately violate a constraint:
```
# N-Queens: don't place a queen if the column/diagonal is attacked
if col in used_cols or row-col in used_diag1 or row+col in used_diag2:
    continue
```

**2. Bound Pruning** — skip choices that cannot possibly lead to a better answer:
```
# Combination Sum: don't recurse if the current sum already exceeds target
if current_sum > target:
    return
```

**3. Duplicate Pruning** — when input has duplicates, skip repeated choices at the same tree level:
```
# Subsets II / Permutations II: sort first, then skip duplicates at each level
for i in range(start, len(nums)):
    if i > start and nums[i] == nums[i-1]:
        continue    # skip duplicate at this level
```

### Pruning Impact

Without pruning, a permutation problem over n elements explores n! paths.  
With duplicate skipping, repeated elements collapse many branches.  
With bound pruning, combination sum can reject entire subtrees early.

---

## Classic Backtracking Patterns

### Pattern 1 — Subsets (Power Set)

Generate all subsets of a list. At each index, decide to **include** or **exclude** the element.

```
def subsets(nums):
    result = []
    def backtrack(start, path):
        result.append(path[:])         # every path is a valid subset
        for i in range(start, len(nums)):
            path.append(nums[i])
            backtrack(i + 1, path)     # i+1: don't reuse elements
            path.pop()
    backtrack(0, [])
    return result
```

**Dry Run** for `[1, 2, 3]`:
```
backtrack(0, []) → add []
  choose 1 → backtrack(1, [1]) → add [1]
    choose 2 → backtrack(2, [1,2]) → add [1,2]
      choose 3 → backtrack(3, [1,2,3]) → add [1,2,3]; return
    unchoose 2; choose 3 → backtrack(3, [1,3]) → add [1,3]; return
  unchoose 1; choose 2 → backtrack(2, [2]) → add [2]
    choose 3 → backtrack(3, [2,3]) → add [2,3]; return
  unchoose 2; choose 3 → backtrack(3, [3]) → add [3]; return

Result: [], [1], [1,2], [1,2,3], [1,3], [2], [2,3], [3]  (8 subsets ✓)
```

---

### Pattern 2 — Permutations

Generate all orderings of a list. At each level, choose from the **remaining unused** elements.

```
def permute(nums):
    result = []
    def backtrack(path, used):
        if len(path) == len(nums):
            result.append(path[:])
            return
        for i in range(len(nums)):
            if used[i]: continue
            used[i] = True
            path.append(nums[i])
            backtrack(path, used)
            path.pop()
            used[i] = False
    backtrack([], [False] * len(nums))
    return result
```

**Alternative (swap-based, in-place):**
```
def permute(nums):
    result = []
    def backtrack(start):
        if start == len(nums):
            result.append(nums[:])
            return
        for i in range(start, len(nums)):
            nums[start], nums[i] = nums[i], nums[start]
            backtrack(start + 1)
            nums[start], nums[i] = nums[i], nums[start]
    backtrack(0)
    return result
```

---

### Pattern 3 — Combinations

Choose exactly `k` elements from `n`:

```
def combine(n, k):
    result = []
    def backtrack(start, path):
        if len(path) == k:
            result.append(path[:])
            return
        # pruning: need at least k-len(path) more elements from [start..n]
        for i in range(start, n - (k - len(path)) + 2):
            path.append(i)
            backtrack(i + 1, path)
            path.pop()
    backtrack(1, [])
    return result
```

**Pruning:** `range(start, n - (k - len(path)) + 2)` — don't start from an index where not enough elements remain to fill `k`.

---

### Pattern 4 — Combination Sum (with repetition)

Choose numbers from candidates (allowing reuse) that sum to target:

```
def combination_sum(candidates, target):
    result = []
    candidates.sort()              # enables early termination
    def backtrack(start, path, remaining):
        if remaining == 0:
            result.append(path[:])
            return
        for i in range(start, len(candidates)):
            if candidates[i] > remaining: break    # pruning (sorted)
            path.append(candidates[i])
            backtrack(i, path, remaining - candidates[i])  # i not i+1: allow reuse
            path.pop()
    backtrack(0, [], target)
    return result
```

---

### Pattern 5 — N-Queens

Place `n` queens on an `n×n` board so none attack each other:

```
def solve_n_queens(n):
    result = []
    cols = set(); diag1 = set(); diag2 = set()
    board = [['.']*n for _ in range(n)]

    def backtrack(row):
        if row == n:
            result.append([''.join(r) for r in board])
            return
        for col in range(n):
            if col in cols or row-col in diag1 or row+col in diag2:
                continue                          # pruning
            cols.add(col); diag1.add(row-col); diag2.add(row+col)
            board[row][col] = 'Q'
            backtrack(row + 1)
            board[row][col] = '.'
            cols.remove(col); diag1.remove(row-col); diag2.remove(row+col)

    backtrack(0)
    return result
```

**Why sets for columns and diagonals?**  
- Same column: `col` value is constant.
- Diagonal `/` (anti-diag): `row + col` is constant along the diagonal.
- Diagonal `\` (main diag): `row - col` is constant along the diagonal.

---

### Pattern 6 — Word Search (Grid Backtracking)

```
def exist(board, word):
    rows, cols = len(board), len(board[0])
    def backtrack(r, c, idx, visited):
        if idx == len(word): return True
        if r<0 or r>=rows or c<0 or c>=cols: return False
        if (r,c) in visited or board[r][c] != word[idx]: return False
        visited.add((r,c))
        found = (backtrack(r+1,c,idx+1,visited) or
                 backtrack(r-1,c,idx+1,visited) or
                 backtrack(r,c+1,idx+1,visited) or
                 backtrack(r,c-1,idx+1,visited))
        visited.remove((r,c))    # unchoose
        return found
    for r in range(rows):
        for c in range(cols):
            if backtrack(r, c, 0, set()): return True
    return False
```

---

### Pattern 7 — Generate Parentheses

Build all valid combinations of `n` pairs of parentheses:

```
def generate_parenthesis(n):
    result = []
    def backtrack(path, open_count, close_count):
        if len(path) == 2 * n:
            result.append(''.join(path))
            return
        if open_count < n:                    # can add '('
            path.append('(')
            backtrack(path, open_count+1, close_count)
            path.pop()
        if close_count < open_count:          # can add ')' only if open > close
            path.append(')')
            backtrack(path, open_count, close_count+1)
            path.pop()
    backtrack([], 0, 0)
    return result
```

**Constraint pruning:** We only add `)` when `close_count < open_count` — this single rule eliminates all invalid sequences without generating them.

---

## Backtracking vs Dynamic Programming vs Brute Force

| Feature | Brute Force | Backtracking | Dynamic Programming |
|---|---|---|---|
| Strategy | Try everything | Try + prune dead ends | Memoize overlapping subproblems |
| Solution space | Full enumeration | Pruned tree | DAG of sub-states |
| State undo | No | Yes (backtrack) | No (states are independent) |
| Best when | Very small n | Combinatorial / constraint | Overlapping subproblems |
| Worst-case | O(state space) | O(state space) | O(states × transitions) |
| Common problems | Tiny inputs | Permutations, N-Queens, Sudoku | Fibonacci, Knapsack, LCS |

**Key distinction from DP:** In backtracking, subproblems are not reused — the same suffix can be processed multiple times from different prefixes. If the same suffix is computed repeatedly and the results only depend on the suffix, DP (memoization) should be applied on top.

---

## Complexity Analysis

| Problem | Time Complexity | Notes |
|---|---|---|
| Subsets of n elements | O(n × 2ⁿ) | 2ⁿ subsets, O(n) to copy each |
| Permutations of n elements | O(n × n!) | n! permutations, O(n) copy |
| Combinations C(n,k) | O(k × C(n,k)) | C(n,k) results |
| Combination Sum | O(t/m × 2^(t/m)) | t=target, m=min candidate; exponential |
| N-Queens | O(n!) | With column/diagonal pruning: ~O(n!) |
| Word Search | O(m × n × 4^L) | m×n cells, L=word length |
| Generate Parentheses | O(4ⁿ / √n) | Catalan number Cₙ |

**General rule:** Backtracking is exponential in the worst case. The power of pruning is that the *constant factor* shrinks dramatically for real inputs.

---

## Language Implementations

### Go

```go
// Subsets
func subsets(nums []int) [][]int {
    result := [][]int{}
    var backtrack func(start int, path []int)
    backtrack = func(start int, path []int) {
        tmp := make([]int, len(path))
        copy(tmp, path)
        result = append(result, tmp)
        for i := start; i < len(nums); i++ {
            path = append(path, nums[i])
            backtrack(i+1, path)
            path = path[:len(path)-1]    // unchoose
        }
    }
    backtrack(0, []int{})
    return result
}

// Permutations
func permute(nums []int) [][]int {
    result := [][]int{}
    var backtrack func(start int)
    backtrack = func(start int) {
        if start == len(nums) {
            tmp := make([]int, len(nums))
            copy(tmp, nums)
            result = append(result, tmp)
            return
        }
        for i := start; i < len(nums); i++ {
            nums[start], nums[i] = nums[i], nums[start]
            backtrack(start + 1)
            nums[start], nums[i] = nums[i], nums[start]  // unchoose
        }
    }
    backtrack(0)
    return result
}
```

### Java

```java
// Subsets
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrackSubsets(nums, 0, new ArrayList<>(), result);
    return result;
}
private void backtrackSubsets(int[] nums, int start,
                               List<Integer> path, List<List<Integer>> result) {
    result.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
        path.add(nums[i]);                        // choose
        backtrackSubsets(nums, i+1, path, result);// explore
        path.remove(path.size()-1);               // unchoose
    }
}

// Generate Parentheses
public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrackParen(result, new StringBuilder(), 0, 0, n);
    return result;
}
private void backtrackParen(List<String> result, StringBuilder sb,
                              int open, int close, int n) {
    if (sb.length() == 2 * n) { result.add(sb.toString()); return; }
    if (open < n)      { sb.append('('); backtrackParen(result,sb,open+1,close,n); sb.deleteCharAt(sb.length()-1); }
    if (close < open)  { sb.append(')'); backtrackParen(result,sb,open,close+1,n); sb.deleteCharAt(sb.length()-1); }
}
```

### Python

```python
# Combination Sum (with repetition)
def combination_sum(candidates: list[int], target: int) -> list[list[int]]:
    candidates.sort()
    result: list[list[int]] = []

    def backtrack(start: int, path: list[int], remaining: int) -> None:
        if remaining == 0:
            result.append(path[:])
            return
        for i in range(start, len(candidates)):
            if candidates[i] > remaining:
                break                             # pruning: sorted → rest too large
            path.append(candidates[i])
            backtrack(i, path, remaining - candidates[i])  # allow reuse
            path.pop()

    backtrack(0, [], target)
    return result

# N-Queens
def solve_n_queens(n: int) -> list[list[str]]:
    result: list[list[str]] = []
    cols: set[int] = set()
    diag1: set[int] = set()   # row - col
    diag2: set[int] = set()   # row + col
    board = [['.']*n for _ in range(n)]

    def backtrack(row: int) -> None:
        if row == n:
            result.append([''.join(r) for r in board])
            return
        for col in range(n):
            if col in cols or row-col in diag1 or row+col in diag2:
                continue
            cols.add(col); diag1.add(row-col); diag2.add(row+col)
            board[row][col] = 'Q'
            backtrack(row + 1)
            board[row][col] = '.'
            cols.discard(col); diag1.discard(row-col); diag2.discard(row+col)

    backtrack(0)
    return result
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Forgetting to pop / unmark after recursion (no unchoose step) | Every `append`/`mark` before the recursive call needs a matching `pop`/`unmark` after |
| Appending `path` without copying: `result.append(path)` | Always copy: `result.append(path[:])` in Python; `new ArrayList<>(path)` in Java |
| Using `i` instead of `i+1` for combinations (allows reuse when not intended) | Use `i+1` for no-reuse; use `i` for combination sum with reuse |
| Checking the base case after the loop instead of at the start | Base case must be checked first; otherwise you loop with an empty choices set |
| Not sorting before duplicate pruning | `if i > start and nums[i] == nums[i-1]: continue` only works on a sorted array |
| Mutating the grid/board without restoring it | Grid-based backtracking must undo cell changes (set back to `'.'` or `False`) |
| Off-by-one in combination pruning bound | `range(start, n - (k - len(path)) + 2)` — the `+2` accounts for 1-indexed range and inclusive upper bound |

---

## Problems Covered

| Problem | Pattern | LeetCode |
|---|---|---|
| Subsets | Subset / Power Set | #78 |
| Subsets II (with duplicates) | Subset + duplicate pruning | #90 |
| Permutations | Permutation (used array) | #46 |
| Permutations II (with duplicates) | Permutation + sort + skip | #47 |
| Combinations | Combination (k of n) | #77 |
| Combination Sum | Combination + reuse | #39 |
| Combination Sum II (no reuse) | Combination + duplicate pruning | #40 |
| Generate Parentheses | Constraint-guided build | #22 |
| N-Queens | Constraint satisfaction | #51 |
| Word Search | Grid DFS + backtrack | #79 |
| Palindrome Partitioning | Substring + backtrack | #131 |
| Letter Combinations of Phone Number | Cartesian product | #17 |
