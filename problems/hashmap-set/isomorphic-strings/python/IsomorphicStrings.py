class IsomorphicStrings:
    
    # Approach 1: Dual HashMap - Optimal
    # Time: O(n), Space: O(1) - bounded by alphabet
    def isIsomorphic(self, s: str, t: str) -> bool:
        s_to_t = {}
        t_to_s = {}
        
        for s_char, t_char in zip(s, t):
            # Check s -> t mapping
            if s_char in s_to_t:
                if s_to_t[s_char] != t_char:
                    return False
            else:
                s_to_t[s_char] = t_char
            
            # Check t -> s mapping (bijection)
            if t_char in t_to_s:
                if t_to_s[t_char] != s_char:
                    return False
            else:
                t_to_s[t_char] = s_char
        
        return True
    
    # Approach 2: Pattern Transformation
    # Time: O(n), Space: O(n)
    def isIsomorphicPattern(self, s: str, t: str) -> bool:
        return self.getPattern(s) == self.getPattern(t)
    
    def getPattern(self, s: str) -> str:
        char_map = {}
        pattern = []
        
        for c in s:
            if c not in char_map:
                char_map[c] = len(char_map)
            pattern.append(str(char_map[c]))
        
        return ''.join(pattern)
