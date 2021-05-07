import java.util.*;

// Deque, Stack
class Solution {
    public int solution(String s) {
        int answer = 0;
        Deque<Character> origin = new ArrayDeque<>();
        for(int i = 0; i < s.length(); i++)
            origin.add(s.charAt(i));
        
        for(int x = 0; x < s.length(); x++) {
            if (!isValid(origin, x)) continue;
            answer++;
        }
        
        return answer;
    } 
    
    private boolean isValid(Deque<Character> origin, int x) {
        Deque<Character> dq = new ArrayDeque<>(origin);
        while(--x >= 0) {
            dq.add(dq.pollFirst());
        }
        Stack<Character> temp = new Stack<>();

        while(!dq.isEmpty()) {
            char cur = dq.pollLast();
            if (cur == '}' || cur == ']' || cur == ')')
                temp.add(cur);
            else {
                char top = (temp.isEmpty()) ? ' ' : temp.peek();
                if ((cur == '{' && top == '}') || (cur == '(' && top == ')') || (cur == '[' && top == ']')) {
                    temp.pop();
                } else 
                    return false;
            }
        }
        
        return temp.isEmpty();
    }
}