import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution16346 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, curSize = 2, eatCnt = 2, curX, curY, totalTime;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 9) {
                    curX = i;
                    curY = j;
                }
            }
        }

        while (bfs());
        System.out.println(totalTime);
    }

    private static boolean bfs() {
        boolean[][] visited = new boolean[N][N];
        visited[curX][curY] = true;
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(curX, curY, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            PriorityQueue<Pair> eating = new PriorityQueue<>();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nX = cur.x + dx[j];
                    int nY = cur.y + dy[j];
                    if (nX < 0 || nY < 0 || nX >= N || nY >= N || visited[nX][nY] || map[nX][nY] > curSize) continue;
                    if (map[nX][nY] == 0 || map[nX][nY] == curSize) {
                        visited[nX][nY] = true;
                        q.add(new Pair(nX, nY, cur.dist + 1));
                    } else
                        eating.add(new Pair(nX, nY, cur.dist+1));

                }
            }
            if (eating.size() == 0) continue;
            Pair dieFish = eating.poll();
            map[curX][curY] = 0;
            curX = dieFish.x;
            curY = dieFish.y;
            map[curX][curY] = 9;
            if (--eatCnt == 0) {
                curSize++;
                eatCnt = curSize;
            }
            totalTime += dieFish.dist;
            return true;
        }
        return false;
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.dist > o.dist) {
                return 1;
            } else if (this.dist == o.dist && this.x > o.x) {
                return 1;
            } else if (this.dist == o.dist && this.x == o.x && this.y > o.y)
                return 1;
            else
                return -1;
        }
    }
}