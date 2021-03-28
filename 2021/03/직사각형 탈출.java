import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution16959 {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, H, W, sX, sY, fX, fY;
    static int[][] map;
    static int[] dx = {1,0, -1, 0}, dy = {0,1,0,-1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 1; j < M + 1; j++) {
                map[i][j] = Integer.parseInt(line[j - 1]);
            }
        }

        String[] fLine = br.readLine().split(" ");
        H = Integer.parseInt(fLine[0]);
        W = Integer.parseInt(fLine[1]);
        sX = Integer.parseInt(fLine[2]);
        sY = Integer.parseInt(fLine[3]);
        fX = Integer.parseInt(fLine[4]);
        fY = Integer.parseInt(fLine[5]);

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sX, sY));
        //init visit
        visited[sX][sY] = true;

        int time = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();
                if (cur.x == fX && cur.y == fY) return time;
                for (int i = 0; i < 4; i++) {
                    if (!isValid(cur.x, cur.y, i) || visited[cur.x + dx[i]][cur.y + dy[i]]) continue;
                    visited[cur.x + dx[i]][cur.y + dy[i]] = true;
                    q.add(new Pair(cur.x + dx[i], cur.y + dy[i]));
                }
            }
            time++;
        }
        return -1;
    }

    private static boolean isValid(int x, int y, int idx) {
        if (idx == 0) {
            // x축 아래
            for (int nY = y; nY < y + W; nY++) {
                if (x + H > N || map[x + H][nY] == 1) return false;
            }
            return true;
        } else if (idx == 1){
            // y축 오른쪽
            for (int nX = x; nX < x + H; nX++) {
                if (y + W > M ||map[nX][y + W] == 1) return false;
            }
            return true;
        } else if (idx == 2) {
            // x축 위
            for (int nY = y; nY < y + W; nY++) {
                if (x - 1 < 1 || map[x-1][nY] == 1) return false;
            }
            return true;
        } else {
            // y축 왼쪽
            for (int nX = x; nX < x + H; nX++) {
                if (y -1 < 1 || map[nX][y - 1] == 1) return false;
            }
            return true;
        }

    }
}