import java.util.*;

// 시뮬레이션
class Solution {
    public String solution(String p) {
        return make(p);
    }
    
    private String make(String origin) {
        if (origin.length() == 0) return origin;
        
        int divIdx = getDivIdx(origin);
        String u = origin.substring(0,divIdx);
        String v = origin.substring(divIdx);

        if (isValid(u)) {
            return u + make(v);
        } else {
            return createInvalidStr(u, v);
        }
    }
    
    private String createInvalidStr(String u, String v) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(make(v));
        sb.append(')');
        for(int i = 1; i < u.length() - 1; i++) {
            char cur = u.charAt(i);
            if(cur == ')')
                sb.append('(');
            else 
                sb.append(')');
        }
        
        return sb.toString();
    }
    
    private boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(') st.add(cur);
            else {
                if (st.isEmpty()) return false;
                st.pop();
            }
        }
        
        return st.isEmpty();
    }
    
    private int getDivIdx(String s) {
        int openCnt = 0;
        int closeCnt = 0;
        for(int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') openCnt++;
            else closeCnt++;
            
            if (openCnt == closeCnt) {
                return i + 1;
            }

        }
        
        return 0;
    }
}