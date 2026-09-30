# Data Structures Cheatsheet: Java · Python · Go

> For each structure: what it is, when to use it, key API, and the translation across all three languages.

---

## ArrayList / Dynamic Array

**Use when:** you need ordered, index-based access with dynamic sizing.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `ArrayList<T>` | `list` | `[]T` (slice) |
| Create | `new ArrayList<>()` | `[]` | `[]int{}` / `make([]int, 0)` |
| Append | `list.add(x)` | `lst.append(x)` | `s = append(s, x)` |
| Get | `list.get(i)` | `lst[i]` | `s[i]` |
| Set | `list.set(i, x)` | `lst[i] = x` | `s[i] = x` |
| Remove by index | `list.remove(i)` | `lst.pop(i)` | `append(s[:i], s[i+1:]...)` |
| Size | `list.size()` | `len(lst)` | `len(s)` |
| Sort | `Collections.sort(list)` | `lst.sort()` | `sort.Ints(s)` |
| Contains | `list.contains(x)` | `x in lst` | *(linear scan)* |

---

## LinkedList

**Use when:** you need O(1) insertions/deletions at head or tail (queue/stack use cases).

> ⚠️ Java's `LinkedList` doubles as a `Deque`. Python has no built-in — use `collections.deque` for double-ended or implement `Node` manually. Go uses `container/list` or a slice for most interview purposes.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `LinkedList<T>` | `collections.deque` | `container/list` or `*Node` |
| Push front | `ll.addFirst(x)` | `dq.appendleft(x)` | `l.PushFront(x)` |
| Push back | `ll.addLast(x)` | `dq.append(x)` | `l.PushBack(x)` |
| Pop front | `ll.removeFirst()` | `dq.popleft()` | `l.Remove(l.Front())` |
| Pop back | `ll.removeLast()` | `dq.pop()` | `l.Remove(l.Back())` |
| Peek front | `ll.peekFirst()` | `dq[0]` | `l.Front().Value` |
| Size | `ll.size()` | `len(dq)` | `l.Len()` |

**Manual singly-linked node (interviews):**
```java
class ListNode { int val; ListNode next; ListNode(int v) { val = v; } }
```
```python
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val; self.next = next
```
```go
type ListNode struct { Val int; Next *ListNode }
```

---

## HashMap / Dictionary

**Use when:** you need O(1) key → value lookups. The single most-used structure in interviews.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `HashMap<K,V>` | `dict` | `map[K]V` |
| Create | `new HashMap<>()` | `{}` | `make(map[K]V)` |
| Put | `m.put(k, v)` | `m[k] = v` | `m[k] = v` |
| Get | `m.get(k)` | `m[k]` | `m[k]` *(zero val if missing)* |
| Safe get | `m.getOrDefault(k, 0)` | `m.get(k, 0)` | `if v, ok := m[k]; ok {...}` |
| Has key | `m.containsKey(k)` | `k in m` | `_, ok := m[k]` |
| Delete | `m.remove(k)` | `del m[k]` | `delete(m, k)` |
| Iterate | `m.entrySet()` | `m.items()` | `for k, v := range m` |
| Frequency map | `m.merge(k, 1, Integer::sum)` | `defaultdict(int)` | `m[k]++` |

---

## HashSet

**Use when:** you need O(1) membership tests or deduplication.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `HashSet<T>` | `set` | `map[T]struct{}` |
| Create | `new HashSet<>()` | `set()` or `{1,2}` | `make(map[T]struct{})` |
| Add | `s.add(x)` | `s.add(x)` | `s[x] = struct{}{}` |
| Contains | `s.contains(x)` | `x in s` | `_, ok := s[x]` |
| Remove | `s.remove(x)` | `s.discard(x)` | `delete(s, x)` |
| Size | `s.size()` | `len(s)` | `len(s)` |
| Union | `s.addAll(other)` | `s \| other` | *(manual loop)* |
| Intersection | `s.retainAll(other)` | `s & other` | *(manual loop)* |

---

## TreeMap / Sorted Map

**Use when:** you need a map sorted by key, or need `floor` / `ceiling` queries.

