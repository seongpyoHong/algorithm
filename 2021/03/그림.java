import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, areaId = 2, areaCnt;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int maxCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 1) continue;
                areaCnt = 0;
                bfs(i, j);
                areaId++;

                if (maxCnt < areaCnt)
                    maxCnt = areaCnt;
            }
        }

        System.out.println(areaId - 2);
        System.out.println(maxCnt);
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        map[x][y] = areaId;
        areaCnt++;
        while(!q.isEmpty()) {

            Pair current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || map[nextX][nextY] != 1) continue;
                map[nextX][nextY] = areaId;
                areaCnt++;
                q.add(new Pair(nextX, nextY));
            }
        }
    }

}