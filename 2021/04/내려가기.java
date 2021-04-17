import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// DP
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        int[][] max = new int[N][3];
        int[][] min = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            map[i][0] = Integer.parseInt(line[0]);
            map[i][1] = Integer.parseInt(line[1]);
            map[i][2] = Integer.parseInt(line[2]);
        }

        max[0] = Arrays.copyOf(map[0], 3);
        min[0] = Arrays.copyOf(map[0], 3);
        for (int i = 1; i < N; i++) {
            max[i][0] = Math.max(max[i - 1][0], max[i - 1][1]) + map[i][0];
            max[i][1] = Math.max(Math.max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]) + map[i][1];
            max[i][2] = Math.max(max[i - 1][1], max[i - 1][2]) + map[i][2];

            min[i][0] = Math.min(min[i - 1][0], min[i - 1][1]) + map[i][0];
            min[i][1] = Math.min(Math.min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]) + map[i][1];
            min[i][2] = Math.min(min[i - 1][1], min[i - 1][2]) + map[i][2];
        }

        System.out.println(Math.max(Math.max(max[N - 1][0], max[N - 1][1]) , max[N - 1][2]) + " " + Math.min(Math.min(min[N - 1][0], min[N - 1][1]), min[N - 1][2]) );
    }
}