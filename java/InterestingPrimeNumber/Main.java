import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//IM 대비문제 풀었던거 기억더듬어서 했음!
//소수 찾기 위키백과 참조 <에라토스테네스의 체> 외웠었음

public class Main {
	static int N = 0;
	static StringBuilder sb = new StringBuilder();
	//에라토스테네스의 체
	//N(1 ≤ N ≤ 8) 
	//배열 크기 100만 ㅎㅎ;;
	static void Eratos(int n) {
		if (n <= 1)
			return;
		boolean arr[] = new boolean[n + 1];

		Arrays.fill(arr, false);

		for (int i = 2; i <= n; i++)
			arr[i] = true;

		for (int i = 2; i * i <= n; i++) {
			if (arr[i] == true) {
				for (int j = i * i; j <= n; j += i) {
					arr[j] = false;
				}
			}
		}
	}
	//에라토스테네스의 체 응용
	public static boolean check(int num) {
		// 1과 0일때는 패스
		if (num == 1 || num == 0)
			return false;
		//
		int sqrt = (int) Math.sqrt(num);
		// sqrt까지 검사했을때 나눠지면 소수가 아니다.
		for (int i = 2; i <= sqrt; i++) {
			if (num % i == 0)
				return false;
		}

		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dfs("", 0);
		System.out.println(sb);
	}

	public static void dfs(String s, int count) {
		if (count == N) {
			sb.append(s).append("\n");
			return;
		}
		// 뒷자리 0부터 9까지 검사 (숫자는 0부터 9까지 가능)
		for (int i = 0; i < 10; i++) {
			// string 뒷자리에 숫자 붙히면 됨 
			if (check(Integer.parseInt(s + i))) {
				dfs(s + i, count + 1);
			}
		}
	}

}
