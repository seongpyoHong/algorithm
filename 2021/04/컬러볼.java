import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

// prefix sum
public class Solution10800 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] prefix;
    static ArrayList<Ball> balls = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        prefix = new int[N + 1];

        // input
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int type = Integer.parseInt(line[0]);
            int weight = Integer.parseInt(line[1]);

            balls.add(new Ball(i, type, weight, 0));
        }

        // sort By type and weight
        balls.sort((e1, e2) -> {
            if (e1.weight - e2.weight != 0)
                return e1.weight - e2.weight;
            else
                return e1.type - e2.type;
        });

        int totalPrefix = 0, prevWeight = 0, prevType = 0, dupCnt = 0;
        // calc
        for (int i = 0; i < balls.size(); i++) {
            if (prevWeight != balls.get(i).weight) {
                dupCnt = 0;
                balls.get(i).prefix = (totalPrefix - prefix[balls.get(i).type]);
            } else if (prevType != balls.get(i).type && prevWeight == balls.get(i).weight){
                dupCnt++;
                balls.get(i).prefix = (totalPrefix - prefix[balls.get(i).type] - (dupCnt * prevWeight));
            } else if (prevType == balls.get(i).type && prevWeight == balls.get(i).weight) {
                dupCnt++;
                balls.get(i).prefix = balls.get(i - 1).prefix;
            }

            // update total
            prefix[balls.get(i).type] += balls.get(i).weight;
            totalPrefix += balls.get(i).weight;

            //update prev
            prevWeight = balls.get(i).weight;
            prevType = balls.get(i).type;
        }

        //sort by id
        balls.sort(Comparator.comparingInt(e -> e.idx));

        StringBuilder sb = new StringBuilder();
        for (Ball ball : balls) {
            sb.append(ball.prefix);
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }


    static class Ball {
        int idx;
        int type;
        int weight;
        int prefix;

        public Ball(int idx, int type, int weight, int prefix) {
            this.idx = idx;
            this.type = type;
            this.weight = weight;
            this.prefix = prefix;
        }
    }
}