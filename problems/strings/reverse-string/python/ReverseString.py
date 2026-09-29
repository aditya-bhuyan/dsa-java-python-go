class ReverseString:
    @staticmethod
    def reverse_string(s):
        left, right = 0, len(s) - 1
        while left < right:
            s[left], s[right] = s[right], s[left]
            left += 1
            right -= 1

if __name__ == "__main__":
    s = ['h','e','l','l','o']
    ReverseString.reverse_string(s)
    print(s)
