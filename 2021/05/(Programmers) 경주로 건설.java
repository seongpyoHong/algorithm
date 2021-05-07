import java.util.*;

//  BFS
class Solution {
    
    int[][] cache, map;
    int N;
    int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public int solution(int[][] board) {
        N = board.length;
        cache = new int[N][N];
        map = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = board[i][j];
                cache[i][j] = Integer.MAX_VALUE;
            }
        }
        
        
        return buildPath(0, 0);
    }
    
    public int buildPath(int startX, int startY) {
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i < 4; i++) {
            int nX = startX + dx[i];
            int nY = startY + dy[i];
            if (nX < 0 || nY < 0 || nX >= N || nY >= N || map[nX][nY] == 1) continue;
            q.add(new Pair(nX, nY, i, 1));
            cache[nX][nY] = 1;
        }
        
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                int nDist = (cur.dIdx == i) ? 1 : 6;
                if (nX < 0 || nY < 0 || nX >= N || nY >= N || map[nX][nY] == 1 || cur.dist + nDist > cache[nX][nY]) continue;
                q.add(new Pair(nX, nY, i, cur.dist + nDist));
                cache[nX][nY] = cur.dist + nDist;
            }
        } 
        
        return cache[N - 1][N - 1] * 100;
    }
    
    static class Pair {
        int dIdx;
        int x;
        int y;
        int dist;
        
        Pair(int x, int y, int dIdx, int dist) {
            this.x = x;
            this.y = y;
            this.dIdx = dIdx;
            this.dist = dist;
        }
        
        @Override
        public String toString() {
            return x + " " + y + " " + dIdx + " " + dist;
            
        }
    }
}