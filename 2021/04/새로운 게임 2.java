import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// 시뮬레이션
public class Solution17837 {
    static class Point {
        int idx;
        int x;
        int y;
        int dir;

        public Point(int idx, int x, int y, int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0};
    static Point[] horses;
    static int[][] color;
    static int N, K;
    static Stack<Integer>[][] exist;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        color = new int[N][N];
        exist = new Stack[N][N];
        horses = new Point[K];

        // color
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(line[j]);
                exist[i][j] = new Stack<>();
            }
        }

        // horse
        for (int i = 0; i < K; i++) {
            String[] line = br.readLine().split(" ");
            horses[i] = new Point(i,Integer.parseInt(line[0]) - 1,Integer.parseInt(line[1]) - 1,Integer.parseInt(line[2]) - 1);
            exist[horses[i].x][horses[i].y].add(i);
        }


        if (isFinished()) {
            System.out.println(0);
            return;
        }

        int total = 0;
        while(total <= 1000) {
            total++;
            for (int i = 0; i < K; i++) {
                move(i);
                if (isFinished()) {
                    System.out.println(total);
                    return;
                }
            }

        }

        System.out.println(-1);
    }

    private static void move(int idx) {
        Point cur = horses[idx];
        int nX = cur.x + dx[cur.dir];
        int nY = cur.y + dy[cur.dir];
        Deque<Point> deque = new ArrayDeque<>();

        // 위에 있는거 뽑기
        while(!exist[cur.x][cur.y].isEmpty()) {
            int next = exist[cur.x][cur.y].pop();
            if (next == idx) break;
            deque.add(horses[next]);
        }

        // 파란색 or 벗어나는 경우
        if (nX < 0 || nY < 0 || nX >= N || nY >= N || color[nX][nY] == 2) {
            // 방향 변경
            cur.dir = (cur.dir % 2 == 0) ? cur.dir + 1 : cur.dir - 1;
            horses[cur.idx].dir = cur.dir;
            nX += (2 * dx[cur.dir]);
            nY += (2 * dy[cur.dir]);
            if (nX < 0 || nY < 0 || nX >= N || nY >= N || color[nX][nY] == 2) {
                exist[cur.x][cur.y].add(cur.idx);
                while(!deque.isEmpty()) {
                    exist[cur.x][cur.y].add(deque.pollLast().idx);
                }
                return;
            }
        }

        deque.add(cur);
        if (color[nX][nY] == 1) {               // 빨간색
            while(!deque.isEmpty()) {
                Point next = deque.pollFirst();
                horses[next.idx].x = nX;
                horses[next.idx].y = nY;
                exist[nX][nY].add(next.idx);
            }
        } else if (color[nX][nY] == 0) {         // 흰색
            while(!deque.isEmpty()) {
                Point next = deque.pollLast();
                horses[next.idx].x = nX;
                horses[next.idx].y = nY;
                exist[nX][nY].add(next.idx);
            }
        }
    }


    private static boolean isFinished() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (exist[i][j].size() >= 4) return true;
            }
        }

        return false;
    }
}