import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    
    // Approach 1: Dual HashMap - Optimal
    // Time: O(n), Space: O(1) - bounded by alphabet
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            // Check s -> t mapping
            if (sToT.containsKey(sChar)) {
                if (sToT.get(sChar) != tChar) {
                    return false;
                }
            } else {
                sToT.put(sChar, tChar);
            }
            
            // Check t -> s mapping (bijection)
            if (tToS.containsKey(tChar)) {
                if (tToS.get(tChar) != sChar) {
                    return false;
                }
            } else {
                tToS.put(tChar, sChar);
            }
        }
        
        return true;
    }
    
    // Approach 2: Pattern Transformation
    // Time: O(n), Space: O(n)
    public boolean isIsomorphicPattern(String s, String t) {
        return getPattern(s).equals(getPattern(t));
    }
    
    private String getPattern(String s) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder pattern = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            pattern.append(map.getOrDefault(c, map.size()));
            map.put(c, map.size());
        }
        
        return pattern.toString();
    }
}
