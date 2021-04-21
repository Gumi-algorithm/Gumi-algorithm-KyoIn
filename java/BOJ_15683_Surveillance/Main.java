import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
4 4
1 0 0 0
0 1 0 0
0 0 1 0
0 0 0 1

 */
public class Main {

	static int N; // 세로
	static int M; // 가로
	static int[][] map;
	static int[][] temp_map;
	static ArrayList<int[]> cctv;
	static int CCTVS;
	static int result = Integer.MAX_VALUE / 2;
	static int[] direction_selected;

	// 상우하좌
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		// 1번 CCTV는 한 쪽 방향만 감시할 수 있다
		// 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고
		// 3번은 직각 방향이어야 한다.
		// 4번은 세 방향, 5번은 네 방향을 감시할 수 있다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// (1 ≤ N, M ≤ 8)
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로

		map = new int[N][M];
		cctv = new ArrayList<>(); // CCTV의 최대 개수는 8개 -> 완탐
		for (int y = 0; y < N; y++) {
			String[] str = br.readLine().split(" ");
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(str[x]);
				if (map[y][x] != 6 && map[y][x] != 0) {
					cctv.add(new int[] { x, y, map[y][x] });
				}
			}
		}

		CCTVS = cctv.size();

		direction_selected = new int[CCTVS];
//		for (int[] a : cctv) {
//			System.out.println(Arrays.toString(a));
//		}

		put_dfs(0);
		System.out.println(result);

	}

	private static void put_dfs(int count) {
		if (count == CCTVS) {
			int temp = 0;

			// map 복사
			temp_map = new int[N][M];
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					temp_map[y][x] = map[y][x];
				}
			}

			for (int i = 0; i < CCTVS; i++) {
				int[] current_cctv = cctv.get(i);

				int cur_x = current_cctv[0];
				int cur_y = current_cctv[1];
				int cur_ccty_type = current_cctv[2];

				if (cur_ccty_type == 5 && direction_selected[i] == 4) { // 모든방향 두기
					for (int d = 0; d < 4; d++) {
						put(cur_x, cur_y, d);
					}
				} else if (cur_ccty_type == 2 && direction_selected[i] == 13) {// 좌우
					put(cur_x, cur_y, 1);
					put(cur_x, cur_y, 3);
				} else if (cur_ccty_type == 2 && direction_selected[i] == 20) { // 상하
					put(cur_x, cur_y, 0);
					put(cur_x, cur_y, 2);
				} else if (cur_ccty_type == 4) { // 한방향만 빼기
					for (int d = 0; d < 4; d++) {
						if (d == direction_selected[i]) {
							continue;
						}
						put(cur_x, cur_y, d);
					}
				} else if (cur_ccty_type == 3) {
					put(cur_x, cur_y, direction_selected[i]);
					put(cur_x, cur_y, (direction_selected[i] + 1) % 4);
				} else if (cur_ccty_type == 1) { // 4
					put(cur_x, cur_y, direction_selected[i]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (temp_map[i][j] == 0) {
						temp++;
					}
				}
			}

			result = Math.min(temp, result);
			return;
		}

		int[] current = cctv.get(count);
		// 타입에 따라서 방향을 두고 다음것으로 넘어가기
		int cctv_type = current[2];

		if (cctv_type == 5) { // 모든방향 두기
			direction_selected[count] = 4;
			put_dfs(count + 1);
		} else if (cctv_type == 2) {
			// 좌우
			direction_selected[count] = 13;
			put_dfs(count + 1);
			// 상하
			direction_selected[count] = 20;
			put_dfs(count + 1);
		} else {// 4, 한쪽 방향 뺄거 넣기 // 3은 4방향이고 // 1도 4방향
			for (int d = 0; d < 4; d++) {
				direction_selected[count] = d;
				put_dfs(count + 1);
			}
		}
	}

	private static void put(int x, int y, int d) {
		int newX = x;
		int newY = y;
		while (true) {
			newX += dx[d];
			newY += dy[d];
			// 범위 밖
			if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
				break;
			}
			// 벽인경우 끝
			if (temp_map[newY][newX] == 6) {
				break;
			}

			if (temp_map[newY][newX] == 0) {
				temp_map[newY][newX] = -1;
			}
		}
	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.printf("%3d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

//	private static void remove(int x, int y, int d) {
//		int newX = x;
//		int newY = y;
//		while (true) {
//			newX += dx[d];
//			newY += dy[d];
//			// 범위 밖
//			if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
//				break;
//			}
//			// 벽인경우 끝
//			if (map[newY][newX] == 6) {
//				break;
//			}
//			// 감시구역 삭제할때 주변 것까지 지워버리는 사태 발생, 처음부터 다시 짜기
//			if (map[newY][newX] == -1) {
//				map[newY][newX] = 0;
//			}
//		}
//	}

}
