import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, INF = 10001;
    static int[][] adj;
    static int[] path;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new int[N + 1][N + 1];
        path = new int[N + 1];

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            adj[src][dest] = weight;
            adj[dest][src] = weight;
        }

        dijkstra();
        StringBuilder sb = new StringBuilder();
        sb.append(N - 1 + "\n");
        for (int i = 2; i <= N; i++) {
            sb.append(i);
            sb.append(" ");
            sb.append(path[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        distance[1] = 0;
        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (distance[cur.idx] < cur.dist) continue;

            for (int next = 1; next <= N; next++) {
                if (next == cur.idx || adj[cur.idx][next] == 0) continue;

                if (distance[next] <= distance[cur.idx] + adj[cur.idx][next]) continue;
                distance[next] = distance[cur.idx] + adj[cur.idx][next];
                path[next] = cur.idx;
                pq.add(new Pair(next, distance[next]));
            }
        }
    }

    static class Pair implements Comparable<Pair>{
        int idx;
        int dist;

        public Pair(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            return this.dist - o.dist;
        }
    }
}