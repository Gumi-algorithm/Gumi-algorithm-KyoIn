import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
5 5
0 0 0 0 0
0 0 10 10 0
0 10 0 10 0
0 0 10 10 0
0 0 0 0 0
 */
public class Main2 {

	static int N = 0;
	static int M = 0;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> ice;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];
		visited = new boolean[N][M];

		ice = new LinkedList<>();

		for (int y = 0; y < N; y++) {
			String[] str1 = br.readLine().split(" ");
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(str1[x]);
				if (map[y][x] != 0) {
					ice.add(new int[] { x, y, map[y][x] });
				}
			}
		}

		// 몇년?
		int timeflies = 0;
		while (true) {
			// 빙산 2개가 된지 아닌지 확인하기
			int iceberg = check(); //bfs연산 300 * 300 = 90,000
			if (iceberg >= 2) {
				System.out.println(timeflies);
				return;
			} else if (iceberg == 0) {
				System.out.println("0");
				return;
			} else {
				// 빙산 녹이기 poll 최대 10,000
				// push 10,000
				int size = ice.size();
				for (int i = 0; i < size; i++) {
					int side = 0;

					int[] now = ice.poll();
					for (int d = 0; d < 4; d++) {
						int temp_x = now[0] + dx[d];
						int temp_y = now[1] + dy[d];

						if (map[temp_y][temp_x] == 0) {
							side++;
						}
					}

					now[2] -= side;
					if (now[2] <= 0) {
						now[2] = 0;
					}
					ice.add(now);
				}

				// 빙산 녹이기 poll 최대 10,000
				// push 10,000
				size = ice.size();
				for (int i = 0; i < size; i++) {
					int[] update = ice.poll();
					map[update[1]][update[0]] = update[2];
					if (update[2] != 0) {
						ice.add(update);
					}
				}
			}
			timeflies++;
		}

	}

	private static int check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = false;
			}
		}

		int iceberg = 0;
		for (int[] a : ice) {
			int x = a[0];
			int y = a[1];

			if (visited[y][x] == true)
				continue;

			if (map[y][x] != 0) {
				iceberg++;
				bfs(x, y);
			}

			if (iceberg >= 2) {
				return iceberg;
			}
		}

		return iceberg;
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
