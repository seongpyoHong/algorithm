import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        System.out.println(solution(input));
    }

    public static String solution(String new_id) {
        Deque<Character> q = new LinkedList<>();

        for (int i = 0; i < new_id.length(); i++) {
            if (Character.isAlphabetic(new_id.charAt(i))) {
                q.add(Character.toLowerCase(new_id.charAt(i)));
            } else
                q.add(new_id.charAt(i)) ;
        }

        int size = q.size();
        for (int i = 0; i < size; i++) {
            char cur = q.poll();
            if (!Character.isAlphabetic(cur) && cur != '-' && cur != '_' && cur != '.' && !Character.isDigit(cur)) continue;
            q.add(cur);
        }

        size = q.size();
        boolean isPrevDot = false;
        for (int i = 0; i < size; i++) {
            char cur = q.poll();
            if (!isPrevDot && cur != '.')
                q.add(cur);
            else if (isPrevDot && cur != '.') {
                q.add('.');
                q.add(cur);
                isPrevDot = false;
            } else if (!isPrevDot && cur == '.')
                isPrevDot = true;
        }

        if (q.size() != 0 && q.getFirst() == '.') q.removeFirst();
        if (q.size() != 0 && q.getLast() == '.') q.removeLast();

        if (q.size() == 0) q.add('a');

        while(q.size() > 15) {
            q.removeLast();
        }
        if (q.getLast() == '.') q.removeLast();

        while (q.size() < 3) {
            q.add(q.getLast());
        }

        String answer = "";
        while(!q.isEmpty()) {
            answer += q.poll();
        }

        return answer;
    }
}
