class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int min = 0, max = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[min]) min = i;
            if (nums[i] > nums[max]) max = i;
        }

        int a = Math.min(min, max);
        int b = Math.max(min, max);

        return Math.min(
            b + 1,
            Math.min(
                n - a,
                a + 1 + n - b
            )
        );
    }
}