import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution17140 {
    static class Pair implements Comparable<Pair>{
        int num;
        int cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.cnt - o.cnt > 0)
                return 1;
            else if (this.cnt == o.cnt && this.num - o.num > 0)
                return 1;
            return -1;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
    static int R, C, K;
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);
        for (int i = 0; i < 3; i++) {
            String[] line = br.readLine().split(" ");
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                arr.add(Integer.parseInt(line[j]));
            }
            matrix.add(arr);
        }
        System.out.println(compute());
    }

    private static int compute() {
        int cnt = 0;
        while(cnt <= 100) {
            if (matrix.size() >= R && matrix.get(0).size() >= C && matrix.get(R - 1).get(C - 1) == K) return cnt;

            int row = Math.min(matrix.size(), 100);
            int col = Math.min(matrix.get(0).size(), 100);
            if (row >= col) {
                computeRow(row, col);
            } else {
                computeCol(row, col);
            }
            cnt++;
        }

        return -1;
    }

    private static void computeCol(int row, int col) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int maxRowSize = 0;
        for (int i = 0; i < col; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            HashMap<Integer, Integer> count = new HashMap<>();
            PriorityQueue<Pair> pq = new PriorityQueue<>();

            for (int r = 0; r < row; r++) {
                int cur = matrix.get(r).get(i);
                if (cur == 0) continue;
                if (!count.containsKey(cur)) {
                    count.put(cur, 0);
                }
                count.put(cur, count.get(cur) + 1);
            }

            for (Integer key : count.keySet()) {
                pq.add(new Pair(key, count.get(key)));
            }
            maxRowSize = Math.max(maxRowSize, pq.size() * 2);

            while(!pq.isEmpty()) {
                Pair cur = pq.poll();
                arr.add(cur.num);
                arr.add(cur.cnt);
            }
            result.add(arr);
        }

        for (int i = 0; i < col; i++) {
            while(result.get(i).size() < maxRowSize) {
                result.get(i).add(0);
            }
        }

        matrix = new ArrayList<>();
        for (int i = 0; i < result.get(0).size(); i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < result.size(); j++) {
                arr.add(result.get(j).get(i));
            }
            matrix.add(arr);
        }
    }

    private static void computeRow(int row, int col) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int maxColSize = 0;
        for (int i = 0; i < row; i++) {
            ArrayList<Integer> arr = new ArrayList<>();
            HashMap<Integer, Integer> count = new HashMap<>();
            PriorityQueue<Pair> pq = new PriorityQueue<>();

            for (int c = 0; c < col; c++) {
                int cur = matrix.get(i).get(c);
                if (cur == 0) continue;
                if (!count.containsKey(cur)) {
                    count.put(cur, 0);
                }
                count.put(cur, count.get(cur) + 1);
            }

            for (Integer key : count.keySet()) {
                pq.add(new Pair(key, count.get(key)));
            }

            maxColSize = Math.max(maxColSize, pq.size() * 2);
            while(!pq.isEmpty()) {
                Pair cur = pq.poll();
                arr.add(cur.num);
                arr.add(cur.cnt);
            }

            result.add(arr);
        }

        for (int i = 0; i < row; i++) {
            while(result.get(i).size() < maxColSize) {
                result.get(i).add(0);
            }
        }
        matrix.clear();
        matrix = result;
    }


}