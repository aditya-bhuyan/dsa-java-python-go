# Graph

> Category: **Data Structure + Algorithm**
> Difficulty: **Intermediate → Advanced**

---

# Table of Contents

1. What is a Graph?
2. Graph Terminology
3. Graph Representations
4. Depth-First Search (DFS)
5. Breadth-First Search (BFS)
6. Topological Sort
7. Cycle Detection
8. Complexity Summary
9. When to Use Which Algorithm
10. Common Mistakes
11. Key Takeaways

---

# What is a Graph?

A **Graph** is a data structure that models a set of **nodes** (vertices) connected by **edges**.

```
    A ─── B
    │   ╱ │
    │ ╱   │
    C ─── D
```

Unlike trees, graphs:
- Can have **cycles**.
- Can be **disconnected** (multiple components).
- Edges can be **directed** or **undirected**.
- Edges can carry **weights**.

---

## Formal Definition

A graph `G = (V, E)` where:
- `V` = set of vertices (nodes)
- `E` = set of edges, where each edge connects two vertices

---

## Directed vs Undirected

**Undirected**: edges have no direction — if A connects to B, B connects to A.

```
A ─── B ─── C
```

**Directed** (Digraph): edges have a direction — A → B does not imply B → A.

```
A ──→ B ──→ C
↑         │
└─────────┘
```

---

## Weighted vs Unweighted

**Weighted**: each edge carries a numeric value (distance, cost, time).

```
A ──5── B ──3── C
```

**Unweighted**: all edges are equal (or unit cost).

---

# Graph Terminology

| Term | Definition |
|------|-----------|
| **Vertex / Node** | A point in the graph |
| **Edge** | A connection between two vertices |
| **Adjacency** | Two vertices connected by an edge |
| **Degree** | Number of edges at a vertex (undirected) |
| **In-degree** | Number of incoming edges (directed) |
| **Out-degree** | Number of outgoing edges (directed) |
| **Path** | A sequence of vertices connected by edges |
| **Cycle** | A path that starts and ends at the same vertex |
| **Connected** | Every vertex is reachable from every other (undirected) |
| **Strongly Connected** | Every vertex reachable from every other (directed) |
| **DAG** | Directed Acyclic Graph — directed, no cycles |
| **Component** | A maximal connected subgraph |
| **Neighbor** | An adjacent vertex |

---

# Graph Representations

## 1. Adjacency List (Most Common)

Each vertex stores a list of its neighbors.

```
Graph:
0 → [1, 2]
1 → [0, 3]
2 → [0, 3]
3 → [1, 2]
```

**Java**
```java
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.put(0, Arrays.asList(1, 2));
```

**Python**
```python
graph = {
    0: [1, 2],
    1: [0, 3],
    2: [0, 3],
    3: [1, 2],
}
```

**Go**
```go
graph := map[int][]int{
    0: {1, 2},
    1: {0, 3},
    2: {0, 3},
    3: {1, 2},
}
```

**When to use**: sparse graphs (most real-world graphs). Space: O(V + E).

---

## 2. Adjacency Matrix

A 2D boolean (or weighted) array. `matrix[i][j] = 1` if edge i→j exists.

```
    0  1  2  3
0 [ 0  1  1  0 ]
1 [ 1  0  0  1 ]
2 [ 1  0  0  1 ]
3 [ 0  1  1  0 ]
```

**When to use**: dense graphs, or when you need O(1) edge existence checks.  
Space: O(V²) — prohibitive for large sparse graphs.

---

## 3. Edge List

A list of `(u, v)` or `(u, v, weight)` tuples.

```
[(0,1), (0,2), (1,3), (2,3)]
```

