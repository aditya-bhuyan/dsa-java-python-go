package linkedlist.middlenode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MiddleNodeTest {

    private final MiddleNode solution = new MiddleNode();

    @Test
    void testOddLength() {
        MiddleNode.ListNode head = MiddleNode.buildList(new int[]{1, 2, 3, 4, 5});
        assertEquals(3, solution.findMiddle(head).val);
    }

    @Test
    void testEvenLengthReturnsSecondMiddle() {
        MiddleNode.ListNode head = MiddleNode.buildList(new int[]{1, 2, 3, 4, 5, 6});
        assertEquals(4, solution.findMiddle(head).val);
    }

    @Test
    void testSingleNode() {
        MiddleNode.ListNode head = MiddleNode.buildList(new int[]{1});
        assertEquals(1, solution.findMiddle(head).val);
    }

    @Test
    void testTwoNodesReturnsSecond() {
        MiddleNode.ListNode head = MiddleNode.buildList(new int[]{1, 2});
        assertEquals(2, solution.findMiddle(head).val);
    }

    @Test
    void testFourNodes() {
        MiddleNode.ListNode head = MiddleNode.buildList(new int[]{1, 2, 3, 4});
        assertEquals(3, solution.findMiddle(head).val);
    }

    @Test
    void testThreeNodes() {
        MiddleNode.ListNode head = MiddleNode.buildList(new int[]{10, 20, 30});
        assertEquals(20, solution.findMiddle(head).val);
    }
}
