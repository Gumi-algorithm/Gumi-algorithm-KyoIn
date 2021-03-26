import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt(); // 가로
		int N = sc.nextInt(); // 세로

		// map확인
		String s = "";
		// 1 ≤ N, M ≤ 100
		int[][] map = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			s = sc.next();
			for (int j = 1; j <= M; j++) {
				map[i][j] = s.charAt(j - 1) - '0';
			}
		}
		// yx지점까지 k번 벽을 부수면서 왔습니다.
		int[][] visited = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				visited[i][j] = -1;
			}
		}
		// bfs

		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[] { 1, 1 });
		visited[1][1] = 0;

		while (!list.isEmpty()) {
			int[] temp = list.poll();

			for (int d = 0; d < 4; d++) {
				int newX = temp[0] + dx[d];
				int newY = temp[1] + dy[d];

				// 범위밖
				if (newX < 1 || newY < 1 || newX > M || newY > N) {
					continue;
				}
				if (map[newY][newX] == 0) { // 벽이 아니면
					if (visited[newY][newX] == -1 || visited[newY][newX] > visited[temp[1]][temp[0]]) {// 가본적이 없거나 기존에
						visited[newY][newX] = visited[temp[1]][temp[0]];
						list.addFirst(new int[] { newX, newY });
					}
				} else { // 벽인 경우 이동해보는데 부수는 횟수 1증가
					if (visited[newY][newX] == -1 || visited[newY][newX] > visited[temp[1]][temp[0]] + 1) {
						visited[newY][newX] = visited[temp[1]][temp[0]] + 1;
						list.addFirst(new int[] { newX, newY });
					}
				}
			}
		}

		System.out.println(visited[N][M]);
	}

}
