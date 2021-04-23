import java.util.*;

// 완전 탐색 - 부분집합
class Solution {
    private static String[][] db;
    private static int col, row, answer;
    private static HashSet<Integer>[] keys;
    public static int solution(String[][] relation) {
        row = relation.length;        
        col = relation[0].length;
        db = new String[row][col];
        keys = new HashSet[col + 1];
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                db[i][j] = relation[i][j];
            }
        }
        
        for(int i = 1; i <= col; i++) {
            keys[i] = new HashSet<>();
        }
        
        choose(0,0,0);
        for(int i = 1; i <= col; i++) {
            answer += keys[i].size();
        }
        return answer;
    }
    
    private static void choose(int idx, int select, int cnt) {
        if (isCandidateKey(select, cnt)) {
            keys[cnt].add(select);
            return;
        }
        if (idx == col) return;
        
        choose(idx + 1, select, cnt);
        choose(idx + 1, select | 1 << idx, cnt + 1);
    }
    
    private static boolean isCandidateKey(int flag, int cnt) {
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < col; j++) {
                if ((flag & 1 << j) == 0) continue;
                sb.append(db[i][j]);
            }
            set.add(sb.toString());
        }
        
        if (set.size() != row) return false;
        
        for(int i = cnt - 1; i >= 1; i--) {
            for(int key : keys[i]) {
                int match = 0;
                for(int c = 0; c < col; c++) {
                    if ((key & 1 << c) != 0 && (flag & 1 << c) != 0) match++;
                }   
                
                if (match == i) return false;
            }
        }
        return true;
    }
}