import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Solution16922 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] num = {1, 5, 10, 50};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> ret = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            set.add(num[i]);
            ret.add(num[i]);
        }

        for (int i = 1; i < N; i++) {
            ret.clear();
            for (Integer number : set) {
                for (int j = 0; j < 4; j++) {
                    ret.add(number + num[j]);
                }
            }

            set.clear();
            for (Integer number : ret) {
                set.add(number);
            }
        }

        System.out.println(ret.size());
    }
}