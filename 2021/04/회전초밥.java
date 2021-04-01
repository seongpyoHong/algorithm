import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 투 포인터
public class Solution15961 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, D, K, C;
    static int[] map;
    static int[] check;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        D = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        C = Integer.parseInt(input[3]);

        check = new int[D + 1];
        map = new int[N + K];
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < K; i++) {
            map[N + i] = map[i];
        }

        int ret = 0;
        int start = 0;
        int end = 0;
        int selectCnt = 0;
        int typeCnt = 0;
        while(start < N && end < N + K) {
            if(selectCnt < K) {
                selectCnt++;
                if (check[map[end++]]++ == 0) typeCnt++;
                if (check[C] == 0)
                    ret = Math.max(ret, typeCnt + 1);
                else
                    ret = Math.max(ret, typeCnt);
            } else {
                if (--check[map[start++]] == 0) typeCnt--;
                selectCnt--;
            }
        }

        System.out.println(ret);
    }
}