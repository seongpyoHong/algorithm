import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// floyd wasahll
public class Solution2660 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] distance;
    static int N, INF = 51;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        distance = new int[N + 1][N + 1];
        while(true) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);

            if (src == -1) break;
            distance[src][dest] = 1;
            distance[dest][src] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == 1) continue;
                distance[i][j] = INF;
            }
        }


        floyd();


        int min = Integer.MAX_VALUE;
        ArrayList<Integer> candidates = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            int curMax = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                curMax = Math.max(curMax, distance[i][j]);
            }

            if (curMax < min) {
                min = curMax;
                candidates.clear();
                candidates.add(i);
            } else if (curMax == min) {
                candidates.add(i);
            }
        }

        System.out.println(min + " " + candidates.size());
        for (Integer c : candidates) {
            System.out.print(c + " " );
        }
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][j] <= distance[i][k] + distance[k][j]) continue;
                    distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }
    }
}