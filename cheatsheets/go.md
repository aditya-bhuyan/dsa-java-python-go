# Go DSA Cheatsheet

> Quick reference for data structures, algorithms, and patterns used in coding interviews.
> Go has no generics-based collections stdlib (pre-1.21) — most structures are built from slices and maps.

---

## 📦 Core Data Structures

### Slice (Dynamic Array)
```go
// Declaration
var s []int
s := []int{}
s := []int{1, 2, 3}
s := make([]int, 5)        // len=5, cap=5, zeroed
s := make([]int, 0, 10)    // len=0, cap=10

// Operations
s = append(s, 4)           // O(1) amortised
s = append(s, 1, 2, 3)     // append multiple
s = append(s, other...)    // spread slice
s[0]                       // access
s[1:4]                     // slice [1, 4)
len(s)
cap(s)

// Remove element at index i (order preserved)
s = append(s[:i], s[i+1:]...)

// Remove element at index i (order NOT preserved — faster)
s[i] = s[len(s)-1]
s = s[:len(s)-1]

// Copy
dst := make([]int, len(s))
copy(dst, s)

// 2D slice
grid := make([][]int, rows)
for i := range grid { grid[i] = make([]int, cols) }
```

### Stack (slice-based)
```go
stack := []int{}

// Push
stack = append(stack, val)

// Pop
top := stack[len(stack)-1]
stack = stack[:len(stack)-1]

// Peek
stack[len(stack)-1]

// Empty check
len(stack) == 0
```

### Queue (slice-based)
```go
queue := []int{}

// Enqueue
queue = append(queue, val)

// Dequeue
front := queue[0]
queue = queue[1:]

// Peek
queue[0]

// Note: for high-performance queues use container/list or a circular buffer
```

### map (HashMap / HashSet)
```go
// HashMap
m := make(map[string]int)
m := map[string]int{"a": 1, "b": 2}

m["key"] = 1
val := m["key"]              // 0 if missing
val, ok := m["key"]          // ok = false if missing
delete(m, "key")
len(m)

// Safe read with default
if v, ok := m[k]; ok {
    // use v
} else {
    // missing
}

// Iteration (unordered)
for k, v := range m {
    fmt.Println(k, v)
}

// HashSet (no built-in set type)
set := make(map[int]struct{})
set[1] = struct{}{}          // add
_, exists := set[1]          // membership
delete(set, 1)               // remove
```

### Sorted Map (manual — no TreeMap)
```go
import "sort"

m := map[string]int{"b": 2, "a": 1}
keys := make([]string, 0, len(m))
for k := range m { keys = append(keys, k) }
sort.Strings(keys)
for _, k := range keys { fmt.Println(k, m[k]) }
```

### container/heap (Priority Queue / Min-Heap)
```go
import "container/heap"

// Min-heap of ints
type MinHeap []int
func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x any)        { *h = append(*h, x.(int)) }
func (h *MinHeap) Pop() any {
    old := *h; n := len(old); x := old[n-1]; *h = old[:n-1]; return x
}

h := &MinHeap{3, 1, 4}
heap.Init(h)
heap.Push(h, 2)
min := heap.Pop(h).(int)  // 1
(*h)[0]                   // peek min

// Max-heap: flip Less
func (h MaxHeap) Less(i, j int) bool { return h[i] > h[j] }
```

### container/list (Doubly Linked List)
```go
import "container/list"

l := list.New()
l.PushBack(1)
l.PushFront(0)
l.Len()

for e := l.Front(); e != nil; e = e.Next() {
    fmt.Println(e.Value)
}
l.Remove(e)
```

---

## 🔤 Strings

```go
import (
    "strings"
    "strconv"
    "unicode"
)

s := "hello"
len(s)                          // byte length
[]rune(s)                       // rune slice (Unicode safe)
s[0]                            // byte, not rune
string(s[1:4])                  // "ell"
s + " world"                    // concatenation (new string)

strings.ToUpper(s)
strings.ToLower(s)
strings.TrimSpace(s)
strings.Trim(s, "h")            // trim leading/trailing "h"
strings.TrimLeft(s, "he")
strings.TrimRight(s, "lo")
strings.Replace(s, "l", "r", -1)  // replace all
strings.ReplaceAll(s, "l", "r")
strings.Split(s, ",")
strings.Join([]string{"a","b"}, ",") // "a,b"
strings.Contains(s, "ell")
strings.HasPrefix(s, "he")
strings.HasSuffix(s, "lo")
strings.Count(s, "l")           // 2
strings.Index(s, "ll")          // 2, -1 if not found
strings.Repeat("ab", 3)         // "ababab"
strings.EqualFold("Go", "go")   // case-insensitive eq

// String ↔ int
strconv.Itoa(42)                // "42"
strconv.Atoi("42")              // 42, err
strconv.ParseInt("FF", 16, 64)  // 255

// Rune helpers
unicode.IsLetter('a')
unicode.IsDigit('0')
unicode.IsSpace(' ')
unicode.ToLower('A')

// StringBuilder equivalent
var sb strings.Builder
sb.WriteString("hello")
sb.WriteByte(' ')
sb.WriteRune('🌍')
sb.String()

// Byte slice ↔ string
bs := []byte(s)
s2 := string(bs)
```

