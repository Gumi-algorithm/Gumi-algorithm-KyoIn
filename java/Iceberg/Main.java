import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/*
5 5
0 0 0 0 0
0 0 10 10 0
0 10 0 10 0
0 0 10 10 0
0 0 0 0 0
 */
public class Main {

	static int N = 0;
	static int M = 0;
	static int[][] map;
	static int[][] update;
	static boolean[][] visited;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];
		update = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}

		// 몇년?
		int timeflies = 0;
		while (true) {
			//visited초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					visited[i][j] = false;
				}
			}

			//떨어진 개수 세기
			int iceberg = 0;
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (map[y][x] != 0 && visited[y][x] == false) {
						bfs(x, y);
						iceberg++;
					}
				}
			}

			// 녹았어
			if (iceberg >= 2) {
				System.out.println(timeflies);
				return;
			}

			// 빙산이 0이됬는데도 2개가 안됬다면 0출력하기
			if (iceberg == 0) {
				System.out.println("0");
				return;
			}
			
			// 빙산 녹이기 
			// 이 문제는 빙산 녹이고 바로 업데이트 X
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (map[y][x] != 0) {
						int side = 0;
						for (int d = 0; d < 4; d++) {
							int temp_x = x + dx[d];
							int temp_y = y + dy[d];

							// 범위X
							if (temp_x < 0 || temp_y < 0 || temp_x >= M || temp_y >= N)
								continue;

							if (map[temp_y][temp_x] == 0) {
								side++;
							}
						}
						int temp = map[y][x] - side;
						if (temp <= 0) {
							temp = 0;
						}
						update[y][x] = temp;
					}
				}
			}

			//녹인거 업데이트 
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = update[i][j];
				}
			}
//
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j]+" ");
//				}System.out.println();
//			}System.out.println();

			timeflies++;
		}

	}

	private static void bfs(int x, int y) {
		LinkedList<int[]> list = new LinkedList<>();

		visited[y][x] = true;
		list.push(new int[] { x, y });

		while (!list.isEmpty()) {
			int[] temp = list.pop();

			for (int d = 0; d < 4; d++) {
				int newX = temp[0] + dx[d];
				int newY = temp[1] + dy[d];

				// 범위X
				if (newX < 0 || newY < 0 || newX >= M || newY >= N)
					continue;

				// 재방문X
				if (visited[newY][newX] == true)
					continue;

				// 빙산이 아닌경우
				if (map[newY][newX] == 0)
					continue;

				// 빙산, 방문X, 범위O
				list.push(new int[] { newX, newY });
				visited[newY][newX] = true;
			}
		}
	}

}
