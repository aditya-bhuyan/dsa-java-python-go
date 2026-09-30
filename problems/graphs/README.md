# Graphs Problems

> **Week 8** · Core concept: BFS/DFS on adjacency lists and 2D grids, cycle detection, topological sort.

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Number of Islands | Medium | [number-of-islands/](number-of-islands/) |
| 2 | Clone Graph | Medium | [clone-graph/](clone-graph/) |
| 3 | Course Schedule | Medium | [course-schedule/](course-schedule/) |
| 4 | Flood Fill | Easy | [flood-fill/](flood-fill/) |

## Key Patterns
- **DFS flood-fill** — mark visited by mutating grid in-place or using a visited set
- **BFS shortest path** — unweighted graph, level-by-level expansion
- **Deep clone with map** — store `original → clone` mapping to handle cycles (Clone Graph)
- **Topological sort / cycle detection** — Kahn's BFS or DFS with 3-color state

## BFS grid template
```python
from collections import deque
dirs = [(0,1),(0,-1),(1,0),(-1,0)]
q = deque([(r, c)]); visited.add((r, c))
while q:
    r, c = q.popleft()
    for dr, dc in dirs:
        nr, nc = r+dr, c+dc
        if valid and (nr,nc) not in visited:
            visited.add((nr,nc)); q.append((nr,nc))
```

## Concepts
→ [concepts/graph.md](../../concepts/graph.md)  
→ [concepts/union-find.md](../../concepts/union-find.md)

← [Back to problems](../README.md)
