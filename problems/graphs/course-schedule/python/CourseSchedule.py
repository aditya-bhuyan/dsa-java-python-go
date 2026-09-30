# Author: Aditya Bhuyan
# Date: 2026-07-29
# Problem: Course Schedule (LeetCode 207)
# Approach: BFS Kahn's Algorithm (Topological Sort)
#
# Dry Run (numCourses=3, prerequisites=[[0,1],[1,2],[2,0]]):
# adj = {1:[0], 2:[1], 0:[2]}, inDegree = [1, 1, 1]
# No node has inDegree=0 → queue is empty → processed=0 ≠ 3 → False ✓
#
# Dry Run (numCourses=2, prerequisites=[[1,0]]):
# adj = {0:[1]}, inDegree = [0, 1]
# queue = [0]; process 0 → processed=1, inDegree[1]→0, enqueue 1
# queue = [1]; process 1 → processed=2
# processed == numCourses → True ✓

from collections import deque


class CourseSchedule:
    """
    Solution for Course Schedule using BFS Kahn's Algorithm.

    Time:  O(V + E)  where V = numCourses, E = len(prerequisites)
    Space: O(V + E)
    """

    def can_finish(self, num_courses: int, prerequisites: list[list[int]]) -> bool:
        """
        Return True if all courses can be completed given the prerequisites.

        Args:
            num_courses:   number of courses labeled 0..num_courses-1
            prerequisites: list of [a, b] meaning course b must precede a
        Returns:
            True if no circular dependency exists, False otherwise.
        """
        adj: list[list[int]] = [[] for _ in range(num_courses)]
        in_degree: list[int] = [0] * num_courses

        for a, b in prerequisites:  # b → a
            adj[b].append(a)
            in_degree[a] += 1

        queue: deque[int] = deque(i for i in range(num_courses) if in_degree[i] == 0)
        processed = 0

        while queue:
            node = queue.popleft()
            processed += 1
            for nb in adj[node]:
                in_degree[nb] -= 1
                if in_degree[nb] == 0:
                    queue.append(nb)

        return processed == num_courses


# ─────────────────────────────────────────────
# Demo
# ─────────────────────────────────────────────
def main() -> None:
    solver = CourseSchedule()

    test_cases = [
        (2, [[1, 0]], True),
        (2, [[1, 0], [0, 1]], False),
        (1, [], True),
        (5, [[1, 0], [2, 1], [3, 2], [4, 3]], True),
        (3, [[0, 1], [1, 2], [2, 0]], False),
    ]

    for i, (n, prereqs, expected) in enumerate(test_cases, 1):
        result = solver.can_finish(n, prereqs)
        status = "PASS" if result == expected else "FAIL"
        print(f"Test {i}: can_finish={result} (expected {expected}) → {status}")


if __name__ == "__main__":
    main()
