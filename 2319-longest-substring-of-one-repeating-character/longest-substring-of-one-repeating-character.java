class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;

        char[] arr = s.toCharArray();
        int[] ans = new int[k];

        SegmentTree tree = new SegmentTree(arr);
        tree.build(1, 0, n - 1);

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            arr[idx] = ch;
            tree.update(1, 0, n - 1, idx, ch);

            ans[i] = tree.max[1];
        }

        return ans;
    }

    static class SegmentTree {
        int[] max, pref, suff, len;
        char[] leftChar, rightChar;
        char[] arr;

        SegmentTree(char[] arr) {
            this.arr = arr;
            int n = arr.length;

            max = new int[4 * n];
            pref = new int[4 * n];
            suff = new int[4 * n];
            len = new int[4 * n];

            leftChar = new char[4 * n];
            rightChar = new char[4 * n];
        }

        void build(int node, int l, int r) {
            if (l == r) {
                len[node] = 1;
                max[node] = 1;
                pref[node] = 1;
                suff[node] = 1;
                leftChar[node] = arr[l];
                rightChar[node] = arr[l];
                return;
            }

            int mid = (l + r) / 2;

            build(node * 2, l, mid);
            build(node * 2 + 1, mid + 1, r);

            merge(node);
        }

        void update(int node, int l, int r, int idx, char ch) {
            if (l == r) {
                arr[idx] = ch;
                leftChar[node] = ch;
                rightChar[node] = ch;
                max[node] = 1;
                pref[node] = 1;
                suff[node] = 1;
                return;
            }

            int mid = (l + r) / 2;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, ch);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, ch);
            }

            merge(node);
        }

        void merge(int node) {
            int a = node * 2;
            int b = node * 2 + 1;

            len[node] = len[a] + len[b];

            leftChar[node] = leftChar[a];
            rightChar[node] = rightChar[b];

            pref[node] = pref[a];
            suff[node] = suff[b];

            max[node] = Math.max(max[a], max[b]);

            if (rightChar[a] == leftChar[b]) {
                max[node] = Math.max(max[node], suff[a] + pref[b]);

                if (pref[a] == len[a]) {
                    pref[node] = len[a] + pref[b];
                }

                if (suff[b] == len[b]) {
                    suff[node] = len[b] + suff[a];
                }
            }
        }
    }
}