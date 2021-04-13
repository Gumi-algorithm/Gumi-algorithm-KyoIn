import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TestCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TestCase; tc++) {
			int result = 0;

			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);
			int K = Integer.parseInt(str[1]);

			int[] arr = new int[N];
			int max = 0;
			String[] str1 = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(str1[i]);
				if (arr[i] > max) {
					max = arr[i];
				}
			}
			int[] block = new int[K];
			String[] str2 = br.readLine().split(" ");
			for (int i = 0; i < K; i++) {
				block[i] = Integer.parseInt(str2[i]);
			}

			// 이분 탐색
			int start = 1;
			int end = max;
			result=max;
			while (start <= end) {
				int mid = (start + end) / 2;
				if (check(arr, block, mid)) {
					if(mid<result)
						result = mid;
					end = mid - 1;
				} else {
					start= mid + 1;
				}
			}

			System.out.println("#" + tc + " " + result);
		}

	}

	private static boolean check(int[] arr, int[] block, int check) {
		// 덩어리들은 입력에 주어진 순서대로 작은 번호의 블록부터 저장되어야 한다
		int Size = arr.length;
		int temp = 0;
		int bidx = 0;
		for (int i = 0; i < Size; i++) {
			if (arr[i] <= check) {
				temp++;
				if (temp >= block[bidx]) {
					temp = 0;
					bidx++;
				}
				if (bidx == block.length) {
					return true;
				}
			} else {
				temp = 0;
			}
		}
		if (bidx == block.length) {
			return true;
		}
		return false;
	}

}
