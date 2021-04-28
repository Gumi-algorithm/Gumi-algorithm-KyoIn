import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// (1 ≤ N ≤ 5,000, 1 ≤ M ≤ N)
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		arr = new int[N];
		String[] str1 = br.readLine().split(" ");
		int max = 0;
		int min = Integer.MAX_VALUE / 2;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str1[i]);
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}

		int start = 0;
		int end = max - min;
		// 최댓값의 최솟값 => 이런 유형은 대부분 이분탐색
		// 문제를 결정 문제로 바꾸기
		int result = end;
		while (start <= end) {
			int mid = (start + end) / 2;

			if (check(mid) == true) { // 더작게 해보기
				result = Math.min(result, mid);
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		System.out.println(result);
	}

	// M개 이하의 구간으로 나누기... 이거 어렵네... 정해져있지가 않ㄴ아엇....뭐야..
	// 완탐X
	private static boolean check(int mid) {
		// 넘는 시점에 구간 +1 이생각이 어려운거 같다...
		int cnt = 1;
		int min = arr[0];
		int max = arr[0];

		for (int i = 0; i < N; i++) {
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];

			if ((max - min) > mid) {
				cnt++;
				min = arr[i];
				max = arr[i];
			}
		}

		if (cnt <= M) {
			return true;
		}
		return false;
	}

}
