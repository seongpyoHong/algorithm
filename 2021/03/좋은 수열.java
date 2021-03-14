import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2661 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] num;
    static boolean isContinue = true;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        num = new int[N];
        num[0] = 1;
        getNums(1);
    }

    private static void getNums(int idx) {
        if (!isContinue) return;
        if (idx == N) {
            printResult();
            isContinue = false;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            num[idx] = i;
            if (!isAvailable(idx)) continue;
            getNums(idx + 1);
        }
    }

    private static void printResult() {
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]);
        }
    }

    private static boolean isAvailable(int idx) {
        int size = 0;
        int cnt = 0;
        while(++size <= (idx + 1) / 2) {
            int front = idx - size;
            int behind = idx;
            boolean isSame = true;
            for (int s = 0; s < size; s++) {
                if (num[front--] != num[behind--]) {
                    isSame = false;
                    break;
                }
            }
            if (!isSame) cnt++;
        }
        if (cnt == size - 1) return true;
        return false;
    }

}