package com.kay.dp;

import java.util.Arrays;

public class CoinChange {

    int coinChange(int[] coins, int amount) {
        if(amount == 0)
            return 0;
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange(coins, amount - i);
            if (sub == -1) {
                continue;
            }

            res = Math.min(res, 1 + sub);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }

    int coinChangeByDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        //still the init value
        if (dp[amount] == amount + 1) {
            return -1;
        }
        return dp[amount];
    }



}
