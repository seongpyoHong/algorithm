import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2589 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] map;
    static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    static int maxDist = 0;
    static boolean[][] visited;
    static int N, M;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = line[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 'L') continue;
                visited = new boolean[N][M];
                bfs(i,j);
            }
        }
        System.out.println(maxDist);
    }

    private static void bfs(int i, int j) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i,j));
        visited[i][j] = true;
        int dist = -1;
        while (!q.isEmpty()) {
            dist++;
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();
                for (int idx = 0; idx < 4; idx++) {
                    int nX = cur.x + dx[idx];
                    int nY = cur.y + dy[idx];
                    if (nX < 0 || nY < 0 || nX >= N || nY >= M || map[nX][nY] != 'L' || visited[nX][nY]) continue;
                    visited[nX][nY] = true;
                    q.add(new Pair(nX, nY));
                }
            }
        }
        if (maxDist < dist) maxDist = dist;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}