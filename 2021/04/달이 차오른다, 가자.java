import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// bfs & bitmasking
public class Solution1194 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static char[][] map;
    static boolean[][][] visited;
    static int N, M, KEYMAX = 64;

    public static void main(String[] args) throws IOException {
        int startX = 0, startY = 0;
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        visited = new boolean[N][M][KEYMAX];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char cur = line.charAt(j);
                if (cur == '0') {
                    startX = i;
                    startY = j;
                }

                map[i][j] = cur;
            }
        }

        System.out.println(bfs(startX, startY));
    }

    private static int bfs(int startX, int startY) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();
            if (map[cur.x][cur.y] == '1') return cur.dist;

            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (!canMove(nX, nY, cur.key)) continue;

                if (Character.isLowerCase(map[nX][nY])) {               // 열쇠
                    visited[nX][nY][cur.key | 1 << (map[nX][nY] - 'a')] = true;
                    q.add(new Point(nX, nY, cur.dist + 1, cur.key | 1 << (map[nX][nY] - 'a')));
                } else {                                                // 나머지
                    visited[nX][nY][cur.key] = true;
                    q.add(new Point(nX, nY, cur.dist + 1, cur.key));
                }
            }
        }

        return -1;
    }

    private static boolean canMove(int nX, int nY, int key) {
        if (nX < 0 || nY < 0 || nX >= N || nY >= M || map[nX][nY] == '#' || visited[nX][nY][key])
            return false;
        return !Character.isUpperCase(map[nX][nY]) || (key & 1 << (map[nX][nY] - 'A')) != 0;
    }

    static class Point {
        int x;
        int y;
        int dist;
        int key;

        public Point(int x, int y, int dist, int key) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.key = key;
        }
    }
}