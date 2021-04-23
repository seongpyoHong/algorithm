import java.util.*;

// 시뮬레이션 + BFS
class Solution {
    int[] dx = {1, -1, 3, -3};
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        int left = 10;
        int right = 12;
        for(int i = 0; i < numbers.length; i++) {
            int cur = (numbers[i] == 0) ? 11 : numbers[i];
            if (cur % 3 == 1) {
                sb.append('L');
                left = cur;
            } else if (cur % 3 == 0) {
                sb.append('R');
                right = cur;
            } else {
                int leftDiff = getDistance(cur, left);
                int rightDiff = getDistance(cur, right);
                if (leftDiff > rightDiff || (leftDiff == rightDiff && hand.equals("right"))) {
                    sb.append('R');
                    right = cur;
                } else if (leftDiff < rightDiff || (leftDiff == rightDiff && hand.equals("left"))) {
                    sb.append('L');
                    left = cur;
                }
            }
        }
        return sb.toString();
    }
    
    public int getDistance(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        int dist = 0;
        q.add(start);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int s = 0; s < size; s++) {
                int cur = q.poll();
                if (cur == end) return dist; 
                
                for(int i = 0; i < 4; i++) {
                    q.add(cur + dx[i]);
                }
            }
            
            dist++;
        }
        return 1;
    }
}