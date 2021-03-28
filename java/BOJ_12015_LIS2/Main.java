import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1 ≤ N ≤ 1,000,000
		int N = Integer.parseInt(br.readLine());
		// 1 ≤ Ai ≤ 1,000,000
		int[] arr = new int[N];

		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		int[] LIS = new int[N];

		int idx = 0;
		LIS[0] = arr[0];
		
		for (int i = 1; i < N; i++) {
			if (LIS[idx] < arr[i]) { // 맨 뒤에 넣기
				idx++;
				LIS[idx] = arr[i];
			} else { // 위치를 찾아서 넣기
				int temp = findplace(LIS, idx, arr[i]);
				LIS[temp] = arr[i];
			}
		}
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(LIS));
		System.out.println((idx + 1));
	}

	private static int findplace(int arr[], int index, int target) {
		int left = 0;
		int right = index;

		while (left < right) {
			int mid = (left + right) / 2;

			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}

}
