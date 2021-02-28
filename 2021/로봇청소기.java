import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution14503 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int N, M, cleanCnt = 1;
    public static void main(String[] args) throws IOException {
        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);

        String[] input2 = br.readLine().split(" ");
        int startX = Integer.parseInt(input2[0]);
        int startY = Integer.parseInt(input2[1]);
        int direction = Integer.parseInt(input2[2]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        map[startX][startY] = 2;
        clean(startX, startY, direction);
        System.out.println(cleanCnt);
    }

    private static void clean(int x, int y, int direction) {
        boolean isContinue = true;
        while (isContinue) {
            for (int i = 0; i < 4; i++) {
                direction = (direction + 3) % 4;
                if (map[x + dx[direction]][y + dy[direction]] == 0) {
                    x += dx[direction];
                    y += dy[direction];
                    map[x][y] = 2;
                    cleanCnt += 1;
                    break;
                }

                if (i == 3) {
                    int backDirection = (direction + 2) % 4;
                    if (map[x + dx[backDirection]][y + dy[backDirection]] == 1) {
                        isContinue = false;
                    } else {
                        x += dx[backDirection];
                        y += dy[backDirection];
                    }
                }
            }
        }
    }
}