# HashSet

## Table of Contents

1. [Introduction](#introduction)
2. [Internal Structure](#internal-structure)
3. [HashSet vs HashMap vs Sorted Set](#hashset-vs-hashmap-vs-sorted-set)
4. [Core Use Cases](#core-use-cases)
5. [Membership / Deduplication Pattern](#membership--deduplication-pattern)
6. [Cycle Detection Pattern](#cycle-detection-pattern)
7. [Set Operations](#set-operations)
8. [Complexity Analysis](#complexity-analysis)
9. [Language Implementations](#language-implementations)
10. [Common Mistakes](#common-mistakes)
11. [Problems Covered](#problems-covered)

---

## Introduction

A **HashSet** stores a collection of **unique elements** with average O(1) membership test, insert, and delete. It is a HashMap where only keys are stored (values are irrelevant or implicitly `true`).

**When to reach for a HashSet:**
- You need to answer "have I seen this before?" in O(1).
- You need to deduplicate a collection.
- You need set operations (union, intersection, difference).
- You need to detect cycles (tortoise-and-hare is better for constant space, but a visited set works too).

---

## Internal Structure

A HashSet is typically implemented as a HashMap with dummy values:

```
Go:    map[T]struct{}{}         (struct{} = zero-byte value, no overhead)
Java:  HashSet backed by HashMap<E, PRESENT>
Python: set  (dedicated C implementation, very efficient)
```

Internally: hash function → bucket array → chaining or open addressing, identical to HashMap.

---

## HashSet vs HashMap vs Sorted Set

| Feature | HashSet | HashMap | TreeSet / SortedSet |
|---|---|---|---|
| Stores | keys only | key → value | keys only (sorted) |
| Membership | O(1) avg | O(1) avg | O(log n) |
| Iteration order | None | None | Sorted |
| Use case | Dedup, visited, membership | Count, group, cache | Range, ordered iteration |
| Python | `set` | `dict` | `sortedcontainers.SortedSet` |
| Java | `HashSet` | `HashMap` | `TreeSet` |
| Go | `map[T]struct{}` | `map[K]V` | No built-in |

---

## Core Use Cases

### 1. Deduplication

```python
unique = list(set(arr))                     # order not guaranteed
# or maintain order (Python 3.7+ dict keys):
seen = {}
unique = [x for x in arr if not (x in seen or seen.update({x: True}))]
```

### 2. O(1) Membership Test

Replace a linear O(n) scan with O(1) set lookup:

```python
# Brute force: O(n) per lookup
if target in list_of_values:    # O(n) scan

# With set: O(1) per lookup
lookup = set(list_of_values)    # O(n) to build, once
if target in lookup:            # O(1) lookup
```

### 3. Track Visited Nodes (BFS / DFS)

```python
visited = set()
queue = deque([start])
visited.add(start)
while queue:
    node = queue.popleft()
    for neighbor in graph[node]:
        if neighbor not in visited:
            visited.add(neighbor)
            queue.append(neighbor)
```

### 4. Find Missing / Duplicate Elements

```python
# Find duplicate
seen = set()
for x in nums:
    if x in seen: return x
    seen.add(x)

# Longest consecutive sequence
num_set = set(nums)
best = 0
for n in num_set:
    if n - 1 not in num_set:          # start of sequence
        length = 1
        while n + length in num_set:
            length += 1
        best = max(best, length)
```

---

## Membership / Deduplication Pattern

```
seen = set()
for item in collection:
    if item in seen:
        # duplicate found — handle
    else:
        seen.add(item)
        # first occurrence — process
```

---

## Cycle Detection Pattern

```
# Floyd's tortoise-and-hare is O(1) space, but visited set is simpler:
seen = set()
current = start
while current is not None:
    if current in seen:
        return True      # cycle detected
    seen.add(current)
    current = next(current)
return False
```

---

## Set Operations

### Mathematical Set Operations

```python
a = {1, 2, 3, 4}
b = {3, 4, 5, 6}

union        = a | b         # {1,2,3,4,5,6}
intersection = a & b         # {3,4}
difference   = a - b         # {1,2}   (in a but not b)
sym_diff     = a ^ b         # {1,2,5,6} (in exactly one)
subset       = a <= b        # False
superset     = a >= b        # False
```

### Java

```java
Set<Integer> a = new HashSet<>(Arrays.asList(1,2,3,4));
Set<Integer> b = new HashSet<>(Arrays.asList(3,4,5,6));

// Intersection
Set<Integer> intersection = new HashSet<>(a);
intersection.retainAll(b);  // {3,4}

// Union
Set<Integer> union = new HashSet<>(a);
union.addAll(b);             // {1,2,3,4,5,6}

// Difference
Set<Integer> diff = new HashSet<>(a);
diff.removeAll(b);           // {1,2}
```

### Go

```go
// Go has no built-in set type; use map[T]struct{}
a := map[int]struct{}{1:{},2:{},3:{},4:{}}

// Membership
_, inA := a[3]   // true

// Intersection
inter := map[int]struct{}{}
for k := range a {
    if _, ok := b[k]; ok { inter[k] = struct{}{} }
}
```

---

## Complexity Analysis

| Operation | Average Case | Worst Case |
|---|---|---|
| Add | O(1) | O(n) |
| Contains | O(1) | O(n) |
| Remove | O(1) | O(n) |
| Iteration | O(n) | O(n) |
| Space | O(n) | O(n) |

Set operations (union, intersection, difference) are O(min(|A|, |B|)) for intersection and O(|A| + |B|) for union.

---

## Language Implementations

### Go

```go
// Simulate a set with map[int]struct{}
visited := make(map[int]struct{})

visited[5] = struct{}{}          // add
_, ok := visited[5]              // contains → ok=true
delete(visited, 5)               // remove

// Convenience: use bool map if struct{} is too verbose
seen := make(map[int]bool)
seen[5] = true
if seen[5] { /* present */ }
```

### Java

```java
Set<Integer> seen = new HashSet<>();
seen.add(5);
seen.contains(5);    // true
seen.remove(5);

// Iterate
for (int v : seen) { System.out.println(v); }

// From a list
Set<Integer> fromList = new HashSet<>(Arrays.asList(1, 2, 3));
```

### Python

```python
seen = set()
seen.add(5)
5 in seen           # True  (O(1))
seen.discard(5)     # remove without error if absent
seen.remove(5)      # remove, raises KeyError if absent

# Frozenset — immutable set (can be used as dict key)
fs = frozenset([1, 2, 3])
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Iterating a set while modifying it | Iterate a copy: `for x in list(my_set)` |
| Using a list as a set element (unhashable) | Use tuple instead: `frozenset` or `tuple` |
| Assuming set iteration order is stable | It is not; use `sorted(my_set)` for deterministic order |
| Confusing `set.remove()` (raises on miss) and `set.discard()` (silent) | Use `discard()` when the element might not be present |
| Using `==` to compare sets by reference in Java | HashSet overrides `.equals()` — `==` checks reference |

---

## Problems Covered

| Problem | HashSet Pattern | LeetCode |
|---|---|---|
| Contains Duplicate | Membership / dedup | #217 |
| Happy Number | Cycle detection via set | #202 |
| Longest Consecutive Sequence | Set membership + extension | #128 |
| First Missing Positive | Set lookup | #41 |
| Intersection of Two Arrays | Set intersection | #349 |
