import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int INF = 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]); // 건물의 수 n
		int M = Integer.parseInt(str[1]); // 길의 수 m

		// init
		int[][] map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					map[i][j] = 0;
				} else {
					map[i][j] = INF;
				}
			}
		}

		// 250이하니까 완탐으로 해도 되나 시간 계산 해봤는데, 
		// 31125개 단방향. 하나씩 완탐으로 하면 시간 터짐(최소 개수 구하는거 못함.)
		// 플루이드이해나 응용을 안하고 외워서 쓰는 것에 대한 허를 찌르는 문제였다

		// update
		for (int i = 0; i < M; i++) {
			String[] str1 = br.readLine().split(" ");
			int u = Integer.parseInt(str1[0]); // start
			int v = Integer.parseInt(str1[1]); // end
			int b = Integer.parseInt(str1[2]);

			// 아무리 생각해도 아이디어를 모르겠어서 답 찾아봤음.
			if (b == 1) { // 양방향, 다리 설치X
				map[u][v] = 0;
				map[v][u] = 0;
			} else { // 단방향, 다리 설치O
				map[u][v] = 0;
				map[v][u] = 1; // 설치횟수(한쪽만)
			}
		}

		// print(N, map);

		// 결 출 도, 플루이드는 최소한의 다리 설치 횟수를 가져간다.
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			String[] str2 = br.readLine().split(" ");
			int start = Integer.parseInt(str2[0]);
			int end = Integer.parseInt(str2[1]);

			System.out.println(map[start][end]);
		}

	}

	// 디버깅용
	private static void print(int N, int[][] map) {
		System.out.println();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

}
