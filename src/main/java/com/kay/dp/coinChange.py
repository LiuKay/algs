def coin_change(coins: list[int], amount: int):
    def dp(n):
        if n == 0:
            return 0
        if n < 0:
            return -1

        res = float('INF')
        for coin in coins:
            sub_problem = dp(n - coin)

            if sub_problem == -1:
                continue
            res = min(res, 1 + sub_problem)
        return res if res != float('INF') else -1

    return dp(amount)
