# Backtracking


- 39 Combination Sum
- 40 Combination Sum II
- 46 Permutations
- 47 Permutations II
- 78 Subsets
- 90 Subsets II
- 22 Generate Parentheses
- 51 N-Queens
- 131 Palindrome Partitioning

Given an array of distinct integers candidates, they can make up different collections(sum,subset,permutations...)

Consider the different scenarios:

1) candidates have duplicate elements?
2) candidates don't have duplicate elements?
3) candidates are ordered?
4) each element can only use once?
5) result order matters?
6) result duplicate?

### Backtracking Template:

```python
res = []
def backtrack(path, choices):
    if condition matched:
        res.add(path)
        return
    
    for c in choices:
        select c
        backtrack(path, choices)
        revoke select c
```
