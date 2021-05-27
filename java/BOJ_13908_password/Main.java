import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int M;
	static int[] check;
	static int[] temp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		str = br.readLine().split(" ");

		temp = new int[7];
		check = new int[M];

		for (int i = 0; i < M; i++) {
			check[i] = Integer.parseInt(str[i]);
		}

		dfs(0);

		System.out.println(result);
	}

	static int result = 0;

	// 순열이야 순열~
	private static void dfs(int count) {
		if (count == N) {

			for (int i = 0; i < M; i++) {
				boolean flag = false;

				for (int j = 0; j < N; j++) {
					if (temp[j] == check[i]) {
						flag = true;
					}
				}
				if (flag == false) {
					return;
				}
			}

			result++;
			return;

		}

		for (int i = 0; i < 10; i++) {
			temp[count] = i;
			dfs(count + 1);
		}
	}

}
