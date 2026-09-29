public class ValidAnagram {

    /**
     * Check if t is anagram of s using sorting approach.
     * Time: O(n log n), Space: O(1)
     */
    public static boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length()) return false;
        
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        java.util.Arrays.sort(sChars);
        java.util.Arrays.sort(tChars);
        return java.util.Arrays.equals(sChars, tChars);
    }

    /**
     * Check if t is anagram of s using frequency counting.
     * Time: O(n), Space: O(1) - fixed array size 26
     */
    public static boolean isAnagramArray(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        for (char c : t.toCharArray()) {
            if (--freq[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if t is anagram of s using HashMap.
     * Time: O(n), Space: O(k) where k = unique characters
     */
    public static boolean isAnagramMap(String s, String t) {
        if (s.length() != t.length()) return false;
        
        java.util.Map<Character, Integer> freq = new java.util.HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        
        for (char c : t.toCharArray()) {
            if (!freq.containsKey(c) || freq.get(c) == 0) {
                return false;
            }
            freq.put(c, freq.get(c) - 1);
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] tests = {
            {"anagram", "nagaram"},
            {"rat", "car"},
            {"abc", "def"},
            {"", ""},
            {"a", "a"}
        };

        for (String[] test : tests) {
            String s = test[0], t = test[1];
            System.out.println("Testing: \"" + s + "\", \"" + t + "\"");
            System.out.println("  Sort: " + isAnagramSort(s, t));
            System.out.println("  Array: " + isAnagramArray(s, t));
            System.out.println("  Map: " + isAnagramMap(s, t));
        }
    }
}
