import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Solution15685 {
    static class Curve {
        int x;
        int y;
        int direction;
        int cnt;

        public Curve(String[] line) {
            this.x = Integer.parseInt(line[0]);
            this.y = Integer.parseInt(line[1]);
            this.direction = Integer.parseInt(line[2]);
            this.cnt = Integer.parseInt(line[3]);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    static boolean[][] visited = new boolean[101][101];
    static ArrayList<Integer> move = new ArrayList<>();
    static Curve[] curves;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        curves = new Curve[N];
        for (int i = 0; i < N; i++) {
            curves[i] = new Curve(br.readLine().split(" "));
        }

        for (int i = 0; i < N; i++) {
            makeCurve(curves[i]);
        }

        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (!visited[i][j] || !visited[i][j+1] || !visited[i+1][j] || !visited[i+1][j+1]) continue;
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void makeCurve(Curve curve) {
        int startX = curve.x;
        int startY = curve.y;
        ArrayList<Integer> moves = getMoves(curve.direction, curve.cnt);

        for (int i = 0; i < moves.size(); i++) {
            visited[startX][startY] = true;
            startX += dx[moves.get(i)];
            startY += dy[moves.get(i)];
        }
        
        visited[startX][startY] = true;
    }

    private static ArrayList<Integer> getMoves(int direction, int cnt) {
        ArrayList<Integer> ret = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        ret.add(direction);
        while(--cnt >= 0) {
            while (!s.isEmpty()) {
                ret.add((s.pop() + 1) % 4);
            }

            for (int i = 0; i < ret.size(); i++) {
                s.add(ret.get(i));
            }
        }

        while (!s.isEmpty()) {
            ret.add((s.pop() + 1) % 4);
        }

        return ret;
    }
}