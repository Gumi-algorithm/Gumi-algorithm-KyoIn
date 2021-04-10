import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
6 6 16
0 0 0 0 1 1
0 0 0 0 0 1
1 1 1 1 1 1
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0
 */
public class Main {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		// (3 ≤ N, M ≤ 100, 1 ≤ T ≤ 10000)
		int N = Integer.parseInt(str[0]); // 행
		int M = Integer.parseInt(str[1]); // 열
		int T = Integer.parseInt(str[2]); // 시간

		// 초기화
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]+" ");
//			}System.out.println();
//		}

		int[][][] visited = new int[2][N][M];

		// x, y, 그람, 카운트
		LinkedList<int[]> list = new LinkedList<>();
		// 시작
		list.add(new int[] { 0, 0, 0, 0 });
		// (1,1)과 (N,M)은 0이다.
		visited[0][0][0] = 0;

		while (!list.isEmpty()) {
			int[] temp = list.poll();
			int x = temp[0];
			int y = temp[1];
			int sword = temp[2];
			int count = temp[3];

			if (count > T) {
				continue;
			}

			if (x == M - 1 && y == N - 1) {
				System.out.println(count);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int newX = x + dx[d];
				int newY = y + dy[d];

				// 범위
				if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
					continue;
				}
				// 재방문
				if (visited[sword][newY][newX] != 0) {
					continue;
				}
				// 그람이 없으면 벽확인
				if (sword == 0) {
					if (map[newY][newX] == 1) {
						continue;
					}
					// 그람이 있으면 검 true
					if (map[newY][newX] == 2) {
						//sword=1;이렇게 줬는데 이렇게 주는 바람에 계속틀림...
						//여기서 1을 줘버리면 그 이후에 sword0인거 없어짐...ㅜㅜ
						//0으로도 돌아야되는데...
						list.add(new int[] { newX, newY, 1, count + 1 });
						visited[sword][newY][newX] = count + 1;
					} else {
						list.add(new int[] { newX, newY, sword, count + 1 });
						visited[sword][newY][newX] = count + 1;
					}
					

				} else { 
					// 그람이 있으면 벽 부숨
					list.add(new int[] { newX, newY, 1, count + 1 });
					visited[sword][newY][newX] = count + 1;
				}

			}

		}

		System.out.println("Fail");
		return;

	}

}
