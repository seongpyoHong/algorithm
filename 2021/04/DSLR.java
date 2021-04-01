import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BFS
public class Solution9019 {
    static class Number {
        int[] numArr = new int[4];
        StringBuilder operand = new StringBuilder();

        public Number(int number, StringBuilder operand) {
            numArr = convert(number);
            this.operand = operand;
        }

        public int getD() {
            int number = getNumber(numArr);
            number = (2 * number > 9999) ? (2 * number) % 10000 : 2 * number;
            return number;
        }

        public int getS() {
            int number = getNumber(numArr);
            number = (number - 1 < 0 ) ? 9999 : number - 1;
            return number;
        }

        public int getL() {
            int[] ret = new int[4];
            ret[3] = numArr[0];
            for (int i = 1; i < 4; i++) {
                ret[i - 1] = numArr[i];
            }
            return getNumber(ret);
        }

        public int getR() {
            int[] ret = new int[4];
            ret[0] = numArr[3];
            for (int i = 0; i < 3; i++) {
                ret[i + 1] = numArr[i];
            }
            return getNumber(ret);
        }

        private int[] convert(int number) {
            int[] ret = new int[4];
            int weight = 1000;
            int idx = 0;
            while(weight > 0) {
                ret[idx++] = number / weight;
                number %= weight;
                weight /= 10;
            }
            return ret;
        }

        private int getNumber(int[] arr) {
            int ret = 0;
            int weight = 1;
            for (int i = 3; i >= 0; i--) {
                int cur = arr[i];
                ret += (cur * weight);
                weight *= 10;
            }
            return ret;
        }

        public StringBuilder getOperand() {
            return this.operand;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        String[] result = new String[T];
        for (int i = 0; i < T; i++) {
            result[i] = getResult();
        }

        for (int i = 0; i < T; i++) {
            System.out.println(result[i]);
        }
    }

    private static String getResult() throws IOException {
        String[] input = br.readLine().split(" ");
        int src = Integer.parseInt(input[0]);
        int dest = Integer.parseInt(input[1]);
        boolean[] visited = new boolean[10001];
        Queue<Number> q = new LinkedList<>();
        q.add(new Number(src, new StringBuilder()));
        visited[src] = true;
        while(!q.isEmpty()) {
            Number cur = q.poll();
            if (cur.getNumber(cur.numArr) == dest) {
                return cur.getOperand().toString();
            }

            int D = cur.getD();
            if (!visited[D] && cur.getNumber(cur.numArr) != D) {
                visited[D] = true;
                q.add(new Number(D, new StringBuilder(cur.getOperand() + "D")));
            }

            int S = cur.getS();
            if (!visited[S] && cur.getNumber(cur.numArr) != S) {
                visited[S] = true;
                q.add(new Number(S, new StringBuilder(cur.getOperand() + "S")));
            }

            int L = cur.getL();
            if (!visited[L] && cur.getNumber(cur.numArr) != L) {
                visited[L] = true;
                q.add(new Number(L, new StringBuilder(cur.getOperand() + "L")));
            }
            int R = cur.getR();
            if (!visited[R] && cur.getNumber(cur.numArr) != R) {
                visited[R] = true;
                q.add(new Number(R, new StringBuilder(cur.getOperand() + "R")));
            }
        }
        return "";
    }
}