import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr, upside, downside;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 2];
        upside = new int[N + 2];
        downside = new int[N + 2];

        String[] line = br.readLine().split(" ");
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(line[i - 1]);
        }

        getUpside();
        getDownside();

        int maxLength = 0;
        for (int i = 1; i < N + 1; i++) {
            maxLength = Math.max(maxLength, upside[i] + downside[i] - 1);
        }
        System.out.println(maxLength);
    }

    private static void getDownside() { ;
        downside[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i + 1; j <= N + 1; j++) {
                if (arr[i] > arr[j] && downside[i] < downside[j] + 1) {
                    downside[i] = downside[j] + 1;
                }
            }
        }
    }

    private static void getUpside() {
        upside[1] = 1;
        for (int i = 2; i < N + 1; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (arr[i] > arr[j] && upside[i] < upside[j] + 1) {
                    upside[i] = upside[j] + 1;
                }
            }
        }
    }
}