class GroupAnagrams:
    @staticmethod
    def group_anagrams(strs):
        from collections import defaultdict
        anagram_map = defaultdict(list)
        for s in strs:
            key = ''.join(sorted(s))
            anagram_map[key].append(s)
        return list(anagram_map.values())

if __name__ == "__main__":
    result = GroupAnagrams.group_anagrams(["eat","tea","tan","ate","nat","bat"])
    print(result)
