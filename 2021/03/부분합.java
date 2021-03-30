import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1806 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);

        arr = new int[N + 1];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        int start = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        while(start <= end && end <= N) {
            if (sum < S) sum += arr[end++];
            else {
                minLength = Math.min(minLength, end-start);
                sum -= arr[start++];
            }
        }
        System.out.println((minLength == Integer.MAX_VALUE) ? 0 : minLength);
    }
}