> ⚠️ Python and Go have no built-in TreeMap. Python uses `sorted(dict)` for sorted iteration. Go manually extracts and sorts keys. For interval or range queries in Go/Python, consider `sortedcontainers.SortedDict` (Python) or a Fenwick tree.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `TreeMap<K,V>` | `dict` + `sorted()` | `map[K]V` + `sort.Slice` |
| First key | `tm.firstKey()` | `min(d)` | `keys[0]` (after sort) |
| Last key | `tm.lastKey()` | `max(d)` | `keys[len-1]` |
| Floor key | `tm.floorKey(k)` | *(manual bisect)* | *(manual bisect)* |
| Ceiling key | `tm.ceilingKey(k)` | *(manual bisect)* | *(manual bisect)* |
| Sorted iter | `tm.entrySet()` | `for k in sorted(d)` | *(sorted keys loop)* |

---

## PriorityQueue / Heap

**Use when:** you need repeated access to the minimum (or maximum) element. Common in Dijkstra, K-th largest, merge intervals.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `PriorityQueue<T>` | `heapq` (list) | `container/heap` |
| Default order | min-heap | min-heap | defined by `Less()` |
| Create | `new PriorityQueue<>()` | `[]` + `heapify` | implement `heap.Interface` |
| Push | `pq.offer(x)` | `heapq.heappush(h, x)` | `heap.Push(h, x)` |
| Pop min | `pq.poll()` | `heapq.heappop(h)` | `heap.Pop(h)` |
| Peek min | `pq.peek()` | `h[0]` | `(*h)[0]` |
| Max-heap | `new PriorityQueue<>(reverseOrder())` | negate values | flip `Less()` |
| Size | `pq.size()` | `len(h)` | `h.Len()` |
| Custom key | `new PriorityQueue<>((a,b)->...)` | `heappush(h, (key, val))` | custom `Less()` |

---

## Stack

**Use when:** LIFO order — expression parsing, DFS iterative, monotonic stack problems.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `ArrayDeque<T>` *(preferred over Stack)* | `list` | `[]T` (slice) |
| Push | `stack.push(x)` | `stack.append(x)` | `s = append(s, x)` |
| Pop | `stack.pop()` | `stack.pop()` | `s[len-1]; s=s[:len-1]` |
| Peek | `stack.peek()` | `stack[-1]` | `s[len(s)-1]` |
| Empty | `stack.isEmpty()` | `not stack` | `len(s) == 0` |
| Size | `stack.size()` | `len(stack)` | `len(s)` |

---

## Queue / Deque

**Use when:** FIFO order — BFS, level-order traversal, sliding window maximum.

| | Java | Python | Go |
|-|------|--------|----|
| Type | `ArrayDeque<T>` | `collections.deque` | `[]T` (slice) |
| Enqueue | `q.offer(x)` | `q.append(x)` | `q = append(q, x)` |
| Dequeue | `q.poll()` | `q.popleft()` | `q[0]; q=q[1:]` |
| Peek front | `q.peek()` | `q[0]` | `q[0]` |
| Empty | `q.isEmpty()` | `not q` | `len(q) == 0` |
| Add front | `q.offerFirst(x)` | `q.appendleft(x)` | *(use container/list)* |
| Remove back | `q.pollLast()` | `q.pop()` | *(use container/list)* |

---

## Comparator (external / ad-hoc sort order)

**Use when:** you want to sort without changing the class, or need multiple different sort orders.

```java
// Java — lambda comparator
list.sort((a, b) -> a.age - b.age);
list.sort(Comparator.comparingInt(p -> p.age));
list.sort(Comparator.comparingInt(Person::getAge).reversed());
// Chain
list.sort(Comparator.comparingInt(Person::getAge)
                    .thenComparing(Person::getName));
```
```python
# Python — key function
lst.sort(key=lambda p: p.age)
lst.sort(key=lambda p: (p.age, p.name))
lst.sort(key=lambda p: p.age, reverse=True)
# cmp_to_key for true comparator
from functools import cmp_to_key
lst.sort(key=cmp_to_key(lambda a, b: a.age - b.age))
```
```go
// Go — sort.Slice / sort.SliceStable
sort.Slice(people, func(i, j int) bool {
    if people[i].Age != people[j].Age {
        return people[i].Age < people[j].Age
    }
    return people[i].Name < people[j].Name
})
```

