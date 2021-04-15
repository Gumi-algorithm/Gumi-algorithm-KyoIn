import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		int R = Integer.parseInt(str[0]);
		int C = Integer.parseInt(str[1]);
		int T = Integer.parseInt(str[2]);

		// x, y
		map = new int[R][C];
		int cleaner1 = -1;// 절대로 못나오거 넣기
		int cleaner2 = -1;

		for (int i = 0; i < R; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
				if (map[i][j] == -1 && cleaner1 == -1) {
					cleaner1 = i;
					cleaner2 = i + 1;
				}
			}
		}
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

//		System.out.println(cleaner1 + " "+cleaner2);
		for (int time = 0; time < T; time++) {

			// 공기 넣기
			LinkedList<int[]> finedust = new LinkedList<>();

			// 먼지는 한번에 업데이트.... 이거 계속 안되서 ....하...
			for (int y = 0; y < R; y++) {
				for (int x = 0; x < C; x++) {
					// 먼지X
					if (map[y][x] == -1 || map[y][x] == 0)
						continue;
					// 먼지O
					finedust.add(new int[] { x, y, map[y][x] });
				}
			}

			// 확산
			while (!finedust.isEmpty()) {
				int[] cur = finedust.poll();
				// 먼지X
				if (cur[2] < 5)
					continue;

				int spread = cur[2] / 5;
				int d_count = 0;

				for (int d = 0; d < 4; d++) {
					int newX = cur[0] + dx[d];
					int newY = cur[1] + dy[d];

					// 범위
					if (newX < 0 || newY < 0 || newY >= R || newX >= C)
						continue;

					// 공기청정기
					if (map[newY][newX] == -1)
						continue;

					map[newY][newX] += spread;
					d_count++;
				}

				// 남은 미세먼지
				map[cur[1]][cur[0]] -= spread * d_count;

			}

			// 돌리기
			for (int i = cleaner1 - 1; i > 0; i--) {
				map[i][0] = map[i - 1][0];
			}
			for (int i = 0; i < C - 1; i++) {
				map[0][i] = map[0][i + 1];
			}
			for (int i = 0; i < cleaner1; i++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}
			for (int i = C - 1; i > 1; i--) {
				map[cleaner1][i] = map[cleaner1][i - 1];
			}

			for (int i = cleaner2 + 1; i < R - 1; i++) {
				map[i][0] = map[i + 1][0];
			}
			for (int i = 0; i < C - 1; i++) {
				map[R - 1][i] = map[R - 1][i + 1];
			}
			for (int i = R - 1; i > cleaner2; i--) {
				map[i][C - 1] = map[i - 1][C - 1];
			}
			for (int i = C - 1; i > 1; i--) {
				map[cleaner2][i] = map[cleaner2][i - 1];
			}

			// 공기청정기 앞 없애기 ... 이거 없었네
			map[cleaner1][1] = 0;
			map[cleaner2][1] = 0;

//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

		}

		// 미세먼지 양 출력;
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;

				result += map[i][j];
			}
		}
		System.out.println(result);
	}

}
