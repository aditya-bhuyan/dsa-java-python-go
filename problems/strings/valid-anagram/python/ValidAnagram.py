class ValidAnagram:
    """Valid Anagram checker using multiple approaches."""
    
    @staticmethod
    def is_anagram_sort(s, t):
        """Sorting approach. Time: O(n log n), Space: O(1)"""
        if len(s) != len(t):
            return False
        return sorted(s) == sorted(t)
    
    @staticmethod
    def is_anagram_array(s, t):
        """Array frequency approach. Time: O(n), Space: O(1)"""
        if len(s) != len(t):
            return False
        
        freq = [0] * 26
        for c in s:
            freq[ord(c) - ord('a')] += 1
        
        for c in t:
            freq[ord(c) - ord('a')] -= 1
            if freq[ord(c) - ord('a')] < 0:
                return False
        
        return True
    
    @staticmethod
    def is_anagram_map(s, t):
        """HashMap approach. Time: O(n), Space: O(k)"""
        if len(s) != len(t):
            return False
        
        freq = {}
        for c in s:
            freq[c] = freq.get(c, 0) + 1
        
        for c in t:
            if c not in freq or freq[c] == 0:
                return False
            freq[c] -= 1
        
        return True
    
    @staticmethod
    def is_anagram_counter(s, t):
        """Using Counter from collections. Time: O(n), Space: O(k)"""
        from collections import Counter
        return Counter(s) == Counter(t)


if __name__ == "__main__":
    tests = [
        ("anagram", "nagaram"),
        ("rat", "car"),
        ("abc", "def"),
        ("", ""),
        ("a", "a")
    ]
    
    for s, t in tests:
        print(f"Testing: \"{s}\", \"{t}\"")
        print(f"  Sort: {ValidAnagram.is_anagram_sort(s, t)}")
        print(f"  Array: {ValidAnagram.is_anagram_array(s, t)}")
        print(f"  Map: {ValidAnagram.is_anagram_map(s, t)}")
        print(f"  Counter: {ValidAnagram.is_anagram_counter(s, t)}")