---

## Comparable (natural / intrinsic sort order)

**Use when:** you want `sort()` to work on a class without passing a comparator every time.

```java
// Java — implement Comparable<T>
class Person implements Comparable<Person> {
    String name; int age;
    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}
Collections.sort(people);   // uses compareTo
```
```python
# Python — __lt__ + @total_ordering
from functools import total_ordering

@total_ordering
class Person:
    def __init__(self, name, age): self.name=name; self.age=age
    def __lt__(self, other): return self.age < other.age
    def __eq__(self, other): return self.age == other.age

people.sort()   # uses __lt__
```
```go
// Go — implement sort.Interface on a named type
type People []Person
func (p People) Len() int           { return len(p) }
func (p People) Less(i, j int) bool { return p[i].Age < p[j].Age }
func (p People) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }

sort.Sort(People(people))
```

---

## Streams / Functional Pipelines

**Use when:** you want concise filter → map → reduce operations without explicit loops.

> ⚠️ Java has full stream API. Python uses list comprehensions + `map`/`filter`. Go has no stream API — write explicit loops (idiomatic).

```java
// Java Streams
List<Integer> result = nums.stream()
    .filter(n -> n % 2 == 0)   // keep evens
    .map(n -> n * n)             // square
    .sorted()                    // sort
    .collect(Collectors.toList());

int sum   = nums.stream().mapToInt(Integer::intValue).sum();
long cnt  = nums.stream().filter(n -> n > 5).count();
Optional<Integer> max = nums.stream().max(Comparator.naturalOrder());
String s  = words.stream().collect(Collectors.joining(", "));

Map<Integer, List<String>> byLen = words.stream()
    .collect(Collectors.groupingBy(String::length));
```
```python
# Python — comprehensions (preferred)
result = [n*n for n in nums if n % 2 == 0]
result.sort()

total = sum(n for n in nums)
count = sum(1 for n in nums if n > 5)
maximum = max(nums)

# map / filter (functional style)
result = list(map(lambda n: n*n, filter(lambda n: n%2==0, nums)))

# groupBy equivalent
from collections import defaultdict
by_len = defaultdict(list)
for w in words: by_len[len(w)].append(w)
```
```go
// Go — explicit loop (idiomatic, no stream API)
result := []int{}
for _, n := range nums {
    if n%2 == 0 {
        result = append(result, n*n)
    }
}
sort.Ints(result)

total := 0
for _, n := range nums { total += n }
```

---

## Quick Comparison: When to Use What

| Structure | Best For | Java | Python | Go |
|-----------|----------|------|--------|----|
| ArrayList | Ordered list, index access | `ArrayList` | `list` | `[]T` |
| LinkedList | Queue/Deque, O(1) head/tail ops | `ArrayDeque` | `deque` | slice or `container/list` |
| HashMap | Key→value lookup, frequency | `HashMap` | `dict` | `map[K]V` |
| HashSet | Dedup, membership O(1) | `HashSet` | `set` | `map[K]struct{}` |
| TreeMap | Sorted keys, floor/ceiling | `TreeMap` | sorted dict | sorted keys slice |
| PriorityQueue | Min/max repeatedly, Dijkstra | `PriorityQueue` | `heapq` | `container/heap` |
| Stack | DFS iterative, matching parens | `ArrayDeque` (push/pop) | `list` | slice |
| Queue | BFS, level traversal | `ArrayDeque` (offer/poll) | `deque` | slice |
| Comparator | Ad-hoc sort order | lambda / `Comparator` | `key=` / `cmp_to_key` | `sort.Slice` |
| Comparable | Natural ordering on type | `implements Comparable` | `@total_ordering` | `sort.Interface` |
| Streams | Functional pipelines | `.stream()` | comprehensions | explicit loop |
