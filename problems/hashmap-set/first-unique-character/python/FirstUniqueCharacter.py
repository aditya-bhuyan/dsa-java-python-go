class FirstUniqueCharacter:
    
    # Approach 1: HashMap Count - Optimal
    # Time: O(n), Space: O(1)
    def firstUniqChar(self, s: str) -> int:
        count = {}
        
        # Count frequencies
        for c in s:
            count[c] = count.get(c, 0) + 1
        
        # Find first unique
        for i, c in enumerate(s):
            if count[c] == 1:
                return i
        
        return -1
    
    # Approach 2: Array Index (Lowercase English)
    # Time: O(n), Space: O(1)
    def firstUniqCharArray(self, s: str) -> int:
        count = [0] * 26
        
        # Count frequencies
        for c in s:
            count[ord(c) - ord('a')] += 1
        
        # Find first unique
        for i, c in enumerate(s):
            if count[ord(c) - ord('a')] == 1:
                return i
        
        return -1
