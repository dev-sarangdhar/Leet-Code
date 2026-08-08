class Solution {
    public int[] validSequence(String word1, String word2) {
         int n = word1.length();
        int m = word2.length();
        
        int[] suffix = new int[n + 1];
        int p = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (p >= 0 && word1.charAt(i) == word2.charAt(p)) {
                p--;
            }
            suffix[i] = m - 1 - p;
        }
        
        int[] ans = new int[m];
        int j = 0;
        int changes = 1;
        
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } else if (changes > 0 && suffix[i + 1] >= m - j - 1) {
                ans[j] = i;
                j++;
                changes--;
            }
        }
        
        if (j == m) {
            return ans;
        }
        return new int[0];
    }
}