import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		String[] str1 = br.readLine().split(" ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str1[i]);
		}

		int start = 0;
		int end = 0;
		int sum = 0;
		int result = 0;
		while (true) {
			if (sum >= M) {
				sum -= arr[start];
				start++;
			} else if (end == N) {
				break;
			}else if (sum < M) {
				sum += arr[end];
				end++;
			} 
			
			if(sum==M) {
				result++;
			}
			System.out.println(start+" " +end+" "+sum);
			
		}
		System.out.println(result);
	}

}
