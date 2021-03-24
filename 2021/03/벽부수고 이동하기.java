import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2206 {
    static class Pair {
        int x;
        int y;
        int dist;
        int remain;

        public Pair(int x, int y, int dist, int remain) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.remain = remain;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visited = new boolean[N][M][2];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 1, 1));
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (cur.x == N - 1 && cur.y == M - 1) return cur.dist;
            if (cur.remain == 1) {
                for (int i = 0; i < 4; i++) {
                    int nX = cur.x + dx[i];
                    int nY = cur.y + dy[i];
                    if (nX < 0 || nY < 0 || nX >= N || nY >= M) continue;
                    if (map[nX][nY] == 1 && !visited[nX][nY][0]) {
                        visited[nX][nY][0] = true;
                        q.add(new Pair(nX, nY, cur.dist + 1, 0));
                    } else if (map[nX][nY] == 0 && !visited[nX][nY][1]) {
                        visited[nX][nY][1] = true;
                        q.add(new Pair(nX, nY, cur.dist + 1, 1));
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (nX < 0 || nY < 0 || nX >= N || nY >= M || visited[nX][nY][cur.remain] || map[nX][nY] == 1) continue;
                visited[nX][nY][cur.remain] = true;
                q.add(new Pair(nX, nY, cur.dist + 1, cur.remain));
            }
        }

        return -1;
    }
}