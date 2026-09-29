# Group Anagrams

## Problem Statement

Given an array of strings `strs`, group the anagrams together. You can return the answer in **any order**.

An **anagram** is a word or phrase formed by rearranging the letters of another.

## Examples

```
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
```

```
Input: strs = [""]
Output: [[""]]
```

```
Input: strs = ["a"]
Output: [["a"]]
```

## Algorithm: HashMap with Sorted String as Key

- Sort characters in each string
- Use sorted string as HashMap key
- Group strings with same sorted form
- Time: O(n * k log k) where n = array size, k = avg string length
- Space: O(n * k) for HashMap storage
