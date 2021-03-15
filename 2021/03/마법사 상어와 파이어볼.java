import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution20056 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<FireBall> balls = new LinkedList<>();
    static FireBall[][] map;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            balls.add(new FireBall(line));
        }
        for (int i = 0; i < K; i++) {
            move();
            fillQueue();
        }

        int totalWeight = 0;
        while(!balls.isEmpty()) {
            FireBall ball = balls.poll();
            totalWeight += ball.m;
        }
        System.out.println(totalWeight);
    }

    private static void move() {
        map = new FireBall[N+1][N+1];
        while (!balls.isEmpty()) {
            FireBall cur = balls.poll();

            for (int i = 0; i < cur.s; i++) {
                cur.x += dx[cur.dIdx];
                cur.y += dy[cur.dIdx];
                if (cur.x <= 0) cur.x = N;
                if (cur.y <= 0) cur.y = N;
                if (cur.x > N) cur.x = 1;
                if (cur.y > N) cur.y = 1;
            }

            if (map[cur.x][cur.y] == null) {
                map[cur.x][cur.y] = new FireBall(cur.x, cur.y, cur.m, cur.s, cur.dIdx);
            } else {
                map[cur.x][cur.y].m += cur.m;
                map[cur.x][cur.y].s += cur.s;
                map[cur.x][cur.y].cnt += 1;
                if (map[cur.x][cur.y].isSame && map[cur.x][cur.y].dIdx % 2 != cur.dIdx % 2) {
                    map[cur.x][cur.y].isSame = false;
                }
            }
        }
    }

    private static void fillQueue() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == null) continue;
                if (map[i][j].cnt == 1) balls.add(new FireBall(i, j, map[i][j].m, map[i][j].s, map[i][j].dIdx));
                else {
                    int weight = map[i][j].m / 5;
                    if (weight == 0) continue;
                    int velocity = map[i][j].s / map[i][j].cnt;
                    int start = (map[i][j].isSame) ? 0 : 1;
                    for (int d = 0; d < 4; d++) {
                        balls.add(new FireBall(i,j,weight, velocity, start));
                        start+=2;
                    }
                }
            }
        }
    }

    static class FireBall {
        int x;
        int y;
        int m;
        int dIdx;
        int s;
        int cnt = 1;
        boolean isSame = true;

        public FireBall(String[] line) {
            this.x = Integer.parseInt(line[0]);
            this.y = Integer.parseInt(line[1]);
            this.m = Integer.parseInt(line[2]);
            this.s = Integer.parseInt(line[3]);
            this.dIdx = Integer.parseInt(line[4]);
        }

        public FireBall(int x, int y, int m, int s, int dIdx) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.dIdx = dIdx;
            this.s = s;
        }
    }
}