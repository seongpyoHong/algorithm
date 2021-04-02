import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

// 백트래킹
public class Solution3980 {
    static class Pair {
        int position;
        int score;

        public Pair(int position, int score) {
            this.position = position;
            this.score = score;
        }
        
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashMap<Integer, ArrayList<Pair>> scores;
    static int maxScore;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int[] result = new int[T];
        for (int i = 0; i < T; i++) {
            result[i] = getResult();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T - 1; i++) {
            sb.append(result[i]);
            sb.append("\n");
        }

        sb.append(result[T - 1]);
        System.out.println(sb.toString());
    }

    private static int getResult() throws IOException {
        scores = new HashMap<>();
        maxScore = 0;

        for (int i = 0; i < 11; i++) {
            scores.put(i, new ArrayList<>());
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 11; j++) {
                if (line[j].equals("0")) continue;
                scores.get(i).add(new Pair(j, Integer.parseInt(line[j])));
            }
        }

        getMaxScore(0, 0, 0);
        return maxScore;
    }

    private static void getMaxScore(int idx, int flag, int total) {
        if (idx == 11) {
            if (maxScore < total) {
                maxScore = total;
            }
            return;
        }

        for (int i = 0; i < scores.get(idx).size(); i++) {
            Pair player = scores.get(idx).get(i);
            if ((flag & 1 << player.position) > 0) continue;
            getMaxScore(idx + 1, flag | 1 << player.position, total + player.score);
        }
    }
}