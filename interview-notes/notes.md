# Interview Notes & Practice Challenges

> **How to use this file**
> Pick 2–3 problems per week at random. Attempt each problem from scratch before looking at any hints. After solving, answer the interview questions out loud as if you were in a real interview.

---

## 🌳 Trees

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Maximum Depth of Binary Tree | Easy | [#104](https://leetcode.com/problems/maximum-depth-of-binary-tree/) |
| 2 | Same Tree | Easy | [#100](https://leetcode.com/problems/same-tree/) |
| 3 | Symmetric Tree | Easy | [#101](https://leetcode.com/problems/symmetric-tree/) |
| 4 | Binary Tree Inorder Traversal | Easy | [#94](https://leetcode.com/problems/binary-tree-inorder-traversal/) |
| 5 | Diameter of Binary Tree | Easy | [#543](https://leetcode.com/problems/diameter-of-binary-tree/) |

### Interview Questions
1. **What is the difference between DFS and BFS on a tree? When would you prefer one over the other?**
2. **Explain the difference between pre-order, in-order, and post-order traversal. What does in-order traversal of a BST give you?**
3. **What is the time and space complexity of a recursive DFS traversal on a balanced binary tree with `n` nodes?**
4. **How would you find the lowest common ancestor (LCA) of two nodes in a binary tree without parent pointers?**
5. **Can you convert a recursive DFS solution to an iterative one? Walk me through how you'd do it for inorder traversal.**
6. **What is the height vs. depth of a node? What is the diameter of a tree?**

---

## 🕸️ Graphs

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Number of Islands | Medium | [#200](https://leetcode.com/problems/number-of-islands/) |
| 2 | Flood Fill | Easy | [#733](https://leetcode.com/problems/flood-fill/) |
| 3 | Clone Graph | Medium | [#133](https://leetcode.com/problems/clone-graph/) |
| 4 | Course Schedule | Medium | [#207](https://leetcode.com/problems/course-schedule/) |
| 5 | Rotting Oranges | Medium | [#994](https://leetcode.com/problems/rotting-oranges/) |

### Interview Questions
1. **What is the difference between an adjacency list and an adjacency matrix? When would you use each?**
2. **How do you detect a cycle in a directed graph? How does that differ from detecting a cycle in an undirected graph?**
3. **Explain topological sort. What algorithm would you use and what problem structure does it require?**
4. **When does BFS give you the shortest path, and when doesn't it? What about Dijkstra's?**
5. **What does the `visited` set prevent in a graph traversal? What could go wrong without it?**
6. **How would you approach a "number of connected components" problem? What data structure alternatives exist (e.g., Union-Find)?**

---

## 📐 Dynamic Programming

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Climbing Stairs | Easy | [#70](https://leetcode.com/problems/climbing-stairs/) |
| 2 | House Robber | Medium | [#198](https://leetcode.com/problems/house-robber/) |
| 3 | Coin Change | Medium | [#322](https://leetcode.com/problems/coin-change/) |
| 4 | Longest Increasing Subsequence | Medium | [#300](https://leetcode.com/problems/longest-increasing-subsequence/) |
| 5 | Unique Paths | Medium | [#62](https://leetcode.com/problems/unique-paths/) |

### Interview Questions
1. **What is the difference between memoization (top-down) and tabulation (bottom-up)? What are the trade-offs?**
2. **How do you identify that a problem has "optimal substructure" and "overlapping subproblems"?**
3. **Walk me through your thought process when approaching a new DP problem. Where do you start?**
4. **In Coin Change, why is greedy not guaranteed to work, but DP is?**
5. **What is the time and space complexity of the LIS solution using a simple DP array vs. the patience sort / binary search approach?**
6. **How would you reconstruct the actual path/sequence (not just the count or min) from a DP solution?**

---

## 🪟 Sliding Window

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Maximum Average Subarray I | Easy | [#643](https://leetcode.com/problems/maximum-average-subarray-i/) |
| 2 | Longest Substring Without Repeating Characters | Medium | [#3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) |
| 3 | Permutation in String | Medium | [#567](https://leetcode.com/problems/permutation-in-string/) |
| 4 | Minimum Window Substring | Hard | [#76](https://leetcode.com/problems/minimum-window-substring/) |
| 5 | Longest Repeating Character Replacement | Medium | [#424](https://leetcode.com/problems/longest-repeating-character-replacement/) |

### Interview Questions
1. **When is the sliding window pattern applicable? What properties must the problem have?**
2. **What is the difference between a fixed-size window and a variable-size (shrink/expand) window?**
3. **In the "Longest Substring Without Repeating Characters" problem, why do we use a HashMap instead of just a Set?**
4. **How do you decide when to shrink the left pointer vs. expand the right pointer?**
5. **What is the time complexity of a sliding window approach compared to a brute-force O(n²) scan?**
6. **How would you adapt the sliding window for a problem that counts subarrays meeting a condition (e.g., "at most k distinct characters")?**

---

## 🔍 Binary Search

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Binary Search | Easy | [#704](https://leetcode.com/problems/binary-search/) |
| 2 | Search Insert Position | Easy | [#35](https://leetcode.com/problems/search-insert-position/) |
| 3 | First Bad Version | Easy | [#278](https://leetcode.com/problems/first-bad-version/) |
| 4 | Search in Rotated Sorted Array | Medium | [#33](https://leetcode.com/problems/search-in-rotated-sorted-array/) |
| 5 | Find Minimum in Rotated Sorted Array | Medium | [#153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) |

### Interview Questions
1. **What are the two common ways to write binary search (`lo <= hi` vs. `lo < hi`)? What are the invariants for each?**
2. **Why is `mid = lo + (hi - lo) / 2` preferred over `mid = (lo + hi) / 2` in some languages?**
3. **How does binary search apply to problems that are not strictly "find a value in an array"? Give an example (e.g., search on answer space).**
4. **In Search in Rotated Sorted Array, how do you determine which half is sorted?**
5. **What is the time complexity of binary search, and why is the space complexity O(1) for the iterative version but O(log n) for recursive?**
6. **How would you use binary search to find the first/last occurrence of a duplicate value in a sorted array?**

---

## 📦 Arrays

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Two Sum | Easy | [#1](https://leetcode.com/problems/two-sum/) |
| 2 | Best Time to Buy and Sell Stock | Easy | [#121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) |
| 3 | Move Zeroes | Easy | [#283](https://leetcode.com/problems/move-zeroes/) |
| 4 | Remove Duplicates from Sorted Array | Easy | [#26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) |
| 5 | Rotate Array | Medium | [#189](https://leetcode.com/problems/rotate-array/) |

### Interview Questions
1. **Two Sum can be solved in O(n²) with brute force and O(n) with a hash map. Walk me through both and explain the space trade-off.**
2. **What is the two-pointer technique? When is it applicable and what does it require of the input?**
3. **In Rotate Array, what are the three approaches (extra array, cyclic replacements, reversal)? What are their space complexities?**
4. **What does it mean to modify an array "in-place"? Why do interviewers care about this?**
5. **How would you find the maximum subarray sum (Kadane's algorithm)? What is the recurrence?**
6. **What is the difference between a prefix sum and a running total? When is a prefix sum array useful?**

---

## 🔤 Strings

### Problems
| # | Problem | Difficulty | LeetCode |
|---|---------|------------|----------|
| 1 | Reverse String | Easy | [#344](https://leetcode.com/problems/reverse-string/) |
| 2 | Valid Palindrome | Easy | [#125](https://leetcode.com/problems/valid-palindrome/) |
| 3 | Valid Anagram | Easy | [#242](https://leetcode.com/problems/valid-anagram/) |
| 4 | Longest Common Prefix | Easy | [#14](https://leetcode.com/problems/longest-common-prefix/) |
| 5 | Group Anagrams | Medium | [#49](https://leetcode.com/problems/group-anagrams/) |

### Interview Questions
1. **Strings are immutable in Java and Python. How does that affect the time complexity of repeated string concatenation inside a loop?**
2. **What is the difference between checking if two strings are anagrams using sorting vs. a frequency count map? Compare time and space.**
3. **How does the KMP (Knuth-Morris-Pratt) algorithm improve on the naive O(n·m) substring search? What is its time complexity?**
4. **How would you check if a string is a palindrome using two pointers? What edge cases do you need to handle?**
5. **In Group Anagrams, the key insight is using a sorted string (or character count tuple) as a hash map key. Why does sorting work as a canonical form?**
6. **What is the difference between ASCII character checks and Unicode-aware string operations? When does it matter in interview problems?**

---

## 🎲 Weekly Random Practice

Pick 2–3 problems below at random each week. Set a 25–30 minute timer per problem.

| Week | Problem 1 | Problem 2 | Problem 3 |
|------|-----------|-----------|-----------|
| 1 | Number of Islands | Coin Change | Longest Substring Without Repeating Characters |
| 2 | Diameter of Binary Tree | Search in Rotated Sorted Array | Group Anagrams |
| 3 | Course Schedule | House Robber | Permutation in String |
| 4 | Clone Graph | Longest Increasing Subsequence | Rotate Array |
| 5 | Symmetric Tree | Minimum Window Substring | First Bad Version |

> **After each session:** Write a 2–3 sentence summary of what pattern you used, where you got stuck, and what you'd do differently.

---

## ✅ Self-Assessment Checklist

Before moving on from any topic, make sure you can:

- [ ] Solve an Easy problem in the topic under 15 minutes
- [ ] Solve a Medium problem in the topic under 30 minutes
- [ ] State the time and space complexity of your solution correctly
- [ ] Explain your approach out loud before writing any code
- [ ] Identify at least one edge case and handle it
- [ ] Refactor your solution if asked to optimise it

---

## 🔁 Data Structure Translation Challenge

> **Challenge rules**
> For each data structure below, implement the same small task in **Java → Python → Go** — from scratch, no copy-paste.
> The task for each structure is shown in the description column.
> Focus on idiomatic usage: what the language calls the type, how you initialise it, and the key API methods.

---

### ArrayList / Dynamic Array
> **Task:** Create a list of integers `[3, 1, 4, 1, 5]`, append `9`, remove the element at index 2, then print the final list.

<details>
<summary>Java</summary>

```java
import java.util.ArrayList;

ArrayList<Integer> list = new ArrayList<>();
list.add(3); list.add(1); list.add(4); list.add(1); list.add(5);
list.add(9);
list.remove(2);           // removes element at index 2
System.out.println(list); // [3, 1, 1, 5, 9]
```
</details>

<details>
<summary>Python</summary>

```python
lst = [3, 1, 4, 1, 5]
lst.append(9)
lst.pop(2)       # removes element at index 2
print(lst)       # [3, 1, 1, 5, 9]
```
</details>

<details>
<summary>Go</summary>

```go
lst := []int{3, 1, 4, 1, 5}
lst = append(lst, 9)
lst = append(lst[:2], lst[3:]...)  // remove index 2
fmt.Println(lst)                   // [3 1 1 5 9]
```
</details>

---

### LinkedList
> **Task:** Build a singly linked list `1 → 2 → 3`, then traverse and print each node value.

<details>
<summary>Java</summary>

```java
import java.util.LinkedList;

LinkedList<Integer> ll = new LinkedList<>();
ll.add(1); ll.add(2); ll.add(3);
for (int val : ll) System.out.println(val);
```
</details>

<details>
<summary>Python</summary>

```python
# Python has no built-in linked list — implement the node yourself
class Node:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next

head = Node(1, Node(2, Node(3)))
cur = head
while cur:
    print(cur.val)
    cur = cur.next
```
</details>

<details>
<summary>Go</summary>

```go
type Node struct {
    Val  int
    Next *Node
}

head := &Node{1, &Node{2, &Node{3, nil}}}
for cur := head; cur != nil; cur = cur.Next {
    fmt.Println(cur.Val)
}
```
</details>

---

### HashMap / Dictionary
> **Task:** Count the frequency of each character in the string `"hello"`.

<details>
<summary>Java</summary>

```java
import java.util.HashMap;

HashMap<Character, Integer> freq = new HashMap<>();
for (char c : "hello".toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
System.out.println(freq); // {h=1, e=1, l=2, o=1}
```
</details>

<details>
<summary>Python</summary>

```python
from collections import defaultdict

freq = defaultdict(int)
for c in "hello":
    freq[c] += 1
print(dict(freq))  # {'h': 1, 'e': 1, 'l': 2, 'o': 1}
```
</details>

<details>
<summary>Go</summary>

```go
freq := make(map[rune]int)
for _, c := range "hello" {
    freq[c]++
}
fmt.Println(freq) // map[e:1 h:1 l:2 o:1]
```
</details>

---

### HashSet
> **Task:** Given `[1, 2, 2, 3, 3, 3]`, store only unique values and check if `4` is present.

<details>
<summary>Java</summary>

```java
import java.util.HashSet;

HashSet<Integer> set = new HashSet<>();
for (int n : new int[]{1, 2, 2, 3, 3, 3}) set.add(n);
System.out.println(set.contains(4)); // false
```
</details>

<details>
<summary>Python</summary>

```python
s = set([1, 2, 2, 3, 3, 3])
print(4 in s)  # False
```
</details>

<details>
<summary>Go</summary>

```go
// Go has no built-in set — use map[T]struct{}
set := make(map[int]struct{})
for _, n := range []int{1, 2, 2, 3, 3, 3} {
    set[n] = struct{}{}
}
_, exists := set[4]
fmt.Println(exists) // false
```
</details>

---

### TreeMap / Sorted Map
> **Task:** Insert `{"banana": 2, "apple": 5, "cherry": 1}` and iterate in **sorted key order**.

<details>
<summary>Java</summary>

```java
import java.util.TreeMap;

TreeMap<String, Integer> tm = new TreeMap<>();
tm.put("banana", 2); tm.put("apple", 5); tm.put("cherry", 1);
for (var entry : tm.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}
// apple=5, banana=2, cherry=1
```
</details>

<details>
<summary>Python</summary>

```python
# Python dicts are insertion-ordered; use sorted() to iterate by key
d = {"banana": 2, "apple": 5, "cherry": 1}
for k in sorted(d):
    print(k, d[k])
# apple 5, banana 2, cherry 1
```
</details>

<details>
<summary>Go</summary>

```go
import "sort"

m := map[string]int{"banana": 2, "apple": 5, "cherry": 1}
keys := make([]string, 0, len(m))
for k := range m { keys = append(keys, k) }
sort.Strings(keys)
for _, k := range keys { fmt.Println(k, m[k]) }
// apple 5, banana 2, cherry 1
```
</details>

---

### PriorityQueue / Min-Heap
> **Task:** Insert `[5, 1, 3, 2, 4]` into a min-heap and extract elements one by one in sorted order.

<details>
<summary>Java</summary>

```java
import java.util.PriorityQueue;

PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int n : new int[]{5, 1, 3, 2, 4}) pq.add(n);
while (!pq.isEmpty()) System.out.print(pq.poll() + " ");
// 1 2 3 4 5
```
</details>

<details>
<summary>Python</summary>

```python
import heapq

heap = [5, 1, 3, 2, 4]
heapq.heapify(heap)
while heap:
    print(heapq.heappop(heap), end=" ")
# 1 2 3 4 5
```
</details>

<details>
<summary>Go</summary>

```go
import "container/heap"

type MinHeap []int
func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x any)        { *h = append(*h, x.(int)) }
func (h *MinHeap) Pop() any          { old := *h; n := len(old); x := old[n-1]; *h = old[:n-1]; return x }

h := &MinHeap{5, 1, 3, 2, 4}
heap.Init(h)
for h.Len() > 0 { fmt.Print(heap.Pop(h), " ") }
// 1 2 3 4 5
```
</details>

---

### Stack
> **Task:** Push `1, 2, 3` onto a stack, then pop and print each element (LIFO order).

<details>
<summary>Java</summary>

```java
import java.util.ArrayDeque;

ArrayDeque<Integer> stack = new ArrayDeque<>();
stack.push(1); stack.push(2); stack.push(3);
while (!stack.isEmpty()) System.out.println(stack.pop());
// 3, 2, 1
```
</details>

<details>
<summary>Python</summary>

```python
stack = []
stack.append(1); stack.append(2); stack.append(3)
while stack:
    print(stack.pop())
# 3, 2, 1
```
</details>

<details>
<summary>Go</summary>

```go
// Use a slice as a stack
stack := []int{}
stack = append(stack, 1, 2, 3)
for len(stack) > 0 {
    n := len(stack) - 1
    fmt.Println(stack[n])
    stack = stack[:n]
}
// 3, 2, 1
```
</details>

---

### Queue
> **Task:** Enqueue `1, 2, 3` into a queue, then dequeue and print each element (FIFO order).

<details>
<summary>Java</summary>

```java
import java.util.ArrayDeque;

ArrayDeque<Integer> queue = new ArrayDeque<>();
queue.offer(1); queue.offer(2); queue.offer(3);
while (!queue.isEmpty()) System.out.println(queue.poll());
// 1, 2, 3
```
</details>

<details>
<summary>Python</summary>

```python
from collections import deque

queue = deque()
queue.append(1); queue.append(2); queue.append(3)
while queue:
    print(queue.popleft())
# 1, 2, 3
```
</details>

<details>
<summary>Go</summary>

```go
// Use a slice as a queue (simple version)
queue := []int{1, 2, 3}
for len(queue) > 0 {
    fmt.Println(queue[0])
    queue = queue[1:]
}
// 1, 2, 3
```
</details>

---

### Comparator (custom sort order)
> **Task:** Sort `["banana", "fig", "apple", "kiwi"]` by **string length** ascending (shortest first).

<details>
<summary>Java</summary>

```java
import java.util.*;

List<String> fruits = new ArrayList<>(List.of("banana", "fig", "apple", "kiwi"));
fruits.sort(Comparator.comparingInt(String::length));
System.out.println(fruits); // [fig, kiwi, apple, banana]
```
</details>

<details>
<summary>Python</summary>

```python
fruits = ["banana", "fig", "apple", "kiwi"]
fruits.sort(key=len)
print(fruits)  # ['fig', 'kiwi', 'apple', 'banana']
```
</details>

<details>
<summary>Go</summary>

```go
import "sort"

fruits := []string{"banana", "fig", "apple", "kiwi"}
sort.Slice(fruits, func(i, j int) bool {
    return len(fruits[i]) < len(fruits[j])
})
fmt.Println(fruits) // [fig kiwi apple banana]
```
</details>

---

### Comparable (natural sort order on a custom type)
> **Task:** Define a `Person` struct/class with `name` and `age`. Sort a list of people by age ascending using the type's natural ordering.

<details>
<summary>Java</summary>

```java
import java.util.*;

class Person implements Comparable<Person> {
    String name; int age;
    Person(String name, int age) { this.name = name; this.age = age; }

    @Override
    public int compareTo(Person other) { return Integer.compare(this.age, other.age); }

    @Override
    public String toString() { return name + "(" + age + ")"; }
}

List<Person> people = new ArrayList<>(List.of(
    new Person("Alice", 30), new Person("Bob", 25), new Person("Carol", 35)
));
Collections.sort(people);
System.out.println(people); // [Bob(25), Alice(30), Carol(35)]
```
</details>

<details>
<summary>Python</summary>

```python
from functools import total_ordering

@total_ordering
class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age
    def __lt__(self, other):
        return self.age < other.age
    def __eq__(self, other):
        return self.age == other.age
    def __repr__(self):
        return f"{self.name}({self.age})"

people = [Person("Alice", 30), Person("Bob", 25), Person("Carol", 35)]
people.sort()
print(people)  # [Bob(25), Alice(30), Carol(35)]
```
</details>

<details>
<summary>Go</summary>

```go
import "sort"

type Person struct { Name string; Age int }

type ByAge []Person
func (a ByAge) Len() int           { return len(a) }
func (a ByAge) Less(i, j int) bool { return a[i].Age < a[j].Age }
func (a ByAge) Swap(i, j int)      { a[i], a[j] = a[j], a[i] }

people := []Person{{"Alice", 30}, {"Bob", 25}, {"Carol", 35}}
sort.Sort(ByAge(people))
fmt.Println(people) // [{Bob 25} {Alice 30} {Carol 35}]
```
</details>

---

### Streams / Functional Pipeline
> **Task:** Given `[1, 2, 3, 4, 5, 6]`, keep only even numbers, multiply each by 10, and collect the result into a new list.

<details>
<summary>Java</summary>

```java
import java.util.*;
import java.util.stream.*;

List<Integer> result = List.of(1, 2, 3, 4, 5, 6)
    .stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 10)
    .collect(Collectors.toList());
System.out.println(result); // [20, 40, 60]
```
</details>

<details>
<summary>Python</summary>

```python
nums = [1, 2, 3, 4, 5, 6]

# Option A — list comprehension (idiomatic)
result = [n * 10 for n in nums if n % 2 == 0]

# Option B — filter + map (functional style)
result = list(map(lambda n: n * 10, filter(lambda n: n % 2 == 0, nums)))

print(result)  # [20, 40, 60]
```
</details>

<details>
<summary>Go</summary>

```go
// Go has no built-in stream API — use explicit loops or a helper slice
nums := []int{1, 2, 3, 4, 5, 6}
result := []int{}
for _, n := range nums {
    if n%2 == 0 {
        result = append(result, n*10)
    }
}
fmt.Println(result) // [20 40 60]
```
</details>

---
