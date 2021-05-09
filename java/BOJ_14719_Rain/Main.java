import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int H = Integer.parseInt(str[0]);
		int W = Integer.parseInt(str[1]);

		int[] map = new int[W];
		str = br.readLine().split(" ");
		for (int i = 0; i < W; i++) {
			map[i] = Integer.parseInt(str[i]);
		}

		int amount = 0;
		for (int i = 1; i < W - 1; i++) { // 맨끝 두개 X
			int left = 0;
			int right = 0;

			// 왼쪽 큰 vs 오른쪽 큰 => 작은 것을 기준으로 자신을 빼면 현재 물 량 구할수있음
			for (int j = 0; j < i; j++) {
				left = Math.max(left, map[j]);
			}

			for (int j = W - 1; j > i; j--) {
				right = Math.max(right, map[j]);
			}

			amount += Math.max(0, Math.min(left, right) - map[i]);
		}
		System.out.println(amount);

	}

}
