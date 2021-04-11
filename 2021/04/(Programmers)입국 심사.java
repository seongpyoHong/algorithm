// 이분 탐색
private static long solution(int n, int[] times) {
        Arrays.sort(times);
        long min = times[0];
        long max = (long)times[times.length - 1] * (long)n;
        long answer = max;
        while(min <= max) {
            long mid = (min + max) / 2;
            long hitCnt = 0;
            for (int i = 0; i < times.length; i++) {
                hitCnt += (mid / (long)times[i]);
            }

            if (hitCnt < n)
                min = mid + 1;
            else {
                answer = mid;
                max = mid - 1;
            }
        }

        return answer;
    }