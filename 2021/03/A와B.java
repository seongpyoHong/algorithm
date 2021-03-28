import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution13706 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String S, TOrigin;
    static boolean isSame = false;
    public static void main(String[] args) throws IOException {
        S = br.readLine();
        TOrigin = br.readLine();

        convert(TOrigin);
        System.out.println((isSame) ? 1 : 0);
    }

    private static void convert(String T) {
        if (T.length() < S.length())
            return;
        else if (T.length() == S.length()) {
            isSame = T.equals(S);
        }

        if (T.charAt(T.length() - 1) == 'A') {
            convert(T.substring(0, T.length() - 1));
        } else {
           convert(getReverseString(T));
        }
    }

    private static String getReverseString(String origin) {
        StringBuilder sb = new StringBuilder();
        for (int i = origin.length() - 2; i >= 0; i--) {
            sb.append(origin.charAt(i));
        }
        return sb.toString();
    }
}