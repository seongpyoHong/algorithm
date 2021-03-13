package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution14502 {
    static class Pair{
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, blankCnt = 0, virusCnt = 0, cnt, minCnt = Integer.MAX_VALUE;
    static Pair[] blank, virus;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1}, flag;

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        //input
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 0) blankCnt += 1;
                else if (map[i][j] == 2) virusCnt += 1;
            }
        }

        blank = new Pair[blankCnt];
        virus = new Pair[virusCnt];
        int blankIdx = 0;
        int virusIdx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0)
                    blank[blankIdx++] = new Pair(i,j);
                else if (map[i][j] == 2)
                    virus[virusIdx++] = new Pair(i,j);
            }
        }


        // initialize flag array
        flag = new int[blankCnt];
        for (int i = flag.length - 1; i >= flag.length - 3; i--) {
            flag[i] = 1;
        }

        do {
            cnt = 0;
            visited = new boolean[N][M];
            bfs();
            minCnt = Math.min(minCnt, cnt);
        } while(nextPermutation());

        System.out.println(blankCnt - minCnt - 3);
    }

    private static void bfs() {
        Queue<Pair> q = new LinkedList<>();

        //check visited for new wall
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == 0) continue;
            visited[blank[i].x][blank[i].y] = true;
        }

        //start
        for (int i = 0; i < virusCnt; i++) {
            q.add(virus[i]);
            visited[virus[i].x][virus[i].y] = true;
        }

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || visited[nextX][nextY] || map[nextX][nextY] != 0) continue;
                visited[nextX][nextY] = true;
                if (++cnt >= minCnt) return;
                q.add(new Pair(nextX, nextY));
            }
        }
    }

    static boolean nextPermutation() {
        int srcIdx = flag.length;
        while(--srcIdx > 0) {
            if (flag[srcIdx] > flag[srcIdx - 1]) break;
        }

        if (--srcIdx == -1) return false;

        int destIdx = flag.length;
        while(--destIdx >= 0) {
            if (flag[destIdx] <= flag[srcIdx]) continue;
            swap(srcIdx, destIdx);
            break;
        }

        destIdx = flag.length;
        while(++srcIdx <= --destIdx) {
            swap(srcIdx, destIdx);
        }
        return true;
    }

    static void swap(int srcIdx, int destIdx) {
        int temp = flag[srcIdx];
        flag[srcIdx] = flag[destIdx];
        flag[destIdx] = temp;
    }
}