import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution1967 {
    static class Node {
        int idx;
        int weight;

        public Node(int idx, int dist) {
            this.idx = idx;
            this.weight = dist;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, maxLength, restartIdx;
    static ArrayList<Node>[] nodes;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N+1];
        for (int i = 0; i < N-1; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight =Integer.parseInt(line[2]);
            if (nodes[src] == null)
                nodes[src] = new ArrayList<>();
            if (nodes[dest] == null)
                nodes[dest] = new ArrayList<>();
            nodes[src].add(new Node(dest, weight));
            nodes[dest].add(new Node(src, weight));

        }

        if (N == 1) {
            System.out.println(0);
            return;
        }
        visited = new boolean[N+1];
        dfs(1, 0);

        maxLength = 0;
        visited = new boolean[N+1];
        dfs(restartIdx, 0);
        System.out.println(maxLength);
    }

    private static void dfs(int src, int weight) {
        if (visited[src]) return;
        visited[src] = true;

        if (maxLength < weight) {
            maxLength = weight;
            restartIdx = src;
        }

        for (int i = 0; i < nodes[src].size(); i++) {
            if (visited[nodes[src].get(i).idx]) continue;

            dfs(nodes[src].get(i).idx, weight + nodes[src].get(i).weight);
        }
    }
}