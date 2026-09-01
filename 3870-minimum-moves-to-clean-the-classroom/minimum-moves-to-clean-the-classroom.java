class Solution {
    public int minMoves(String[] classroom, int energy) {
        int n = classroom.length;
        int m = classroom[0].length();

        int sr = 0, sc = 0, k = 0;
        int[][] id = new int[n][m];

        for (int[] row : id)
            Arrays.fill(row, -1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    id[i][j] = k++;
                }
            }
        }

        if (k == 0) return 0;

        int size = 1 << k;
        int[][][][] dist = new int[n][m][size][energy + 1];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int mask = 0; mask < size; mask++)
                    Arrays.fill(dist[i][j][mask], -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, 0, energy});
        dist[sr][sc][0][energy] = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int mask = cur[2];
            int e = cur[3];

            int moves = dist[r][c][mask][e];

            if (mask == size - 1)
                return moves;

            if (e == 0)
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m)
                    continue;

                char ch = classroom[nr].charAt(nc);

                if (ch == 'X')
                    continue;

                int ne = e - 1;
                int nm = mask;

                if (ch == 'L')
                    nm |= 1 << id[nr][nc];

                if (ch == 'R')
                    ne = energy;

                if (dist[nr][nc][nm][ne] == -1) {
                    dist[nr][nc][nm][ne] = moves + 1;
                    q.offer(new int[]{nr, nc, nm, ne});
                }
            }
        }

        return -1;
    }
}