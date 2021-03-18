import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution1976 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] root;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        makeSet();
        for (int i = 1; i < N + 1; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 1; j < N + 1; j++) {
                if (line[j-1].equals("0")) continue;
                union(i, j);
            }
        }
        String[] travelPath = br.readLine().split(" ");
        int start = Integer.parseInt(travelPath[0]);
        boolean isPossible = true;
        for (int i = 1; i < travelPath.length; i++) {
            int next = Integer.parseInt(travelPath[i]);
            if (find(start) == find(next)) continue;
            isPossible = false;
        }
        System.out.println((isPossible) ? "YES" : "NO");
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;
        root[rootA] = rootB;
    }

    private static int find(int city) {
        if(root[city] == city) return city;
        return root[city] = find(root[city]);
    }

    private static void makeSet() {
        root = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            root[i] = i;
        }
    }
}