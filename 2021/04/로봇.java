import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// bfs
public class Solution1726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static boolean[][][] visited;
    static int M, N;
    static Operation start, end;

    static int[] dy = {1, -1, 0, 0}, dx = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        map = new int[M][N];
        visited = new boolean[M][N][4];
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        String[] startLine = br.readLine().split(" ");
        start = new Operation(Integer.parseInt(startLine[0]) - 1, Integer.parseInt(startLine[1]) - 1, Integer.parseInt(startLine[2]) - 1, 0);

        String[] endLine = br.readLine().split(" ");
        end = new Operation(Integer.parseInt(endLine[0]) - 1, Integer.parseInt(endLine[1]) - 1, Integer.parseInt(endLine[2]) - 1, 0);

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Operation> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y][start.direction] = true;
        while (!q.isEmpty()) {
            Operation cur = q.poll();
            if (cur.equals(end)) return cur.dist;

            // go
            for (int i = 1; i <= 3; i++) {
                int nX = cur.x + dx[cur.direction] * i;
                int nY = cur.y + dy[cur.direction] * i;

                if (nX < 0 || nY < 0 || nX >= M || nY >= N || map[nX][nY] == 1) break;
                if (visited[nX][nY][cur.direction]) continue;
                visited[nX][nY][cur.direction] = true;
                q.add(new Operation(nX, nY, cur.direction, cur.dist + 1));
            }

            // turn left
            int leftDirection = getLeftDirection(cur.direction);
            if (!visited[cur.x][cur.y][leftDirection]) {
                visited[cur.x][cur.y][leftDirection] = true;
                q.add(new Operation(cur.x, cur.y, leftDirection, cur.dist + 1));
            }

            // turn right
            int rightDirection = getRightDirection(cur.direction);
            if (!visited[cur.x][cur.y][rightDirection]) {
                visited[cur.x][cur.y][rightDirection] = true;
                q.add(new Operation(cur.x, cur.y, rightDirection, cur.dist + 1));
            }
        }
        return 0;
    }

    private static int getLeftDirection(int direction) {
        if (direction == 0 || direction == 1)
            return direction + 2;
        else if (direction == 2)
            return 1;
        else
            return 0;
    }

    private static int getRightDirection(int direction) {
        if (direction == 0)
            return 3;
        else if (direction == 1)
            return 2;
        else
            return direction - 2;
    }

    static class Operation {
        int x;
        int y;
        int direction;
        int dist;

        public Operation(int x, int y, int direction, int dist) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Operation operation = (Operation) o;
            if (x != operation.x) return false;
            if (y != operation.y) return false;
            return direction == operation.direction;
        }
    }
}