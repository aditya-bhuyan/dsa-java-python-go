package trees.symmetrictree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SymmetricTreeTest {
    private final SymmetricTree sol = new SymmetricTree();
    SymmetricTree.TreeNode n(int v) { return new SymmetricTree.TreeNode(v); }

    @Test void nullTree()   { assertTrue(sol.isSymmetric(null)); }
    @Test void singleNode() { assertTrue(sol.isSymmetric(n(1))); }
    @Test void symmetric() {
        var r = new SymmetricTree.TreeNode(1);
        r.left  = new SymmetricTree.TreeNode(2, n(3), n(4));
        r.right = new SymmetricTree.TreeNode(2, n(4), n(3));
        assertTrue(sol.isSymmetric(r));
    }
    @Test void asymmetric() {
        var r = new SymmetricTree.TreeNode(1);
        r.left = n(2); r.left.right = n(3);
        r.right = n(2); r.right.right = n(3);
        assertFalse(sol.isSymmetric(r));
    }
    @Test void differentValues() {
        var r = new SymmetricTree.TreeNode(1, n(2), n(3));
        assertFalse(sol.isSymmetric(r));
    }
}
