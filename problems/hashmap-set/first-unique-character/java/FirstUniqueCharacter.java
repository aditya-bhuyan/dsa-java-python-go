import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqueCharacter {
    
    // Approach 1: HashMap Count - Optimal
    // Time: O(n), Space: O(1)
    public int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<>();
        
        // Count frequencies
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        // Find first unique
        for (int i = 0; i < s.length(); i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Approach 2: Array Index (Lowercase English)
    // Time: O(n), Space: O(1)
    public int firstUniqCharArray(String s) {
        int[] count = new int[26];
        
        // Count frequencies
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Find first unique
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Approach 3: LinkedHashMap (Maintains Order)
    // Time: O(n), Space: O(1)
    public int firstUniqCharLinked(String s) {
        Map<Character, Integer> count = new LinkedHashMap<>();
        
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                return s.indexOf(entry.getKey());
            }
        }
        
        return -1;
    }
}
