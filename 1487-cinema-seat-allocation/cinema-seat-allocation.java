class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
         HashMap<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            if (s >= 2 && s <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << s));
            }
        }

        int ans = (n - map.size()) * 2;

        for (int mask : map.values()) {
            boolean left = true;   // 2-5
            boolean middle = true; // 4-7
            boolean right = true;  // 6-9

            for (int s = 2; s <= 5; s++)
                if ((mask & (1 << s)) != 0) left = false;

            for (int s = 4; s <= 7; s++)
                if ((mask & (1 << s)) != 0) middle = false;

            for (int s = 6; s <= 9; s++)
                if ((mask & (1 << s)) != 0) right = false;

            if (left && right)
                ans += 2;
            else if (left || middle || right)
                ans++;
        }

        return ans;
    }
}