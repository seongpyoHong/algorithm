import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1719 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, NIL = -1, INF = 10000 * 201;
    static int[][] map;
    static int[][] path;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N + 1][N + 1];
        path = new int[N + 1][N + 1];

        initMap();

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            map[src][dest] = weight;
            map[dest][src] = weight;
        }

        initPath();

        floydWashall();
        printPath(updatePathToFirst());
    }

    private static void initPath() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j || map[i][j] == INF) path[i][j] = NIL;
                else
                    path[i][j] = i;
            }
        }
    }

    private static void initMap() {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) map[i][j] = 0;
                else map[i][j] = INF;
            }
        }
    }

    private static int[][] updatePathToFirst() {
        int[][] newPath = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) newPath[i][j] = NIL;
                else
                    newPath[i][j] = findNextPath(i, j);
            }
        }
        return newPath;
    }

    private static int findNextPath(int src, int dest) {
        if (path[src][dest] == src) return dest;
        return findNextPath(src, path[src][dest]);
    }

    private static void printPath(int[][] arr) {
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (arr[i][j] == NIL) System.out.print("- ");
                else System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void floydWashall() {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }
}