import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution16562 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final String WORST = "Oh no";
    static int N, M, K;
    static int[] friends, pay, groupPay;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        makeSet();
        pay = new int[N + 1];
        groupPay = new int[N + 1];
        String[] pays = br.readLine().split(" ");
        for (int i = 1; i < N + 1; i++) {
            pay[i] = Integer.parseInt(pays[i - 1]);
        }
        for (int i = 0; i < M; i++) {
            String[] friends = br.readLine().split(" ");
            union(Integer.parseInt(friends[0]), Integer.parseInt(friends[1]));
        }

        for (int i = 1; i < N + 1; i++) {
            groupPay[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < N + 1; i++) {
            int groupIdx = findGroup(i);
            groupPay[groupIdx] = Math.min(groupPay[groupIdx], pay[i]);
        }

        long ret = 0L;
        for (int i = 1; i < N + 1; i++) {
            if (groupPay[i] == Integer.MAX_VALUE) continue;
            ret += groupPay[i];
        }

        System.out.println((ret <= K) ? ret : WORST);
    }

    private static boolean union(int f1, int f2) {
        int root1 = findGroup(f1);
        int root2 = findGroup(f2);
        if (root1 == root2) return false;
        friends[root1] = findGroup(root2);
        return true;
    }

    private static int findGroup(int target) {
        if (friends[target] == target) return target;
        return friends[target] = findGroup(friends[target]);
    }

    private static void makeSet() {
        friends = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            friends[i] = i;
        }
    }
}