package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution2668 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean[] visited;
    static boolean[] successed;
    static int[] nums;
    static int N, cnt;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        successed = new boolean[N + 1];
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            if (successed[i]) continue;
            visited = new boolean[N + 1];
            ArrayList<Integer> temp = new ArrayList<>();
            dfs(i, temp, i);
        }

        System.out.println(cnt);
        for (int i = 1; i < N+1; i++) {
            if (!successed[i]) continue;
            System.out.println(i);
        }
    }

    private static void fillSuccess(ArrayList<Integer> temp) {
        for(Integer i : temp) {
            successed[i] = true;
        }
        cnt += temp.size();
    }

    private static void dfs(int i, ArrayList<Integer> temp, int start) {
        visited[i] = true;
        temp.add(i);

        int next = nums[i];
        if (next == start) {
            fillSuccess(temp);
            return;
        }

        if (!visited[next]) {
            dfs(next, temp, start);
        }
    }
}