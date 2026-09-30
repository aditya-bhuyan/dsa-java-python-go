package trees.sametree;

/**
 * Same Tree — pairwise DFS.
 * Time: O(n)  Space: O(h)
 */
public class SameTree {

    public static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val)         return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SameTree sol = new SameTree();
        TreeNode p1 = new TreeNode(1); p1.left = new TreeNode(2); p1.right = new TreeNode(3);
        TreeNode q1 = new TreeNode(1); q1.left = new TreeNode(2); q1.right = new TreeNode(3);
        System.out.println("Same (expected true) : " + sol.isSameTree(p1, q1));
    }
}
