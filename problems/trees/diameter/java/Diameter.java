package trees.diameter;

/**
 * Diameter of Binary Tree — postorder DFS with running max.
 * Time: O(n)  Space: O(h)
 */
public class Diameter {

    public static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
        TreeNode(int v, TreeNode l, TreeNode r) { val=v; left=l; right=r; }
    }

    private int maxDiameter;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        int left  = depth(node.left);
        int right = depth(node.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        Diameter sol = new Diameter();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3);
        System.out.println("============================================================");
        System.out.println("Diameter of Binary Tree");
        System.out.println("============================================================");
        System.out.println("Diameter: " + sol.diameterOfBinaryTree(root) + "  (expected 3)");
    }
}
