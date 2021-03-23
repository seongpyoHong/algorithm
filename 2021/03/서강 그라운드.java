import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, R, INF = 15 * 101;
    static int[] items;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);

        items = new int[N + 1];
        String[] itemLine = br.readLine().split(" ");
        for (int i = 1; i < N + 1; i++) {
            items[i] = Integer.parseInt(itemLine[i - 1]);
        }

        map = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }

        for (int i = 0; i < R; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            map[src][dest] = weight;
            map[dest][src] = weight;
        }

        floydWashall();
        System.out.println(getMaxItems());
    }

    private static int getMaxItems() {
        int maxCnt = 0;
        for (int i = 1; i < N + 1; i++) {
            int itemCnt = 0;
            for (int j = 1; j < N + 1; j++) {
                if (map[i][j] > M) continue;
                itemCnt += items[j];
            }
            maxCnt = Math.max(maxCnt, itemCnt);
        }
        return maxCnt;
    }

    private static void floydWashall() {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }
}