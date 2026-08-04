class Solution {
    public List<Integer> findMissingElements(int[] nums) {
         List<Integer> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();

        int min = nums[0], max = nums[0];

        for (int x : nums) {
            set.add(x);
            min = Math.min(min, x);
            max = Math.max(max, x);
        }

        for (int i = min; i <= max; i++) {
            if (!set.contains(i))
                ans.add(i);
        }

        return ans;
    }
}