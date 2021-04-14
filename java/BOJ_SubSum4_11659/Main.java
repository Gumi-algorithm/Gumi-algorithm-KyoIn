import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];
		String[] str1 = br.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(str1[i - 1]);
			dp[i] = arr[i] + dp[i - 1];
		}

		for (int i = 0; i < M; i++) {
			String[] str2 = br.readLine().split(" ");
			int start = Integer.parseInt(str2[0]);
			int end = Integer.parseInt(str2[1]);

			System.out.println(dp[end] - dp[start - 1]);
		}

	}

}
