import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// bfs
public class Solution5014 {
    static class Pair {
        int floor;
        int cnt;

        public Pair(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int F,S,G,U,D;
    static boolean[] visited = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        F = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);
        G = Integer.parseInt(input[2]);
        U = Integer.parseInt(input[3]);
        D = Integer.parseInt(input[4]);

        Queue<Pair> q = new LinkedList<>();
        visited[S] = true;
        q.add(new Pair(S, 0));
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (cur.floor == G) {
                System.out.println(cur.cnt);
                return;
            }
            if (cur.floor + U <= F && !visited[cur.floor+ U]) {
                visited[cur.floor + U] = true;
                q.add(new Pair(cur.floor + U, cur.cnt + 1));
            }

            if (cur.floor - D > 0 && !visited[cur.floor - D]) {
                visited[cur.floor - D] = true;
                q.add(new Pair(cur.floor - D, cur.cnt + 1));
            }
        }

        System.out.println("use the stairs");
    }
}
