import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	static int M;
	static int N;
	static int H;

	static int[][][] tomato;
	static boolean[][][] visited;

	static int result = 0;

	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향
	static int[] dy = { -1, 1, 0, 0, 0, 0 };
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dh = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);

		tomato = new int[H][N][M];
		visited = new boolean[H][N][M];

		LinkedList<int[]> list = new LinkedList<>();

		for (int i = 0; i < H; i++) {
			for (int y = 0; y < N; y++) {
				String[] str1 = br.readLine().split(" ");
				for (int x = 0; x < M; x++) {
					tomato[i][y][x] = Integer.parseInt(str1[x]);
					if (tomato[i][y][x] == 1) {
						list.push(new int[] { x, y, i });
						visited[i][y][x] = true;
					}
				}
			}
		}

		// bfs
		while (!list.isEmpty()) {
			int[] temp = list.poll();
			int temp_x = temp[0];
			int temp_y = temp[1];
			int temp_h = temp[2];

			for (int d = 0; d < 6; d++) {
				int newX = temp_x + dx[d];
				int newY = temp_y + dy[d];
				int newH = temp_h + dh[d];

				// 범위밖
				if (newX < 0 || newY < 0 || newX >= M || newY >= N || newH < 0 || newH >= H) {
					continue;
				}
				// 재방문
				if (visited[newH][newY][newX]) {
					continue;
				}
				// 토마토가 없는 경우
				if (tomato[newH][newY][newX] == -1) {
					continue;
				}

				// 범위 안, 토마토 있음, 방문X
				visited[newH][newY][newX] = true;
				tomato[newH][newY][newX] = tomato[temp_h][temp_y][temp_x] + 1;
				list.add(new int[] { newX, newY, newH });

			}

		}
		
		
//		// 출력 확인
//		for (int i = 0; i < H; i++) {
//			for (int y = 0; y < N; y++) {
//				for (int x = 0; x < M; x++) {
//					System.out.print(tomato[i][y][x] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}

		// 토마토 확인
		for (int i = 0; i < H; i++) {
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (tomato[i][y][x] == 0) {
						System.out.println("-1");
						return;
					}
					result = Math.max(result, tomato[i][y][x]);
				}
			}
		}
		System.out.println(result-1);

	}

}
