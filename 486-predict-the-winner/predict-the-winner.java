class Solution {
    int[][] dp;
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];

        for (int i = 0; i < n; i++)
            java.util.Arrays.fill(dp[i], Integer.MIN_VALUE);

        return solve(nums, 0, n - 1) >= 0;
    }

    int solve(int[] nums, int l, int r) {
        if (l == r) return nums[l];

        if (dp[l][r] != Integer.MIN_VALUE)
            return dp[l][r];

        int left = nums[l] - solve(nums, l + 1, r);
        int right = nums[r] - solve(nums, l, r - 1);

        return dp[l][r] = Math.max(left, right);
    }
}