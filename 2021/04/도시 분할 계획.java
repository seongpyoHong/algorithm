import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

// MST
public class Solution1647 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Integer, ArrayList<Pair>> adj = new HashMap<>();
    static int N, M, INF = 100000001;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);

            if (!adj.containsKey(src))
                adj.put(src, new ArrayList<>());
            if (!adj.containsKey(dest))
                adj.put(dest, new ArrayList<>());

            adj.get(src).add(new Pair(dest, weight));
            adj.get(dest).add(new Pair(src, weight));
        }

        System.out.println(prim());
    }

    private static int prim() {
        boolean[] visited = new boolean[N + 1];
        int total = 0;
        int max = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(1, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            if (visited[cur.idx]) continue;

            total += cur.weight;
            max = Math.max(max, cur.weight);
            visited[cur.idx] = true;
            for (Pair next : adj.get(cur.idx)) {
                if (visited[next.idx]) continue;
                pq.add(new Pair(next.idx, next.weight));
            }
        }
        return total - max;
    }

    static class Pair implements Comparable<Pair> {
        int idx;
        int weight;

        public Pair(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }
}
