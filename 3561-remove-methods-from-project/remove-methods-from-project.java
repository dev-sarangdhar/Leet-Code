class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int[] e : invocations)
            g[e[0]].add(e[1]);

        boolean[] vis = new boolean[n];
        dfs(k, g, vis);

        for (int[] e : invocations) {
            if (!vis[e[0]] && vis[e[1]]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) ans.add(i);
                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (!vis[i]) ans.add(i);

        return ans;
    }
     private void dfs(int u, List<Integer>[] g, boolean[] vis) {
        if (vis[u]) return;
        vis[u] = true;
        for (int v : g[u])
            dfs(v, g, vis);
     }
}
