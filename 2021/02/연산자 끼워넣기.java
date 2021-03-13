import java.util.Scanner;

public class Solution14888 {
	static Scanner sc = new Scanner(System.in);
	static char[] operator = { '+', '-', '*', '/' };
	static int[] operatorCnt = new int[4];
	static int[] expr;
	static int max, min;

	public static void main(String[] args) {
		int N = sc.nextInt();
		expr = new int[N];
		for (int i = 0; i < N; i++) {
			expr[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			operatorCnt[i] = sc.nextInt();
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		computeMax(0, expr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}

	static void computeMax(int idx, int res) {
		if (idx == expr.length - 1) {
			if (max < res)
				max = res;
			if (min > res)
				min = res;
			return;
		}

		for (int j = 0; j < 4; j++) {
			if (operatorCnt[j] != 0) {
				operatorCnt[j] -= 1;
				computeMax(idx + 1, applyOperator(res, expr[idx + 1], operator[j]));
				operatorCnt[j] += 1;				
			}
		}
		return;
	}

	static int applyOperator(int x, int y, char oper) {
		int res;
		if (oper == '+') {
			res = x + y;
		} else if (oper == '-') {
			res = x - y;
		} else if (oper == '*') {
			res = x * y;
		} else {
			if (x < 0 && y > 0) {
				res = -(Math.abs(x)/y);
			} else {
				res = x / y;				
			}
		}
		return res;
	}
}
