# Clone Graph

## Problem Statement

Given a reference of a node in a **connected undirected graph**, return a **deep copy (clone)** of the graph.

Each node in the graph contains a value (`int`) and a list of its neighbors (`List[Node]`).

```
class Node {
    public int val;
    public List<Node> neighbors;
}
```

For simplicity, each node's value is the same as its 1-based index (values 1–n).

**LeetCode:** [133. Clone Graph](https://leetcode.com/problems/clone-graph/)  
**Difficulty:** Medium  
**Topic Tags:** Hash Table, Depth-First Search, Breadth-First Search, Graph

---

## Examples

### Example 1
```
Input:  adjList = [[2,4],[1,3],[2,4],[1,3]]
         (Node 1 connects to 2,4; Node 2 connects to 1,3; etc.)
Output: deep copy of the same graph structure
```

### Example 2
```
Input:  adjList = [[]]   (single node, no neighbors)
Output: deep copy of the single node
```

### Example 3
```
Input:  adjList = []     (null input — no node)
Output: null
```

---

## Constraints

- The number of nodes is in the range `[0, 100]`.
- `1 <= Node.val <= 100`
- `Node.val` is unique for each node.
- There are no repeated edges and no self-loops.
- The graph is connected (if non-empty).

---

## Understanding the Problem

We need to duplicate every node and every edge, creating an entirely new set of objects. If we naively follow neighbors recursively without tracking what we have already cloned, we enter infinite loops on cycles.

**Key insight:** Use a hash map `{original_node → cloned_node}` as both a *visited* set and a lookup for already-created clones.

### Visual Representation
```
Original:          Clone:

  1 — 2              1' — 2'
  |   |              |    |
  4 — 3              4' — 3'

All edges duplicated; no shared object references.
```

---

## Approach 1: Brute Force (Naive recursion, no visited map)

**Why it fails:** Graph may contain cycles. Without tracking visited nodes the recursion never terminates.

---

## Approach 2: DFS + HashMap (Optimal)

**Idea:**
1. Maintain a `visited` map: `original node → cloned node`.
2. DFS from the start node.
3. On visiting a node:
   - If already in map → return the existing clone.
   - Else create a new clone, store it in map, then recursively clone all neighbors and append to clone's neighbor list.

### Algorithm Steps
```
visited = {}

dfs(node):
    if node is None: return None
    if node in visited: return visited[node]
    clone = Node(node.val)
    visited[node] = clone
    for neighbor in node.neighbors:
        clone.neighbors.append(dfs(neighbor))
    return clone

return dfs(node)
```

### Dry Run (4-node cycle)
```
dfs(1):
  clone1 = Node(1); visited={1:clone1}
  neighbor 2 → dfs(2):
    clone2 = Node(2); visited={1:clone1, 2:clone2}
    neighbor 1 → in visited → return clone1 ✓
    neighbor 3 → dfs(3):
      clone3 = Node(3); visited={..., 3:clone3}
      neighbor 2 → in visited → return clone2 ✓
      neighbor 4 → dfs(4):
        clone4 = Node(4)
        neighbor 1 → in visited → return clone1 ✓
        neighbor 3 → in visited → return clone3 ✓
        return clone4
      clone3.neighbors = [clone2, clone4]
    clone2.neighbors = [clone1, clone3]
  neighbor 4 → in visited → return clone4 ✓
  clone1.neighbors = [clone2, clone4]
return clone1 ✓
```

---

## Approach 3: BFS + HashMap

Same map trick, but with an iterative BFS queue.

```
visited = {node: Node(node.val)}
queue = deque([node])
while queue:
    curr = queue.popleft()
    for nb in curr.neighbors:
        if nb not in visited:
            visited[nb] = Node(nb.val)
            queue.append(nb)
        visited[curr].neighbors.append(visited[nb])
return visited[node]
```

Both DFS and BFS are O(V + E) time and space.

---

## Complexity Summary

| Approach | Time | Space | Notes |
|---|---|---|---|
| Naive recursion | ∞ | ∞ | Infinite loop on cycles |
| DFS + HashMap | O(V + E) | O(V) | V = nodes, E = edges |
| BFS + HashMap | O(V + E) | O(V) | Queue size ≤ V |

---

## Edge Cases

| Case | Expected |
|---|---|
| `null` / `None` input | `null` / `None` |
| Single node, no neighbors | cloned single node |
| Two nodes connected to each other | both cloned with mutual refs |
| Fully connected graph (K₄) | full clone |

---

## Interview Discussion Points

1. **Why do we need the visited map?** — Cycle detection + O(1) lookup of already-cloned nodes.
2. **DFS vs BFS?** — Both valid; DFS is more concise, BFS avoids deep recursion stacks.
3. **How do you verify the clone is deep?** — Show that `clonedNode != originalNode` but `clonedNode.val == originalNode.val` for every node.
4. **What if the graph is disconnected?** — Problem guarantees it is connected; otherwise iterate all nodes.

---

## Common Mistakes

- Not checking `visited` before creating a new clone → creates duplicate nodes, breaks cycle handling.
- Appending neighbors before storing the clone in `visited` → revisiting the same node before it's registered causes an infinite loop.
- Shallow copy of the neighbor list instead of recursively cloning each neighbor.

---

## Key Takeaways

- The `visited` map serves **dual purpose**: cycle guard + already-cloned node lookup.
- This is the canonical template for cloning any graph or linked structure with back-references.
- Pattern reuse: same technique appears in "Copy List with Random Pointer" (LeetCode 138).

---

## Next Problem

➡️ [Course Schedule](../course-schedule/README.md)
