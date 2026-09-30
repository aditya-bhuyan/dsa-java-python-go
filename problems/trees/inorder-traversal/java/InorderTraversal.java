package trees.inordertraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Binary Tree Inorder Traversal — recursive and iterative.
 * Time: O(n)  Space: O(h)
 */
public class InorderTraversal {

    public static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    /** Recursive inorder. */
    public List<Integer> inorderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private void dfs(TreeNode node, List<Integer> result) {
        if (node == null) return;
        dfs(node.left, result);
        result.add(node.val);
        dfs(node.right, result);
    }

    /** Iterative inorder using explicit stack. */
    public List<Integer> inorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    public static void main(String[] args) {
        //     4
        //    / \
        //   2   5
        //  / \
        // 1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);

        InorderTraversal sol = new InorderTraversal();
        System.out.println("============================================================");
        System.out.println("Binary Tree Inorder Traversal");
        System.out.println("============================================================");
        System.out.println("Recursive : " + sol.inorderRecursive(root) + "  (expected [1,2,3,4,5])");
        System.out.println("Iterative : " + sol.inorderIterative(root) + "  (expected [1,2,3,4,5])");
    }
}
