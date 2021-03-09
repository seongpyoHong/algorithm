import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1697 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, minCnt;
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        bfs(N);
        System.out.println(minCnt);
    }

    private static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == K) {
                    minCnt = cnt;
                    return;
                }

                if (cur + 1 <= 100000 && !visited[cur + 1]) {
                    visited[cur+1] = true;
                    q.add(cur+1);
                }
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    visited[cur - 1] = true;
                    q.add(cur - 1);
                }
                if (cur * 2 <= 100000 && !visited[cur * 2]) {
                    visited[cur * 2] = true;
                    q.add(cur * 2);
                }
            }
            cnt++;
        }
    }
}