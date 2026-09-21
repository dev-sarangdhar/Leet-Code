class Solution {
    public long[] resultArray(int[] nums, int k) {
         long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];
            int x = num % k;

            next[x]++;

            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    next[(r * x) % k] += dp[r];
                }
            }

            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}