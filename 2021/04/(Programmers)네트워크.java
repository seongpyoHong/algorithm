import java.util.*;

// dfs
class Solution {
    int[] network;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        network = new int[n];
        int id = 0;
        for(int i = 0; i < n; i++) {
            if (network[i] != 0) continue;
            id++;
            connect(i, id, computers);
        }
        return id;
    }
    
    private void connect(int idx, int id, int[][] computers) {
        network[idx] = id;
        for(int i = 0; i < network.length; i++) {
            if (computers[idx][i] == 0 || network[i] != 0) continue;
            connect(i, id, computers);
        }
    }
}