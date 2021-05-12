import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static void dfs(int sum, int sign, int num, int now, String str, int end) {
		if (now == end) {
			sum = sum + (num * sign);
			if (sum == 0)
				System.out.println(str);
			return;
		}
		//빈칸
		dfs(sum, sign, num * 10 + (now + 1), now + 1, str + " " + String.valueOf(now + 1), end);
		//더하기
		dfs(sum + (sign*num), 1, now + 1, now + 1, str + "+" + String.valueOf(now + 1), end);
		//빼기
		dfs(sum + (sign * num), -1, now + 1, now + 1, str + "-" + String.valueOf(now + 1), end);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int end = Integer.parseInt(br.readLine());
			dfs(0, 1, 1, 1, "1", end);
			System.out.println();
		}
	}

}
