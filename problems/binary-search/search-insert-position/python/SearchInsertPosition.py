"""Search Insert Position — Template 2 left boundary. Time: O(log n)  Space: O(1)"""


class SearchInsertPosition:
    def search_insert(self, nums: list[int], target: int) -> int:
        left, right = 0, len(nums)  # right = n (open bound)
        while left < right:
            mid = left + (right - left) // 2
            if nums[mid] >= target: right = mid
            else:                   left  = mid + 1
        return left


def main():
    s = SearchInsertPosition()
    nums = [1, 3, 5, 6]
    print(f"target=5 → {s.search_insert(nums, 5)}  (expected 2)")
    print(f"target=2 → {s.search_insert(nums, 2)}  (expected 1)")
    print(f"target=7 → {s.search_insert(nums, 7)}  (expected 4)")
    print(f"target=0 → {s.search_insert(nums, 0)}  (expected 0)")

if __name__ == "__main__": main()
