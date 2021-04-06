import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 우리의 크기 N(1≤N≤100,000)
		int N = Integer.parseInt(br.readLine());

		// 2*N 너무 큼
		//방법1
//		int[][] dp = new int[N + 1][3];
//
//		dp[1][0]=1;
//		dp[1][1]=1;
//		dp[1][2]=1;
	
//		for (int i = 2; i <= N; i++) {
//			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % 9901;
//			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901;
//			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
//		}
//
//		System.out.println((dp[N][0] + dp[N][1] + dp[N][2])%9901);

		//방법2
		int[] dp = new int[N+1];
		dp[1]=1;
		dp[2]=3;
		dp[3]=17;
		
		for(int i=4;i<=N;i++) {
			dp[i]=(dp[i-1]*2+dp[i-2])%9901;
		}
		System.out.println(dp[N]%9901);
		
	}

}
