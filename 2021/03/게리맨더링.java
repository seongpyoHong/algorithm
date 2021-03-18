import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution17471 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] peopleCnt;
    static boolean[] select;
    static boolean[][] map;
    static int N, minDiff = Integer.MAX_VALUE, totalPeople;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        peopleCnt = new int[N + 1];
        map = new boolean[N + 1][N + 1];
        select = new boolean[N + 1];
        String[] cntLine = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            peopleCnt[i] = Integer.parseInt(cntLine[i - 1]);
            totalPeople += peopleCnt[i];
        }

        for (int i = 1; i <= N; i++) {
            String[] edge = br.readLine().split(" ");
            for (int j = 0; j < Integer.parseInt(edge[0]); j++) {
                map[i][Integer.parseInt(edge[j + 1])] = true;
                map[Integer.parseInt(edge[j + 1])][i] = true;
            }
        }

        subset(1, 0, 0);
        System.out.println((minDiff == Integer.MAX_VALUE) ? -1 : minDiff);
    }

    private static void subset(int idx, int total, int cnt) {
        if (idx == N + 1 || cnt > N/2) {
            if (isConnected(true) && isConnected(false)) {
                minDiff = Math.min(minDiff,  Math.abs(2 * total - totalPeople));
            }
            return;
        }

        subset(idx + 1, total, cnt);
        select[idx] = true;
        subset(idx + 1, total + peopleCnt[idx], cnt + 1);
        select[idx] = false;
    }

    private static boolean isConnected(boolean isOther) {
        //dfs
        Stack<Integer> s = new Stack<Integer>();
        boolean[] visited = new boolean[N + 1];
        int remainCnt = 0;
        boolean isStart = true;
        for (int i = 1; i < select.length; i++) {
            if (select[i] != isOther) continue;
            remainCnt++;
            if (isStart) {
                s.add(i);
                visited[i] = true;
                isStart = false;
            }
        }

        int visitCnt = 0;
        while (!s.isEmpty()) {
            visitCnt++;
            int cur = s.pop();

            for (int i = 1; i < N + 1; i++) {
                if (visited[i] || select[i] != isOther|| !map[cur][i]) continue;
                visited[i] = true;
                s.add(i);
            }
        }


        return visitCnt == remainCnt && remainCnt != 0;
    }

}