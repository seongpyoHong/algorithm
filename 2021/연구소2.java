import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://www.acmicpc.net/problem/17141
public class Solution17141 {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    static int N, M, time, blankCnt, minTime;
    static int[][] map;
    static boolean[][] visited;
    static int[] flag, dx = {1,-1,0,0}, dy = {0,0,1,-1};
    static ArrayList<Pair> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 2)
                    virus.add(new Pair(i,j));
                else if (map[i][j] == 0)
                    blankCnt++;
            }
        }

        minTime = Integer.MAX_VALUE;
        flag = new int[virus.size()];
        int idx = 0;
        while(++idx <= M) flag[flag.length - idx] = 1;
        do {
            visited = new boolean[N][N];
            time = -1;
            if (!bfs(0)) continue;
            minTime = Integer.min(minTime, time);
        } while(nextPermutation());

        minTime = (minTime == Integer.MAX_VALUE) ? -1 : minTime;
        System.out.println(minTime);
    }

    private static boolean bfs(int cnt) {
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 0) continue;
            Pair v = virus.get(i);
            q.add(v);
            visited[v.x][v.y] = true;
        }

        while(!q.isEmpty()) {
            if (++time > minTime) return false;
            int size = q.size();
            for (int t = 0; t < size; t++) {
                Pair cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nextX = cur.x + dx[i];
                    int nextY = cur.y + dy[i];
                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >=N || map[nextX][nextY] == 1 || visited[nextX][nextY]) continue;
                    cnt++;
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }

        if (cnt != blankCnt + virus.size() - M)
            return false;
        return true;
    }

    private static boolean nextPermutation() {
        int srcIdx = flag.length;
        while(--srcIdx > 0) {
            if (flag[srcIdx] > flag[srcIdx - 1]) break;
        }
        if (--srcIdx == -1) return false;

        int destIdx = flag.length;
        while(--destIdx >= 0){
            if (flag[destIdx] <= flag[srcIdx]) continue;
            swap(srcIdx, destIdx);
        }
        destIdx = flag.length;
        while(++srcIdx <= --destIdx)
            swap(srcIdx, destIdx);
        return true;
    }

    private static void swap(int srcIdx, int destIdx) {
        int tmp = flag[srcIdx];
        flag[srcIdx] = flag[destIdx];
        flag[destIdx] = tmp;
    }
}