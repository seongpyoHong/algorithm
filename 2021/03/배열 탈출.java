import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution11909 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] cache;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cache = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        cache[0][0] = 0;
        getPayment();
        System.out.println(cache[N-1][N-1]);
    }

    private static void getPayment() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0) continue;
                if (j - 1 < 0) {
                // only up
                    cache[i][j] = (map[i][j] < map[i- 1][j]) ? cache[i-1][j] : cache[i-1][j] + map[i][j] - map[i-1][j] + 1;
                } else if (i - 1 < 0) {
                // only left
                    cache[i][j] = (map[i][j] < map[i][j - 1]) ? cache[i][j - 1] : cache[i][j-1] + map[i][j] - map[i][j-1] + 1;
                } else {
                    int up = (map[i][j] < map[i- 1][j]) ? cache[i-1][j] : cache[i - 1][j] + map[i][j] - map[i-1][j] + 1;
                    int left = (map[i][j] < map[i][j - 1]) ? cache[i][j - 1] : cache[i][j-1] + map[i][j] - map[i][j-1] + 1;
                    cache[i][j] = Math.min(up,left);
                }
            }
        }
    }
}