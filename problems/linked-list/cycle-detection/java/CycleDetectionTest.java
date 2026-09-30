package linkedlist.cycledetection;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CycleDetectionTest {

    private final CycleDetection solution = new CycleDetection();

    @Test
    void testNoCycle() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{1, 2, 3, 4}, -1);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void testCycleTailToHead() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{1, 2, 3}, 0);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void testCycleTailToMiddle() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{3, 2, 0, -4}, 1);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void testSelfLoop() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{1}, 0);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void testSingleNodeNoCycle() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{1}, -1);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void testTwoNodesCycle() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{1, 2}, 0);
        assertTrue(solution.hasCycle(head));
    }

    @Test
    void testTwoNodesNoCycle() {
        CycleDetection.ListNode head = CycleDetection.buildList(new int[]{1, 2}, -1);
        assertFalse(solution.hasCycle(head));
    }

    @Test
    void testEmptyList() {
        assertFalse(solution.hasCycle(null));
    }
}
