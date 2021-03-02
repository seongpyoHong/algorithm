import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution1058 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, cnt, maxCnt = Integer.MIN_VALUE;
    static char[][] map;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                map[i][j] = line[j];
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i, i, 0, -1);
            visited[i] = false;

            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (!visited[j]) continue;
                cnt++;
            }
            maxCnt = Math.max(maxCnt, cnt);
        }

        System.out.println(maxCnt);
    }

    private static void dfs(int x, int start, int depth, int store) {
        if (depth == 3) return;
        for (int y = 0; y < N; y++) {
            if (depth == 0 && map[x][y] == 'Y' )
                visited[y] = true;
            else if (depth == 0 && map[x][y] =='N')
                dfs(y, start, depth + 1, store);
            else if (depth == 1 && map[x][y] == 'Y') {
                store = x;
                dfs(y, start, depth + 1, store);
            }
            else if (depth == 2 && map[x][y] == 'Y' && y == start)
                visited[store] = true;
        }
    }
}