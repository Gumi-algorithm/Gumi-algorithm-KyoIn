import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		long S = Integer.parseInt(str[1]);

		String[] str1 = br.readLine().split(" ");
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str1[i]);
		}

		int length = N;
		int start = 0;
		int end = 0;
		long sum = 0;

		boolean flag = false;
		while (start <= end) {
			// 맨처음에 계속 틀려서 투포인터 알고리즘 다시 이론 공부했음
			// if문 순서 를 이상하게해서 구현이 제대로 되지않았음. 이론은 쉬웠는데 논리를 구현하는게 어려웠음.. 
			if (sum >= S) {
				sum -= arr[start];
				start++;
			} else if (end == N) {
				break;
			} else if (sum < S) {
				sum += arr[end];
				end++;
			}

			if (sum >= S) {
				int temp = end - start;
				if (temp < length) {
					length = temp;
				}
				flag = true;
			}

		}
		if (flag == true) {
			System.out.println(length);
		} else {
			System.out.println("0");
		}

	}

}
