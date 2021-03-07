import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution14891 {
    static final int LEFT_SIDE = 6, RIGHT_SIDE = 2;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] chain = new int[5][8];   // 0 => 12시
    static int K;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                chain[i][j] = Character.getNumericValue(line.charAt(j));
            }
        }


        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            String[] rotateLine = br.readLine().split(" ");
            move(Integer.parseInt(rotateLine[0]), Integer.parseInt(rotateLine[1]));
        }

        int ret = 0;
        for (int i = 1; i < 5; i++) {
            ret += (chain[i][0] * Math.pow(2, i-1));
        }
        System.out.println(ret);
    }

    private static void move(int idx, int direction) {

        int curLeft = chain[idx][LEFT_SIDE];
        int curRight = chain[idx][RIGHT_SIDE];
        int curDirection = direction;
        // rotate current
        rotate(idx, direction);

        //left
        for (int i = idx - 1; i > 0; i--) {
            if (chain[i][RIGHT_SIDE] == curLeft)
                break;
            direction *= -1;
            curLeft = chain[i][LEFT_SIDE];
            rotate(i, direction);
        }

        direction = curDirection;
        //right
        for (int i = idx + 1; i < 5; i++) {
            if (chain[i][LEFT_SIDE] == curRight)
                break;

            direction *= -1;
            curRight = chain[i][RIGHT_SIDE];
            rotate(i, direction);
        }
    }

    private static void rotate(int idx, int direction) {
        if (direction == 1) {
            int temp = chain[idx][7];
            for (int i = 7; i > 0; i--) {
                chain[idx][i] = chain[idx][i-1];
            }
            chain[idx][0] = temp;
        } else {
            int temp = chain[idx][0];
            for (int i = 0; i < 7; i++) {
                chain[idx][i] = chain[idx][i+1];
            }
            chain[idx][7] = temp;
        }
    }

}