import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2156 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, maxCnt = 0;
    static int[] drink;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        drink = new int[N + 1];
        cache = new int[N + 1][3];
        for (int i = 1; i < N+1; i++) {
            drink[i] = Integer.parseInt(br.readLine());
        }

        cache[1][1] = drink[1];
        for (int i = 2; i < N + 1; i++) {
            cache[i][0] = Math.max(cache[i-1][2], Math.max(cache[i-1][0], cache[i-1][1]));
            cache[i][1] = cache[i-1][0] + drink[i];
            cache[i][2] = cache[i-1][1] + drink[i];
        }
        System.out.println(Math.max(cache[N][2], Math.max(cache[N][0], cache[N][1])));
    }
}