# Python DSA Cheatsheet

> Quick reference for data structures, algorithms, and patterns used in coding interviews.

---

## 📦 Core Data Structures

### List (Dynamic Array)
```python
lst = []
lst.append(10)          # O(1) amortised
lst.insert(0, 99)       # O(n)
lst[0]                  # access
lst[-1]                 # last element
lst[1:4]                # slice [1, 4)
lst.pop()               # remove last O(1)
lst.pop(2)              # remove at index O(n)
lst.remove(10)          # remove first occurrence O(n)
lst.index(10)           # first index of value
len(lst)
10 in lst               # membership O(n)
lst.sort()              # in-place, Timsort O(n log n)
lst.sort(key=lambda x: -x)   # descending
lst.sort(key=lambda x: (x[1], x[0]))  # multi-key
sorted(lst)             # returns new list
lst.reverse()
lst.count(10)
lst.extend([1, 2, 3])
lst + [4, 5]            # concatenation (new list)
lst * 3                 # repeat
```

### deque (Stack & Queue)
```python
from collections import deque

dq = deque()
dq.append(1)        # push right
dq.appendleft(1)    # push left
dq.pop()            # pop right  (Stack)
dq.popleft()        # pop left   (Queue)
dq[0]               # peek left
dq[-1]              # peek right
len(dq)
dq.rotate(k)        # rotate right by k
deque([1,2,3], maxlen=3)  # fixed-size circular buffer
```

### dict (HashMap)
```python
d = {}
d = dict()
d["key"] = 1
d.get("key", 0)         # default 0 if missing
d["key"]                # KeyError if missing
"key" in d
del d["key"]
d.pop("key", None)      # safe delete
len(d)
d.keys()
d.values()
d.items()               # (k, v) pairs
d.setdefault("k", []).append(1)  # init if missing

# Comprehensions
squares = {x: x*x for x in range(5)}

# Counter (frequency map)
from collections import Counter
c = Counter("hello")    # {'l': 2, 'h': 1, 'e': 1, 'o': 1}
c.most_common(2)        # [('l', 2), ('h', 1)]
c["l"]                  # 2
c["z"]                  # 0 (no KeyError)

# defaultdict
from collections import defaultdict
freq = defaultdict(int)
freq["a"] += 1

adj = defaultdict(list)
adj[0].append(1)
```

### set (HashSet)
```python
s = set()
s = {1, 2, 3}
s.add(4)
s.discard(4)        # no error if missing
s.remove(4)         # KeyError if missing
4 in s              # O(1)
len(s)

# Set operations
a | b               # union
a & b               # intersection
a - b               # difference (a not in b)
a ^ b               # symmetric difference
a.issubset(b)
a.issuperset(b)
frozenset([1, 2])   # immutable set (hashable)
```

### heapq (Min-Heap / Priority Queue)
```python
import heapq

heap = []
heapq.heappush(heap, 3)
heapq.heappush(heap, 1)
heapq.heappop(heap)     # 1 (min)
heap[0]                 # peek min without removing
heapq.heapify(lst)      # convert list in-place O(n)
heapq.nsmallest(3, lst)
heapq.nlargest(3, lst)

# Max-heap: negate values
heapq.heappush(heap, -val)
-heapq.heappop(heap)

# Heap of tuples: sorted by first element
heapq.heappush(heap, (priority, value))
```

---

## 🔤 Strings

```python
s = "hello"
len(s)
s[0]            # 'h'
s[-1]           # 'o'
s[1:4]          # 'ell'
s[::-1]         # reverse: 'olleh'
s.upper()
s.lower()
s.strip()       # strip whitespace
s.lstrip("h")
s.rstrip("o")
s.replace("l", "r")
s.split(",")    # list of parts
s.split()       # split on whitespace
",".join(["a", "b", "c"])   # "a,b,c"
s.find("ll")    # index or -1
s.index("ll")   # index or ValueError
s.count("l")    # 2
s.startswith("he")
s.endswith("lo")
s.isalpha()
s.isdigit()
s.isalnum()
"hello" in s
s == "hello"    # equality (no .equals() needed)
ord('a')        # 97
chr(97)         # 'a'

# f-strings
name = "Alice"
f"Hello, {name}!"
f"{42:05d}"     # '00042'
f"{3.14:.2f}"   # '3.14'

# String building (use list + join, not +=)
parts = []
for c in s:
    parts.append(c.upper())
result = "".join(parts)
```

