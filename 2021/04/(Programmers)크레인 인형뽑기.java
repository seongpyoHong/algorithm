import java.util.*;

// 시뮬레이션 + 자료구조 (Stack)
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> bucket = new Stack<>();
        for(int i = 0; i < moves.length; i++) {
            int col = moves[i] - 1;
            // select
            int pick = -1;
            for(int row = 0; row < board.length; row++) {
                if (board[row][col] == 0) continue;
                pick = board[row][col];
                board[row][col] = 0;
                break;
            }

            if (pick == -1) continue;
            if (bucket.isEmpty() || bucket.peek() != pick) {
                bucket.push(pick);
                continue;
            }
            
            answer += 2;                
            bucket.pop();
        }
        return answer;
    }
}