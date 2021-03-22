import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

// O(V^2)
public class Solution1753 {
    static class Edge implements Comparable<Edge> {
        int id;
        int weight;
        public Edge(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static final int INF = 10 * 20001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Edge>[] adj;
    static boolean[] visited;
    static int[] distance;
    static int V, E, start;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        adj = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        distance = new int[V + 1];

        start = Integer.parseInt(br.readLine());

        for (int i = 1; i < V + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            adj[src].add(new Edge(dest, weight));
        }

        // init distance
        for (int i = 1; i < V + 1; i++) {
            if (start == i) {
                distance[i] = 0;
                continue;
            }
            distance[i] = INF;
        }

        djkstra();

        for (int i = 1; i < V + 1; i++) {
            if (distance[i] == INF)
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }
    }

    private static void djkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.weight > distance[cur.id]) continue;
            for (int i = 0; i < adj[cur.id].size(); i++) {
                Edge next = adj[cur.id].get(i);
                if (distance[next.id] > distance[cur.id] + next.weight) {
                    distance[next.id] = distance[cur.id] + next.weight;
                    pq.add(new Edge(next.id, distance[next.id]));
                }
            }
        }
    }
}