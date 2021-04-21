import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

// 투 포인터 & 슬라이딩 윈도우
public class SolutionKakao5 {
    public static void main(String[] args) {
        String[] gems = {"C", "B", "D", "A", "C"};
        System.out.println(Arrays.toString(solution(gems)));
    }

    private static int[] solution(String[] gems) {
        int[] ret = new int[2];
        HashSet<String> entry = new HashSet<>();
        for (int i = 0; i < gems.length; i++) {
            entry.add(gems[i]);
        }
        int total = entry.size();

        HashMap<String, Integer> buy = new HashMap<>();
        int start = 0;
        int end = 0;
        int cnt = 0;


        for (; end < gems.length; end++) {
            if (!buy.containsKey(gems[end])) {
                cnt++;
                buy.put(gems[end], 0);
            }

            buy.put(gems[end], buy.get(gems[end]) + 1);
            if (cnt == total) break;
        }

        int buyCnt = end - start + 1;
        ret[0] = start + 1;
        ret[1] = end + 1;

        while (end < gems.length) {
            if (cnt == total) {
                if (buyCnt > end - start + 1) {
                    buyCnt = end - start + 1;
                    ret[0] = start + 1;
                    ret[1] = end + 1;
                }

                int prev = buy.get(gems[start]);
                if (prev == 1) {
                    cnt--;
                    buy.remove(gems[start]);
                } else {
                    buy.put(gems[start], prev - 1);
                }
                start++;

            } else {
                if (++end == gems.length) break;
                if (!buy.containsKey(gems[end])) {
                    cnt++;
                    buy.put(gems[end], 1);
                } else {
                    buy.put(gems[end], buy.get(gems[end]) + 1);
                }
            }
        }
        return ret;
    }
}