import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] schedule = new int[N + 2][2]; // 기간, 금액
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			schedule[i + 1][0] = Integer.parseInt(str[0]);
			schedule[i + 1][1] = Integer.parseInt(str[1]);
		}

		int[] dp = new int[N + 2];
		int cur_max=0;
		for (int i = 1; i <= N+1; i++) {
			int time = schedule[i][0];
			int price = schedule[i][1];
			cur_max = Math.max(cur_max, dp[i]);

			if (time + i <= N+1) {
				dp[time+i] = Math.max(dp[time + i], cur_max + price);
			} else {

			}
			System.out.println(Arrays.toString(dp));
		}
		System.out.println(cur_max);
	}

}
