import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution17413 {
	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String line = br.readLine();
		boolean isTag = false;
		Stack<String> temp = new Stack<>();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '<') {
				reverse(temp);
				isTag = !isTag;
			}
			
			if (isTag) {
				sb.append(line.charAt(i));
				if (line.charAt(i) == '>')
					isTag = !isTag;
			} else {
				if (line.charAt(i) == ' ') {
					reverse(temp);
					sb.append(line.charAt(i));
				} else {
					temp.add(String.valueOf(line.charAt(i)));					
				}
			}

		}
		
		reverse(temp);
		System.out.println(sb.toString());
	}
	
	static void reverse(Stack<String> s) {
		while (!s.isEmpty()) {
			sb.append(s.pop());
		}
	}
}