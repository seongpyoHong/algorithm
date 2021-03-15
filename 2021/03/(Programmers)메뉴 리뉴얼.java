import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    static int[] select;
    static int[] bitOrders;

    public static String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        bitOrders = new int[orders.length];

        for (int i = 0; i < orders.length; i++) {
            int bit = 0;
            for (int j = 0; j < orders[i].length(); j++) {
                bit = (bit | 1 << (orders[i].charAt(j) - 'A'));
            }
            bitOrders[i] = bit;
        }

        for (int keySize = 0; keySize < course.length; keySize++) {
            int maxCnt = 0;
            HashSet<String> temp = new HashSet<>();
            for (int i = 0; i < bitOrders.length; i++) {
                if (initializeSelect(orders[i], course[keySize])) continue;
                do {
                    System.out.println(Arrays.toString(select));
                    String key = getKey(orders[i], course[keySize]);
                    int matchCnt = getValidCourse(i, key);
                    if (maxCnt < matchCnt) {
                        temp = new HashSet<>();
                        temp.add(key);
                        maxCnt = matchCnt;
                    } else if (maxCnt == matchCnt) {
                        temp.add(key);
                    }
                } while (nextPermutation());
            }

            for (String key : temp) {
                answer.add(key);
            }
        }

        answer.sort(String::compareTo);
        String[] answerArr = convertToArray(answer);
        return answerArr;
    }

    private static String[] convertToArray(ArrayList<String> answer) {
        String[] answerArr = new String[answer.size()];
        int idx = 0;
        for (String key : answer) {
            answerArr[idx++] = key;
        }
        return answerArr;
    }

    private static boolean initializeSelect(String order, int cnt) {
        select = new int[order.length()];
        if (cnt > select.length) return true;

        int idx = order.length();
        while (--cnt >= 0) select[--idx] = 1;
        return false;
    }

    private static String getKey(String order, int cnt) {
        char[] key = new char[cnt];
        int idx = 0;
        for (int strIdx = 0; strIdx < select.length; strIdx++) {
            if (select[strIdx] == 0) continue;
            key[idx++] = order.charAt(strIdx);
        }
        Arrays.sort(key);
        return String.valueOf(key);
    }

    private static int getValidCourse(int cur, String key) {
        System.out.println(key);
        int cnt = 1;
        for (int i = 0; i < bitOrders.length; i++) {
            if (i == cur) continue;
            boolean isContain = true;
            for (int j = 0; j < key.length(); j++) {
                if ((bitOrders[i] & 1 << (key.charAt(j) - 'A')) == 0) {
                    isContain = false;
                    break;
                }
            }
            if (isContain)
                cnt++;
        }

        return (cnt > 1) ? cnt : -1;
    }

    private static boolean nextPermutation() {
        int srcIdx = select.length;
        while (--srcIdx > 0) 
            if (select[srcIdx] > select[srcIdx - 1]) break;
        
        if (--srcIdx == -1) return false;

        int destIdx = select.length;
        while (--destIdx >= 0) {
            if (select[srcIdx] >= select[destIdx]) continue;
            swap(srcIdx, destIdx);
        }

        destIdx = select.length;
        while (++srcIdx <= --destIdx) swap(srcIdx, destIdx);
        return true;
    }

    private static void swap(int srcIdx, int destIdx) {
        int temp = select[srcIdx];
        select[srcIdx] = select[destIdx];
        select[destIdx] = temp;
    }
}