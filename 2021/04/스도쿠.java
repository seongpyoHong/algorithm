import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//  백트래킹
public class Solution2239 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[][] row = new boolean[9][10];
    static boolean[][] col = new boolean[9][10];
    static int[][] map = new int[9][9];
    static int[] dx = {0,1}, dy = {1, -8};
    static boolean isCompleted = false;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
                if (map[i][j] == 0) continue;
                row[i][map[i][j]] = true;
                col[j][map[i][j]] = true;
            }
        }

        fillMap(0,0);

    }

    private static void fillMap(int x, int y) {
        if (x == 8 && y == 8 && !isCompleted) {
            for (int i = 1; i <= 9; i++) {
                if (row[x][i] || col[y][i] || !isValidinSquare(x,y,i)) continue;
                map[x][y] = i;
            }
            printMap();
            isCompleted = true;
            return;
        }
        if (isCompleted) return;
        int dIdx = (y == 8) ? 1 : 0;
        int nX = x + dx[dIdx];
        int nY = y + dy[dIdx];

        if (map[x][y] != 0) {
            fillMap(nX, nY);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (row[x][i] || col[y][i] || !isValidinSquare(x,y,i)) continue;
                map[x][y] = i;
                row[x][i] = true;
                col[y][i] = true;
                fillMap(nX, nY);
                map[x][y] = 0;
                row[x][i] = false;
                col[y][i] = false;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean isValidinSquare(int x, int y, int num) {
        int xDiv = (x / 3) * 3;
        int yDiv = (y / 3) * 3;

        for (int i = xDiv; i < xDiv + 3; i++) {
            for (int j = yDiv; j < yDiv + 3; j++) {
                if (map[i][j] != num) continue;
                return false;
            }
        }
        return true;
    }
}