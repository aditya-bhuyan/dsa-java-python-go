# Course Schedule

## Problem Statement

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [aᵢ, bᵢ]` indicates that you **must** take course `bᵢ` first if you want to take course `aᵢ`.

Return `true` if you can finish all courses, `false` otherwise.

**LeetCode:** [207. Course Schedule](https://leetcode.com/problems/course-schedule/)  
**Difficulty:** Medium  
**Topic Tags:** Depth-First Search, Breadth-First Search, Graph, Topological Sort

---

## Examples

### Example 1
```
Input:  numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: Take course 0 first, then course 1.
```

### Example 2
```
Input:  numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: Course 1 requires 0, and course 0 requires 1 → cycle.
```

---

## Constraints

- `1 <= numCourses <= 2000`
- `0 <= prerequisites.length <= 5000`
- `prerequisites[i].length == 2`
- `0 <= aᵢ, bᵢ < numCourses`
- All the pairs are **unique**.

---

## Understanding the Problem

Model courses as graph nodes and each prerequisite `[a, b]` as a **directed edge b → a** (b must come before a).

**The question becomes:** Does the directed graph contain a cycle?
- **No cycle** → topological ordering exists → all courses can be completed → `true`
- **Cycle** → impossible to complete → `false`

### Visual Representation
```
Example 2:
  0 → 1
  1 → 0
  Cycle detected → false

Example with 4 courses, prerequisites [[1,0],[2,1],[3,2]]:
  0 → 1 → 2 → 3   (no cycle → true)
```

---

## Approach 1: Brute Force (try all orderings)

Try every permutation of courses and check if the prerequisite constraints are satisfied. O(n!) — completely infeasible.

---

## Approach 2: DFS Cycle Detection (3-color)

**Idea:** Use three states per node:
- `0` = unvisited (WHITE)
- `1` = in current DFS path (GRAY) — back-edge here = cycle
- `2` = fully processed (BLACK)

```
for each node:
    if state[node] == 0:
        if dfs(node) has cycle: return false
return true

dfs(node):
    state[node] = 1  (GRAY)
    for neighbor in adj[node]:
        if state[neighbor] == 1: cycle! return true
        if state[neighbor] == 0:
            if dfs(neighbor): return true
    state[node] = 2  (BLACK)
    return false
```

### Dry Run (Example 2)
```
Graph: 0→1, 1→0
dfs(0): state[0]=GRAY
  visit 1: state[1]=GRAY
    visit 0: state[0] == GRAY → CYCLE → return true
Answer: false ✓
```

### Dry Run (Example 1)
```
Graph: 0→1
dfs(0): state[0]=GRAY
  visit 1: state[1]=GRAY → no neighbors → state[1]=BLACK
  state[0]=BLACK
No cycle found → true ✓
```

---

## Approach 3: BFS Topological Sort — Kahn's Algorithm (Optimal)

**Idea:**
1. Build adjacency list + in-degree array.
2. Enqueue all nodes with in-degree 0 (no prerequisites).
3. Process queue: for each node dequeued, decrement in-degree of its neighbors; enqueue any that reach 0.
4. If total processed == `numCourses` → no cycle → `true`.

```
inDegree = [0] * numCourses
build adj from prerequisites
queue = all nodes where inDegree[node] == 0
processed = 0
while queue:
    node = dequeue
    processed++
    for nb in adj[node]:
        inDegree[nb]--
        if inDegree[nb] == 0: enqueue(nb)
return processed == numCourses
```

**Time:** O(V + E)  **Space:** O(V + E)

---

## Complexity Summary

| Approach | Time | Space | Notes |
|---|---|---|---|
| Brute Force | O(n!) | O(n) | Infeasible |
| DFS 3-color | O(V + E) | O(V) | Recursion stack depth ≤ V |
| BFS Kahn's | O(V + E) | O(V + E) | Iterative, no stack concern |

---

## Edge Cases

| Case | Expected |
|---|---|
| `prerequisites = []` | `true` (no dependencies) |
| Single course | `true` |
| Self-loop `[0, 0]` | `false` |
| Long chain, no cycle | `true` |
| Multiple disconnected components | handled by iterating all nodes |

---

## Interview Discussion Points

1. **Why directed graph?** — Prerequisite order matters; `[a,b]` ≠ `[b,a]`.
2. **DFS vs BFS?** — DFS is more intuitive (3-color); BFS (Kahn's) naturally produces the topological order as a bonus.
3. **What does the topological order give you?** — The actual valid course sequence for Course Schedule II (LeetCode 210).
4. **What is in-degree?** — Number of incoming edges; a node with in-degree 0 has no prerequisites.

---

## Common Mistakes

- Building edges in the wrong direction (a→b instead of b→a from `[a,b]`).
- Forgetting to handle nodes not reachable from the initial BFS/DFS traversal (disconnected components).
- Using only a `visited` boolean instead of 3-color → can't distinguish "in path" vs "already done".

---

## Key Takeaways

- **Cycle detection in a directed graph** = DFS 3-color OR BFS Kahn's in-degree count.
- Topological sort exists iff the graph is a DAG (Directed Acyclic Graph).
- This is the foundation for Course Schedule II, Alien Dictionary, Build Order, and many dependency resolution problems.

---

## Next Problem

➡️ [Flood Fill](../flood-fill/README.md)
