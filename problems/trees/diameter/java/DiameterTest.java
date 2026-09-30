package trees.diameter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DiameterTest {
    Diameter.TreeNode n(int v) { return new Diameter.TreeNode(v); }

    @Test void singleNode() { assertEquals(0, new Diameter().diameterOfBinaryTree(n(1))); }
    @Test void twoNodes() {
        var r = n(1); r.right = n(2);
        assertEquals(1, new Diameter().diameterOfBinaryTree(r));
    }
    @Test void example1() {
        var r = new Diameter.TreeNode(1);
        r.left = new Diameter.TreeNode(2, n(4), n(5)); r.right = n(3);
        assertEquals(3, new Diameter().diameterOfBinaryTree(r));
    }
    @Test void nullRoot() { assertEquals(0, new Diameter().diameterOfBinaryTree(null)); }
    @Test void diameterNotThroughRoot() {
        // diameter lives in left subtree: 5→3→2→4 = 3
        var r = n(1);
        r.left = n(2); r.left.left = n(3); r.left.right = n(4);
        r.left.left.left = n(5);
        assertEquals(3, new Diameter().diameterOfBinaryTree(r));
    }
}
