import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N; // 세로
	static int M; // 가로

	static int[][] map; // 원래꺼
	static boolean[] comb; // 조합 용
	static int[][] check; // 바꿀꺼

	static ArrayList<int[]> virus; // 바이러스 담아놓기
	static ArrayList<int[]> blank; // 빈공간

	static int result = -1;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		// (3 ≤ N, M ≤ 8) 완탐
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];

		// 0 빈칸
		// 1 벽
		// 2 바이러스
		virus = new ArrayList<>();
		blank = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
				if (map[i][j] == 2) { // 바이러스 넣기
					virus.add(new int[] { j, i });
				}
				if (map[i][j] == 0) { // 빈칸 넣기
					blank.add(new int[] { j, i });
				}
			}
		}

		comb = new boolean[blank.size()];
		put_wall_comb(0, 0);
		System.out.println(result);

	}

	private static void put_wall_comb(int count, int level) {
		if (count == 3) {
			// check배열 만들기
			check = new int[N][M];

			// 원래 map -> check로 옮기기

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					check[i][j] = map[i][j];
				}
			}
			// check에 벽두기
			for (int i = 0; i < blank.size(); i++) {
				if (comb[i] == true) {
					int[] temp = blank.get(i);
					check[temp[1]][temp[0]] = 1; // 벽으로 두기
				}
			}

			// 바이러스 늘리기
			virus_bfs();

			// 살아남은 공간 찾기
			int temp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j] == 0) {
						temp++;
					}
				}
			}
			result = Math.max(temp, result);
			return;
		}

		// 조합
		for (int i = level; i < blank.size(); i++) {
			comb[i] = true;
			put_wall_comb(count + 1, i + 1);
			comb[i] = false;
		}

	}

	private static void virus_bfs() {
		Queue<int[]> q = new LinkedList<int[]>();

		// 방문체크배열
		boolean visited[][] = new boolean[N][M];

		// 바이러스 다 때려넣기
		for (int i = 0; i < virus.size(); i++) {
			int[] temp = virus.get(i);
			q.add(temp);
			visited[temp[1]][temp[0]] = true;
		}

		// bfs
		while (!q.isEmpty()) {
			int[] temp = q.poll();

			for (int d = 0; d < 4; d++) {
				int newX = temp[0] + dx[d];
				int newY = temp[1] + dy[d];

				// 범위
				if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
					continue;
				}
				// 재방문
				if (visited[newY][newX] == true) {
					continue;
				}
				// 벽
				if (check[newY][newX] == 1) {
					continue;
				}

				// 바이러스두기
				check[newY][newX] = 2;
				// 넣기
				q.offer(new int[] { newX, newY });
				// 체크
				visited[newY][newX] = true;
			}
		}

	}

}
