# Java DSA Cheatsheet

> Quick reference for data structures, algorithms, and patterns used in coding interviews.

---

## 📦 Core Data Structures

### ArrayList
```java
import java.util.ArrayList;

ArrayList<Integer> list = new ArrayList<>();
list.add(10);               // append
list.add(0, 99);            // insert at index
list.get(0);                // access
list.set(0, 42);            // update
list.remove(Integer.valueOf(10)); // remove by value
list.remove(0);             // remove by index
list.size();
list.contains(42);
list.indexOf(42);
Collections.sort(list);
Collections.reverse(list);
```

### LinkedList (as Deque / Queue / Stack)
```java
import java.util.LinkedList;
import java.util.ArrayDeque; // preferred over Stack

// Queue (FIFO)
ArrayDeque<Integer> queue = new ArrayDeque<>();
queue.offer(1);   // enqueue
queue.poll();     // dequeue (returns null if empty)
queue.peek();     // front without removing

// Stack (LIFO)
ArrayDeque<Integer> stack = new ArrayDeque<>();
stack.push(1);    // push to front
stack.pop();      // pop from front
stack.peek();     // top without removing
```

### HashMap
```java
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.get("a");                        // 1
map.getOrDefault("z", 0);           // 0
map.containsKey("a");
map.containsValue(1);
map.remove("a");
map.size();
map.putIfAbsent("b", 2);

// Iteration
for (Map.Entry<String, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + " -> " + e.getValue());
}
map.forEach((k, v) -> System.out.println(k + " -> " + v));
```

### HashSet
```java
import java.util.HashSet;

HashSet<Integer> set = new HashSet<>();
set.add(1);
set.contains(1);   // true
set.remove(1);
set.size();

// Set operations
set.addAll(other);       // union
set.retainAll(other);    // intersection
set.removeAll(other);    // difference
```

### TreeMap (sorted by key)
```java
import java.util.TreeMap;

TreeMap<String, Integer> tm = new TreeMap<>();
tm.put("b", 2); tm.put("a", 1);
tm.firstKey();   // "a"
tm.lastKey();    // "b"
tm.floorKey("c"); // greatest key <= "c"
tm.ceilingKey("b"); // smallest key >= "b"
// Iterates in natural key order
for (var entry : tm.entrySet()) { ... }
```

### PriorityQueue (Min-Heap by default)
```java
import java.util.PriorityQueue;

PriorityQueue<Integer> minPQ = new PriorityQueue<>();
PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

minPQ.offer(5); minPQ.offer(1); minPQ.offer(3);
minPQ.peek();   // 1 (min)
minPQ.poll();   // 1 (removes min)
minPQ.size();

// Custom comparator (e.g. sort int[] by first element)
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
```

---

## 🔢 Arrays

```java
import java.util.Arrays;

int[] arr = {3, 1, 4, 1, 5};
Arrays.sort(arr);                    // in-place sort O(n log n)
Arrays.sort(arr, 1, 4);              // sort subarray [1, 4)
int idx = Arrays.binarySearch(arr, 4); // O(log n), array must be sorted
Arrays.fill(arr, 0);                 // fill with value
int[] copy = Arrays.copyOf(arr, arr.length);
int[] slice = Arrays.copyOfRange(arr, 1, 4); // [1, 4)
Arrays.equals(arr, copy);

// 2D array
int[][] grid = new int[3][4];
int[][] matrix = {{1,2},{3,4},{5,6}};
```

---

## 🔤 Strings

```java
String s = "hello";
s.length();
s.charAt(2);           // 'l'
s.indexOf('l');        // 2
s.lastIndexOf('l');    // 3
s.substring(1, 3);     // "el"
s.toUpperCase();
s.toLowerCase();
s.trim();
s.strip();             // Unicode-aware trim
s.replace('l', 'r');
s.replaceAll("\\s+", " "); // regex replace
s.split(",");
s.contains("ell");
s.startsWith("he");
s.endsWith("lo");
s.equals("hello");     // use equals(), not ==
s.equalsIgnoreCase("HELLO");
String.valueOf(42);    // int → String
Integer.parseInt("42"); // String → int
char[] chars = s.toCharArray();
String joined = String.join(", ", "a", "b", "c"); // "a, b, c"

// Mutable string
StringBuilder sb = new StringBuilder();
sb.append("hi");
sb.insert(0, "say ");
sb.deleteCharAt(0);
sb.reverse();
sb.toString();
```

