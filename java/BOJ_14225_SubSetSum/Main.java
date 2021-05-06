import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] arr;
	static boolean[] visited;
	static boolean[] check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		int max = 0;
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
			max += arr[i];
		}

		check = new boolean[N];
		visited = new boolean[max + 2]; //모든걸 다 더한것보다 큰것 X

		dfs(0);

		for (int i = 1; i <= max+1; i++) {
			if (visited[i] == false) {
				System.out.println(i);
				return;
			}
		}
	}

	private static void dfs(int count) {
		if (count == N) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				if (check[i] == true) {
					temp += arr[i];
				}
			}

			visited[temp] = true;
			return;
		}

		check[count] = true;
		dfs(count + 1);

		check[count] = false;
		dfs(count + 1);
	}

}
