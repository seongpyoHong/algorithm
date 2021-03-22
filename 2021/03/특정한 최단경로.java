import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution1504 {
    static class Edge implements Comparable<Edge>{
        int id;
        long weight;

        public Edge(int id, long weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static final int INF = 800 * 1001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int a,b,N,E;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        map = new int[N+1][N+1];
        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            map[src][dest] = weight;
            map[dest][src] = weight;
        }

        String[] destLine = br.readLine().split(" ");
        a = Integer.parseInt(destLine[0]);
        b = Integer.parseInt(destLine[1]);

        long startToA = dijstra(1, a);
        long startToB = dijstra(1,b);
        long AToB = dijstra(a,b);
        long BToA = dijstra(b,a);
        long BToN = dijstra(b, N);
        long AToN = dijstra(a, N);
        if ((startToA == INF || AToB == INF || BToN == INF) && (startToB == INF || BToA == INF  || AToN == INF)) {
            System.out.println(-1);
        } else if (startToA == INF || AToB == INF || BToN == INF) {
            System.out.println(startToB + BToA + AToN);
        } else if (startToB == INF || BToA == INF || AToN == INF) {
            System.out.println(startToA + AToB + BToN);
        } else {
            System.out.println(Math.min(startToB + BToA + AToN, startToA + AToB + BToN));
        }
    }

    private static long dijstra(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        long[] distance = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            distance[i] = INF;
        }
        distance[start] = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.id == end) break;
            if (cur.weight > distance[cur.id]) continue;
            for (int i = 1; i < N + 1; i++) {
                if (map[cur.id][i] == 0 || distance[i] <= distance[cur.id] + map[cur.id][i]) continue;
                distance[i] = distance[cur.id] + map[cur.id][i];
                pq.add(new Edge(i, distance[i]));
            }
        }

        return distance[end];
    }

}