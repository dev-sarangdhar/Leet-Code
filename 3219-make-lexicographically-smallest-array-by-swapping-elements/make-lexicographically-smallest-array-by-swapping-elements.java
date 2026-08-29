class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        HashMap<Integer, List<Integer>> groups = new HashMap<>();
        int group = 0;

        groups.put(group, new ArrayList<>());

        for (int i = 0; i < n; i++) {
            if (i > 0 && sorted[i] - sorted[i - 1] > limit) {
                group++;
                groups.put(group, new ArrayList<>());
            }

            groups.get(group).add(sorted[i]);
        }

        int[] groupId = new int[n];
        HashMap<Integer, Integer> valueGroup = new HashMap<>();

        group = 0;
        valueGroup.put(sorted[0], 0);

        for (int i = 1; i < n; i++) {
            if (sorted[i] - sorted[i - 1] > limit)
                group++;

            valueGroup.put(sorted[i], group);
        }

        HashMap<Integer, Integer> ptr = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int g = valueGroup.get(nums[i]);
            int p = ptr.getOrDefault(g, 0);

            nums[i] = groups.get(g).get(p);
            ptr.put(g, p + 1);
        }

        return nums;
    }
}