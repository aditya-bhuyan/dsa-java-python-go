// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Clone Graph — Java JUnit 5 Tests

package graphs.clonegraph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CloneGraphTest {

    private CloneGraph solver;

    @BeforeEach
    void setUp() {
        solver = new CloneGraph();
    }

    @Test
    void testNullInput() {
        assertNull(solver.cloneGraph(null));
    }

    @Test
    void testSingleNodeNoNeighbors() {
        CloneGraph.Node n = new CloneGraph.Node(1);
        CloneGraph.Node clone = solver.cloneGraph(n);
        assertNotSame(n, clone);
        assertEquals(1, clone.val);
        assertTrue(clone.neighbors.isEmpty());
    }

    @Test
    void testFourNodeCycle() {
        CloneGraph.Node original = CloneGraph.buildGraph(new int[][]{{2, 4}, {1, 3}, {2, 4}, {1, 3}});
        CloneGraph.Node clone = solver.cloneGraph(original);

        assertNotSame(original, clone);
        assertEquals(CloneGraph.toAdjMap(original), CloneGraph.toAdjMap(clone));
    }

    @Test
    void testTwoNodeMutual() {
        CloneGraph.Node original = CloneGraph.buildGraph(new int[][]{{2}, {1}});
        CloneGraph.Node clone = solver.cloneGraph(original);

        assertNotSame(original, clone);
        assertEquals(CloneGraph.toAdjMap(original), CloneGraph.toAdjMap(clone));
    }

    @Test
    void testCloneIsDeep() {
        CloneGraph.Node original = CloneGraph.buildGraph(new int[][]{{2}, {1}});
        CloneGraph.Node clone = solver.cloneGraph(original);

        // Modifying original should not affect clone
        original.val = 99;
        assertEquals(1, clone.val);
    }
}
