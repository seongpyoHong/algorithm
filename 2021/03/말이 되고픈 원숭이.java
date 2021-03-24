import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution1600 {
    static class Pair {
        int x;
        int y;
        int dist;
        int remainCnt;

        public Pair(int x, int y, int dist, int remainCnt) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.remainCnt = remainCnt;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {1, -1, 0, 0}, dy = {0,0,1,-1}, horseDx = {-1, -2, -2, -1, 1, 2, 1, 2}, horseDy={-2,-1,1,2,2,1,-2,-1};
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        W = Integer.parseInt(input[0]);
        H = Integer.parseInt(input[1]);

        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for (int i = 0; i < H; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0,0,K));
        visited[0][0][K] = true;
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            if (cur.x == H -1 && cur.y == W - 1) return cur.dist;

            if (cur.remainCnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nX = cur.x + horseDx[i];
                    int nY = cur.y + horseDy[i];
                    if (nX < 0 || nY < 0 || nX >= H || nY >= W || visited[nX][nY][cur.remainCnt - 1] || map[nX][nY] == 1) continue;
                    visited[nX][nY][cur.remainCnt - 1] = true;
                    q.add(new Pair(nX, nY, cur.dist + 1, cur.remainCnt - 1));
                }
            }

            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (nX < 0 || nY < 0 || nX >= H || nY >= W || visited[nX][nY][cur.remainCnt] || map[nX][nY] == 1) continue;
                visited[nX][nY][cur.remainCnt] = true;
                q.add(new Pair(nX, nY, cur.dist + 1, cur.remainCnt));
            }
        }
        return -1;
    }
}