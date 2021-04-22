import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;

// 자료구조
public class SolutionKakao1 {

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        System.out.println(Arrays.toString(solution(s)));
    }

    private static int[] solution(String s) {
        String[] subsets = s.substring(2, s.length() - 2).split("},\\{");
        Arrays.sort(subsets, Comparator.comparingInt(String::length));
        LinkedHashMap<Integer, Boolean> answer = new LinkedHashMap<>();
        for (int i = 0; i < subsets.length; i++) {
            String[] elements = subsets[i].split(",");
            for (int j = 0; j < elements.length; j++) {
                int key = Integer.parseInt(elements[j]);
                if (answer.containsKey(key)) continue;
                answer.put(key, true);
            }
        }

        int[] ret = new int[answer.size()];
        int idx = 0;
        for(Integer key : answer.keySet()) {
            ret[idx++] = key;
        }
        return ret;
    }
}
