import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 정렬, 투 포인터
public class Solution2470 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(input[i]);
            if (cur > 0)
                positive.add(cur);
            else
                negative.add(cur);
        }
        positive.sort(Integer::compareTo);
        negative.sort(Collections.reverseOrder());
        int positiveMin = (positive.size() >= 2) ? Math.abs(positive.get(0) + positive.get(1)) : Integer.MAX_VALUE;
        int negativeMin = (negative.size() >= 2) ? Math.abs(negative.get(0) + negative.get(1)) : Integer.MAX_VALUE;

        int[] result = new int[2];
        int min = Math.min(positiveMin, negativeMin);

        int positiveIdx = 0;
        int negativeIdx = 0;
        int positiveDidx = 0;
        int negativeDidx = 1;
        int curPos = -1000000001, curNeg = 1000000001;
        while (positiveIdx < positive.size() && negativeIdx < negative.size()) {
            curPos = positive.get(positiveIdx);
            curNeg = negative.get(negativeIdx);
            int curMin = Integer.MAX_VALUE;
            int[] temp = new int[2];
            while(positiveIdx < positive.size() && negativeIdx < negative.size()) {
                curPos = positive.get(positiveIdx);
                curNeg = negative.get(negativeIdx);
                if (curMin < Math.abs(curPos + curNeg)) {
                    positiveIdx -= positiveDidx;
                    negativeIdx -= negativeDidx;
                    positiveDidx = (positiveDidx + 1) % 2;
                    negativeDidx = (negativeDidx + 1) % 2;
                    break;
                }
                curMin = Math.abs(curPos + curNeg);
                temp[0] = curNeg;
                temp[1] = curPos;
                positiveIdx += positiveDidx;
                negativeIdx += negativeDidx;
            }

            if (min > curMin) {
                min = curMin;
                result[0] = temp[0];
                result[1] = temp[1];
            }

            positiveIdx += positiveDidx;
            negativeIdx += negativeDidx;
        }


        positiveIdx = (positiveIdx == positive.size()) ? positiveIdx - 1 : positiveIdx;
        negativeIdx = (negativeIdx == negative.size()) ? negativeIdx - 1 : negativeIdx;
        while (!positive.isEmpty() && positiveIdx < positive.size()) {
            curPos = positive.get(positiveIdx++);
            if (min > Math.abs(curPos + curNeg)) {
                min = Math.abs(curPos + curNeg);
                result[0] = curNeg;
                result[1] = curPos;
            }
        }

        while (!negative.isEmpty() && negativeIdx < negative.size()) {
            curNeg = negative.get(negativeIdx++);
            if (min > Math.abs(curPos + curNeg)) {
                min = Math.abs(curPos + curNeg);
                result[0] = curNeg;
                result[1] = curPos;
            }
        }

        if (negativeMin <= positiveMin && negativeMin <= min) {
            System.out.println(negative.get(1) + " " + negative.get(0));
        } else if (positiveMin <= negativeMin && positiveMin <= min) {
                System.out.println(positive.get(0) + " " + positive.get(1));
        } else if (min <= positiveMin && min <= negativeMin) {
            System.out.println(result[0] + " " + result[1]);
        }
    }
}