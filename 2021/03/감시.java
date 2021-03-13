import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution15683 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N,M, blankCnt, minCnt = Integer.MAX_VALUE;
    static int map[][];
    static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
    static ArrayList<CCTV> cctv = new ArrayList<>();
    static class CCTV {
        int type;
        int rotationCnt;
        int x;
        int y;

        public CCTV(int x, int y, int type) {
            this.type = type;
            if (type == 2)
                this.rotationCnt = 2;
            else if (type == 5)
                this.rotationCnt = 1;
            else
                this.rotationCnt = 4;
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        supervise(0);
        System.out.println(minCnt);
    }
    static void findNone() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    cnt += 1;
            }
        }
        minCnt = Math.min(minCnt, cnt);
    }
    private static void supervise(int idx) {

        if (idx == cctv.size()) {
            findNone();
            return;
        }

        CCTV cur = cctv.get(idx);
        if (cur.type == 1) {
            for (int i = 0; i < cur.rotationCnt; i++) {
                fillMap(cur.x, cur.y, i);
                supervise(idx + 1);
                emptyMap(cur.x, cur.y, i);
            }
        } else if (cur.type == 2) {
            for (int i = 0; i < cur.rotationCnt; i++) {
                fillMap(cur.x, cur.y, i);
                fillMap(cur.x, cur.y, i + 2);
                supervise(idx + 1);
                emptyMap(cur.x, cur.y, i + 2);
                emptyMap(cur.x, cur.y, i);
            }
        } else if (cur.type == 3) {
            for (int i = 0; i < cur.rotationCnt; i++) {
                fillMap(cur.x, cur.y, i);
                fillMap(cur.x, cur.y, (i + 1) % 4);
                supervise(idx + 1);
                emptyMap(cur.x, cur.y, (i + 1) % 4);
                emptyMap(cur.x, cur.y, i);
            }
        } else if (cur.type == 4) {
            for (int i = 0; i < cur.rotationCnt; i++) {
                fillMap(cur.x, cur.y, i);
                fillMap(cur.x, cur.y, (i + 1) % 4);
                fillMap(cur.x, cur.y, (i + 2) % 4);
                supervise(idx + 1);
                emptyMap(cur.x, cur.y, (i + 2) % 4);
                emptyMap(cur.x, cur.y, (i + 1) % 4);
                emptyMap(cur.x, cur.y, i);
            }
        } else {
            for (int i = 0; i < cur.rotationCnt; i++) {
                fillMap(cur.x, cur.y, i);
                fillMap(cur.x, cur.y, (i + 1) % 4);
                fillMap(cur.x, cur.y, (i + 2) % 4);
                fillMap(cur.x, cur.y, (i + 3) % 4);
                supervise(idx + 1);
                emptyMap(cur.x, cur.y, (i + 3) % 4);
                emptyMap(cur.x, cur.y, (i + 2) % 4);
                emptyMap(cur.x, cur.y, (i + 1) % 4);
                emptyMap(cur.x, cur.y, i);
            }
        }

    }

    private static void emptyMap(int x, int y, int dIdx) {
        while(true) {
            x += dx[dIdx];
            y += dy[dIdx];
            if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 6) break;
            if (map[x][y] % 7 == 0)
                map[x][y] -= 7;
        }
    }

    private static void fillMap(int x, int y, int dIdx) {
        while(true) {
            x += dx[dIdx];
            y += dy[dIdx];
            if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 6) break;
            if (map[x][y] % 7 == 0) {
                map[x][y] += 7;
            }
        }
    }
}