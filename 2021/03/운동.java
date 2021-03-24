import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1956 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E, INF = 401 * 10000;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        map = new int[V + 1][V + 1];

        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V  + 1; j++) {
                if (i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }

        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            map[src][dest] = weight;
        }

        floydWashall();
        System.out.println(findMinCycle());
    }

    private static int findMinCycle() {
        int minDist = INF;
        for (int i = 1; i < V + 1; i++) {
            for (int j = i + 1; j < V + 1; j++) {
                minDist = Math.min(minDist, map[i][j] + map[j][i]);
            }
        }
        return (minDist == INF) ? -1 : minDist;
    }

    private static void floydWashall() {
        for (int k = 1; k < V + 1; k++) {
            for (int i = 1; i < V + 1; i++) {
                for (int j = 1; j < V + 1; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }
}