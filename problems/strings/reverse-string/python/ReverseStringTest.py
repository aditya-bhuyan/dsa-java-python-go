import unittest
from ReverseString import ReverseString

class TestReverseString(unittest.TestCase):
    def test1(self): 
        s = ['h','e','l','l','o']
        ReverseString.reverse_string(s)
        self.assertEqual(['o','l','l','e','h'], s)

if __name__ == '__main__':
    unittest.main()
