import unittest
from GroupAnagrams import GroupAnagrams

class TestGroupAnagrams(unittest.TestCase):
    def test1(self): 
        result = GroupAnagrams.group_anagrams(["eat","tea","tan","ate","nat","bat"])
        self.assertEqual(3, len(result))
    def test2(self): 
        result = GroupAnagrams.group_anagrams([""])
        self.assertEqual(1, len(result))

if __name__ == '__main__':
    unittest.main()
