import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int T;
	static int N;
	static int[] card;
	static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			card = new int[N];
			dp= new int[N+1][N+1];
			
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				card[i] = Integer.parseInt(str[i]);
			}
			
			System.out.println(func(0, N-1,0));
		}

		
	}

	private static int func(int left, int right, int turn) {
		if (left > right) return 0;
		
		if (dp[left][right]!=0)
			return dp[left][right];

		if (turn % 2==0) // 근우 차례
		   return dp[left][right] = Math.max(card[left] + func(left + 1, right, turn + 1), card[right] + func(left, right - 1, turn + 1));
		else // 명우 차례 
		   return dp[left][right] = Math.min(func(left + 1, right, turn + 1), func(left, right - 1, turn + 1));
	}

}
