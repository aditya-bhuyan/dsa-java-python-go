# Queue

> Category: **Data Structure**
> Difficulty: **Foundational**

---

# Table of Contents

1. What is a Queue?
2. Core Operations
3. Internal Implementations
4. Types of Queues
5. Deque (Double-Ended Queue)
6. Complexity Summary
7. When to Use a Queue
8. Common Mistakes
9. Key Takeaways

---

# What is a Queue?

A **Queue** is a linear data structure that follows the **FIFO** principle:

```
FIFO — First In, First Out
```

The first element added is the first one to be removed.

```
Enqueue →  [ 1 | 2 | 3 | 4 ]  → Dequeue
            ↑                       ↑
           Rear                   Front
```

Elements enter from the **rear** and leave from the **front**.

---

## Real-World Analogies

| Analogy | FIFO Behaviour |
|---------|----------------|
| Queue at a ticket counter | First person in line is first served |
| Printer job queue | First job submitted prints first |
| BFS traversal | First node discovered is first expanded |
| CPU task scheduler | First task submitted runs first |

---

## Stack vs Queue

| Property | Stack | Queue |
|----------|-------|-------|
| Principle | LIFO | FIFO |
| Insert | Top (push) | Rear (enqueue) |
| Remove | Top (pop) | Front (dequeue) |
| Use case | DFS, undo, parsing | BFS, scheduling, buffering |

---

# Core Operations

## Enqueue (offer / add)

Add an element to the rear.

```
Queue before: [1, 2, 3]
enqueue(4)
Queue after : [1, 2, 3, 4]
                           ↑ rear
```

Time: O(1)

---

## Dequeue (poll / remove)

Remove and return the front element.

```
Queue before: [1, 2, 3, 4]
dequeue() → 1
Queue after : [2, 3, 4]
```

Time: O(1)

---

## Peek / Front

View the front element without removing it.

```
Queue: [2, 3, 4]
peek() → 2
Queue: [2, 3, 4]   (unchanged)
```

Time: O(1)

---

## isEmpty

Check whether the queue is empty.

Time: O(1)

---

## Size

Return the number of elements.

Time: O(1)

---

# Internal Implementations

## Circular Array

Array with two pointers (`front`, `rear`) that wrap around. Efficient O(1) enqueue and dequeue without shifting elements.

```
[ _ | 3 | 4 | 5 | _ | _ ]
       ↑           ↑
     front        rear
```

---

## Linked List

Enqueue at the tail, dequeue from the head. Both are O(1) with a tail pointer.

---

## Language Idioms

**Java**

```java
Queue<Integer> queue = new LinkedList<>();
queue.offer(1);          // enqueue
int front = queue.peek();
int val   = queue.poll(); // dequeue
```

> For BFS, `ArrayDeque` is faster than `LinkedList`:

```java
Queue<Integer> queue = new ArrayDeque<>();
```

**Python**

```python
from collections import deque

queue = deque()
queue.append(1)       # enqueue (rear)
front = queue[0]      # peek
val = queue.popleft() # dequeue (front)
```

> Do not use a plain `list` for queues — `list.pop(0)` is O(n). Always use `collections.deque`.

**Go**

```go
// Go has no built-in queue. Use a slice with index tracking
// or the container/list package.

queue := []int{}
queue = append(queue, 1)         // enqueue
front := queue[0]                // peek
queue = queue[1:]                // dequeue
```

> For performance-critical code, maintain a `head` index instead of re-slicing to avoid O(n) shifting:

```go
head := 0
val := queue[head]
head++
```

---

# Types of Queues

## Simple Queue

Standard FIFO as described above.

---

## Circular Queue

The rear wraps around to the front when it reaches the end of the backing array. Avoids wasted space after dequeue operations.

```
Array size = 5

After several enqueues and dequeues:

[ 4 | 5 | _ | 2 | 3 ]
  ↑           ↑
 rear        front
```

---

## Double-Ended Queue (Deque)

Can insert and remove from **both ends**. See the Deque section below.

---

## Priority Queue

Elements are dequeued in order of their priority, not insertion order. Covered in the Priority Queue concept file.

---

# Deque (Double-Ended Queue)

A **Deque** supports push and pop at **both** the front and the back.

```
addFirst ← [ 1 | 2 | 3 | 4 ] → addLast
removeFirst ←                  → removeLast
```

This makes it the most flexible of the three structures — a deque can act as both a stack (using only one end) and a queue (using both ends).

---

## Language Idioms

**Java**

```java
Deque<Integer> deque = new ArrayDeque<>();

deque.offerFirst(1);   // add to front
deque.offerLast(2);    // add to back
deque.peekFirst();     // peek front
deque.peekLast();      // peek back
deque.pollFirst();     // remove from front
deque.pollLast();      // remove from back
```

**Python**

```python
from collections import deque

d = deque()
d.appendleft(1)   # add to front
d.append(2)       # add to back
d[0]              # peek front
d[-1]             # peek back
d.popleft()       # remove from front
d.pop()           # remove from back
```

**Go**

```go
// container/list provides a doubly linked list that can act as a deque.
import "container/list"

d := list.New()
d.PushFront(1)
d.PushBack(2)
d.Remove(d.Front())
d.Remove(d.Back())
```

---

## When Deques Are Used in Algorithms

| Algorithm | Deque Role |
|-----------|------------|
| Sliding Window Maximum | Maintain indices of candidates in decreasing order |
| BFS with priorities | Push high-priority items to front |
| Palindrome check | Compare characters from both ends |

---

# Complexity Summary

| Operation | Array Queue | Linked List Queue | Deque |
|-----------|-------------|-------------------|-------|
| Enqueue (rear) | O(1) amortized | O(1) | O(1) |
| Dequeue (front) | O(1) with head index | O(1) | O(1) |
| Peek | O(1) | O(1) | O(1) |
| isEmpty | O(1) | O(1) | O(1) |

---

# When to Use a Queue

Use a queue when:

- You need **BFS** (Breadth-First Search) traversal of a graph or tree.
- You need **level-order** traversal of a binary tree.
- You need to process items in **arrival order** (task scheduling, event simulation).
- You need a **sliding window** with both ends (use deque).
- You need to produce/consume items from a **buffer** (producer-consumer pattern).

---

# Common Mistakes

## Using `list.pop(0)` in Python

`list.pop(0)` is O(n) because it shifts all elements. Always use `collections.deque` with `popleft()` for O(1) front removal.

---

## Using `re-slicing` for Dequeue in Go

```go
queue = queue[1:]   ← O(1) for the slice header, but holds the original array in memory
```

For long-running programs, use an index pointer or `container/list` to avoid a memory leak.

---

## Confusing Queue and Stack Order

Queue → FIFO → process **oldest** element first.
Stack → LIFO → process **newest** element first.

BFS uses a **queue**. DFS uses a **stack** (or recursion).

---

# Key Takeaways

After studying this concept, you should understand:

- The FIFO principle and the four core queue operations.
- How queues differ from stacks and why the distinction matters for BFS vs DFS.
- How a deque generalizes both stack and queue.
- Python's `collections.deque` and why a plain list is wrong for queue operations.
- The Java `ArrayDeque` preference over `LinkedList` for BFS.

---

# Problems Covered

Queue and deque patterns appear in:

| Problem | Queue / Deque Role |
|---------|--------------------|
| BFS on binary trees | Level-order processing |
| Sliding Window Maximum | Monotonic deque |
| Rotting Oranges | Multi-source BFS |
