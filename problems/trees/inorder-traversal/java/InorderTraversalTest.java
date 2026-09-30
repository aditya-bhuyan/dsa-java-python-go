package trees.inordertraversal;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class InorderTraversalTest {

    private final InorderTraversal sol = new InorderTraversal();

    private InorderTraversal.TreeNode n(int v) { return new InorderTraversal.TreeNode(v); }

    private InorderTraversal.TreeNode example() {
        var r = n(4); r.left = n(2); r.right = n(5);
        r.left.left = n(1); r.left.right = n(3);
        return r;
    }

    @Test void nullRecursive()   { assertEquals(List.of(), sol.inorderRecursive(null)); }
    @Test void nullIterative()   { assertEquals(List.of(), sol.inorderIterative(null)); }
    @Test void singleRecursive() { assertEquals(List.of(1), sol.inorderRecursive(n(1))); }
    @Test void singleIterative() { assertEquals(List.of(1), sol.inorderIterative(n(1))); }
    @Test void exampleRecursive(){ assertEquals(List.of(1,2,3,4,5), sol.inorderRecursive(example())); }
    @Test void exampleIterative(){ assertEquals(List.of(1,2,3,4,5), sol.inorderIterative(example())); }
    @Test void rightSkewedRecursive() {
        var r = n(1); r.right = n(2); r.right.right = n(3);
        assertEquals(List.of(1,2,3), sol.inorderRecursive(r));
    }
}
