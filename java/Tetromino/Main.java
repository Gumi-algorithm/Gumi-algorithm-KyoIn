import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
	static int N = 0;
	static int M = 0;
	static int[][] map;
	static boolean[][] visited;
	static int max;

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				// 생각하지도 못한 dfs 방법
				visited[y][x] = true;
				dfs(x, y, map[y][x], 1);
				visited[y][x] = false;
				//
				check(x, y);
			}
		}
		System.out.println(max);
	}

	// 노가다로 1시간쯤 짜다가 이 문제가 골드5인데 이렇게 멍청한 방식으로 풀리가 없다는 생각으로 인터넷 찾아봄
	// dfs 4칸인게 너무 충격인듯...
	// 답보고 진짜 충격 받아서 처음부터 다시품...ㅎㅎ;;
	private static void dfs(int x, int y, int sum, int count) {
		if (count == 4) {
			max = Math.max(sum, max);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];

			// 범위
			if (newX < 0 || newY < 0 || newX >= M || newY >= N)
				continue;

			// 재방문
			if (visited[newY][newX] == true)
				continue;

			visited[newY][newX] = true;
			dfs(newX, newY, sum + map[newY][newX], count + 1);
			visited[newY][newX] = false;
		}
	}

	// ㅗ 모양 돌리기
	private static void check(int x, int y) {
		int sum = 0;
		int temp = 0;

		// ㅗ
		if (x - 1 >= 0 && x + 1 < M && y - 1 >= 0) {
			sum += (map[y][x] + map[y][x - 1] + map[y][x + 1] + map[y - 1][x]);
		}
		// ㅏ
		if (x + 1 < M && y - 1 >= 0 && y + 1 < N) {
			temp += (map[y][x] + map[y][x + 1] + map[y - 1][x] + map[y + 1][x]);
		}
		sum = Math.max(temp, sum);
		temp = 0;
		// ㅓ
		if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < N) {
			temp += (map[y][x] + map[y - 1][x] + map[y + 1][x] + map[y][x - 1]);
		}
		sum = Math.max(temp, sum);
		temp = 0;
		// ㅜ
		if (x - 1 >= 0 && x + 1 < M && y + 1 < N) {
			temp += (map[y][x] + map[y + 1][x] + map[y][x - 1] + map[y][x + 1]);
		}
		sum = Math.max(temp, sum);

		max = Math.max(sum, max);

	}

}
