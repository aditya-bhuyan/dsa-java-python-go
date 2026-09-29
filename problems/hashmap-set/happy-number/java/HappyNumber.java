import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    
    // Approach 1: HashSet Cycle Detection - Optimal
    // Time: O(log n * m), Space: O(m)
    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumOfSquares(n);
        }
        return n == 1;
    }
    
    // Approach 2: Floyd's Cycle Detection (Space Optimized)
    // Time: O(log n * m), Space: O(1)
    public boolean isHappyFloyd(int n) {
        int slow = n;
        int fast = sumOfSquares(n);
        
        while (fast != 1 && slow != fast) {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        }
        
        return fast == 1;
    }
    
    // Helper method to calculate sum of squares of digits
    private int sumOfSquares(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
