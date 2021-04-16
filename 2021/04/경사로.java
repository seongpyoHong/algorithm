import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시뮬레이션
public class Solution14890 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int N, L;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int[] result = new int[T];
        for (int i = 0; i < T; i++) {
            result[i] = getResult();
        }

        for (int i = 0; i < T; i++) {
            System.out.printf("#%d %d\n", i + 1, result[i]);
        }
    }

    static int getResult() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (!isPassableByVertical(i)) continue;
            cnt++;
        }

        for (int i = 0; i < N; i++) {
            if (!isPassableByHorizontal(i)) continue;
            cnt++;
        }
        return cnt;
    }



    private static boolean isPassableByHorizontal(int col) {
        int curHeight = map[0][col];
        int curCnt = 1;
        for (int idx = 1; idx < N; idx++) {
            if (curHeight == map[idx][col]) {
                curCnt++;
            } else if (curHeight + 1 == map[idx][col] && curCnt >= L) {
                curHeight += 1;
                curCnt = 1;
            } else if (curHeight - 1 == map[idx][col]) {
                int len = 0;
                while (idx < N) {
                    if (len == L || map[idx][col] != curHeight - 1) break;
                    len++;
                    idx++;
                }
                if (len < L) return false;
                idx--;
                curHeight -= 1;
                curCnt = 0;
            } else return false;
        }
        return true;
    }

    private static boolean isPassableByVertical(int row) {
        int curHeight = map[row][0];
        int curCnt = 1;
        for (int idx = 1; idx < N; idx++) {
            if (curHeight == map[row][idx]) {
                curCnt++;
            } else if (curHeight + 1 == map[row][idx] && curCnt >= L) {
                curHeight += 1;
                curCnt = 1;
            } else if (curHeight - 1 == map[row][idx]) {
                int len = 0;
                while (idx < N) {
                    if (len == L || map[row][idx] != curHeight - 1) break;
                    len++;
                    idx++;
                }
                if (len < L) return false;
                idx--;
                curHeight -= 1;
                curCnt = 0;
            } else return false;
        }
        return true;
    }

}