import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution16953 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int A, B;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        A = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(B);
        int dist = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int cur = q.poll();
                if (cur == A) return dist;

                if (cur % 10 == 1 && (cur - 1) / 10 >= A)
                    q.add((cur - 1) / 10);

                if (cur % 2 == 0 && cur / 2 >= A)
                    q.add(cur / 2);
            }
            dist++;
        }

        return -1;
    }
}