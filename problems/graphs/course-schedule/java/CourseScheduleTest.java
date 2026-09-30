// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Course Schedule — Java JUnit 5 Tests

package graphs.courseschedule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleTest {

    private CourseSchedule solver;

    @BeforeEach
    void setUp() {
        solver = new CourseSchedule();
    }

    @Test
    void testTwoCoursesPossible() {
        assertTrue(solver.canFinish(2, new int[][]{{1, 0}}));
    }

    @Test
    void testTwoCoursesCycle() {
        assertFalse(solver.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    @Test
    void testNoPrerequisites() {
        assertTrue(solver.canFinish(1, new int[][]{}));
    }

    @Test
    void testNoPrerequisitesManyCourses() {
        assertTrue(solver.canFinish(5, new int[][]{}));
    }

    @Test
    void testLongChainNoCycle() {
        assertTrue(solver.canFinish(5, new int[][]{{1,0},{2,1},{3,2},{4,3}}));
    }

    @Test
    void testThreeNodeCycle() {
        assertFalse(solver.canFinish(3, new int[][]{{0,1},{1,2},{2,0}}));
    }

    @Test
    void testSelfLoop() {
        assertFalse(solver.canFinish(2, new int[][]{{0, 0}}));
    }

    @Test
    void testDisconnectedNoCycle() {
        assertTrue(solver.canFinish(4, new int[][]{{1,0},{3,2}}));
    }
}
