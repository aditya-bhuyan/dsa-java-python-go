// Author: Aditya Bhuyan
// Date: 2026-07-29
// Problem: Flood Fill (LeetCode 733)
// Approach: DFS recursive flood fill

package graphs.floodfill;

/**
 * Solution for Flood Fill.
 *
 * <p>Strategy: Capture the original color at (sr, sc). If it already equals
 * the new color, return immediately. Otherwise DFS from (sr, sc), painting
 * every reachable same-color pixel with the new color.
 *
 * <p>Time:  O(m * n)
 * Space: O(m * n)  — recursion stack worst-case
 */
public class FloodFill {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * Flood fill the image starting at (sr, sc) with the given color.
     *
     * @param image 2D pixel grid
     * @param sr    starting row
     * @param sc    starting column
     * @param color new fill color
     * @return modified image
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int origColor = image[sr][sc];
        if (origColor == color) return image;
        dfs(image, sr, sc, origColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int origColor, int newColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length
                || image[r][c] != origColor) {
            return;
        }
        image[r][c] = newColor;
        for (int[] d : DIRS) dfs(image, r + d[0], c + d[1], origColor, newColor);
    }

    // -------------------------------------------------------------------------
    // Helper — deep copy image
    // -------------------------------------------------------------------------
    public static int[][] copyImage(int[][] img) {
        int[][] cp = new int[img.length][];
        for (int i = 0; i < img.length; i++) cp[i] = img[i].clone();
        return cp;
    }

    // -------------------------------------------------------------------------
    // Demo
    // -------------------------------------------------------------------------
    public static void main(String[] args) {
        FloodFill solver = new FloodFill();

        int[][][] images = {
            {{1,1,1},{1,1,0},{1,0,1}},
            {{0,0,0},{0,0,0}},
        };
        int[] srs = {1, 0};
        int[] scs = {1, 0};
        int[] colors = {2, 0};
        int[][][] expected = {
            {{2,2,2},{2,2,0},{2,0,1}},
            {{0,0,0},{0,0,0}},
        };

        for (int i = 0; i < images.length; i++) {
            int[][] result = solver.floodFill(copyImage(images[i]), srs[i], scs[i], colors[i]);
            boolean ok = java.util.Arrays.deepEquals(result, expected[i]);
            System.out.printf("Test %d: %s → %s%n",
                i + 1, java.util.Arrays.deepToString(result), ok ? "PASS" : "FAIL");
        }
    }
}
