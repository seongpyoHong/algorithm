import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution12871 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String s,t;
    public static void main(String[] args) throws IOException {
        s = br.readLine();
        t = br.readLine();


        if (s.length() > t.length()) {
            t = getAddedString(t, s.length() * 2);
            s += s;
        } else if (s.length() < t.length()) {
            s = getAddedString(s, t.length() * 2);
            t += t;
        }

        int len = Math.min(s.length(), t.length());
        int ret = 1;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                ret = 0;
                break;
            }
        }
        System.out.println(ret);
    }

    private static String getAddedString(String s, int len) {
        while (s.length() < len) {
            s += s;
        }
        return s;
    }
}