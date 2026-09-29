import unittest
from Palindrome import Palindrome

class TestPalindrome(unittest.TestCase):
    def test1(self): self.assertTrue(Palindrome.is_palindrome("A man, a plan, a canal: Panama"))
    def test2(self): self.assertFalse(Palindrome.is_palindrome("race a car"))
    def test3(self): self.assertTrue(Palindrome.is_palindrome(" "))

if __name__ == '__main__':
    unittest.main()
