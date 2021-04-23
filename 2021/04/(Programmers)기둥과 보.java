import java.util.*;

// 시뮬레이션
class Solution {
    static final int DELETE = 0, PILLAR = 0, ADD = 1, ROOF = 1;
    static boolean[][][] map;
    static int N;
    public static int[][] solution(int n, int[][] build_frame) {
        ArrayList<Action> result = new ArrayList<>();
        map = new boolean[n + 1][n + 1][2];
        N = n + 1;
        for(int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][1];
            int y = build_frame[i][0];
            int type = build_frame[i][2];
            int act = build_frame[i][3];
            Action cur = new Action(x, y, type);   
            if (act == ADD) {
                map[x][y][type] = true;
                result.add(cur);                
            }
            else {
                map[x][y][type] = false;
                result.remove(cur);                                
            }
            
            boolean isValidAction = true;
            for(int idx = 0; idx < result.size(); idx++) {
                if (isValid(result.get(idx))) continue;
                    isValidAction = false;
                    break;
            }
            
            if (!isValidAction) {
                if (act == ADD) {
                    map[x][y][type] = false;
                    result.remove(cur);                
                }
                else {
                    map[x][y][type] = true;
                    result.add(cur);                                
                }
            }
        }
        int[][] answer = new int[result.size()][3];
        int idx = 0;
        Collections.sort(result, Action::compareTo);
        for(Action action : result) {
            answer[idx][0] = action.y;
            answer[idx][1] = action.x;
            answer[idx][2] = action.type;
            idx++;
        }
         
        return answer;
    }
    
    private static boolean isValid(Action action) {
        int x, y, type;
        x = action.x;
        y = action.y;
        type = action.type;
        
        if (type == PILLAR) {
             return x == 0 || (y - 1 >= 0 && map[x][y-1][ROOF]) || (map[x][y][ROOF]) || (x - 1 >= 0 && map[x - 1][y][PILLAR]);
        } else {
             return map[x - 1][y][PILLAR] || map[x - 1][y + 1][PILLAR] || ((y - 1 >= 0 && map[x][y - 1][ROOF]) && map[x][y + 1][ROOF]);
        }
    }
    
    static class Action implements Comparable<Action>{
        int x;
        int y;
        int type;
        
        public Action(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        @Override
        public int compareTo(Action o) {
            if (this.y - o.y > 0)
                return 1;
            else if (this.y == o.y && this.x != o.x)
                return this.x - o.x;
            else if (this.y == o.y && this.x == o.x)
                return this.type - o.type;
            return -1;
        }
        
        @Override
        public boolean equals(Object o) {
            Action oa = (Action) o;
            return this.x == oa.x && this.y == oa.y && this.type == oa.type;
        }
    }
}