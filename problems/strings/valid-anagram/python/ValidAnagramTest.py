import unittest
from ValidAnagram import ValidAnagram

class TestValidAnagram(unittest.TestCase):
    def test1(self): self.assertTrue(ValidAnagram.is_anagram_array("anagram", "nagaram"))
    def test2(self): self.assertFalse(ValidAnagram.is_anagram_array("rat", "car"))
    def test3(self): self.assertTrue(ValidAnagram.is_anagram_array("", ""))

if __name__ == '__main__':
    unittest.main()
