import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int G, P, cnt = 0;
    static int[] gate, airplane;
    public static void main(String[] args) throws IOException {
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        gate = new int[G + 1];
        airplane = new int[P];
        for (int i = 0; i < P; i++) {
            int cur = Integer.parseInt(br.readLine());
            airplane[i] = cur;
        }

        for (int i = 0; i < P; i++) {
            int destGate = airplane[i];
            boolean isContinous = false;
            for (int j = destGate; j >= 1; j--) {
                if (gate[j] != 0) continue;
                    cnt++;
                    gate[j] = 1;
                    isContinous = true;
                    break;
            }
            if (!isContinous) break;
        }

        System.out.println(cnt);
    }
}