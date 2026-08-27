class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int n = s.length();

        int i = 0;
        while (i < n && cnt[target.charAt(i) - 'a'] > 0) {
            cnt[target.charAt(i) - 'a']--;
            i++;
        }

        while (i >= 0) {
            if (i < n) {
                int cur = target.charAt(i) - 'a';

                for (int c = cur + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        StringBuilder ans = new StringBuilder();

                        ans.append(target, 0, i);

                        ans.append((char) ('a' + c));
                        cnt[c]--;

                        for (int x = 0; x < 26; x++) {
                            while (cnt[x] > 0) {
                                ans.append((char) ('a' + x));
                                cnt[x]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            if (i == 0) break;

            i--;
            cnt[target.charAt(i) - 'a']++;
        }

        return "";
    }
}