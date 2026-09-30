package trees.maximumdepth;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MaximumDepthTest {

    private final MaximumDepth sol = new MaximumDepth();

    private MaximumDepth.TreeNode n(int v) { return new MaximumDepth.TreeNode(v); }

    @Test void nullRoot()    { assertNull(null); assertEquals(0, sol.maxDepth(null)); }
    @Test void singleNode()  { assertEquals(1, sol.maxDepth(n(1))); }
    @Test void twoNodeLeft() {
        var r = n(1); r.left = n(2);
        assertEquals(2, sol.maxDepth(r));
    }
    @Test void example1() {
        var r = n(3); r.left = n(9); r.right = n(20);
        r.right.left = n(15); r.right.right = n(7);
        assertEquals(3, sol.maxDepth(r));
    }
    @Test void rightSkewed() {
        var r = n(1); r.right = n(2); r.right.right = n(3); r.right.right.right = n(4);
        assertEquals(4, sol.maxDepth(r));
    }
}
