import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
5 3
1
7
8
9
10
 */

public class Main {
	static int N; // 집
	static int C; // 공유기
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");

		// 집의 개수 N (2 ≤ N ≤ 200,000)
		N = Integer.parseInt(str[0]);
		// 공유기의 개수 C (2 ≤ C ≤ N)
		C = Integer.parseInt(str[1]);

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			arr[i] = temp;
		}

		// 이분탐색을 위해서 sort
		Arrays.sort(arr);

		// 스터디에서 했었던 풍선공장과 비슷했는데, 기억을 못해서 인터넷 아이디어 참조함. 했던걸 기억을 못하다니 ㅠㅠ
		// Parametric Search 공부 => 최적화 = 이진탐색 + 결정
		// 임의 값을 계산 => 조건 만족하는지 않하는지 확인

		int left = 1; // 가능 최소 N=C =>1
		int right = arr[N - 1] - arr[0];// 가능 최대 C=2 => 끝 - 처음

		int result = 0;

		while (left <= right) {
			// 기준 간격을 반틈으로 만들기
			int mid = (left + right) / 2;

			if (check(mid)) {
				result = Math.max(result, mid);
				left = mid + 1; // C이상가능 (간격을 늘리기)
			} else {
				right = mid - 1; // C미만 (간격을 줄이기)
			}
		}

		System.out.println(result);

	}

	private static boolean check(int d) {
		int cnt = 1; // 공유기 개수 저장
		int left = arr[0]; // 공유기 첫장소

		// 집전부 돌면서 간격비교
		for (int i = 0; i < N; i++) {
			// 집간격끼리 비교
			if (arr[i] - left >= d) {
				cnt++;
				left = arr[i]; // 다음꺼로 바꾸기
			}
		}

		// 공유기 설치 가능
		if (cnt >= C)
			return true;

		// 불가능
		return false;
	}

}
