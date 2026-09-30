# DSA Progress Tracker

> **How to use this file**
> Check off each item as you complete it. Update the weekly status emoji as you go:
> 🔲 Not started · 🔄 In progress · ✅ Done

---

## 🗺️ Roadmap Overview

| Month | Focus | Weeks |
|-------|-------|-------|
| Month 1 | Core Data Structures | 1 – 4 |
| Month 2 | Trees & Graphs | 5 – 8 |
| Month 3 | Interview Patterns | 9 – 12 |

---

## 📅 Month 1 — Core Data Structures

---

### Week 1 — Arrays 🔲

**Goal:** Understand how arrays work in memory and master the foundational patterns that appear in almost every other topic.

#### Topics
- [ ] Time Complexity (Big-O review — O(1), O(n), O(n²), O(log n))
- [ ] Array Traversal (forward, backward, with index)
- [ ] Prefix Sum (build prefix array, range sum queries)
- [ ] Two Pointer (opposite ends, same direction fast/slow)
- [ ] Frequency Array (26-letter counts, bucket sort idea)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Two Sum | Easy | 🔲 |
| 2 | Best Time to Buy and Sell Stock | Easy | 🔲 |
| 3 | Remove Duplicates from Sorted Array | Easy | 🔲 |
| 4 | Move Zeroes | Easy | 🔲 |
| 5 | Rotate Array | Medium | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Write a prefix sum array from scratch in all 3 languages
> - Solve Two Sum in O(n) using a hash map (and explain why)
> - Implement two-pointer on a sorted array without looking it up

---

### Week 2 — Strings 🔲

**Goal:** Get comfortable with string immutability, character manipulation, and using hash maps to track character frequencies.

#### Topics
- [ ] String immutability and `StringBuilder` / `join` patterns
- [ ] Character arrays and `char ↔ int` conversions (`ord`, `charAt`, rune)
- [ ] HashMap for frequency counting
- [ ] Sliding window introduction (preview)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Valid Anagram | Easy | 🔲 |
| 2 | Longest Common Prefix | Easy | 🔲 |
| 3 | Reverse String | Easy | 🔲 |
| 4 | Valid Palindrome | Easy | 🔲 |
| 5 | Group Anagrams | Medium | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Build a character frequency map from a string in O(n) in all 3 languages
> - Explain why string concatenation in a loop is O(n²) in Java/Python
> - Solve Group Anagrams using a sorted string as a map key

---

### Week 3 — HashMap & HashSet 🔲

**Goal:** Master the two most important lookup structures. Almost every medium problem needs one of these.

#### Topics
- [ ] HashMap internals (hash function, collision, load factor)
- [ ] HashSet for deduplication and O(1) membership
- [ ] Counting patterns (`getOrDefault`, `Counter`, `map[k]++`)
- [ ] Two-pass vs. one-pass lookup

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Contains Duplicate | Easy | 🔲 |
| 2 | Happy Number | Easy | 🔲 |
| 3 | Isomorphic Strings | Easy | 🔲 |
| 4 | First Unique Character in a String | Easy | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Explain the difference between a HashMap and a HashSet
> - Detect a cycle using a HashSet (Happy Number pattern)
> - Implement a frequency map idiom from memory in all 3 languages

---

### Week 4 — Linked List 🔲

**Goal:** Understand pointer manipulation. This is tested heavily in interviews for both correctness and edge-case handling.

