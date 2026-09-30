package trees.sametree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SameTreeTest {
    private final SameTree sol = new SameTree();
    private SameTree.TreeNode n(int v) { return new SameTree.TreeNode(v); }

    @Test void bothNull()  { assertTrue(sol.isSameTree(null, null)); }
    @Test void pNull()     { assertFalse(sol.isSameTree(null, n(1))); }
    @Test void qNull()     { assertFalse(sol.isSameTree(n(1), null)); }
    @Test void singleEqual()   { assertTrue(sol.isSameTree(n(1), n(1))); }
    @Test void singleUnequal() { assertFalse(sol.isSameTree(n(1), n(2))); }
    @Test void sameStructureSameValues() {
        var p = new SameTree.TreeNode(1); p.left = n(2); p.right = n(3);
        var q = new SameTree.TreeNode(1); q.left = n(2); q.right = n(3);
        assertTrue(sol.isSameTree(p, q));
    }
    @Test void differentStructure() {
        var p = n(1); p.left = n(2);
        var q = n(1); q.right = n(2);
        assertFalse(sol.isSameTree(p, q));
    }
}
