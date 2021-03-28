import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution1238 {
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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, X, INF = 100 * 1001;
    static ArrayList<Edge>[] adj;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        X = Integer.parseInt(input[2]);

        // initialize
        adj = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            adj[src].add(new Edge(dest, weight));
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            int distanceToX = getDistance(i, X);
            int distanceFromX = getDistance(X, i);
            result.add(distanceToX + distanceFromX);
        }

        result.sort(Integer::compareTo);
        System.out.println(result.get(result.size() - 1));
    }

    private static int getDistance(int start, int end) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] distance = new int[N  + 1];
        for (int i = 1; i < N + 1; i++) {
            if (i == start) distance[i] = 0;
            else distance[i] = INF;
        }
        pq.add(new Edge(start, 0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (distance[cur.id] < cur.weight) continue;
            for(Edge e : adj[cur.id]) {
                if (distance[e.id] > distance[cur.id] + e.weight) {
                    distance[e.id] = distance[cur.id] + e.weight;
                    pq.add(new Edge(e.id, distance[cur.id] + e.weight));
                }
            }
        }
        return distance[end];
    }
}