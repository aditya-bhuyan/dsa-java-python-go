package trees.maximumdepth;

/**
 * Maximum Depth of Binary Tree
 *
 * maxDepth(node) = 0                      if node == null
 *               = 1 + max(left, right)    otherwise
 *
 * Time: O(n)  Space: O(h)
 */
public class MaximumDepth {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    /** Returns the maximum depth of the binary tree. */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        //       3
        //      / \
        //     9  20
        //        / \
        //       15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left  = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaximumDepth sol = new MaximumDepth();
        System.out.println("============================================================");
        System.out.println("Maximum Depth of Binary Tree");
        System.out.println("============================================================");
        System.out.println("Depth: " + sol.maxDepth(root) + "  (expected 3)");
    }
}
