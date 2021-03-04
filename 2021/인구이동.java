import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution16234 {
    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] check;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1}, sum = new int[2501], cnt = new int[2501];
    static int N, L, R, area;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int ret = 0;
        while(true) {
            area = 2;
            sum = new int[2501];
            cnt = new int[2501];
            check = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (check[i][j] > 0 || !bfs(i, j)) continue;
                    area++;
                }
            }
            movePeople();
            if (area == 2)
                break;
            ret++;
        }

        System.out.println(ret);
    }

    private static void movePeople() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check[i][j] == 0) continue;
                sum[check[i][j]] += map[i][j];
                cnt[check[i][j]] += 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (check[i][j] == 0) continue;
                map[i][j] = (int) Math.floor(sum[check[i][j]] / cnt[check[i][j]]);
            }
        }
    }


    static boolean bfs(int x, int y) {
        boolean isOpen = false;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        check[x][y] = area;
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
                if (L <= Math.abs(map[nextX][nextY] - map[cur.x][cur.y])  && Math.abs(map[nextX][nextY] - map[cur.x][cur.y])  <= R) {
                    isOpen = true;

                    if (check[nextX][nextY] != 0) {
                        check[cur.x][cur.y] = check[nextX][nextY];
                    } else {
                        check[nextX][nextY] = check[cur.x][cur.y];
                        q.add(new Pair(nextX, nextY));
                    }
                }
            }
        }
        if (!isOpen) {
            check[x][y] = 0;
        }
        return isOpen;
    }
}
