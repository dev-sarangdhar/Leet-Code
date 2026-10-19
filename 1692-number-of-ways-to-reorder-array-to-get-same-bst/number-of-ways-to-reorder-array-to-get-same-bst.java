class Solution {
    static final int MOD = 1000000007;
    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums) - 1 + MOD) % MOD);
    }

    private long solve(int[] nums) {
        int n = nums.length;
        if (n <= 2) return 1;

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[0]) left.add(nums[i]);
            else right.add(nums[i]);
        }

        long ways = comb[n - 1][left.size()];
        ways = ways * solve(left.stream().mapToInt(Integer::intValue).toArray()) % MOD;
        ways = ways * solve(right.stream().mapToInt(Integer::intValue).toArray()) % MOD;

        return ways;
    }
}