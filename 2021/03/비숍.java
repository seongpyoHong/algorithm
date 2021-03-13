import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1799 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, macCnt;
    static int[] cache;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        cache = new int[2 * N - 1];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        putBishop(0, 0);
        System.out.println(macCnt);
    }

    private static void putBishop(int idx, int cnt) {
        if (idx == (2 * N) - 1) {
            if (macCnt < cnt) macCnt = cnt;
            return;
        }

        boolean isExist = false;
        for (int i = 0; i <= idx; i++) {
            if (i >= N || idx - i >= N || map[i][idx - i] == 0 || !isAvailable(i, idx - i, idx)) continue;
            cache[idx] = i;
            putBishop(idx + 1, cnt + 1);
            isExist = true;
        }
        if (!isExist) {
            cache[idx] = -1;
            putBishop(idx + 1, cnt);
        }
    }

    private static boolean isAvailable(int x, int y, int idx) {
        while(idx >= 2 && x >= 1 && y >= 1) {
            idx -= 2;
            x -= 1;
            y -= 1;
            if (cache[idx] == x) return false;
        }

        return true;
    }
}