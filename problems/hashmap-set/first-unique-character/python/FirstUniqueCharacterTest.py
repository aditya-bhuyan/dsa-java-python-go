import unittest
from FirstUniqueCharacter import FirstUniqueCharacter

class TestFirstUniqueCharacter(unittest.TestCase):
    
    def setUp(self):
        self.fuc = FirstUniqueCharacter()
    
    def test_first_uniq_leetcode(self):
        self.assertEqual(0, self.fuc.firstUniqChar("leetcode"))
    
    def test_first_uniq_loveleetcode(self):
        self.assertEqual(2, self.fuc.firstUniqChar("loveleetcode"))
    
    def test_all_duplicates(self):
        self.assertEqual(-1, self.fuc.firstUniqChar("aabb"))
    
    def test_single_char(self):
        self.assertEqual(0, self.fuc.firstUniqChar("a"))
    
    def test_unique_at_end(self):
        self.assertEqual(4, self.fuc.firstUniqChar("aabbc"))
    
    def test_all_unique(self):
        self.assertEqual(0, self.fuc.firstUniqChar("abc"))
    
    def test_array_approach(self):
        self.assertEqual(0, self.fuc.firstUniqCharArray("leetcode"))
        self.assertEqual(2, self.fuc.firstUniqCharArray("loveleetcode"))

if __name__ == '__main__':
    unittest.main()
