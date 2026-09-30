# Greedy Problems

> **Week 11** · Core concept: always take the locally optimal choice; prove it leads to a globally optimal solution.

| # | Problem | Difficulty | Folder |
|---|---------|------------|--------|
| 1 | Jump Game | Medium | [jump-game/](jump-game/) |
| 2 | Gas Station | Medium | [gas-station/](gas-station/) |
| 3 | Assign Cookies | Easy | [assign-cookies/](assign-cookies/) |

## Key Patterns
- **Max reach tracking** — maintain the furthest index reachable so far (Jump Game)
- **Circular array / reset** — if total gas >= total cost a solution exists; find the valid start (Gas Station)
- **Sort + two pointer** — match smallest cookie to smallest unsatisfied child (Assign Cookies)

## When greedy works
Greedy works when:
1. **Greedy choice property** — a local optimum leads to a global optimum
2. **Optimal substructure** — optimal solution to sub-problems builds the full solution

> ⚠️ Greedy fails for Coin Change with arbitrary denominations — use DP instead.

## Concepts
→ [concepts/greedy.md](../../concepts/greedy.md)

← [Back to problems](../README.md)
