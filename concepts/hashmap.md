# HashMap

## Table of Contents

1. [Introduction](#introduction)
2. [Internal Structure — How HashMap Works](#internal-structure--how-hashmap-works)
3. [HashMap vs HashSet vs TreeMap](#hashmap-vs-hashset-vs-treemap)
4. [Core Use Cases](#core-use-cases)
5. [Counting Pattern](#counting-pattern)
6. [Lookup / Complement Pattern](#lookup--complement-pattern)
7. [Grouping Pattern](#grouping-pattern)
8. [Complexity Analysis](#complexity-analysis)
9. [Language Implementations](#language-implementations)
10. [Common Mistakes](#common-mistakes)
11. [Problems Covered](#problems-covered)

---

## Introduction

A **HashMap** (also called hash table, dictionary, or associative array) stores **key → value** pairs and supports average O(1) insert, lookup, and delete. It is one of the most powerful and widely used data structures in competitive programming and software engineering.

**When to reach for a HashMap:**
- You need to remember a value associated with a key you've seen before.
- You need O(1) lookup by a custom key (not just array index).
- You need to count frequencies, group items, or cache results.

---

## Internal Structure — How HashMap Works

### Hash Function

Every key is passed through a **hash function** that maps it to a bucket index:

```
bucket_index = hash(key) % capacity

Example:
  key = "apple"
  hash("apple") = 92501  (some integer)
  capacity = 16
  bucket = 92501 % 16 = 5
```

### Collision Resolution

When two keys map to the same bucket (**collision**):

**Chaining:** Each bucket holds a linked list of entries.
```
Bucket 5: [("apple",1)] → [("mango",3)] → None
```

**Open Addressing:** On collision, probe the next available slot.

### Load Factor & Rehashing

- **Load factor** = number of entries / capacity.
- When load factor exceeds a threshold (typically 0.75), the map **rehashes** — doubles capacity and redistributes all entries.
- Rehash is O(n) but amortized O(1) per insert.

### ASCII Diagram

```
keys: "a","b","c","d"

hash("a") % 8 = 1   hash("b") % 8 = 2   hash("c") % 8 = 1 (collision!)

Buckets:
[0]:  empty
[1]:  ("a", val_a) → ("c", val_c)   ← chaining
[2]:  ("b", val_b)
[3]:  empty
...
```

---

## HashMap vs HashSet vs TreeMap

| Feature | HashMap | HashSet | TreeMap / TreeSet |
|---|---|---|---|
| Stores | key → value | keys only | key → value (sorted) |
| Lookup | O(1) avg | O(1) avg | O(log n) |
| Order | None (unordered) | None | Sorted by key |
| Use case | Count, group, cache | Membership, dedup | Range queries, ordered iteration |
| Python | `dict` | `set` | `sortedcontainers.SortedDict` |
| Java | `HashMap` | `HashSet` | `TreeMap` / `TreeSet` |
| Go | `map[K]V` | `map[K]struct{}` | No built-in; use `btree` |

---

## Core Use Cases

### 1. Counting

Count occurrences of each element:

```python
freq = {}
for x in arr:
    freq[x] = freq.get(x, 0) + 1
```

### 2. Lookup / Complement

Check if something has been seen; retrieve its associated value:

```python
# Two Sum — find complement
seen = {}
for i, v in enumerate(nums):
    complement = target - v
    if complement in seen:
        return [seen[complement], i]
    seen[v] = i
```

### 3. Grouping

Group items by a computed key:

```python
# Group anagrams
groups = {}
for s in strs:
    key = tuple(sorted(s))       # canonical form
    groups.setdefault(key, []).append(s)
```

### 4. Caching / Memoization

Store previously computed results:

```python
memo = {}
def fib(n):
    if n <= 1: return n
    if n not in memo:
        memo[n] = fib(n-1) + fib(n-2)
    return memo[n]
```

---

## Counting Pattern

**Template:**

```
count = defaultdict(int)          # Python
count = new HashMap<>()            # Java
count = make(map[T]int)            # Go

for item in collection:
    count[item]++

# Find most frequent
most_common = max(count, key=count.get)

# Find items appearing exactly k times
result = [k for k, v in count.items() if v == target_count]
```

**Frequency array optimisation (lowercase letters only):**

```python
freq = [0] * 26
for ch in s:
    freq[ord(ch) - ord('a')] += 1
```

---

## Lookup / Complement Pattern

**Template:**

```
seen = {}
for i, val in enumerate(collection):
    look_for = transform(val)          # complement, pair, etc.
    if look_for in seen:
        # found a valid pair
        use(seen[look_for], i)
    else:
        seen[val] = i                  # store for future lookup
```

This pattern solves: Two Sum, Three Sum (with outer loop), Contains Duplicate, Longest Consecutive Sequence.

---

## Grouping Pattern

**Template:**

```
groups = defaultdict(list)
for item in collection:
    key = canonical_form(item)         # sorted letters, hash, etc.
    groups[key].append(item)
return list(groups.values())
```

---

## Complexity Analysis

| Operation | Average Case | Worst Case | Notes |
|---|---|---|---|
| Insert | O(1) | O(n) | Worst case on many collisions |
| Lookup | O(1) | O(n) | Rare with good hash functions |
| Delete | O(1) | O(n) | Same as lookup |
| Iteration | O(n) | O(n) | Visits all entries |
| Space | O(n) | O(n) | One entry per unique key |

---

## Language Implementations

### Go

```go
// Count frequencies
freq := make(map[string]int)
for _, w := range words { freq[w]++ }

// Lookup with default
val, ok := freq["hello"]
if !ok { val = 0 }

// Iterate
for key, count := range freq {
    fmt.Printf("%s: %d\n", key, count)
}

// Delete
delete(freq, "hello")
```

### Java

```java
// Count frequencies
Map<String, Integer> freq = new HashMap<>();
for (String w : words)
    freq.merge(w, 1, Integer::sum);

// Lookup with default
int count = freq.getOrDefault("hello", 0);

// Group by key
Map<String, List<String>> groups = new HashMap<>();
for (String s : strs)
    groups.computeIfAbsent(canonicalKey(s), k -> new ArrayList<>()).add(s);
```

### Python

```python
from collections import defaultdict, Counter

# Count frequencies
freq = Counter(words)                # most idiomatic
freq = defaultdict(int)              # or manually

# Lookup with default
count = freq.get("hello", 0)

# Group by key
groups = defaultdict(list)
for s in strs:
    groups[canonical_key(s)].append(s)

# Most common k elements
top_k = freq.most_common(k)         # Counter method
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| KeyError on missing key in Python dict | Use `.get(key, default)` or `defaultdict` |
| Mutating a dict while iterating it | Iterate a copy: `for k in list(d.keys())` |
| Using a mutable type (list) as a key | Use tuples or frozensets — must be hashable |
| Assuming HashMap is ordered (pre-Python 3.7) | Python 3.7+ dicts maintain insertion order; for sorted order use `sortedcontainers` |
| Comparing HashMaps with `==` in Java by reference | Use `.equals()` not `==` in Java |
| Forgetting that Java `HashMap` is not thread-safe | Use `ConcurrentHashMap` for concurrent access |

---

## Problems Covered

| Problem | HashMap Pattern | LeetCode |
|---|---|---|
| Two Sum | Complement lookup | #1 |
| Group Anagrams | Grouping by sorted key | #49 |
| Contains Duplicate | Membership check (set) | #217 |
| First Unique Character | Frequency count | #387 |
| Happy Number | Cycle detection via set | #202 |
| Isomorphic Strings | Bidirectional mapping | #205 |
| Longest Consecutive Sequence | Set + O(n) extension | #128 |
