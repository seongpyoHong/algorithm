class Solution {
    // 그리디 + 완탐
    private static final int MILISECOND_OF_HOUR = 60 * 60 * 1000;
    private static final int MILISECOND_OF_MIN = 60 * 1000;
    private static final int MILISECOND_OF_SECOND = 1000;
    private static final int END_TIME = 24 * 60 * 60 * 1000;
    public static int solution(String[] lines) {
        int maxCnt = 0;
        Time[] times = new Time[lines.length];
        for (int i = 0; i < lines.length; i++) {
            times[i] = convertToTime(lines[i]);
        }

        for (int i = 0; i < times.length; i++) {
            Time cur = times[i];
            int startCnt = 1;
            int endCnt = 1;
            for (int j = 0; j < times.length; j++) {
                if (i == j) continue;
                if (isInclude(times[j], cur.start, cur.start + 999)) startCnt++;
                if (isInclude(times[j], cur.end, cur.end + 999)) endCnt++;
            }
            maxCnt = (startCnt < endCnt) ? Math.max(maxCnt, endCnt) : Math.max(maxCnt, startCnt);
        }

        return maxCnt;
    }

    public static boolean isInclude(Time time, int start, int end) {
        if (end >= END_TIME || start < 0) return false;
        if ((time.start < start && time.end < start) || (time.start > end && time.end > end)) return false;
        return true;
    }

    public static Time convertToTime(String line) {
        String[] splited = line.split(" ");
        String startTimeStr = splited[1];
        String durationStr = splited[2];
        int duration = (int) (Double.parseDouble(durationStr.substring(0,durationStr.length() - 1)) * MILISECOND_OF_SECOND);
        return new Time(convertToMicroSecond(startTimeStr), duration);

    }

    public static int convertToMicroSecond(String time) {
        String[] splited = time.split(":");
        String[] secondStr = splited[2].split("\\.");
        int hour = Integer.parseInt(splited[0]) * MILISECOND_OF_HOUR;
        int min = Integer.parseInt(splited[1]) * MILISECOND_OF_MIN;
        int sec = Integer.parseInt(secondStr[0]) * MILISECOND_OF_SECOND;
        int miliSec = Integer.parseInt(secondStr[1]);
        return hour + min + sec + miliSec;
    }

    static class Time {
        int start;
        int end;

        public Time(int end, int duration) {
            this.start = Math.max(0, end - (duration - 1));
            this.end = end;
        }
    }
}