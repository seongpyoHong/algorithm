import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution13549 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, time;
    static int[] visited = new int[100001];
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        for (int i = 0; i < 100001; i++) {
            visited[i] = -1;
        }
        bfs();
        System.out.println((visited[K] == -1) ? 0 : visited[K]);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(N);

        while (!q.isEmpty()) {
            int size = q.size();
            Queue<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                tmp.add(cur);
                if (cur == K) return;
                visited[cur] = (visited[cur] == -1) ? time : visited[cur];

                if (2 * cur <= 100000 && visited[2 * cur] == -1) {
                    visited[2 * cur] = visited[cur];
                    q.add(2 * cur);
                }
            }

            for (int i = 0; i < size; i++) {
                int cur = tmp.poll();
                if (cur > 0 && visited[cur - 1] == -1) {
                    visited[cur - 1] = visited[cur] + 1;
                    q.add(cur - 1);
                }

                if (cur < 100000 && visited[cur + 1] == -1) {
                    visited[cur + 1] = visited[cur] + 1;
                    q.add(cur + 1);
                }
            }

            time++;
        }
    }
 }