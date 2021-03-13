import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2636 {
    static class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, cheeseCnt;
    static int[][] map;
    static ArrayList<Integer> times = new ArrayList<>();
    static Queue<Pair> startQueue = new LinkedList<>();
    static Queue<Pair> meltQueue = new LinkedList<>();
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 1) cheeseCnt++;
                if (i == 0 || j == 0 || i == N-1 || j == M-1) startQueue.add(new Pair(i, j));
            }
        }

        while(cheeseCnt > 0) {
            findStart();
            meltCheese();
        }

        System.out.println(times.size());
        System.out.println(times.get(times.size() - 1));
    }

    private static void meltCheese() {
        int cnt = 0;
        while(!meltQueue.isEmpty()) {
            Pair cur = meltQueue.poll();
            startQueue.add(cur);
            map[cur.x][cur.y] = 0;
            cnt += 1;
        }
        cheeseCnt -= cnt;
        times.add(cnt);
    }

    static void findStart() {
        visited = new boolean[N][M];
        while(!startQueue.isEmpty()) {
            Pair cur = startQueue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (nextX <= 0 || nextY <= 0 || nextX >= N-1 || nextY >= M -1 || visited[nextX][nextY]) continue;
                visited[nextX][nextY] = true;
                if (map[nextX][nextY] == 1) {
                    meltQueue.add(new Pair(nextX, nextY));
                    continue;
                } else
                    startQueue.add(new Pair(nextX, nextY));
            }
        }
    }
}