# Union-Find (Disjoint Set Union)

> Category: **Data Structure**
> Difficulty: **Intermediate**
> Also known as: **DSU (Disjoint Set Union)**

---

# Table of Contents

1. What is Union-Find?
2. Core Operations
3. Naive Implementation
4. Optimization 1 — Union by Rank
5. Optimization 2 — Path Compression
6. Combined Implementation
7. Language Implementations
8. Complexity Summary
9. When to Use Union-Find
10. Union-Find vs BFS/DFS
11. Common Mistakes
12. Key Takeaways

---

# What is Union-Find?

**Union-Find** (or Disjoint Set Union) is a data structure that tracks a collection of elements partitioned into **disjoint sets** (groups with no overlap).

It efficiently answers two questions:

1. **Find**: which set does element `x` belong to?
2. **Union**: merge the sets containing elements `x` and `y`.

```
Initial sets (each element is its own set):
{0}  {1}  {2}  {3}  {4}

After union(0,1), union(2,3):
{0,1}  {2,3}  {4}

find(0) == find(1) → True   (same set)
find(0) == find(2) → False  (different sets)
```

---

## Key Metaphor

Think of it as a forest of trees. Each set is represented by a tree. The **root** of the tree is the **representative** (or "parent") of the set.

`find(x)` follows parent pointers up to the root.  
`union(x, y)` merges two trees by attaching one root under the other.

---

# Core Operations

## find(x) — Find the representative of x's set

```
def find(x):
    while parent[x] != x:
        x = parent[x]
    return x
```

Follows the chain of parents until reaching the root (where `parent[root] == root`).

---

## union(x, y) — Merge the sets of x and y

```
def union(x, y):
    rootX = find(x)
    rootY = find(y)
    if rootX != rootY:
        parent[rootX] = rootY   # attach x's root under y's root
```

If `find(x) == find(y)`, they are already in the same set — no action needed.

---

## connected(x, y) — Are x and y in the same set?

```
def connected(x, y):
    return find(x) == find(y)
```

---

# Naive Implementation

```
parent = list(range(n))   # parent[i] = i initially (each node is its own root)

def find(x):
    while parent[x] != x:
        x = parent[x]
    return x

def union(x, y):
    parent[find(x)] = find(y)
```

**Problem**: without optimizations, chains can grow to O(n), making `find` O(n).

---

# Optimization 1 — Union by Rank

Always attach the **shorter tree** under the **taller tree**. This keeps the tree height bounded at O(log n).

```
parent = list(range(n))
rank   = [0] * n          # rank ≈ upper bound on height

def union(x, y):
    rootX, rootY = find(x), find(y)
    if rootX == rootY:
        return
    if rank[rootX] < rank[rootY]:
        parent[rootX] = rootY
    elif rank[rootX] > rank[rootY]:
        parent[rootY] = rootX
    else:
        parent[rootY] = rootX
        rank[rootX] += 1     # only increase rank when heights were equal
```

---

# Optimization 2 — Path Compression

During `find`, flatten the tree by making every node point **directly to the root**.

```
def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])   # path compression (recursive)
    return parent[x]
```

Iterative version:

```
def find(x):
    root = x
    while parent[root] != root:
        root = parent[root]
    # Now flatten path
    while parent[x] != root:
        parent[x], x = root, parent[x]
    return root
```

After `find(x)`, every node on the path from `x` to root points directly to root. Future calls are O(1).

---

# Combined Implementation

Union by rank + path compression together achieve nearly O(1) amortized per operation (inverse Ackermann function α(n), which is ≤ 4 for all practical n).

```
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank   = [0] * n
        self.count  = n    # number of disjoint sets

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])  # path compression
        return self.parent[x]

    def union(self, x, y):
        rootX, rootY = self.find(x), self.find(y)
        if rootX == rootY:
            return False   # already same set
        if self.rank[rootX] < self.rank[rootY]:
            self.parent[rootX] = rootY
        elif self.rank[rootX] > self.rank[rootY]:
            self.parent[rootY] = rootX
        else:
            self.parent[rootY] = rootX
            self.rank[rootX] += 1
        self.count -= 1    # two sets merged into one
        return True        # merge happened

    def connected(self, x, y):
        return self.find(x) == self.find(y)
```

---

## Step-by-Step Example

