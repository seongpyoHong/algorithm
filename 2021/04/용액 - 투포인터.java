import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2467 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(map);
        int left = 0, right = N - 1;
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while(left < right) {
            int sum = map[right] + map[left];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                answer[0] = map[left];
                answer[1] = map[right];
            }

            if (sum > 0)
                right--;
            else
                left++;
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}