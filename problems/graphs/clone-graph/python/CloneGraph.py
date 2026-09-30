# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Clone Graph (LeetCode 133)
# Approach: BFS + dict (original → clone)
#
# Dry Run (4-node cycle: 1↔2↔3↔4↔1, plus 1↔4):
# Start: visited = {node1: clone1}, queue = [node1]
# Process node1: neighbors [2, 4]
#   node2 not visited → clone2, enqueue; visited={1:c1,2:c2}
#   node4 not visited → clone4, enqueue; visited={1:c1,2:c2,4:c4}
#   clone1.neighbors = [clone2, clone4]
# Process node2: neighbors [1, 3]
#   node1 already in visited → use clone1
#   node3 not visited → clone3, enqueue; visited={...,3:c3}
#   clone2.neighbors = [clone1, clone3]
# Process node4: neighbors [1, 3]
#   both known → clone4.neighbors = [clone1, clone3]
# Process node3: neighbors [2, 4]
#   both known → clone3.neighbors = [clone2, clone4]
# Result: full deep copy ✓

from collections import deque


class Node:
    """Graph node with integer value and list of neighbor nodes."""

    def __init__(self, val: int = 0, neighbors: list = None):
        self.val = val
        self.neighbors: list["Node"] = neighbors if neighbors is not None else []


class CloneGraph:
    """
    Solution for Clone Graph using BFS + dict.

    Time:  O(V + E)  — every node and edge visited once
    Space: O(V)      — dict stores one entry per node
    """

    def clone_graph(self, node: "Node | None") -> "Node | None":
        """
        Return a deep copy of the graph.

        Args:
            node: any node in the connected graph, or None.
        Returns:
            The cloned root node, or None.
        """
        if node is None:
            return None

        visited: dict[Node, Node] = {node: Node(node.val)}
        queue: deque[Node] = deque([node])

        while queue:
            curr = queue.popleft()
            for nb in curr.neighbors:
                if nb not in visited:
                    visited[nb] = Node(nb.val)
                    queue.append(nb)
                visited[curr].neighbors.append(visited[nb])

        return visited[node]


# ─────────────────────────────────────────────
# Helpers
# ─────────────────────────────────────────────

def build_graph(adj_list: list[list[int]]) -> "Node | None":
    """Build a graph from a 1-indexed adjacency list. Returns node with val=1."""
    if not adj_list:
        return None
    nodes = {i: Node(i) for i in range(1, len(adj_list) + 1)}
    for i, neighbors in enumerate(adj_list, start=1):
        nodes[i].neighbors = [nodes[nb] for nb in neighbors]
    return nodes[1]


def to_adj_map(node: "Node | None") -> dict[int, list[int]]:
    """Convert graph to a sorted adjacency map for easy comparison."""
    if node is None:
        return {}
    result: dict[int, list[int]] = {}
    visited: set[int] = set()
    queue: deque[Node] = deque([node])
    visited.add(node.val)
    while queue:
        curr = queue.popleft()
        result[curr.val] = sorted(nb.val for nb in curr.neighbors)
        for nb in curr.neighbors:
            if nb.val not in visited:
                visited.add(nb.val)
                queue.append(nb)
    return result


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = CloneGraph()

    original = build_graph([[2, 4], [1, 3], [2, 4], [1, 3]])
    clone = solver.clone_graph(original)
    print("Original adj:", to_adj_map(original))
    print("Clone adj:   ", to_adj_map(clone))
    print("Different objects:", original is not clone)

    single = Node(1)
    cloned_single = solver.clone_graph(single)
    print(f"Single clone: val={cloned_single.val}, same_ptr={single is cloned_single}")

    print("None clone:", solver.clone_graph(None))


if __name__ == "__main__":
    main()
