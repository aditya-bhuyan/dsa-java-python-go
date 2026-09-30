package stackqueue.validparentheses;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * ============================================================================
 * Problem: Valid Parentheses
 * ============================================================================
 *
 * Problem Statement
 * -----------------
 * Given a string s containing only '(', ')', '{', '}', '[', ']',
 * determine if the string is valid.
 *
 * Valid means:
 *   1. Every open bracket is closed by the same bracket type.
 *   2. Brackets are closed in the correct order.
 *   3. Every close bracket has a corresponding open bracket.
 *
 * Examples
 * --------
 *
 * "()"      → true
 * "()[]{}"  → true
 * "(]"      → false
 * "([)]"    → false
 * "{[]}"    → true
 *
 * ============================================================================
 *
 * Approach
 * --------
 *
 * Stack-based single pass.
 *
 * - Open bracket  → push.
 * - Close bracket → check top of stack matches; pop if yes, return false if no.
 *
 * After processing all characters, valid iff stack is empty.
 *
 * ============================================================================
 *
 * Dry Run
 * -------
 *
 * Input: "{[()]}"
 *
 * { → push    stack: [ { ]
 * [ → push    stack: [ { [ ]
 * ( → push    stack: [ { [ ( ]
 * ) → top=(   match! pop   stack: [ { [ ]
 * ] → top=[   match! pop   stack: [ { ]
 * } → top={   match! pop   stack: []
 *
 * Empty → true
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 *
 * ============================================================================
 */
public class ValidParentheses {

    /**
     * Map from close bracket to its expected open bracket.
     */
    private static final Map<Character, Character> MATCHING = Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    );

    /**
     * Returns true if the bracket string is valid.
     *
     * @param s
     *         Input string containing only bracket characters.
     *
     * @return
     *         true if valid, false otherwise.
     */
    public boolean isValid(String s) {

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '[' || ch == '{') {

                /*
                 * Open bracket — push onto the stack.
                 */
                stack.push(ch);

            } else {

                /*
                 * Close bracket — stack must not be empty,
                 * and top must match.
                 */
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (top != MATCHING.get(ch)) {
                    return false;
                }
            }
        }

        /*
         * All brackets must be matched.
         */
        return stack.isEmpty();
    }

    /**
     * Demonstrates the algorithm.
     */
    public static void main(String[] args) {

        ValidParentheses solution = new ValidParentheses();

        String[] cases = {"()", "()[]{}", "(]", "([)]", "{[]}", ""};

        System.out.println("============================================================");
        System.out.println("Valid Parentheses");
        System.out.println("============================================================");

        for (String s : cases) {
            System.out.printf("%-14s → %b%n", "\"" + s + "\"", solution.isValid(s));
        }
    }
}
