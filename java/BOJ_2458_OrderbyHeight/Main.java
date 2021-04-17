import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int INF = 1000; // 아무리 돌아도 1000은 절대 불가능

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]); // N (2<=N<=500)
		int M = Integer.parseInt(str[1]); // M (0<=M<=N(N-1)/2)

		// 학생수, 비교횟수가 엄청 작은데 -> 그래프 =>플루이드
		int[][] map = new int[N + 1][N + 1];

		// 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = 1000;
			}
		}

		// 값넣기
		for (int i = 0; i < M; i++) {
			String[] str1 = br.readLine().split(" ");
			int start = Integer.parseInt(str1[0]);
			int end = Integer.parseInt(str1[1]);
			map[start][end] = 1;
		}

		// 플루이드 와샬
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
//		System.out.println();
//		for(int i=1;i<=N;i++) {
//			for(int j=1;j<=N;j++) {
//				System.out.print(map[i][j]+ " ");
//			}System.out.println();
//		}

		// 다른 모든 사람이랑 연결되어 있다 ==> 몇번째 인지 검사 가능하다!!!!

		int result = 0; // 몇명인지 저장용
		for (int i = 1; i <= N; i++) { // i->j
			boolean flag = true;

			for (int j = 1; j <= N; j++) {
				if (i == j) { // 자기자신으로 오는것X
					continue;
				}

				if (map[i][j] == INF) { // 만약 갈수없는데
					if (map[j][i] != INF) { // 누군가로 이어져있다면 연결된거

					} else { // 모든 사람이랑 연결되어있지 않다.
						flag = false;
					}
				}
			}
			if (flag == true) {
				result++;
			}
		}
		System.out.println(result);

	}

}
