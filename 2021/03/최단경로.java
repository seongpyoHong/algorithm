// O(V^2)
public class Solution1753 {
    static final int INF = 10 * 20001;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Integer, Integer>[] adj;
    static boolean[] visited;
    static int[] distance;
    static int V, E, start;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);
        adj = new HashMap[V + 1];
        visited = new boolean[V + 1];
        distance = new int[V + 1];

        start = Integer.parseInt(br.readLine());

        for (int i = 1; i < V + 1; i++) {
            adj[i] = new HashMap<>();
        }

        for (int i = 0; i < E; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            if (adj[src].containsKey(dest) && adj[src].get(dest) > weight) {
                adj[src].put(dest, weight);
            } else if (!adj[src].containsKey(dest)) {
                adj[src].put(dest, weight);
            }
        }

        // init distance
        for (int i = 1; i < V + 1; i++) {
            if (start == i) {
                distance[i] = 0;
                continue;
            }

            if (!adj[start].containsKey(i))
                distance[i] = INF;
            else
                distance[i] = adj[start].get(i);
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
      for (int id = 1; id < V + 1; id++) {
          int idx = -1;
          int minDist = INF;
          for (int i = 1; i < V + 1; i++) {
              if (visited[i] || minDist < distance[i]) continue;
              idx = i;
              minDist = distance[i];
          }

          visited[idx] = true;
          if (minDist == INF) continue;
          for(Integer dest : adj[idx].keySet()) {
              int weight = adj[idx].get(dest);
              if (visited[dest] || distance[dest] <= distance[idx] + weight) continue;
              distance[dest] = distance[idx] + weight;
          }
      }
  }
}