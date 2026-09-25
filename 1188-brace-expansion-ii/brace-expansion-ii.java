class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = new TreeSet<>();

        dfs(expression, result);

        return new ArrayList<>(result);

    }

    private Set<String> dfs(String s, int[] idx) {

        Set<String> result = new TreeSet<>();

        Set<String> current = new TreeSet<>();

        current.add("");

        while (idx[0] < s.length() && s.charAt(idx[0]) != '}') {

            char ch = s.charAt(idx[0]);

            if (ch == ',') {

                result.addAll(current);

                current = new TreeSet<>();

                current.add("");

                idx[0]++;

            } 

            else {

                Set<String> part;

                if (ch == '{') {

                    idx[0]++;

                    part = dfs(s, idx);

                    idx[0]++;

                } else {

                    part = new TreeSet<>();

                    part.add(String.valueOf(ch));

                    idx[0]++;

                }

                Set<String> next = new TreeSet<>();

                for (String a : current) {

                    for (String b : part) {

                        next.add(a + b);

                    }

                }

                current = next;

            }

        }

        result.addAll(current);

        return result;

    }

    private void dfs(String s, Set<String> result) {

        int[] idx = {0};

        result.addAll(dfs(s, idx));
    }
}