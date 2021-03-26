import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PQ {
	static class Position implements Comparable<Position> {
		public Position(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

		int x;
		int y;
		int count;

		@Override
		public int compareTo(Position o) {
			return Integer.compare(count, o.count);
		}

	}

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

		boolean[][] visited = new boolean[N + 1][M + 1];

		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		PriorityQueue<Position> list = new PriorityQueue<>();
		list.add(new Position(1, 1, 0));
		visited[1][1] = true;

		int result = Integer.MAX_VALUE;
		while (!list.isEmpty()) {
			Position temp = list.poll();

			if (temp.x == M && temp.y == N) {
				result = Math.min(result, temp.count);
				break;
			}

			for (int d = 0; d < 4; d++) {
				int newX = temp.x + dx[d];
				int newY = temp.y + dy[d];

				// 범위밖
				if (newX < 1 || newY < 1 || newX > M || newY > N) {
					continue;
				}
				// 재방문
				if (visited[newY][newX]) {
					continue;
				}

				visited[newY][newX] = true;
				
				if (map[newY][newX] == 0) {
					list.add(new Position(newX, newY, temp.count));
				} else {
					list.add(new Position(newX, newY, temp.count + 1));
				}
			}

		}

		System.out.println(result);
	}

}
