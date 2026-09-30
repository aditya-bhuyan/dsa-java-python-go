# Syntax Comparison: Java · Python · Go

> Side-by-side reference for the most common constructs used in DSA problems.

---

## Variables & Types

| Concept | Java | Python | Go |
|---------|------|--------|----|
| Declare + assign | `int x = 5;` | `x = 5` | `x := 5` |
| Explicit type | `int x;` | *(dynamic)* | `var x int` |
| Constant | `final int N = 10;` | `N = 10` *(convention)* | `const N = 10` |
| Multiple assign | `int a=1, b=2;` | `a, b = 1, 2` | `a, b := 1, 2` |
| Swap | `int t=a; a=b; b=t;` | `a, b = b, a` | `a, b = b, a` |
| Null/nil/None | `null` | `None` | `nil` |
| Max int | `Integer.MAX_VALUE` | `float('inf')` | `math.MaxInt` |
| Min int | `Integer.MIN_VALUE` | `float('-inf')` | `math.MinInt` |

---

## Control Flow

### If / Else
```java
// Java
if (x > 0) {
    System.out.println("pos");
} else if (x < 0) {
    System.out.println("neg");
} else {
    System.out.println("zero");
}
```
```python
# Python
if x > 0:
    print("pos")
elif x < 0:
    print("neg")
else:
    print("zero")
```
```go
// Go
if x > 0 {
    fmt.Println("pos")
} else if x < 0 {
    fmt.Println("neg")
} else {
    fmt.Println("zero")
}
// Go: init statement in if
if v, ok := m[k]; ok {
    fmt.Println(v)
}
```

### For Loop

| Pattern | Java | Python | Go |
|---------|------|--------|----|
| Range | `for (int i=0; i<n; i++)` | `for i in range(n):` | `for i := 0; i < n; i++` |
| While | `while (cond) {}` | `while cond:` | `for cond {}` |
| Infinite | `while (true) {}` | `while True:` | `for {}` |
| For-each | `for (int x : arr)` | `for x in arr:` | `for _, x := range arr` |
| With index | `for (int i=0; i<arr.length; i++)` | `for i, x in enumerate(arr):` | `for i, x := range arr` |
| Reverse | `for (int i=n-1; i>=0; i--)` | `for i in range(n-1,-1,-1):` | `for i := n-1; i >= 0; i--` |

### Switch / Match
```java
// Java
switch (x) {
    case 1 -> System.out.println("one");
    case 2 -> System.out.println("two");
    default -> System.out.println("other");
}
```
```python
# Python 3.10+ (match)
match x:
    case 1: print("one")
    case 2: print("two")
    case _: print("other")
```
```go
// Go
switch x {
case 1:
    fmt.Println("one")
case 2:
    fmt.Println("two")
default:
    fmt.Println("other")
}
```

---

## Functions

```java
// Java
static int add(int a, int b) { return a + b; }
// Lambda
Function<Integer, Integer> double = x -> x * 2;
```
```python
# Python
def add(a, b):
    return a + b
# Lambda
double = lambda x: x * 2
# Default arg
def greet(name="World"): ...
# *args / **kwargs
def f(*args, **kwargs): ...
```
```go
// Go
func add(a, b int) int { return a + b }
// Multiple return values
func divide(a, b int) (int, error) { ... }
// Variadic
func sum(nums ...int) int { ... }
// Anonymous / closure
double := func(x int) int { return x * 2 }
// Recursive closure
var dfs func(n int) int
dfs = func(n int) int { return dfs(n-1) }
```

---

## Arrays / Slices / Lists

| Operation | Java | Python | Go |
|-----------|------|--------|----|
| Create | `int[] a = {1,2,3};` | `a = [1,2,3]` | `a := []int{1,2,3}` |
| Length | `a.length` | `len(a)` | `len(a)` |
| Access | `a[0]` | `a[0]` | `a[0]` |
| Append | `list.add(x)` | `a.append(x)` | `a = append(a, x)` |
| Slice | `Arrays.copyOfRange(a,1,4)` | `a[1:4]` | `a[1:4]` |
| Sort | `Arrays.sort(a)` | `a.sort()` | `sort.Ints(a)` |
| Fill | `Arrays.fill(a, 0)` | `[0]*n` | `make([]int, n)` |
| 2D | `int[][] g = new int[r][c];` | `[[0]*c for _ in range(r)]` | `make([][]int, r)` |

---

## HashMap / dict / map

