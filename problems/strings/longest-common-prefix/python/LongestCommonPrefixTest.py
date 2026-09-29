import unittest
from LongestCommonPrefix import LongestCommonPrefix

class TestLongestCommonPrefix(unittest.TestCase):
    def test1(self): self.assertEqual("fl", LongestCommonPrefix.longest_common_prefix(["flower","flow","flight"]))
    def test2(self): self.assertEqual("", LongestCommonPrefix.longest_common_prefix(["dog","racecar","car"]))

if __name__ == '__main__':
    unittest.main()
