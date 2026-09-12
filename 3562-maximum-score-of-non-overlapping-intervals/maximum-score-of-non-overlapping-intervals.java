import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[]{intervals.get(i).get(0), intervals.get(i).get(1), intervals.get(i).get(2), i};
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            if (a[0] != b[0]) return a[0] - b[0];
            return a[3] - b[3];
        });

        long[][] dp = new long[n + 1][5];
        List<Integer>[][] indices = new List[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = Long.MIN_VALUE;
                indices[i][k] = new ArrayList<>();
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            int[] curr = arr[i - 1];
            int l = curr[0], w = curr[2], idx = curr[3];
            int lastNonOverlap = findLastNonOverlapping(arr, i - 1, l);

            for (int k = 0; k <= 4; k++) {
                if (dp[i - 1][k] > dp[i][k] ||
                        (dp[i - 1][k] == dp[i][k] && dp[i - 1][k] != Long.MIN_VALUE
                                && isLexSmaller(indices[i - 1][k], indices[i][k]))) {
                    dp[i][k] = dp[i - 1][k];
                    indices[i][k] = new ArrayList<>(indices[i - 1][k]);
                }

                if (k > 0 && dp[lastNonOverlap + 1][k - 1] != Long.MIN_VALUE) {
                    long newWeight = dp[lastNonOverlap + 1][k - 1] + w;
                    List<Integer> newIndices = new ArrayList<>(indices[lastNonOverlap + 1][k - 1]);
                    newIndices.add(idx);
                    Collections.sort(newIndices);

                    if (newWeight > dp[i][k] ||
                            (newWeight == dp[i][k] && isLexSmaller(newIndices, indices[i][k]))) {
                        dp[i][k] = newWeight;
                        indices[i][k] = newIndices;
                    }
                }
            }
        }

        long bestWeight = 0;
        List<Integer> bestIndices = new ArrayList<>();
        for (int k = 1; k <= 4; k++) {
            if (dp[n][k] > bestWeight ||
                    (dp[n][k] == bestWeight && dp[n][k] != Long.MIN_VALUE
                            && isLexSmaller(indices[n][k], bestIndices))) {
                bestWeight = dp[n][k];
                bestIndices = new ArrayList<>(indices[n][k]);
            }
        }

        int[] result = new int[bestIndices.size()];
        for (int i = 0; i < bestIndices.size(); i++) {
            result[i] = bestIndices.get(i);
        }
        return result;
    }

    private int findLastNonOverlapping(int[][] arr, int endIdx, int start) {
        int left = 0, right = endIdx - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid][1] < start) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean isLexSmaller(List<Integer> a, List<Integer> b) {
        if (a.isEmpty() && b.isEmpty()) return false;
        if (a.isEmpty()) return true;
        if (b.isEmpty()) return false;
        int size = Math.min(a.size(), b.size());
        for (int i = 0; i < size; i++) {
            if (a.get(i) < b.get(i)) return true;
            if (a.get(i) > b.get(i)) return false;
        }
        return a.size() < b.size();
    }
}