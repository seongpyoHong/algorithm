import java.util.Arrays;
import java.util.HashSet;

// 완전 탐색 + Bitmasking
public class SolutionKakao2 {
    static String[] user;
    static String[] ban;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        String[] userId = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] bannedId = {"*rodo", "*rodo", "******"};
        System.out.println(solution(userId, bannedId));
    }

    private static int solution(String[] userId, String[] bannedId) {
        user = Arrays.copyOf(userId, userId.length);
        ban = Arrays.copyOf(bannedId, bannedId.length);

        combination(0, 0, 0, 0);
        return set.size();
    }

    private static void combination(int idx, int banFlag, int userFlag, int cnt) {
        if (cnt == ban.length) {
            set.add(userFlag);
            return;
        }

        if (idx == user.length) return;
        combination(idx + 1, banFlag, userFlag, cnt);
        for (int i = 0; i < ban.length; i++) {
            if ((banFlag & 1 << i) != 0 || !isMathched(user[idx], ban[i])) continue;
            combination(idx + 1, banFlag | 1 << i, userFlag | 1 << idx, cnt + 1);
        }
    }

    private static boolean isMathched(String target, String regex) {
        if (target.length() != regex.length()) return false;
        for (int i = 0; i < target.length(); i++) {
            if (regex.charAt(i) != '*' && regex.charAt(i) != target.charAt(i)) return false;
        }
        return true;
    }
}