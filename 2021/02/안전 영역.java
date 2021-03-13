package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2468 {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, maxCnt = 1;
    static int[][] map;
    static boolean[][] visited;
    static int[] height = new int[101], dx = {1,-1,0,0}, dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                height[map[i][j]] = 1;
            }
        }
        for (int i = 1; i < 101; i++) {
            if (height[i] == 0) continue;
            visited = new boolean[N][N];
            int areaCnt = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] <= i || visited[x][y]) continue;
                    areaCnt += 1;
                    bfs(x, y, i);
                }
            }

            maxCnt = Math.max(maxCnt, areaCnt);
        }
        System.out.println(maxCnt);
    }

    private static void bfs(int x, int y, int height) {
        Queue<Pair> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new Pair(x,y));
        while(!queue.isEmpty()) {
            Pair current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visited[nextX][nextY] || map[nextX][nextY] <= height) continue;
                visited[nextX][nextY] = true;
                queue.add(new Pair(nextX, nextY));
            }
        }
    }
}