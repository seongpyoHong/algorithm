import java.util.*;

//dijstra
class Solution {
    
    int[][] dist;
    int INF = 50 * 10001;
    public int solution(int N, int[][] road, int K) {
        dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;
            }
        }
        
        for(int i = 0; i < road.length; i++) {
            int src = road[i][0] - 1;
            int dest =road[i][1] - 1;
            int weight = road[i][2];
            
            dist[src][dest] = Math.min(dist[src][dest], weight);
            dist[dest][src] = Math.min(dist[dest][src], weight);
        }
        
        return dijkstra(0, K);
    }
    
    public int dijkstra(int src, int K) {
        int[] weight = new int[dist.length];
        for(int i = 1; i < weight.length; i++) {
            weight[i] = INF;    
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while(!pq.isEmpty()) {
            Pair cur = pq.poll();
            if (cur.weight > weight[cur.idx]) continue;
            
            for(int i = 0; i < dist.length; i++) {
                if (dist[cur.idx][i] == INF || cur.weight + dist[cur.idx][i] >= weight[i]) continue;
                weight[i] = cur.weight + dist[cur.idx][i];
                pq.add(new Pair(i, weight[i]));
            }
        }
        
        int answer = 1;
        for(int i = 1; i < weight.length; i++) {
            if (weight[i] > K) continue;
            answer++;
        }
        return answer;
    }
    
    private void printMap() {
        for(int i = 0; i < dist.length; i++) {
            for(int j = 0; j < dist.length; j++) {
                System.out.print(dist[i][j] + " " );
            }
            System.out.println();
        }
    }
    static class Pair implements Comparable<Pair> {
        int idx;
        int weight;
        
        Pair(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Pair o) {
            return this.weight - o.weight;
        }
    }
}