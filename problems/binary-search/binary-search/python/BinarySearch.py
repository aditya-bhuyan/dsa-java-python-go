"""Binary Search — Template 1 exact match. Time: O(log n)  Space: O(1)"""


class BinarySearch:
    def search(self, nums: list[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = left + (right - left) // 2
            if   nums[mid] == target: return mid
            elif nums[mid] <  target: left  = mid + 1
            else:                     right = mid - 1
        return -1


def main():
    s = BinarySearch()
    nums = [-1, 0, 3, 5, 9, 12]
    print(f"search(9) = {s.search(nums, 9)}  (expected 4)")
    print(f"search(2) = {s.search(nums, 2)}  (expected -1)")

if __name__ == "__main__": main()