**When to use**: sorting edges by weight (Kruskal's MST algorithm).

---

## Grid as a Graph

Many interview problems represent graphs as 2D grids. Each cell `(r, c)` is a node; neighbors are the 4 (or 8) adjacent cells.

```
directions = [(0,1), (0,-1), (1,0), (-1,0)]   # right, left, down, up
```

---

# Depth-First Search (DFS)

DFS explores as far as possible along a branch before backtracking. It uses a **stack** (explicit or via the call stack through recursion).

## Recursive DFS

```
visited = set()

def dfs(node):
    if node in visited:
        return
    visited.add(node)
    process(node)
    for neighbor in graph[node]:
        dfs(neighbor)
```

## Iterative DFS

```
visited = set()
stack = [start]

while stack:
    node = stack.pop()
    if node in visited:
        continue
    visited.add(node)
    process(node)
    for neighbor in graph[node]:
        if neighbor not in visited:
            stack.append(neighbor)
```

## DFS on a Grid

```
def dfs(grid, r, c, visited):
    if r < 0 or r >= rows or c < 0 or c >= cols:
        return
    if visited[r][c] or grid[r][c] == '0':
        return
    visited[r][c] = True
    for dr, dc in [(0,1),(0,-1),(1,0),(-1,0)]:
        dfs(grid, r+dr, c+dc, visited)
```

## DFS Use Cases

| Problem | DFS Application |
|---------|----------------|
| Number of Islands | Count connected components in grid |
| Flood Fill | Recolor connected region |
| Detect cycle | Track recursion stack (back edge) |
| Topological Sort | Post-order DFS on DAG |
| Clone Graph | DFS with memo map |
| Path finding | All paths from source to target |

---

# Breadth-First Search (BFS)

BFS explores all neighbors at the current depth before going deeper. It uses a **queue** (FIFO).

BFS on an unweighted graph finds the **shortest path** in terms of number of edges.

## Algorithm

```
from collections import deque

visited = set([start])
queue = deque([start])

while queue:
    node = queue.popleft()
    process(node)
    for neighbor in graph[node]:
        if neighbor not in visited:
            visited.add(neighbor)
            queue.append(neighbor)
```

## BFS Shortest Path

```
dist = {start: 0}
queue = deque([start])

while queue:
    node = queue.popleft()
    for neighbor in graph[node]:
        if neighbor not in dist:
            dist[neighbor] = dist[node] + 1
            queue.append(neighbor)
```

## Multi-Source BFS

Start BFS from **multiple sources simultaneously**. Common in grid problems where you expand outward from all sources at once.

```
queue = deque()
for r, c in sources:
    queue.append((r, c))
    visited[r][c] = True

while queue:
    r, c = queue.popleft()
    for dr, dc in directions:
        nr, nc = r+dr, c+dc
        if in_bounds(nr, nc) and not visited[nr][nc]:
            visited[nr][nc] = True
            queue.append((nr, nc))
```

## BFS Use Cases

| Problem | BFS Application |
|---------|----------------|
| Shortest Path | Unweighted graph — minimum edges |
| Level-order traversal | Tree levels |
| Rotting Oranges | Multi-source BFS |
| Word Ladder | BFS on implicit graph |
| 0-1 BFS | Deque-based BFS for 0/1 edge weights |

---

# Topological Sort

**Topological Sort** orders the vertices of a **DAG** (Directed Acyclic Graph) such that for every directed edge `u → v`, vertex `u` comes before `v` in the ordering.

It only exists for DAGs — any cycle makes topological ordering impossible.

**Use cases**: task scheduling, build systems, course prerequisites, dependency resolution.

---

## Algorithm 1 — Kahn's Algorithm (BFS-based)

Uses in-degrees. Iteratively removes nodes with in-degree 0.

```
1. Compute in-degree for every vertex.
2. Enqueue all vertices with in-degree 0.
3. While queue not empty:
      u = dequeue
      add u to result
      for each neighbor v of u:
          in_degree[v] -= 1
          if in_degree[v] == 0:
              enqueue v
4. If result length == V: valid topological order.
   If result length < V:  cycle detected.
```

### Example

```
Courses: 0→1, 0→2, 1→3, 2→3

In-degrees: {0:0, 1:1, 2:1, 3:2}

Queue: [0]
Process 0 → result:[0], decrement 1,2 → queue:[1,2]
Process 1 → result:[0,1], decrement 3 → queue:[2]
Process 2 → result:[0,1,2], decrement 3 → queue:[3]
Process 3 → result:[0,1,2,3]

All 4 processed → no cycle
```

---

## Algorithm 2 — DFS-based Topological Sort

Post-order DFS: add a node to the result **after** all its descendants are processed.

```
WHITE = 0  # unvisited
GRAY  = 1  # in current DFS path (cycle detection)
BLACK = 2  # fully processed

def dfs(node):
    if color[node] == GRAY:   # back edge → cycle!
        raise CycleError
    if color[node] == BLACK:
        return
    color[node] = GRAY
    for neighbor in graph[node]:
        dfs(neighbor)
    color[node] = BLACK
    result.append(node)       # post-order

for node in all_nodes:
    dfs(node)

result.reverse()              # reverse post-order = topological order
```

---

## Kahn's vs DFS Topological Sort

| | Kahn's (BFS) | DFS |
|-|------------|-----|
| Cycle detection | `len(result) < V` | Gray node encountered |
| Natural order | BFS order (levels) | Reverse post-order |
| Implementation | Queue + in-degree array | Recursive + color array |
| Preferred for | Detecting cycles cleanly | Generating all orderings |

---

# Cycle Detection

## Undirected Graph — DFS with Parent Tracking

```
def has_cycle(node, parent, visited):
    visited.add(node)
    for neighbor in graph[node]:
        if neighbor not in visited:
            if has_cycle(neighbor, node, visited):
                return True
        elif neighbor != parent:   # back edge found
            return True
    return False
```

## Directed Graph — Three-Color DFS

Use WHITE / GRAY / BLACK coloring (as shown in DFS topological sort above). A gray-to-gray edge is a back edge = cycle.

## Directed Graph — Union-Find

See [`concepts/union-find.md`](concepts/union-find.md).

---

# Complexity Summary

| Algorithm | Time | Space |
|-----------|------|-------|
| DFS | O(V + E) | O(V) |
| BFS | O(V + E) | O(V) |
| Topological Sort (Kahn's) | O(V + E) | O(V) |
| Topological Sort (DFS) | O(V + E) | O(V) |
| Cycle Detection | O(V + E) | O(V) |

Where V = vertices, E = edges.

For grid graphs: V = rows × cols, E = 4 × V (4-directional).

---

# When to Use Which Algorithm

| Goal | Algorithm |
|------|----------|
| Explore all reachable nodes | DFS or BFS |
| Shortest path (unweighted) | BFS |
| Shortest path (weighted) | Dijkstra (min heap + BFS) |
| Detect cycle (undirected) | DFS with parent tracking |
| Detect cycle (directed) | Three-color DFS or Kahn's |
| Topological ordering | Kahn's (BFS) or DFS post-order |
| Count connected components | DFS/BFS per unvisited node |
| Flood fill / region coloring | DFS or BFS |
| Minimum spanning tree | Kruskal (Union-Find) or Prim |

---

# Common Mistakes

## Forgetting the Visited Set

Without tracking visited nodes, DFS/BFS loops forever on cyclic graphs.

```
Wrong:  dfs(neighbor)                           # revisits nodes
Correct: if neighbor not in visited: dfs(...)
```

---

## Marking Visited Too Late (BFS)

In BFS, mark nodes visited **when you enqueue them**, not when you dequeue them. Otherwise the same node gets added to the queue multiple times.

```
Wrong:
    node = queue.popleft()
    visited.add(node)        ← too late; duplicates already in queue

Correct:
    visited.add(neighbor)
    queue.append(neighbor)   ← mark before enqueue
```

---

## Not Handling Disconnected Graphs

A single DFS/BFS call only visits nodes reachable from the start. To visit all nodes in a disconnected graph, loop over all vertices:

```
for node in all_nodes:
    if node not in visited:
        dfs(node)
```

---

## Confusing In-Degree / Out-Degree

For Kahn's algorithm: decrement the in-degree of **neighbors** (nodes pointed to), not the node being processed.

---

# Key Takeaways

After studying this concept, you should understand:

- The three graph representations and when to use each.
- DFS uses a stack (call stack or explicit); BFS uses a queue.
- BFS gives the shortest path on unweighted graphs; DFS does not.
- Topological sort only applies to DAGs; use Kahn's for clean cycle detection.
- Grid problems are implicit graphs — treat each cell as a node.
- Always mark visited nodes **before** enqueuing (BFS) or at the start of the call (DFS).

---

# Problems Covered

| Problem | Algorithm |
|---------|----------|
| Number of Islands | DFS/BFS — count connected components in grid |
| Clone Graph | DFS with hash map (node → clone) |
| Course Schedule | Kahn's topological sort — cycle detection in DAG |
| Flood Fill | DFS/BFS — recolor connected region |
