class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int length = 2; length <= n; length++) {
            for (int left = 0; left + length - 1 < n; left++) {
                int right = left + length - 1;

                int pickLeft = piles[left] - dp[left + 1][right];
                int pickRight = piles[right] - dp[left][right - 1];

                dp[left][right] = Math.max(pickLeft, pickRight);
            }
        }

        return dp[0][n - 1] > 0;
    }
}