import java.util.ArrayList;
import java.util.Arrays;

// Permutation
public class SolutionKakao4 {
    static char[] oper = {'*', '-', '+'};
    static int[] flag = {1, 2, 3};
    static ArrayList<Long> operand;
    static ArrayList<Character> operator;

    public static void main(String[] args) {
        String expression = "100-200*300-500+20";
        System.out.println(solution(expression));
    }

    private static long solution(String expression) {
        long answer = 0;
        do {
            operand = new ArrayList<>();
            operator = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.length(); i++) {
                char cur = expression.charAt(i);
                if (Character.isDigit(cur))
                    sb.append(cur);
                else {
                    operand.add(Long.parseLong(sb.toString()));
                    operator.add(cur);
                    sb = new StringBuilder();
                }
            }
            operand.add(Long.parseLong(sb.toString()));
            answer = Long.max(answer, getResult());
        } while (nextPermutation());

        return answer;
    }

    private static long getResult() {
        for (int i = 0; i < flag.length; i++) {
            char curOperator = oper[flag[i] - 1];
            int idx = 0;
            while (idx < operator.size()) {
                if (operator.get(idx) != curOperator) {
                    idx++;
                    continue;
                }

                long result = compute(operand.get(idx), operand.get(idx + 1), curOperator);
                operand.remove(idx);
                operand.remove(idx);
                operand.add(idx, result);
                operator.remove(idx);
            }
        }

        return Math.abs(operand.get(0));
    }

    private static long compute(long src, long dest, char operator) {
        if (operator == '*')
            src *= dest;
        else if (operator == '+')
            src += dest;
        else
            src -= dest;
        return src;
    }

    private static boolean nextPermutation() {
        int srcIdx = flag.length;
        while (--srcIdx > 0) {
            if (flag[srcIdx] > flag[srcIdx - 1]) break;
        }

        if (--srcIdx == -1) return false;

        int destIdx = flag.length;
        while (--destIdx > 0) {
            if (flag[destIdx] <= flag[srcIdx]) continue;
            swap(srcIdx, destIdx);
            break;
        }

        destIdx = flag.length;
        while (++srcIdx < --destIdx) {
            swap(srcIdx, destIdx);
        }
        return true;
    }

    private static void swap(int srcIdx, int destIdx) {
        int temp = flag[srcIdx];
        flag[srcIdx] = flag[destIdx];
        flag[destIdx] = temp;
    }
}