import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution11286 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        Queue<Integer> q = new PriorityQueue<>((e1,e2) -> {
            if (Math.abs(e1) > Math.abs(e2))
                return 1;
            else if (Math.abs(e1) == Math.abs(e2) && e1 > e2)
                return 1;
            return -1;
        });

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());
            if (cur != 0)
                q.add(cur);
            else if (q.size() == 0)
                sb.append(0 + "\n");
            else
                sb.append(q.poll() + "\n");
        }

        System.out.print(sb);
    }
}