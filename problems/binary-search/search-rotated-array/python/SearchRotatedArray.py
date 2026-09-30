"""Search in Rotated Sorted Array. Time: O(log n)  Space: O(1)"""


class SearchRotatedArray:
    def search(self, nums: list[int], target: int) -> int:
        left, right = 0, len(nums) - 1

        while left <= right:
            mid = left + (right - left) // 2

            if nums[mid] == target:
                return mid

            if nums[left] <= nums[mid]:          # left half sorted
                if nums[left] <= target < nums[mid]:
                    right = mid - 1              # target in sorted left half
                else:
                    left = mid + 1               # target in right half
            else:                                # right half sorted
                if nums[mid] < target <= nums[right]:
                    left = mid + 1               # target in sorted right half
                else:
                    right = mid - 1              # target in left half

        return -1


def main():
    s = SearchRotatedArray()
    print(f"[4,5,6,7,0,1,2] target=0 → {s.search([4,5,6,7,0,1,2], 0)}  (expected 4)")
    print(f"[4,5,6,7,0,1,2] target=3 → {s.search([4,5,6,7,0,1,2], 3)}  (expected -1)")

if __name__ == "__main__": main()
