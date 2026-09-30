package trees.symmetrictree;

/**
 * Symmetric Tree — mirror DFS.
 * Time: O(n)  Space: O(h)
 */
public class SymmetricTree {

    public static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
        TreeNode(int v, TreeNode l, TreeNode r) { val=v; left=l; right=r; }
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val)         return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public static void main(String[] args) {
        SymmetricTree sol = new SymmetricTree();
        TreeNode r = new TreeNode(1);
        r.left  = new TreeNode(2, new TreeNode(3), new TreeNode(4));
        r.right = new TreeNode(2, new TreeNode(4), new TreeNode(3));
        System.out.println("Symmetric (expected true): " + sol.isSymmetric(r));
    }
}
