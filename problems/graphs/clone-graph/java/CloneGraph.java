// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Clone Graph (LeetCode 133)
// Approach: DFS + HashMap (original → clone)

package graphs.clonegraph;

import java.util.*;

/**
 * Solution for Clone Graph.
 *
 * <p>Strategy: DFS with a HashMap&lt;Node, Node&gt; visited map.
 * Register each clone BEFORE recursing into neighbors to correctly handle cycles.
 *
 * <p>Time:  O(V + E)
 * Space: O(V) — map size + recursion depth
 */
public class CloneGraph {

    /** Graph node definition. */
    public static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    /**
     * Returns a deep copy of the graph.
     *
     * @param node any node in the connected graph, or null
     * @return cloned root node, or null
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    private Node dfs(Node node, Map<Node, Node> visited) {
        if (visited.containsKey(node)) return visited.get(node);
        Node clone = new Node(node.val);
        visited.put(node, clone); // register before recursing
        for (Node nb : node.neighbors) {
            clone.neighbors.add(dfs(nb, visited));
        }
        return clone;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Build a graph from 1-indexed adjacency list. */
    public static Node buildGraph(int[][] adjList) {
        if (adjList.length == 0) return null;
        Node[] nodes = new Node[adjList.length + 1];
        for (int i = 1; i <= adjList.length; i++) nodes[i] = new Node(i);
        for (int i = 0; i < adjList.length; i++) {
            for (int nb : adjList[i]) nodes[i + 1].neighbors.add(nodes[nb]);
        }
        return nodes[1];
    }

    /** Convert graph to adjacency map (sorted neighbor lists) for comparison. */
    public static Map<Integer, List<Integer>> toAdjMap(Node node) {
        if (node == null) return null;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Set<Node> visited = new HashSet<>();
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            List<Integer> nbVals = new ArrayList<>();
            for (Node nb : curr.neighbors) {
                nbVals.add(nb.val);
                if (!visited.contains(nb)) { visited.add(nb); queue.add(nb); }
            }
            Collections.sort(nbVals);
            map.put(curr.val, nbVals);
        }
        return map;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        CloneGraph solver = new CloneGraph();

        Node original = buildGraph(new int[][]{{2, 4}, {1, 3}, {2, 4}, {1, 3}});
        Node clone = solver.cloneGraph(original);

        System.out.println("Original adj: " + toAdjMap(original));
        System.out.println("Clone adj:    " + toAdjMap(clone));
        System.out.println("Different objects: " + (original != clone));

        // Single node
        Node single = new Node(1);
        Node clonedSingle = solver.cloneGraph(single);
        System.out.println("Single clone val=" + clonedSingle.val
            + " different=" + (single != clonedSingle));

        // Null
        System.out.println("Null clone: " + solver.cloneGraph(null));
    }
}
