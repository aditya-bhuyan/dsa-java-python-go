package binarysearch.firstbadversion;

/**
 * First Bad Version — Template 2 left boundary on boolean space.
 * Time: O(log n)  Space: O(1)
 */
public class FirstBadVersion {

    // In the real problem this would be provided by the platform.
    // We make it overridable for testing.
    protected boolean isBadVersion(int version) {
        throw new UnsupportedOperationException("override in subclass or test");
    }

    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) right = mid;
            else                   left  = mid + 1;
        }
        return left;
    }

    public static void main(String[] args) {
        FirstBadVersion sol = new FirstBadVersion() {
            @Override protected boolean isBadVersion(int v) { return v >= 4; }
        };
        System.out.println("n=5, bad=4 → " + sol.firstBadVersion(5) + "  (expected 4)");
    }
}