```
n = 5, edges = [(0,1), (1,2), (3,4)]

Initial: parent = [0,1,2,3,4]  rank = [0,0,0,0,0]  count = 5

union(0,1): root0=0, root1=1, same rank → parent[1]=0, rank[0]=1
  parent = [0,0,2,3,4]  count=4

union(1,2): find(1)→0, find(2)→2
  rank[0]=1 > rank[2]=0 → parent[2]=0
  parent = [0,0,0,3,4]  count=3

union(3,4): root3=3, root4=4, same rank → parent[4]=3, rank[3]=1
  parent = [0,0,0,3,3]  count=2

connected(0,2)? find(0)=0, find(2)=0 → True
connected(0,3)? find(0)=0, find(3)=3 → False
```

---

# Language Implementations

## Java

```java
class UnionFind {
    int[] parent, rank;
    int count;

    UnionFind(int n) {
        parent = new int[n];
        rank   = new int[n];
        count  = n;
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return false;
        if (rank[rx] < rank[ry])      parent[rx] = ry;
        else if (rank[rx] > rank[ry]) parent[ry] = rx;
        else { parent[ry] = rx; rank[rx]++; }
        count--;
        return true;
    }

    boolean connected(int x, int y) { return find(x) == find(y); }
}
```

---

## Go

```go
type UnionFind struct {
    parent []int
    rank   []int
    count  int
}

func NewUnionFind(n int) *UnionFind {
    uf := &UnionFind{parent: make([]int, n), rank: make([]int, n), count: n}
    for i := range uf.parent { uf.parent[i] = i }
    return uf
}

func (uf *UnionFind) Find(x int) int {
    if uf.parent[x] != x {
        uf.parent[x] = uf.Find(uf.parent[x])
    }
    return uf.parent[x]
}

func (uf *UnionFind) Union(x, y int) bool {
    rx, ry := uf.Find(x), uf.Find(y)
    if rx == ry { return false }
    if uf.rank[rx] < uf.rank[ry]      { uf.parent[rx] = ry
    } else if uf.rank[rx] > uf.rank[ry] { uf.parent[ry] = rx
    } else { uf.parent[ry] = rx; uf.rank[rx]++ }
    uf.count--
    return true
}
```

---

# Complexity Summary

| Operation | Naive | With Union by Rank | With Both Optimizations |
|-----------|-------|--------------------|------------------------|
| find | O(n) | O(log n) | O(α(n)) ≈ O(1) |
| union | O(n) | O(log n) | O(α(n)) ≈ O(1) |
| Space | O(n) | O(n) | O(n) |

α(n) = inverse Ackermann function. For all practical n (up to 10^80), α(n) ≤ 4.

---

# When to Use Union-Find

Use Union-Find when:

- You need to **dynamically merge sets** and check connectivity.
- **Number of connected components** — union edges; count = number of roots.
- **Cycle detection in undirected graphs** — if `union(u,v)` returns false (already connected), there's a cycle.
- **Kruskal's Minimum Spanning Tree** — process edges by weight; union if not already connected.
- **Accounts Merge** — group emails that belong to the same account.
- **Redundant Connection** — find the first edge that creates a cycle.

---

# Union-Find vs BFS/DFS

| Scenario | Prefer |
|----------|--------|
| Static graph, one-time connectivity queries | BFS/DFS |
| Dynamic edges arriving one by one | Union-Find |
| Need shortest path | BFS |
| Need topological order | BFS (Kahn's) / DFS |
| Detect cycle in undirected graph | Union-Find (cleaner) or DFS |
| Count connected components (online) | Union-Find |

---

# Common Mistakes

## Not Using Path Compression

```
Wrong:
def find(x):
    while parent[x] != x:
        x = parent[x]
    return x
# O(n) per call without compression
```

---

## Applying to Directed Graphs

Union-Find is designed for **undirected** connectivity. For directed graphs, use DFS-based three-color cycle detection or Kahn's algorithm.

---

## Union by Size vs Union by Rank

Both work. Union by **size** (attach smaller tree under larger) is slightly easier to reason about. Union by **rank** uses an approximate height. Either combined with path compression achieves O(α(n)).

---

## Initializing Parent Array Incorrectly

```
Wrong:  parent = [0] * n        # all point to 0
Correct: parent = list(range(n)) # each points to itself
```

---

# Key Takeaways

After studying this concept, you should understand:

- Union-Find tracks disjoint sets with two operations: `find` (representative) and `union` (merge).
- Path compression makes every `find` nearly O(1) by flattening the tree.
- Union by rank prevents trees from growing tall.
- Together they achieve O(α(n)) per operation — essentially constant time.
- Best suited for dynamic connectivity, component counting, and cycle detection in undirected graphs.
- The `count` field (tracking number of disjoint sets) is a powerful addition for "number of islands" style problems.
