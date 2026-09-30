package stackqueue.minstack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MinStackTest {

    @Test
    void testBasicOperations() {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        assertEquals(-3, ms.getMin());
        ms.pop();
        assertEquals(0,  ms.top());
        assertEquals(-2, ms.getMin());
    }

    @Test
    void testDuplicateMinimums() {
        MinStack ms = new MinStack();
        ms.push(1);
        ms.push(1);
        ms.pop();
        assertEquals(1, ms.getMin());
    }

    @Test
    void testMinUpdatesOnPop() {
        MinStack ms = new MinStack();
        ms.push(5);
        ms.push(3);
        ms.push(7);
        assertEquals(3, ms.getMin());
        ms.pop();  // remove 7
        assertEquals(3, ms.getMin());
        ms.pop();  // remove 3
        assertEquals(5, ms.getMin());
    }

    @Test
    void testSingleElement() {
        MinStack ms = new MinStack();
        ms.push(42);
        assertEquals(42, ms.top());
        assertEquals(42, ms.getMin());
    }

    @Test
    void testIncreasingValues() {
        MinStack ms = new MinStack();
        ms.push(1);
        ms.push(2);
        ms.push(3);
        assertEquals(1, ms.getMin());
    }

    @Test
    void testDecreasingValues() {
        MinStack ms = new MinStack();
        ms.push(3);
        ms.push(2);
        ms.push(1);
        assertEquals(1, ms.getMin());
        ms.pop();
        assertEquals(2, ms.getMin());
    }
}
