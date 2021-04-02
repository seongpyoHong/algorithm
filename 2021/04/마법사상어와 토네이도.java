import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시뮬레이션
public class Solution20057 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int N, out = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j <  N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        tornado(N/2, N/2);
        System.out.println(out);
    }

    private static void tornado(int x, int y) {
        int cnt = 0;
        int size = 1;
        int dIdx = 0;
        while(true) {

            if (cnt++ == 2) {
                cnt = 1;
                size++;
            }

            for (int i = 0; i < size; i++) {
                if (x == 0 && y == 0) return;
                updateMap(x, y, dIdx);
                x += dx[dIdx];
                y += dy[dIdx];
            }
            dIdx = (dIdx + 1) % 4;
        }
    }

    private static void updateMap(int x, int y, int dIdx) {
        int nX = x + dx[dIdx];
        int nY = y + dy[dIdx];

        int amount1 = (int) (map[nX][nY] * 0.01);
        int amount2 = (int) (map[nX][nY] * 0.02);
        int amount7 = (int) (map[nX][nY] * 0.07);
        int amount5 = (int) (map[nX][nY] * 0.05);
        int amount10 = (int) (map[nX][nY] * 0.1);

        // 5%
        int percent5X = x + dx[dIdx] * 3;
        int percent5Y = y + dy[dIdx] * 3;
        if (percent5X >= 0 && percent5Y >= 0 && percent5X < N && percent5Y < N) {
            map[percent5X][percent5Y] += amount5;
        } else out += amount5;

        // 10%
        int percent10X = x + dx[dIdx] * 2 + dx[(dIdx == 0) ? 3 : dIdx - 1];
        int percent10Y = y + dy[dIdx] * 2 + dy[(dIdx == 0) ? 3 : dIdx - 1];
        if (percent10X >= 0 && percent10Y >= 0 && percent10X < N && percent10Y < N) {
            map[percent10X][percent10Y] += amount10;
        } else out+= amount10;

        int percent10X2 = x + dx[dIdx] * 2 + dx[(dIdx == 3) ? 0 : dIdx + 1];
        int percent10Y2 = y + dy[dIdx] * 2 + dy[(dIdx == 3) ? 0 : dIdx + 1];
        if (percent10X2 >= 0 && percent10Y2 >= 0 && percent10X2 < N && percent10Y2 < N) {
            map[percent10X2][percent10Y2] += amount10;
        } else out+= amount10;

        // 7%
        int percent7X = x + dx[dIdx] + dx[(dIdx == 0) ? 3 : dIdx - 1];
        int percent7Y = y + dy[dIdx] + dy[(dIdx == 0) ? 3 : dIdx - 1];
        if (percent7X >= 0 && percent7Y >= 0 && percent7X < N && percent7Y < N) {
            map[percent7X][percent7Y] += amount7;
        } else out += amount7;

        int percent7X2 = x + dx[dIdx] + dx[(dIdx == 3) ? 0 : dIdx + 1];
        int percent7Y2 = y + dy[dIdx] + dy[(dIdx == 3) ? 0 : dIdx + 1];
        if (percent7X2 >= 0 && percent7Y2 >= 0 && percent7X2 < N && percent7Y2 < N) {
            map[percent7X2][percent7Y2] += amount7;
        } else out += amount7;

        // 2%
        int percent2X = x + dx[dIdx] + dx[(dIdx == 0) ? 3 : dIdx - 1] * 2;
        int percent2Y = y + dy[dIdx] + dy[(dIdx == 0) ? 3 : dIdx - 1] * 2;
        if (percent2X >= 0 && percent2Y >= 0 && percent2X < N && percent2Y < N) {
            map[percent2X][percent2Y] += amount2;
        } else out += amount2;

        int percent2X2 = x + dx[dIdx] + dx[(dIdx == 3) ? 0 : dIdx + 1] * 2;
        int percent2Y2 = y + dy[dIdx] + dy[(dIdx == 3) ? 0 : dIdx + 1] * 2;
        if (percent2X2 >= 0 && percent2Y2 >= 0 && percent2X2 < N && percent2Y2 < N) {
            map[percent2X2][percent2Y2] += amount2;
        } else out += amount2;
        // 1%
        int percent1X = x + dx[(dIdx == 0) ? 3 : dIdx - 1];
        int percent1Y = y + dy[(dIdx == 0) ? 3 : dIdx - 1];
        if (percent1X >= 0 && percent1Y >= 0 && percent1X < N && percent1Y < N) {
            map[percent1X][percent1Y] += amount1;
        } else out += amount1;

        int percent1X2 = x + dx[(dIdx == 3) ? 0 : dIdx + 1];
        int percent1Y2 = y + dy[(dIdx == 3) ? 0 : dIdx + 1];
        if (percent1X2 >= 0 && percent1Y2 >= 0 && percent1X2 < N && percent1Y2 < N) {
            map[percent1X2][percent1Y2] += amount1;
        } else out+= amount1;

        // alpha
        int alphaX = x + dx[dIdx] * 2;
        int alphaY = y + dy[dIdx] * 2;
        if (alphaX >= 0 && alphaY >= 0 && alphaX < N && alphaY <N) {
            map[alphaX][alphaY] += (map[nX][nY] - (2 * amount1 + 2 * amount2 + 2 * amount7 + 2 * amount10 + amount5));
        } else out += (map[nX][nY] - (2 * amount1 + 2 * amount2 + 2 * amount7 + 2 * amount10 + amount5));

        map[nX][nY] = 0;
    }
}