#### Topics
- [ ] Singly linked list — node definition, traversal, insertion, deletion
- [ ] Fast/slow pointer (Floyd's cycle detection)
- [ ] Reverse a linked list (iterative and recursive)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Reverse Linked List | Easy | 🔲 |
| 2 | Middle of the Linked List | Easy | 🔲 |
| 3 | Linked List Cycle (detection) | Easy | 🔲 |
| 4 | Merge Two Sorted Lists | Easy | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Reverse a singly linked list iteratively without looking it up
> - Explain the fast/slow pointer trick and why it detects cycles
> - Define a `ListNode` struct/class from scratch in all 3 languages

---

## 📅 Month 2 — Trees & Graphs

---

### Week 5 — Stack & Queue 🔲

**Goal:** Understand LIFO vs. FIFO and the classic problems they solve. Monotonic stack is a high-frequency interview pattern.

#### Topics
- [ ] Stack (LIFO) — `ArrayDeque`, `list`, slice
- [ ] Queue (FIFO) — `ArrayDeque.offer/poll`, `deque`, slice
- [ ] Monotonic stack pattern (next greater element family)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Valid Parentheses | Easy | 🔲 |
| 2 | Min Stack | Medium | 🔲 |
| 3 | Daily Temperatures | Medium | 🔲 |
| 4 | Next Greater Element I | Easy | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Implement a stack and queue using only slices/arrays (no library types)
> - Explain the monotonic stack pattern and identify when to use it
> - Solve Valid Parentheses without looking at any hints

---

### Week 6 — Trees 🔲

**Goal:** Master recursive tree thinking. Most tree problems are a one-liner once you see the recurrence.

#### Topics
- [ ] Binary tree node definition and terminology (height, depth, diameter)
- [ ] DFS — pre-order, in-order, post-order (recursive and iterative)
- [ ] BFS — level-order using a queue
- [ ] Recursive return value patterns (pass-down vs. return-up)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Maximum Depth of Binary Tree | Easy | 🔲 |
| 2 | Binary Tree Inorder Traversal | Easy | 🔲 |
| 3 | Same Tree | Easy | 🔲 |
| 4 | Symmetric Tree | Easy | 🔲 |
| 5 | Diameter of Binary Tree | Easy | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Write DFS and BFS on a binary tree without a template
> - Explain the difference between height and depth
> - Convert a recursive DFS solution to an iterative one using a stack

---

### Week 7 — Binary Search 🔲

**Goal:** Recognise when binary search applies beyond "find value in sorted array" — it applies to any monotonic decision function.

#### Topics
- [ ] Classic binary search (`lo <= hi` vs `lo < hi` invariants)
- [ ] Off-by-one: `mid = lo + (hi - lo) / 2`
- [ ] Binary search on answer space
- [ ] Finding first/last occurrence

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Binary Search | Easy | 🔲 |
| 2 | Search Insert Position | Easy | 🔲 |
| 3 | First Bad Version | Easy | 🔲 |
| 4 | Search in Rotated Sorted Array | Medium | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Write binary search from memory with correct boundary conditions
> - Solve First Bad Version without an off-by-one error
> - Explain how to determine which half is sorted in a rotated array

---

### Week 8 — Graphs 🔲

**Goal:** Apply BFS and DFS to 2D grids and adjacency-list graphs. Understand cycle detection and topological sort.

#### Topics
- [ ] Graph representations — adjacency list vs. adjacency matrix
- [ ] BFS on a graph (shortest path in unweighted graph)
- [ ] DFS on a graph (connected components, cycle detection)
- [ ] Topological sort (Kahn's algorithm / DFS with state)
- [ ] Union-Find (disjoint set union) — optional but useful

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Number of Islands | Medium | 🔲 |
| 2 | Clone Graph | Medium | 🔲 |
| 3 | Course Schedule | Medium | 🔲 |
| 4 | Flood Fill | Easy | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Write BFS and DFS for a 2D grid problem from scratch
> - Detect a cycle in a directed graph using DFS with a color/state array
> - Explain topological sort and implement it for Course Schedule

---

## 📅 Month 3 — Interview Patterns

---

### Week 9 — Sliding Window 🔲

**Goal:** Learn to eliminate the O(n²) brute-force on subarray/substring problems by maintaining a window that shrinks and grows in O(n).

#### Topics
- [ ] Fixed-size window (sum, average, max)
- [ ] Variable-size window (expand right, shrink left)
- [ ] Window with a hash map (character frequency tracking)
- [ ] "At most K" trick for counting subarrays

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Maximum Average Subarray I | Easy | 🔲 |
| 2 | Longest Substring Without Repeating Characters | Medium | 🔲 |
| 3 | Minimum Window Substring | Hard | 🔲 |
| 4 | Permutation in String | Medium | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Distinguish a fixed-size window problem from a variable-size one on sight
> - Solve Longest Substring Without Repeating Characters in O(n) from memory
> - Explain why the left pointer never moves backwards (amortised O(n))

---

### Week 10 — Dynamic Programming 🔲

**Goal:** Stop seeing DP as magic. Learn to identify overlapping subproblems, write the recurrence, and choose between memoization and tabulation.

#### Topics
- [ ] Optimal substructure and overlapping subproblems
- [ ] Memoization (top-down recursion + cache)
- [ ] Tabulation (bottom-up DP array)
- [ ] Space optimisation (rolling array)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Climbing Stairs | Easy | 🔲 |
| 2 | House Robber | Medium | 🔲 |
| 3 | Coin Change | Medium | 🔲 |
| 4 | Longest Increasing Subsequence | Medium | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Write the recurrence for Climbing Stairs and Coin Change without hints
> - Implement both memoization and tabulation versions of House Robber
> - Explain why greedy fails for Coin Change but DP works

---

### Week 11 — Greedy 🔲

**Goal:** Recognise the class of problems where always making the locally optimal choice leads to a globally optimal solution — and understand when it doesn't.

#### Topics
- [ ] Greedy choice property and proof sketch (exchange argument)
- [ ] Interval scheduling intuition
- [ ] When greedy fails (counterexamples)

#### Problems
| # | Problem | Difficulty | Status |
|---|---------|------------|--------|
| 1 | Jump Game | Medium | 🔲 |
| 2 | Gas Station | Medium | 🔲 |
| 3 | Assign Cookies | Easy | 🔲 |

#### Landmark ✅
> Before moving on, you should be able to:
> - Articulate why Jump Game works with a greedy "max reach" approach
> - Explain the circular array trick used in Gas Station
> - Give an example where greedy gives the wrong answer (e.g. Coin Change with non-standard coins)

---

### Week 12 — Mixed Interview Questions 🔲

**Goal:** Simulate real interview conditions. No topic hints — figure out the pattern yourself, explain your approach before coding, and handle follow-up questions.

#### Practice Topics (rotate through each day)
- [ ] Trees
- [ ] Graphs
- [ ] Dynamic Programming
- [ ] Sliding Window
- [ ] Binary Search
- [ ] Arrays
- [ ] Strings

#### Weekly Challenge
Solve **2–3 random medium problems** from the list below. Set a 30-minute timer. Do not look at hints.

| Problem | Topic | Status |
|---------|-------|--------|
| Number of Islands | Graphs | 🔲 |
| Coin Change | DP | 🔲 |
| Longest Substring Without Repeating Characters | Sliding Window | 🔲 |
| Diameter of Binary Tree | Trees | 🔲 |
| Search in Rotated Sorted Array | Binary Search | 🔲 |
| Group Anagrams | Strings | 🔲 |
| Course Schedule | Graphs | 🔲 |
| House Robber | DP | 🔲 |
| Permutation in String | Sliding Window | 🔲 |
| Clone Graph | Graphs | 🔲 |
| Longest Increasing Subsequence | DP | 🔲 |
| Rotate Array | Arrays | 🔲 |
| Symmetric Tree | Trees | 🔲 |
| Minimum Window Substring | Sliding Window | 🔲 |
| First Bad Version | Binary Search | 🔲 |

#### Final Landmark ✅
> You are interview-ready when you can:
> - Identify the pattern (sliding window / DP / BFS / etc.) within 2 minutes of reading a problem
> - State the brute-force solution and its complexity before optimising
> - Arrive at an optimal solution under 30 minutes for a medium problem
> - Explain every line of your code and handle edge cases when asked
> - Answer the 6 interview questions in [`interview-notes/notes.md`](interview-notes/notes.md) for your chosen topic

---

## 📊 Overall Progress

| Week | Topic | Status |
|------|-------|--------|
| Week 1 | Arrays | 🔲 |
| Week 2 | Strings | 🔲 |
| Week 3 | HashMap & HashSet | 🔲 |
| Week 4 | Linked List | 🔲 |
| Week 5 | Stack & Queue | 🔲 |
| Week 6 | Trees | 🔲 |
| Week 7 | Binary Search | 🔲 |
| Week 8 | Graphs | 🔲 |
| Week 9 | Sliding Window | 🔲 |
| Week 10 | Dynamic Programming | 🔲 |
| Week 11 | Greedy | 🔲 |
| Week 12 | Mixed Interview Practice | 🔲 |

---

## 📁 Resources

| File | Purpose |
|------|---------|
| [`cheatsheets/java.md`](cheatsheets/java.md) | Java DSA API reference |
| [`cheatsheets/python.md`](cheatsheets/python.md) | Python DSA API reference |
| [`cheatsheets/go.md`](cheatsheets/go.md) | Go DSA API reference |
| [`cheatsheets/syntax-comparison.md`](cheatsheets/syntax-comparison.md) | Side-by-side syntax table |
| [`cheatsheets/data-structures.md`](cheatsheets/data-structures.md) | Data structure translation challenge |
| [`interview-notes/notes.md`](interview-notes/notes.md) | Interview questions + weekly random practice |
