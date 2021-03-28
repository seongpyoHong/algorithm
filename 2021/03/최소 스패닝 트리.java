import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution1197 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int INF = 1000001, V, E;
    static ArrayList<Edge>[] distance;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        distance = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            distance[i]= new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            distance[src].add(new Edge(dest, weight));
            distance[dest].add(new Edge(src, weight));
        }

        System.out.println(prim());
    }

    private static int prim() {
        int total = 0;
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.id]) continue;
            visited[cur.id] = true;
            total += cur.weight;
            for(Edge e : distance[cur.id]) {
                if (visited[e.id]) continue;
                pq.add(e);
            }
        }
        return total;
    }

    static class Edge implements Comparable<Edge> {
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
}