---

## 🔢 Numbers & Math

```go
import "math"

math.MaxInt     // max int
math.MinInt
math.MaxFloat64
math.Inf(1)     // +Inf
math.Inf(-1)    // -Inf
math.IsInf(x, 1)
math.Abs(-5.0)
math.Sqrt(16)   // 4
math.Pow(2, 10) // 1024
math.Log2(8)    // 3
math.Ceil(3.2)  // 4
math.Floor(3.7) // 3
math.Round(3.5) // 4
math.Max(3, 5)
math.Min(3, 5)

// No built-in max/min for ints before Go 1.21
func maxInt(a, b int) int { if a > b { return a }; return b }

// Go 1.21+
import "cmp"
cmp.Max(3, 5)   // 5
min(3, 5)       // built-in
max(3, 5)       // built-in

// Bit manipulation
n & 1           // is odd
n >> 1          // n / 2
n << 1          // n * 2
n & (n - 1)     // clear lowest set bit
n ^ n           // 0
bits.OnesCount(uint(n))  // count set bits  (import "math/bits")
```

---

## ♻️ Sorting

```go
import "sort"

// Sort slice of ints / strings / float64
sort.Ints(s)
sort.Strings(s)
sort.Float64s(s)

// Sort with custom comparator
sort.Slice(s, func(i, j int) bool { return s[i] < s[j] })

// Stable sort
sort.SliceStable(s, func(i, j int) bool { return s[i][0] < s[j][0] })

// Sort structs
type Person struct{ Name string; Age int }
people := []Person{{"Bob", 25}, {"Alice", 30}}
sort.Slice(people, func(i, j int) bool {
    return people[i].Age < people[j].Age
})

// Implement sort.Interface
type ByAge []Person
func (a ByAge) Len() int           { return len(a) }
func (a ByAge) Less(i, j int) bool { return a[i].Age < a[j].Age }
func (a ByAge) Swap(i, j int)      { a[i], a[j] = a[j], a[i] }
sort.Sort(ByAge(people))

// Binary search (slice must be sorted)
i := sort.SearchInts(arr, target)        // first index >= target
i := sort.Search(n, func(i int) bool { return arr[i] >= target })
```

---

## 📐 Common Patterns

### Two Pointers
```go
l, r := 0, len(arr)-1
for l < r {
    sum := arr[l] + arr[r]
    if sum == target {
        return []int{l, r}
    } else if sum < target {
        l++
    } else {
        r--
    }
}
```

### Sliding Window (variable)
```go
freq := make(map[byte]int)
l, maxLen := 0, 0
for r := 0; r < len(s); r++ {
    freq[s[r]]++
    for len(freq) > k {
        freq[s[l]]--
        if freq[s[l]] == 0 { delete(freq, s[l]) }
        l++
    }
    if r-l+1 > maxLen { maxLen = r - l + 1 }
}
```

### Binary Search
```go
lo, hi := 0, len(arr)-1
for lo <= hi {
    mid := lo + (hi-lo)/2
    if arr[mid] == target {
        return mid
    } else if arr[mid] < target {
        lo = mid + 1
    } else {
        hi = mid - 1
    }
}
return -1
```

### BFS (graph / grid)
```go
type Point struct{ r, c int }
dirs := []Point{{0,1},{0,-1},{1,0},{-1,0}}
visited := make([][]bool, rows)
for i := range visited { visited[i] = make([]bool, cols) }

q := []Point{{sr, sc}}
visited[sr][sc] = true

for len(q) > 0 {
    cur := q[0]; q = q[1:]
    for _, d := range dirs {
        nr, nc := cur.r+d.r, cur.c+d.c
        if nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc] {
            visited[nr][nc] = true
            q = append(q, Point{nr, nc})
        }
    }
}
```

### DFS (recursive)
```go
var dfs func(r, c int)
dfs = func(r, c int) {
    if r < 0 || r >= rows || c < 0 || c >= cols { return }
    if visited[r][c] { return }
    visited[r][c] = true
    for _, d := range dirs { dfs(r+d.r, c+d.c) }
}
dfs(startR, startC)
```

### Dynamic Programming (bottom-up)
```go
// Coin change template
func coinChange(coins []int, amount int) int {
    dp := make([]int, amount+1)
    for i := range dp { dp[i] = math.MaxInt32 }
    dp[0] = 0
    for i := 1; i <= amount; i++ {
        for _, c := range coins {
            if c <= i && dp[i-c] != math.MaxInt32 {
                if dp[i-c]+1 < dp[i] { dp[i] = dp[i-c] + 1 }
            }
        }
    }
    if dp[amount] == math.MaxInt32 { return -1 }
    return dp[amount]
}
```
