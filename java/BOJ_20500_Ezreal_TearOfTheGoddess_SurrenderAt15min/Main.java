import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 1<=N<=1515
	// dfs는 시간초과 발생할것, 2^1515 불가능

	// 수학 공식을 찾아내야할꺼같음 => dp
	// 1의자리수 5이여야된다말고는 아무리 생각해도 모르겠음...
	// 합이 6, 12이런식으로 가던데 6의 배수인것같다고 생각해서 머리를 굴렸는데 아무리해도 모르겠더라
	// 합이 15인경우도 되길래 15와 6의 배수는 다되나 보다!라고 생각함 ==> 결국 실패함.

	// 접근도 못하겠어서 인터넷 찾아보니 아이디어 문제였다
	// 규칙을 찾는 문제였음!

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			System.out.println("0");
			return;
		}

		long MOD = 1000000007;
		
		//10, 100, 1000, 10000, .... 의 15로 나눈 나머지는 모두 10이고
		//50, 500, 5000, 50000, ... 의 15로 나눈 나머지는 모두 5이다.

		long[][] dp = new long[3][N + 1];
		dp[0][1] = 0; // 1의 자리수는 0게

		dp[0][2] = 1;// 나머지0 (15)
		dp[1][2] = 0; // 나머지5 없음
		dp[2][2] = 1; // 나머지 10 (55)

		// int값이 넘어갈꺼같아보이는 문제여서
		// (x+y)%z = (x%z+y%z)%z 모듈러 공식을 사용하였음.
		for (int i = 3; i <= N; i++) { // i는 자리수
			// 나머지 0인 개수 = 나머지 5개수 + 나머지10개수 (나머지 합은 이전10+이전5= 15=> 0)
			dp[0][i] = (dp[2][i - 1] + dp[1][i - 1]) % MOD;
			// 나머지 5인 개수 = 나머지 0개수 + 나머지 10개수 (나머지 합은 이전10 +이전0 = 10=> 나머지 5)
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
			// 나머지 10인 개수 = 나머지 0개수 + 나머지 5개수 (나머지 합은 원래 이전 5+이전0 = 5=>나머지10)
			dp[2][i] = (dp[1][i - 1] + dp[0][i - 1]) % MOD;
		}

		//맨처음 0을 나머지 0으로 뒀으니 이게 답
		System.out.println(dp[0][N] % MOD);
	}

}
