// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Course Schedule (LeetCode 207)
// Approach: BFS Kahn's Algorithm (Topological Sort)

package graphs.courseschedule;

import java.util.*;

/**
 * Solution for Course Schedule.
 *
 * <p>Strategy: Build a directed graph where prerequisite[b] → course[a].
 * Use Kahn's BFS topological sort: if all nodes are processed, no cycle exists.
 *
 * <p>Time:  O(V + E)  where V = numCourses, E = prerequisites.length
 * Space: O(V + E)
 */
public class CourseSchedule {

    /**
     * Returns true if all courses can be completed without circular dependencies.
     *
     * @param numCourses   total number of courses (labeled 0..numCourses-1)
     * @param prerequisites [a, b] means b must be taken before a
     * @return true if a valid course order exists
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            int a = pre[0], b = pre[1]; // b → a
            adj.get(b).add(a);
            inDegree[a]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;
            for (int nb : adj.get(node)) {
                if (--inDegree[nb] == 0) queue.add(nb);
            }
        }
        return processed == numCourses;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        CourseSchedule solver = new CourseSchedule();

        Object[][] tests = {
            {2, new int[][]{{1, 0}},             true},
            {2, new int[][]{{1, 0}, {0, 1}},     false},
            {1, new int[][]{},                    true},
            {5, new int[][]{{1,0},{2,1},{3,2},{4,3}}, true},
            {3, new int[][]{{0,1},{1,2},{2,0}},   false},
        };

        for (int i = 0; i < tests.length; i++) {
            int n = (int) tests[i][0];
            int[][] pre = (int[][]) tests[i][1];
            boolean exp = (boolean) tests[i][2];
            boolean result = solver.canFinish(n, pre);
            System.out.printf("Test %d: canFinish=%b (expected %b) → %s%n",
                i + 1, result, exp, result == exp ? "PASS" : "FAIL");
        }
    }
}
