import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution1916 {
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

        @Override
        public String toString() {
            return "Edge{" +
                    "id=" + id +
                    ", weight=" + weight +
                    '}';
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // idx = row * i + j
    static int[][] map;
    static int[] dx = {1, -1, 0, 0} , dy = {0,0, 1, -1};
    static int N, INF = 10 * 125 * 125;
    static ArrayList<Edge>[] adj;

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> result = new ArrayList<>();
        while(true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            result.add(getResult());
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.printf("Problem %d: %d\n", i + 1, result.get(i));
        }
    }
    public static int getResult() throws IOException {
        map = new int[N][N];
        adj = new ArrayList[N*N];
        for (int i = 0; i < N * N ; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        initialize();
        return dikstra();
    }

    private static int dikstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] distance = new int[N * N ];
        pq.add(new Edge(0, map[0][0]));
        for (int i = 0; i < N * N; i++) {
            distance[i] = INF;
        }

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (distance[cur.id] < cur.weight) continue;
            distance[cur.id] = cur.weight;

            if (cur.id == N * N - 1) break;
            for(Edge e : adj[cur.id]) {
                if (distance[e.id] > distance[cur.id] + e.weight) {
                    pq.add(new Edge(e.id, distance[cur.id] + e.weight));
                    distance[e.id] = distance[cur.id] + e.weight;
                }
            }
        }
        return distance[N * N - 1];
    }

    private static void initialize() {
        for (int i = 0; i < N * N; i++) {
            int x = i / N;
            int y = i % N;
            for (int j = 0; j < 4; j++) {
                int nX = x + dx[j];
                int nY = y + dy[j];
                if (nX < 0 || nY < 0 || nX >= N || nY >= N ) continue;
                adj[i].add(new Edge(nX * N + nY, map[nX][nY]));
            }
        }
    }
}