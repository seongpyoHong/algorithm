import java.util.Stack;

// 완전 탐색
public class SolutionKakao {
    static int removedCnt = 0;
    static char[][] map;
    static boolean[][] isRemove;
    static int[] dx = {0,0,1,1}, dy = {0,1,1,0};
    private static int solution(int m, int n, String[] board) {
        map = new char[m][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        while(true) {
            isRemove = new boolean[m][n];
            if (!checkRemove(m,n)) break;
            compaction(m, n);
        }
        return removedCnt;
    }

    private static void compaction(int m, int n) {
        for (int col = 0; col < n; col++) {
            Stack<Character> rows = new Stack<>();
            for (int row = 0; row < m; row++) {
                if (isRemove[row][col] || map[row][col] == 'X') continue;
                rows.add(map[row][col]);
            }

            for (int row = m - 1; row >= 0; row--) {
                if (rows.isEmpty()) map[row][col] = 'X';
                else map[row][col] = rows.pop();
            }
        }
    }

    private static boolean checkRemove(int m, int n) {
        int totalRemoveCnt = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (map[i][j] == 'X') continue;
                boolean isSame = true;
                for (int dIdx = 1; dIdx < 4; dIdx++) {
                    if (map[i][j] == map[i + dx[dIdx]][j + dy[dIdx]]) continue;
                    isSame = false;
                    break;
                }

                if (!isSame) continue;
                for (int dIdx = 0; dIdx < 4; dIdx++) {
                    if (isRemove[i + dx[dIdx]][j + dy[dIdx]]) continue;
                    totalRemoveCnt++;
                    isRemove[i + dx[dIdx]][j + dy[dIdx]] = true;
                }
            }
        }

        if (totalRemoveCnt == 0) return false;

        removedCnt += totalRemoveCnt;
        return true;
    }
}