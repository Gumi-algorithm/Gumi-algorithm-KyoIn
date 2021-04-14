import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
/*
1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
 */
public class Solution {
	static int N;// 벽돌개수
	static int W;// 너비
	static int H;// 높이
	static int result;
	static int[][] map;
	static int[][] temp_map;
	static int[] arr;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);// N최대 4개면 완탐이네
			W = Integer.parseInt(str[1]);
			H = Integer.parseInt(str[2]);

			result = W * H; // 최대치

			map = new int[H][W];
			temp_map = new int[H][W];

			arr = new int[N];

			for (int i = 0; i < H; i++) {
				String[] str1 = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(str1[j]);
				}
			}

			// 중복 순열 만들기
			making(0);

			System.out.println("#" + tc + " " + result);

		}

	}

	private static void making(int count) {
		if (count == N) {

			// 벽돌 부수기
			breaking();
			// 남은 벽돌 확인하기
			int temp = 0;

			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (temp_map[i][j] != 0) {
						temp++;
					}
				}
			}
			result = Math.min(temp, result);
			return;
		}

		for (int i = 0; i < W; i++) {
			arr[count] = i;
			making(count + 1);
		}

	}

	private static void breaking() {
		// temp map으로 옮겨두기
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp_map[i][j] = map[i][j];
			}
		}

		LinkedList<int[]> list = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			// 떨어뜨릴곳
			int down = arr[i];
			// height 찾기
			int height = 0;
			for (int h = 0; h < H; h++) {
				if (temp_map[h][down] != 0) {
					height = h;
					break;
				}
			}
			// 연쇄작업시작
			list.add(new int[] { down, height, temp_map[height][down] });

			while (!list.isEmpty()) {
				int[] temp = list.poll();

				int x = temp[0];
				int y = temp[1];
				int distance = temp_map[y][x];
				temp_map[y][x] = 0;

				// 길이만큼 방향돌리기
				for (int howlong = 1; howlong < distance; howlong++) {
					for (int d = 0; d < 4; d++) {
						int newX = x + dx[d] * howlong;
						int newY = y + dy[d] * howlong;
						// 범위
						if (newX < 0 || newY < 0 || newY >= H || newX >= W) {
							continue;
						}
						// 0이면 안넣음
						if (temp_map[newY][newX] == 0) {
							continue;
						}

						list.add(new int[] { newX, newY, temp_map[newY][newX] });
					}
				}

			}
			// 바닥으로 내리기
			for (int a = 0; a < W; a++) {
				// 전부다 놓기
				LinkedList<Integer> temp = new LinkedList<>();
				for (int b = H - 1; b >= 0; b--) {
					if (temp_map[b][a] != 0) {
						temp.add(temp_map[b][a]);
					}
				}
				// 밑에서부터 배치
				int b = H - 1;
				while (!temp.isEmpty()) {
					temp_map[b][a] = temp.poll();
					b--;
				}
				while (b >= 0) {
					temp_map[b][a] = 0;
					b--;
				}
			}

//			System.out.println();
//			for (int y = 0; y < H; y++) {
//				for (int x = 0; x < W; x++) {
//					System.out.print(temp_map[y][x] + " ");
//				}
//				System.out.println();
//
//			}

		}

	}

}
