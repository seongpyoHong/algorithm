import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution13913 {
    static class Number {
        int val;
        int distance;
        StringBuilder path = new StringBuilder();
        public Number(int val, int distance, StringBuilder previous) {
            this.val = val;
            this.distance = distance;
            this.path.append(previous);
            this.path.append(" ");
            this.path.append(val);
        }
    }
    static String result;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, minCnt = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[200001];
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        if (N >= K) {
            StringBuilder sb = new StringBuilder();
            for (int i = N; i >= K ; i--) {
                sb.append(i);
                sb.append(" ");
            }
            System.out.println(N-K);
            System.out.println(sb);
            return;
        }
        bfs(N);

        System.out.println(minCnt);
        System.out.println(result);
    }

    private static void bfs(int n) {
        Queue<Number> q = new LinkedList<>();
        ArrayList<Integer> start = new ArrayList<>();
        q.add(new Number(n, 0, new StringBuilder()));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Number cur = q.poll();
                if (cur.val == K) {
                    minCnt = cur.distance;
                    result = cur.path.substring(1,cur.path.length());
                    return;
                }

                if (cur.val + 1 <= 200000 && !visited[cur.val + 1]) {
                    q.add(new Number(cur.val + 1, cur.distance + 1, cur.path));
                    visited[cur.val + 1] = true;
                }
                if (cur.val - 1 >= 0 && !visited[cur.val - 1]) {
                    q.add(new Number(cur.val - 1, cur.distance + 1, cur.path));
                    visited[cur.val - 1] = true;
                }
                if (cur.val * 2 <= 200000 && !visited[cur.val * 2]) {
                    q.add(new Number(cur.val * 2, cur.distance+1, cur.path));
                    visited[cur.val * 2] = true;
                }

            }
        }
    }
}

//