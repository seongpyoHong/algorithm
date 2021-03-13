import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Number {
        int val;
        int distance;

        public Number(int val, int distance) {
            this.val = val;
            this.distance = distance;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, minCnt = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        bfs(N);

        System.out.println(minCnt);

    }

    private static void bfs(int n) {
        Queue<Number> q = new LinkedList<>();
        q.add(new Number(n, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Number cur = q.poll();
                visited[cur.val] = true;
                if (cur.val == K) {
                    if (minCnt < cur.distance) return;
                    minCnt = cur.distance;
                }

                if (cur.val * 2 <= 100000 && !visited[cur.val * 2]) {
                    q.add(new Number(cur.val * 2, cur.distance));
                }
                if (cur.val + 1 <= 100000 && !visited[cur.val + 1]) {
                    q.add(new Number(cur.val + 1, cur.distance + 1));
                }
                if (cur.val - 1 >= 0 && !visited[cur.val - 1]) {
                    q.add(new Number(cur.val - 1, cur.distance + 1));
                }

            }
        }
    }
}

//