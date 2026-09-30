# Trie (Prefix Tree)

## Table of Contents

1. [Introduction](#introduction)
2. [Trie Structure](#trie-structure)
3. [Core Operations](#core-operations)
4. [Compressed Trie / Patricia Trie](#compressed-trie--patricia-trie)
5. [Trie vs HashMap](#trie-vs-hashmap)
6. [Complexity Analysis](#complexity-analysis)
7. [Language Implementations](#language-implementations)
8. [Common Mistakes](#common-mistakes)
9. [Problems Covered](#problems-covered)

---

## Introduction

A **Trie** (pronounced "try", from re**trie**val) is a tree-shaped data structure used to store a dynamic set of strings and support efficient prefix-based searches.

**When to use a Trie:**
- Autocomplete / prefix search
- Spell checking
- IP routing (longest-prefix matching)
- Word games (Boggle, word search)
- Checking if any dictionary word can be formed from characters

A trie outperforms a hash set when the operation involves **prefixes**, since a hash set cannot answer "how many words start with 'app'" in O(length) time.

---

## Trie Structure

Each **node** in a trie represents one character position. A node has:
- `children`: an array or map of child nodes indexed by character.
- `is_end`: boolean marking whether the path from root to this node spells a complete word.

### Visual Example

```
Insert: "apple", "app", "apricot", "banana"

          root
          / \
         a   b
         |   |
         p   a
        / \  |
       p   r n
       |   | |
       l   i a
       |   | |
       e  c  n
       *   | |
           o a
           | *
           t
           *

* = is_end = True
```

For the word `"app"`:
- root → 'a' → 'p' → 'p' → `is_end = True`

For the word `"apple"`:
- root → 'a' → 'p' → 'p' → 'l' → 'e' → `is_end = True`

---

## Core Operations

### Insert

```
insert(root, word):
    node = root
    for ch in word:
        if ch not in node.children:
            node.children[ch] = TrieNode()
        node = node.children[ch]
    node.is_end = True
```

**Time:** O(m) where m = length of word.

### Search (exact match)

```
search(root, word):
    node = root
    for ch in word:
        if ch not in node.children:
            return False
        node = node.children[ch]
    return node.is_end
```

**Time:** O(m).

### Starts With (prefix search)

```
starts_with(root, prefix):
    node = root
    for ch in prefix:
        if ch not in node.children:
            return False
        node = node.children[ch]
    return True           # prefix exists (may or may not be a complete word)
```

**Time:** O(m) where m = length of prefix.

### Delete

```
delete(node, word, depth=0):
    if depth == len(word):
        if node.is_end: node.is_end = False
        return len(node.children) == 0   # safe to delete if no children
    ch = word[depth]
    if ch not in node.children: return False
    should_delete_child = delete(node.children[ch], word, depth+1)
    if should_delete_child:
        del node.children[ch]
        return not node.is_end and len(node.children) == 0
    return False
```

---

### Dry Run — Insert & Search

```
Insert "cat", "can":

root
  └─ c
      └─ a
          ├─ t  (is_end=True)
          └─ n  (is_end=True)

search("cat"):   root → c → a → t, is_end=True  → True ✓
search("ca"):    root → c → a,     is_end=False → False ✓
starts_with("ca"): root → c → a → return True  ✓
search("cap"):   root → c → a, 'p' not in children → False ✓
```

---

## Node Implementation Choices

### Array of 26 (for lowercase letters only)

```python
class TrieNode:
    def __init__(self):
        self.children = [None] * 26   # index by ch - 'a'
        self.is_end = False
```

**Pro:** O(1) child access by index. **Con:** 26 pointers per node regardless of branching factor.

### HashMap (for any character set)

```python
class TrieNode:
    def __init__(self):
        self.children: dict[str, 'TrieNode'] = {}
        self.is_end = False
```

**Pro:** Space-efficient for large alphabets or sparse tries. **Con:** Slightly slower hash-map lookup.

---

## Compressed Trie / Patricia Trie

A **compressed trie** merges chains of single-child nodes into a single edge labelled with the entire substring:

```
Standard:  root → c → a → t →(end)
Compressed: root → "cat" →(end)
```

Used in suffix trees and IP routing tables where space efficiency matters. Standard tries are more common in interview problems.

---

## Trie vs HashMap

| Feature | Trie | HashMap<String, …> |
|---|---|---|
| Exact word lookup | O(m) | O(m) average |
| Prefix search | O(m) | O(n) — must scan all keys |
| All words with prefix | O(m + output) | O(n) |
| Space | O(n × m) total chars | O(n × m) |
| Memory locality | Poor (pointer chasing) | Depends on implementation |
| Best for | Prefix queries, autocomplete | Simple existence checks |

---

## Complexity Analysis

| Operation | Time | Space |
|---|---|---|
| Insert | O(m) | O(m) per word |
| Search | O(m) | O(1) |
| Starts With | O(m) | O(1) |
| Delete | O(m) | O(1) |
| Build trie from n words | O(n × m) | O(n × m) |

m = average/max word length.

---

## Language Implementations

### Go

```go
type TrieNode struct {
    children [26]*TrieNode
    isEnd    bool
}

type Trie struct{ root *TrieNode }

func Constructor() Trie { return Trie{root: &TrieNode{}} }

func (t *Trie) Insert(word string) {
    node := t.root
    for _, ch := range word {
        idx := ch - 'a'
        if node.children[idx] == nil { node.children[idx] = &TrieNode{} }
        node = node.children[idx]
    }
    node.isEnd = true
}

func (t *Trie) Search(word string) bool {
    node := t.root
    for _, ch := range word {
        idx := ch - 'a'
        if node.children[idx] == nil { return false }
        node = node.children[idx]
    }
    return node.isEnd
}

func (t *Trie) StartsWith(prefix string) bool {
    node := t.root
    for _, ch := range prefix {
        idx := ch - 'a'
        if node.children[idx] == nil { return false }
        node = node.children[idx]
    }
    return true
}
```

### Java

```java
class Trie {
    private TrieNode root = new TrieNode();

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) node.children[i] = new TrieNode();
            node = node.children[i];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) return false;
            node = node.children[i];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) return false;
            node = node.children[i];
        }
        return true;
    }
}
```

### Python

```python
class TrieNode:
    def __init__(self):
        self.children: dict[str, 'TrieNode'] = {}
        self.is_end = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        node = self.root
        for ch in word:
            if ch not in node.children:
                node.children[ch] = TrieNode()
            node = node.children[ch]
        node.is_end = True

    def search(self, word: str) -> bool:
        node = self.root
        for ch in word:
            if ch not in node.children: return False
            node = node.children[ch]
        return node.is_end

    def starts_with(self, prefix: str) -> bool:
        node = self.root
        for ch in prefix:
            if ch not in node.children: return False
            node = node.children[ch]
        return True
```

---

## Common Mistakes

| Mistake | Fix |
|---|---|
| Returning `True` from `search` without checking `is_end` | `search` must verify `node.is_end` — a prefix may exist without being a word |
| Forgetting to handle overlapping words (e.g., "app" and "apple") | `is_end` marks word boundaries independently of children |
| Array-indexed node but input may contain non-lowercase chars | Switch to `dict` children or add bounds check |
| Not initialising children in Go struct (nil pointer dereference) | Always `node.children[i] = &TrieNode{}` before descending |
| Inserting words during search (mutation during traversal) | Separate insert and search calls |

---

## Problems Covered

| Problem | Trie Operation | LeetCode |
|---|---|---|
| Implement Trie | Insert, Search, StartsWith | #208 |
| Word Search II | Trie + DFS on grid | #212 |
| Design Add and Search Words | Trie with wildcard `.` | #211 |
| Replace Words | Prefix matching | #648 |
| Longest Word in Dictionary | BFS/DFS on trie | #720 |
