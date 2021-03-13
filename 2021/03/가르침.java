import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//https://www.acmicpc.net/problem/1062
public class Solution1062 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, cnt;
    static int[] flag = new int[21];
    static String[] words;
    static int[] presigned = {'a' - 'a', 'c' - 'a', 'i' - 'a', 'n' - 'a', 't' - 'a'};

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        words = new String[N];
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word.substring(4,word.length() - 4);
        }

        if (K < 5) {
            System.out.println(cnt);
            return;
        }

        int idx = flag.length;
        int k = K - 5;
        while(--k >= 0) flag[--idx] = 1;

        do {
            cnt = Math.max(cnt, findReadableWord());
        } while(nextPermutation());

        System.out.println(cnt);
    }

    private static int findReadableWord() {
        int match = 0;
        ArrayList<Integer> key = new ArrayList<>();
        for (int i = 0; i < presigned.length; i++) {
            key.add(presigned[i]);
        }

        int skip = 0;
        for (int i = 0; i < flag.length; i++) {
            if (i + skip== 'a' - 'a' || i + skip== 'c' - 'a' || i +skip == 'i' - 'a' || i + skip == 'n' - 'a' || i + skip== 't' - 'a') {
                skip++;
            }
            if (flag[i] == 0) continue;
            key.add(i + skip);
        }

        for (int i = 0; i < N; i++) {
            boolean isFound = true;
            for (int j = 0; j < words[i].length(); j++) {
                int target = words[i].charAt(j) - 'a';
                if (!key.contains(target)) {
                    isFound = false;
                    break;
                }
            }
            if (isFound)
                match++;
        }
        return match;
    }


    public static boolean nextPermutation() {
        int srcIdx = flag.length;
        while(--srcIdx > 0)
            if (flag[srcIdx] > flag[srcIdx - 1]) break;
        if (--srcIdx == -1) return false;


        int destIdx = flag.length;
        while(--destIdx >= 0) {
            if (flag[destIdx] <= flag[srcIdx]) continue;
            swap(srcIdx, destIdx);
        }

        destIdx = flag.length;
        while(++srcIdx <= --destIdx)
            swap(srcIdx, destIdx);

        return true;
    }

    private static void swap(int srcIdx, int destIdx) {
        int temp = flag[srcIdx];
        flag[srcIdx] = flag[destIdx];
        flag[destIdx] = temp;
    }
}