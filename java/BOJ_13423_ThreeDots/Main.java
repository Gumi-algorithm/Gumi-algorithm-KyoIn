import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] arr;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			int result = 0;

			// N(3 ≤ N ≤ 1,000)
			N = Integer.parseInt(br.readLine());
			arr = new int[N];

			// 모든 점의 위치는 -100,000,000이상 100,000,000이하의 정수
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}

			Arrays.sort(arr);
			System.out.println(Arrays.toString(arr));
			// 이분탐색 생각 못하겠음, 인터넷 찾아봤는데도 이해가 X 인터넷에 나온 그냥 완탐식으로 풀어야겠다..
			// i처음, j중간, third 끝
			for (int i = 0; i < N - 2; i++) {
				int third = i + 2;
				for (int j = i + 1; j < N - 1; j++) {
					int diff = arr[j] - arr[i];

					// diff될때까지 계속 달리기
					while (third < N - 1 && arr[third] - arr[j] < diff) {
						// System.out.println(arr[j]+"에 "+diff +"더해주기 맞니"+arr[third]+"");
						third++;
					}

					if (arr[third] - arr[j] == diff) {
						// System.out.println(arr[i] + " " + arr[j] + " " + arr[third]);
						result++;
					}
				}
			}

			System.out.println(result);
		}

	}

}
