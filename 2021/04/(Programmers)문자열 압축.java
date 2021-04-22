import java.util.*;

// 완전 탐색
class Solution {
    public int solution(String s) {
        int minLength = s.length();
        for(int size = 1; size <= s.length() / 2; size++) {
            StringBuilder sb = new StringBuilder();
            String prev = "";
            int cnt = 1;
            int idx = 0;
            while(idx <= s.length() - size) {
                String cur = s.substring(idx, idx + size);
                if (prev.equals(cur)) cnt++;
                else {
                    String cntStr = (cnt == 1) ? "" : String.valueOf(cnt);
                    sb.append(cntStr + prev);
                    prev = cur;
                    cnt = 1;
                }
                idx += size;
            }

            String cntStr = (cnt == 1) ? "" : String.valueOf(cnt);
            sb.append(cntStr + prev);
        
            while(idx < s.length()) {
                sb.append(s.charAt(idx++));
            }
            
            minLength = Math.min(minLength, sb.length());
        
        }
        return minLength;

    }
}