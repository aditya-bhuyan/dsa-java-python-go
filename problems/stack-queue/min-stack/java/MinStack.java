package stackqueue.minstack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ============================================================================
 * Problem: Min Stack
 * ============================================================================
 *
 * Design a stack with push, pop, top, and getMin — all in O(1).
 *
 * ============================================================================
 *
 * Approach — Auxiliary Min Stack
 * --------------------------------
 *
 * Maintain two stacks:
 *   main     — all pushed values.
 *   minStack — running minimum at each stack level.
 *
 * push(val):
 *   main.push(val)
 *   minStack.push( min(val, minStack.peek()) )
 *
 * pop():
 *   main.pop()
 *   minStack.pop()
 *
 * top()    → main.peek()
 * getMin() → minStack.peek()
 *
 * ============================================================================
 *
 * Dry Run
 * -------
 *
 * push(-2)  main:[-2]        minStack:[-2]
 * push(0)   main:[-2,0]      minStack:[-2,-2]
 * push(-3)  main:[-2,0,-3]   minStack:[-2,-2,-3]
 * getMin()  → -3
 * pop()     main:[-2,0]      minStack:[-2,-2]
 * top()     → 0
 * getMin()  → -2
 *
 * ============================================================================
 *
 * Complexity Analysis
 * -------------------
 *
 * All operations: O(1) time.
 * Space         : O(n) total.
 *
 * ============================================================================
 */
public class MinStack {

    private final Deque<Integer> main     = new ArrayDeque<>();
    private final Deque<Integer> minStack = new ArrayDeque<>();

    /**
     * Pushes val onto the stack.
     *
     * @param val  Value to push.
     */
    public void push(int val) {
        main.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        } else {
            minStack.push(minStack.peek());
        }
    }

    /**
     * Removes the top element.
     */
    public void pop() {
        main.pop();
        minStack.pop();
    }

    /**
     * Returns the top element without removing it.
     *
     * @return top value.
     */
    public int top() {
        return main.peek();
    }

    /**
     * Returns the minimum element in the stack.
     *
     * @return minimum value.
     */
    public int getMin() {
        return minStack.peek();
    }

    /**
     * Demonstrates the MinStack.
     */
    public static void main(String[] args) {

        MinStack ms = new MinStack();

        ms.push(-2);
        ms.push(0);
        ms.push(-3);

        System.out.println("============================================================");
        System.out.println("Min Stack");
        System.out.println("============================================================");

        System.out.println("After push(-2), push(0), push(-3)");
        System.out.println("getMin() = " + ms.getMin() + "  (expected -3)");

        ms.pop();

        System.out.println("After pop()");
        System.out.println("top()    = " + ms.top()    + "  (expected 0)");
        System.out.println("getMin() = " + ms.getMin() + "  (expected -2)");
    }
}
