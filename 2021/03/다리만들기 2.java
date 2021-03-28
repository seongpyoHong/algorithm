import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution17472 {
    static class Edge implements Comparable<Edge>{
        int id;
        int weight;
        public Edge(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, id = 1, INF = 20000;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    static int[][] map, adjMatrix;
    static HashMap<Integer, Queue<Pair>> islands = new HashMap<>();
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        findIsland();
        updateDistance();
        int totalDist = prim();
        System.out.println((totalDist >= INF) ? -1 : totalDist);
    }

    private static int prim() {
        int totalDist = 0;
        boolean[] checked = new boolean[adjMatrix.length];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (checked[cur.id]) continue;
            totalDist += cur.weight;
            checked[cur.id] = true;
            for (int i = 1; i < adjMatrix.length; i++) {
                if (checked[i]) continue;
                pq.add(new Edge(i, adjMatrix[cur.id][i]));
            }
        }
        return totalDist;
    }

    private static void updateDistance() {
        adjMatrix = new int[id][id];
        for (int i = 0; i < id; i++) {
            for (int j = 0; j < id; j++) {
                if (i == j) adjMatrix[i][j] = 0;
                else adjMatrix[i][j] = INF;
            }
        }
        while(--id >= 1) {
            findOtherIsland();
        }
    }

    private static void findOtherIsland() {
        visited = new boolean[N][M];
        Queue<Pair> q = islands.get(id);
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int x = cur.x;
                int y = cur.y;
                int dist = 0;
                while(true) {
                    x += dx[i];
                    y += dy[i];
                    if (x < 0 || y < 0 || x >= N || y >= M || map[x][y] == id) break;
                    if (map[x][y] != 0) {
                        if (dist >= 2) {
                            adjMatrix[id][map[x][y]] = Math.min(adjMatrix[id][map[x][y]], dist);
                            adjMatrix[map[x][y]][id] = Math.min(adjMatrix[map[x][y]][id], dist);
                        }
                        break;
                    }
                    dist++;
                }
            }
        }
    }

    private static void findIsland() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                islands.put(id, new LinkedList<>());
                markId(i, j);
                id++;
            }
        }
    }

    private static void markId(int startX, int startY) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startX, startY));
        islands.get(id).add(new Pair(startX, startY));
        visited[startX][startY] = true;
        map[startX][startY] = id;
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (nX < 0 || nY < 0 || nX >= N || nY >= M || map[nX][nY] == 0 || visited[nX][nY]) continue;
                map[nX][nY] = id;
                visited[nX][nY] = true;
                islands.get(id).add(new Pair(nX, nY));
                q.add(new Pair(nX, nY));
            }
        }
    }
}