| Operation | Java | Python | Go |
|-----------|------|--------|----|
| Create | `new HashMap<>()` | `{}` or `dict()` | `make(map[K]V)` |
| Set | `m.put(k, v)` | `m[k] = v` | `m[k] = v` |
| Get | `m.get(k)` | `m[k]` | `m[k]` |
| Default get | `m.getOrDefault(k, 0)` | `m.get(k, 0)` | `m[k]` *(zero value)* |
| Has key | `m.containsKey(k)` | `k in m` | `_, ok := m[k]` |
| Delete | `m.remove(k)` | `del m[k]` | `delete(m, k)` |
| Size | `m.size()` | `len(m)` | `len(m)` |
| Iterate | `m.entrySet()` | `m.items()` | `for k, v := range m` |

---

## String Operations

| Operation | Java | Python | Go |
|-----------|------|--------|----|
| Length | `s.length()` | `len(s)` | `len(s)` *(bytes)* |
| Char at | `s.charAt(i)` | `s[i]` | `s[i]` *(byte)* |
| Substring | `s.substring(1,4)` | `s[1:4]` | `s[1:4]` |
| Upper | `s.toUpperCase()` | `s.upper()` | `strings.ToUpper(s)` |
| Split | `s.split(",")` | `s.split(",")` | `strings.Split(s,",")` |
| Join | `String.join(",", list)` | `",".join(lst)` | `strings.Join(lst,",")` |
| Contains | `s.contains("x")` | `"x" in s` | `strings.Contains(s,"x")` |
| Equals | `s.equals(t)` | `s == t` | `s == t` |
| Reverse | `new StringBuilder(s).reverse()` | `s[::-1]` | *(manual loop)* |
| To int | `Integer.parseInt(s)` | `int(s)` | `strconv.Atoi(s)` |
| From int | `String.valueOf(n)` | `str(n)` | `strconv.Itoa(n)` |
| Mutable | `StringBuilder` | `list` + `join` | `strings.Builder` |

---

## Classes / Structs

```java
// Java
class Node {
    int val;
    Node next;
    Node(int val) { this.val = val; }
}
```
```python
# Python (dataclass)
from dataclasses import dataclass

@dataclass
class Node:
    val: int
    next: 'Node' = None

# Or plain class
class Node:
    def __init__(self, val, next=None):
        self.val = val
        self.next = next
```
```go
// Go
type Node struct {
    Val  int
    Next *Node
}
// Constructor (by convention)
func NewNode(val int) *Node { return &Node{Val: val} }
```

---

## Error Handling

```java
// Java
try {
    int x = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    System.out.println("bad input");
} finally {
    // always runs
}
```
```python
# Python
try:
    x = int("abc")
except ValueError as e:
    print("bad input")
finally:
    pass  # always runs
```
```go
// Go — no exceptions; errors are values
val, err := strconv.Atoi("abc")
if err != nil {
    fmt.Println("bad input")
}
// Panic / recover (rare; don't use in interviews)
defer func() {
    if r := recover(); r != nil { fmt.Println("recovered") }
}()
panic("oops")
```

---

## Interfaces / Duck Typing

```java
// Java
interface Printable { void print(); }
class Dog implements Printable {
    public void print() { System.out.println("Woof"); }
}
```
```python
# Python — duck typing, no interface keyword
class Dog:
    def print(self): print("Woof")
# Protocol (structural typing, Python 3.8+)
from typing import Protocol
class Printable(Protocol):
    def print(self) -> None: ...
```
```go
// Go — interfaces are implicit (structural)
type Printable interface { Print() }
type Dog struct{}
func (d Dog) Print() { fmt.Println("Woof") }
// Dog satisfies Printable automatically
var p Printable = Dog{}
```

---

## Generics (brief)

```java
// Java — generics on classes/methods
<T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```
```python
# Python — type hints (not enforced at runtime)
from typing import TypeVar, List
T = TypeVar('T')
def first(lst: List[T]) -> T:
    return lst[0]
```
```go
// Go 1.18+ generics
func Max[T constraints.Ordered](a, b T) T {
    if a > b { return a }
    return b
}
```

---

## Complexity Quick Reference

| Structure | Access | Search | Insert | Delete |
|-----------|--------|--------|--------|--------|
| Array / Slice | O(1) | O(n) | O(n) | O(n) |
| Dynamic Array (end) | O(1) | O(n) | O(1)* | O(1)* |
| LinkedList | O(n) | O(n) | O(1) | O(1) |
| HashMap / dict / map | — | O(1)* | O(1)* | O(1)* |
| HashSet | — | O(1)* | O(1)* | O(1)* |
| TreeMap / SortedMap | — | O(log n) | O(log n) | O(log n) |
| Binary Heap | O(1) peek | O(n) | O(log n) | O(log n) |
| Binary Search Tree | O(log n)* | O(log n)* | O(log n)* | O(log n)* |

`*` amortised or average case
