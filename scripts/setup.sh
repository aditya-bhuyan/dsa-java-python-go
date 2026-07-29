#!/usr/bin/env bash

set -euo pipefail

echo "========================================"
echo "Creating DSA Repository Structure"
echo "========================================"

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

cd "$ROOT_DIR"

echo "Repository: $ROOT_DIR"

# Top-level folders
mkdir -p concepts
mkdir -p problems
mkdir -p cheatsheets
mkdir -p templates/java
mkdir -p templates/python
mkdir -p templates/go
mkdir -p interview-notes
mkdir -p resources
mkdir -p .github/workflows

###########################################################################
# Concepts
###########################################################################

CONCEPTS=(
arrays
strings
linked-list
stack
queue
hashmap
hashset
tree
binary-tree
bst
heap
priority-queue
graph
trie
recursion
backtracking
greedy
divide-and-conquer
sliding-window
two-pointers
binary-search
sorting
dynamic-programming
bit-manipulation
union-find
segment-tree
fenwick-tree
)

for topic in "${CONCEPTS[@]}"; do
    touch "concepts/${topic}.md"
done

###########################################################################
# Problems
###########################################################################

TOPICS=(
arrays
strings
linked-list
stack
queue
hashmap
tree
graph
heap
trie
recursion
backtracking
greedy
sliding-window
two-pointers
binary-search
sorting
dynamic-programming
bit-manipulation
union-find
segment-tree
fenwick-tree
)

for topic in "${TOPICS[@]}"; do
    mkdir -p "problems/${topic}"
done

###########################################################################
# Documentation files
###########################################################################

touch ROADMAP.md
touch PROGRESS.md

touch cheatsheets/java.md
touch cheatsheets/python.md
touch cheatsheets/go.md
touch cheatsheets/syntax-comparison.md

touch interview-notes/notes.md

###########################################################################
# GitHub workflow placeholder
###########################################################################

touch .github/workflows/README.md

echo
echo "Repository structure created successfully."