---

## ♻️ Sorting & Comparators

```java
import java.util.*;

// Sort list with lambda
List<int[]> pairs = new ArrayList<>();
pairs.sort((a, b) -> a[0] - b[0]);             // by first element asc
pairs.sort((a, b) -> b[1] - a[1]);             // by second element desc
pairs.sort(Comparator.comparingInt((int[] x) -> x[0])
           .thenComparingInt(x -> x[1]));       // chained

// Sort strings by length then lexicographically
List<String> words = new ArrayList<>();
words.sort(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));

// Implement Comparable on a class
class Point implements Comparable<Point> {
    int x, y;
    public int compareTo(Point o) { return this.x - o.x; }
}
```

---

## 🌊 Streams (Java 8+)

```java
import java.util.stream.*;

List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

// filter → map → collect
List<Integer> result = nums.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * 10)
    .collect(Collectors.toList());

// reduce
int sum = nums.stream().reduce(0, Integer::sum);

// count, min, max
long count = nums.stream().filter(n -> n > 3).count();
Optional<Integer> max = nums.stream().max(Comparator.naturalOrder());

// distinct, sorted, limit, skip
nums.stream().distinct().sorted().limit(3).collect(Collectors.toList());

// groupingBy
Map<Integer, List<String>> grouped = words.stream()
    .collect(Collectors.groupingBy(String::length));

// joining
String sentence = words.stream().collect(Collectors.joining(", "));

// int stream
int[] arr = {1, 2, 3};
IntStream.of(arr).sum();
IntStream.range(0, 5).forEach(System.out::println); // 0..4
IntStream.rangeClosed(1, 5);                         // 1..5
```

---

## 🧮 Math & Bit Tricks

```java
Math.max(a, b);
Math.min(a, b);
Math.abs(x);
Math.pow(2, 10);      // 1024.0
Math.sqrt(16);        // 4.0
Math.floor(3.7);      // 3.0
Math.ceil(3.2);       // 4.0
Integer.MAX_VALUE;    // 2^31 - 1
Integer.MIN_VALUE;

// Bit manipulation
n & 1          // is odd?
n >> 1         // n / 2
n << 1         // n * 2
n & (n - 1)    // clear lowest set bit
n ^ n          // 0
Integer.bitCount(n)          // count set bits
Integer.highestOneBit(n)
Integer.numberOfTrailingZeros(n)
```

---

## 📐 Common Patterns

### Two Pointers
```java
int l = 0, r = arr.length - 1;
while (l < r) {
    if (arr[l] + arr[r] == target) return new int[]{l, r};
    else if (arr[l] + arr[r] < target) l++;
    else r--;
}
```

### Sliding Window (variable)
```java
int l = 0, max = 0;
Map<Character, Integer> window = new HashMap<>();
for (int r = 0; r < s.length(); r++) {
    char c = s.charAt(r);
    window.merge(c, 1, Integer::sum);
    while (window.size() > k) {
        char lc = s.charAt(l++);
        window.merge(lc, -1, Integer::sum);
        if (window.get(lc) == 0) window.remove(lc);
    }
    max = Math.max(max, r - l + 1);
}
```

### Binary Search
```java
int lo = 0, hi = arr.length - 1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (arr[mid] == target) return mid;
    else if (arr[mid] < target) lo = mid + 1;
    else hi = mid - 1;
}
return -1;
```

### BFS (graph / grid)
```java
Queue<int[]> q = new ArrayDeque<>();
boolean[][] visited = new boolean[rows][cols];
q.offer(new int[]{startR, startC});
visited[startR][startC] = true;
int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

while (!q.isEmpty()) {
    int[] cur = q.poll();
    for (int[] d : dirs) {
        int nr = cur[0] + d[0], nc = cur[1] + d[1];
        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
            visited[nr][nc] = true;
            q.offer(new int[]{nr, nc});
        }
    }
}
```

### DFS (recursive)
```java
void dfs(int[][] grid, int r, int c) {
    if (r < 0 || r >= rows || c < 0 || c >= cols) return;
    if (visited[r][c]) return;
    visited[r][c] = true;
    for (int[] d : dirs) dfs(grid, r + d[0], c + d[1]);
}
```
