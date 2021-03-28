import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class dijkstra_PQ {

	static class Position implements Comparable<Position> {
		int x;
		int y;
		int cnt;

		@Override
		public int compareTo(Position o) {
			return Integer.compare(cnt, o.cnt);
		}

		public Position(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = 1;
		while (true) {
			int T = Integer.parseInt(br.readLine());
			if (T == 0) {
				return;
			}

			int[][] map = new int[T][T];

			for (int i = 0; i < T; i++) {
				String[] str = br.readLine().split(" ");
				for (int j = 0; j < T; j++) {
					map[i][j] = Integer.parseInt(str[j]);
				}
			}
			// 오, 아래
			int[] dx = { 0, 0, -1, 1 };
			int[] dy = { 1, -1, 0, 0 };

			PriorityQueue<Position> list = new PriorityQueue<Position>();
			list.add(new Position(0, 0, map[0][0]));

			int[][] val = new int[T][T];
			for (int i = 0; i < T; i++) {
				for (int j = 0; j < T; j++) {
					val[i][j] = Integer.MAX_VALUE - 100;
				}
			}

			val[0][0] = map[0][0];

			while (!list.isEmpty()) {
				Position temp = list.poll();
				if (temp.x == T - 1 && temp.y == T - 1) {
					break;
				}

				if (val[temp.y][temp.x] < temp.cnt)
					continue;

				for (int d = 0; d < 4; d++) {
					int newX = temp.x + dx[d];
					int newY = temp.y + dy[d];

					if (newX < 0 || newY < 0 || newX >= T || newY >= T) {
						continue;
					}

					int cost = map[newY][newX]+temp.cnt;
					if(val[newY][newX]>cost) {
						val[newY][newX]=cost;
						list.add(new Position(newX, newY, cost));
					}
				}
			}
			System.out.println("Problem " + testcase + ": " + val[T - 1][T - 1]);
			testcase++;
		}

	}

}
