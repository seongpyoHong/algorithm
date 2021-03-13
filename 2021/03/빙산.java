import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2573 {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] map;
    static Queue<Pair> q = new LinkedList<>();
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 0) continue;
                q.add(new Pair(i,j));
            }
        }


        int year = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            if (isDivided(size, q.peek())) {
                System.out.println(year);
                return;
            }
            Queue<Integer> removeQueue = new LinkedList<>();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();
                int removeCnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nX = cur.x + dx[i];
                    int nY = cur.y + dy[i];
                    if (nX < 0 || nY < 0 || nX >= N || nY >= M || map[nX][nY] != 0) continue;
                    removeCnt++;
                }
                removeQueue.add(removeCnt);
                q.add(cur);
            }

            for (int i = 0; i < size; i++) {
                Pair cur = q.poll();
                int removeCnt = removeQueue.poll();
                if (map[cur.x][cur.y] - removeCnt <= 0)
                    map[cur.x][cur.y] = 0;
                else {
                    map[cur.x][cur.y] -= removeCnt;
                    q.add(cur);
                }
            }
            year++;
        }

        System.out.println(0);
    }

    private static boolean isDivided(int size, Pair start) {
        int cnt = 1;
        boolean[][] visited = new boolean[N][M];
        visited[start.x][start.y] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (nX < 0 || nY < 0 || nX >= N || nY >= M || map[nX][nY] == 0 || visited[nX][nY]) continue;
                visited[nX][nY] = true;
                q.add(new Pair(nX,nY));
                cnt++;
            }
        }

        if (size == cnt) return false;
        return true;
    }
}