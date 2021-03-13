import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution17144 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R,C,T;
    static int[][] map;
    static int[] dxUp = {0, -1, 0, 1}, dyUp = {1, 0, -1, 0};
    static int[] dxDown = {0, 1, 0, -1}, dyDown = {1, 0, -1, 0};
    static ArrayList<Integer> cleaner = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == -1)
                    cleaner.add(i);
            }
        }

        for (int i = 0; i < T; i++) {
            spreadDust();
            circle();
        }

        int total = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] < 1) continue;
                total += map[i][j];
            }
        }
        System.out.println(total);
    }

    private static void circle() {
        //upside
        int dIdx = 0;
        int x = cleaner.get(0);
        int y = 0;
        Queue<Integer> q = new LinkedList<>();
        while(true) {
            if (x + dxUp[dIdx] < 0 || x + dxUp[dIdx] >= R || y + dyUp[dIdx] < 0|| y + dyUp[dIdx] >= C)
                dIdx += 1;

            x += dxUp[dIdx];
            y += dyUp[dIdx];
            if (x == cleaner.get(0) - 1 && y == 0) break;

            q.add(map[x][y]);
        }

        dIdx = 0;
        x = cleaner.get(0);
        y = 1;
        map[x][y] = 0;
        while (!q.isEmpty()) {
            if (x + dxUp[dIdx] < 0 || x + dxUp[dIdx] >= R || y + dyUp[dIdx] < 0|| y + dyUp[dIdx] >= C)
                dIdx += 1;
            x += dxUp[dIdx];
            y += dyUp[dIdx];
            map[x][y] = q.poll();
        }

        //downside
        dIdx = 0;
        x = cleaner.get(1);
        y = 0;

        while(true) {
            if (x + dxDown[dIdx] < 0 || x + dxDown[dIdx] >= R || y + dyDown[dIdx] < 0|| y + dyDown[dIdx] >= C)
                dIdx += 1;

            x += dxDown[dIdx];
            y += dyDown[dIdx];
            if (x == cleaner.get(1) + 1 && y == 0) break;

            q.add(map[x][y]);
        }

        dIdx = 0;
        x = cleaner.get(1);
        y = 1;
        map[x][y] = 0;
        while (!q.isEmpty()) {
            if (x + dxDown[dIdx] < 0 || x + dxDown[dIdx] >= R || y + dyDown[dIdx] < 0|| y + dyDown[dIdx] >= C)
                dIdx += 1;
            x += dxDown[dIdx];
            y += dyDown[dIdx];
            map[x][y] = q.poll();
        }
    }

    private static void spreadDust() {
        int[][] spreadAmount = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) continue;
                int dust = map[i][j]/5;
                int cnt = 0;
                for (int dIdx = 0; dIdx < 4; dIdx++) {
                    int nextX = i + dxUp[dIdx];
                    int nextY = j + dyUp[dIdx];
                    if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C || map[nextX][nextY] == -1) continue;
                    cnt++;
                    spreadAmount[nextX][nextY] += dust;
                }
                map[i][j] -= (dust * cnt);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += spreadAmount[i][j];
            }
        }
    }
}