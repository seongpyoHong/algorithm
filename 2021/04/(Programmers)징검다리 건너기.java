// binary search
public class SolutionKakao3 {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

    private static int solution(int[] stones, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < stones.length; i++) {
            min = Integer.min(min, stones[i]);
            max = Integer.max(max, stones[i]);
        }

        int answer = 0;
        while(min <= max) {
            int mid = (min + max) / 2;
            boolean isValid = true;
            int cnt = 0;
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] > mid) {
                    cnt = 0;
                    continue;
                }

                if (++cnt == k) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                min = mid + 1;
            } else if (!isValid) {
                answer = mid;   // 지나갈 수 있는 사람의 최대값인 경우
                max = mid - 1;
            }
        }

        return answer;
    }
}