---

## 🔢 Numbers & Math

```python
float('inf')        # positive infinity
float('-inf')       # negative infinity
abs(-5)
max(3, 5)
min(3, 5)
pow(2, 10)          # 1024
2 ** 10             # 1024
10 // 3             # 3 (floor division)
10 % 3              # 1
divmod(10, 3)       # (3, 1)
round(3.567, 2)     # 3.57

import math
math.sqrt(16)       # 4.0
math.ceil(3.2)      # 4
math.floor(3.7)     # 3
math.log(8, 2)      # 3.0
math.inf
math.gcd(12, 8)     # 4
math.lcm(4, 6)      # 12  (Python 3.9+)

# Bit manipulation
n & 1               # is odd
n >> 1              # n // 2
n << 1              # n * 2
n & (n - 1)         # clear lowest set bit
bin(n)              # '0b1010'
n.bit_length()
```

---

## ♻️ Sorting

```python
lst.sort()                          # in-place
sorted(lst)                         # new list
lst.sort(reverse=True)
lst.sort(key=lambda x: x[1])        # by second element
lst.sort(key=lambda x: (x[1], -x[0]))  # multi-key

# Custom class ordering
from functools import total_ordering
@total_ordering
class Item:
    def __lt__(self, other): ...
    def __eq__(self, other): ...

# cmp_to_key (rare, but useful for complex comparisons)
from functools import cmp_to_key
lst.sort(key=cmp_to_key(lambda a, b: a - b))
```

---

## 📐 Common Patterns

### Two Pointers
```python
l, r = 0, len(arr) - 1
while l < r:
    if arr[l] + arr[r] == target:
        return [l, r]
    elif arr[l] + arr[r] < target:
        l += 1
    else:
        r -= 1
```

### Sliding Window (variable)
```python
from collections import defaultdict

freq = defaultdict(int)
l = max_len = 0
for r in range(len(s)):
    freq[s[r]] += 1
    while len(freq) > k:
        freq[s[l]] -= 1
        if freq[s[l]] == 0:
            del freq[s[l]]
        l += 1
    max_len = max(max_len, r - l + 1)
```

### Binary Search
```python
lo, hi = 0, len(arr) - 1
while lo <= hi:
    mid = (lo + hi) // 2
    if arr[mid] == target:
        return mid
    elif arr[mid] < target:
        lo = mid + 1
    else:
        hi = mid - 1
return -1

# Built-in
import bisect
bisect.bisect_left(arr, target)    # first index >= target
bisect.bisect_right(arr, target)   # first index > target
bisect.insort(arr, val)            # insert in sorted order
```

### BFS (graph / grid)
```python
from collections import deque

def bfs(grid, sr, sc):
    rows, cols = len(grid), len(grid[0])
    visited = set()
    q = deque([(sr, sc)])
    visited.add((sr, sc))
    dirs = [(0,1),(0,-1),(1,0),(-1,0)]

    while q:
        r, c = q.popleft()
        for dr, dc in dirs:
            nr, nc = r + dr, c + dc
            if 0 <= nr < rows and 0 <= nc < cols and (nr, nc) not in visited:
                visited.add((nr, nc))
                q.append((nr, nc))
```

### DFS (recursive)
```python
def dfs(grid, r, c, visited):
    rows, cols = len(grid), len(grid[0])
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if (r, c) in visited:
        return
    visited.add((r, c))
    for dr, dc in [(0,1),(0,-1),(1,0),(-1,0)]:
        dfs(grid, r + dr, c + dc, visited)
```

### Dynamic Programming (bottom-up)
```python
# Coin change template
def coinChange(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    for i in range(1, amount + 1):
        for coin in coins:
            if coin <= i:
                dp[i] = min(dp[i], dp[i - coin] + 1)
    return dp[amount] if dp[amount] != float('inf') else -1
```
