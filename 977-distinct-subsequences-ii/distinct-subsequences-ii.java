class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            long sum = 0;

            for (long x : dp) {
                sum = (sum + x) % MOD;
            }

            dp[c - 'a'] = (sum + 1) % MOD;
        }

        long ans = 0;

        for (long x : dp) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}