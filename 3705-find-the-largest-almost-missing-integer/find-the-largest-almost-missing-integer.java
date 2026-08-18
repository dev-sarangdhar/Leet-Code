class Solution {
    public int largestInteger(int[] nums, int k) {
          int n = nums.length;
        int ans = -1;

        for (int x = 0; x <= 50; x++) {
            int count = 0;

            for (int i = 0; i <= n - k; i++) {
                for (int j = i; j < i + k; j++) {
                    if (nums[j] == x) {
                        count++;
                        break;
                    }
                }
            }

            if (count == 1) {
                ans = x;
            }
        }

        return ans;
    }
}