import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 대표 반례
/*
4
1
5
4
1
 */
public class Main {

	static int N;
	static int[][] dp;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 벼의 개수 N(1 ≤ N ≤ 2,000)
		// N+1번쨰 줄까지 벼의 가치 v(i) (1 ≤ v(i) ≤ 1,000)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long result = 0;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		
		dp = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) { //그전꺼 참조하려고 할때 -1 나오고 N나오고 이래서 그냥 하나 더 주고 1부터 시작
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 그냥 왼쪽 오른쪽 비교해서 더 작은거 고르기로 했는데 안됨 => 너무 쉽다고 생각한 내 자신 잠시 눈물흘림
		
		// 그 자리 다음꺼랑 비교해서 계산을 해야만 가능하다 ==> 이걸 몰라서 인터넷 참조함... dp는 진짜 지옥이다.
		
		// 대표적으로 예시가  1 5 4 1 처럼 같은 경우 계산을 못하게 되었다.
		
		System.out.println(dfs(1, N, 1));

	}

	private static int dfs(int left, int right, int count) {
		// 범위체크 => 범위넘어가면 없는 값으로 생각
		if (left > N || right > N && left <= 0 && right <= 0)
			return 0;

		// 있는 경우 피보나치때 메모이제이션때 처럼 예전값 다시쓰기
		if (dp[left][right] != 0)
			return dp[left][right];

		// 끝남
		if (left > right)
			return 0;

		// 왼쪽 고르기 vs 오른쪽 고르기로 끝나는게 아니라
		// (왼쪽+ 왼쪽다음) vs (오른쪽 전 +오른쪽)을 비교해야함
		return dp[left][right] = Math.max(dfs(left + 1, right, count + 1) + count * arr[left],
				dfs(left, right - 1, count + 1) + count * arr[right]);

	}

}
