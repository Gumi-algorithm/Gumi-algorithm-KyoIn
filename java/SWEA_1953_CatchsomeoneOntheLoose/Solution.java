import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
1
20 20 13 11 13
0 0 0 1 4 4 4 0 0 0 0 0 0 0 0 1 2 3 1 0
0 0 0 0 0 0 0 0 0 0 0 4 2 7 7 2 0 1 1 0
0 0 0 0 0 0 0 0 0 6 2 4 4 2 0 4 7 0 6 0
0 0 0 7 5 5 3 0 0 7 5 0 5 6 4 2 6 3 1 5
0 0 0 1 2 6 3 3 7 0 3 6 2 4 5 6 7 7 5 7
0 0 0 3 7 6 1 5 3 3 4 5 7 6 0 4 3 3 1 1
0 1 2 1 5 6 1 6 1 6 5 1 6 0 0 3 4 1 7 6
0 2 3 2 2 7 3 0 0 3 2 5 2 1 0 6 5 1 6 5
0 2 5 7 0 7 1 3 3 4 1 3 3 0 2 3 3 2 4 1
4 0 0 7 2 4 2 2 1 3 1 6 5 5 6 2 5 1 1 6
5 6 4 0 3 6 5 2 2 6 1 2 0 1 7 5 7 2 2 2
1 6 3 1 4 4 1 0 3 0 4 2 7 2 0 2 3 6 2 5
1 5 7 2 1 1 4 4 2 1 0 2 7 1 6 2 6 6 2 2
3 7 0 6 5 0 4 0 6 6 7 1 3 1 1 1 5 1 6 6
0 4 0 1 6 2 1 0 7 0 4 2 5 2 7 0 2 7 1 6
0 7 3 0 1 7 6 2 0 0 4 2 4 1 3 3 7 0 1 3
0 1 1 4 3 7 4 5 2 2 4 7 4 7 7 4 6 0 1 6
0 5 2 2 1 4 6 3 7 0 6 3 5 0 0 6 4 4 2 1
0 1 2 4 5 6 0 2 0 0 5 6 2 4 6 4 7 6 3 7
7 7 4 2 3 0 0 4 0 0 7 2 7 5 6 1 4 5 5 4
 */
public class Solution {

	static int N;// 세로
	static int M;// 가로
	static int R;// 맨홀 세로
	static int C;// 맨홀 가로
	static int L;// 탈출 소요된 시간

	static int[][] map;
	static boolean[][] visited;
	static String[] type = { null, "0312", "03", "12", "02", "32", "31", "01" };

	// 상좌우하
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { -1, 0, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TestCase; tc++) {

			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			R = Integer.parseInt(str[2]);
			C = Integer.parseInt(str[3]);
			L = Integer.parseInt(str[4]);

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				String[] str1 = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(str1[j]);
				}
			}

			visited = new boolean[N][M];
			bfs();

//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(visited[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();

			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j] == true) {
						result++;
					}
				}
			}
			System.out.println("#" + tc + " " + result);
		}
	}

	private static void bfs() {
		LinkedList<int[]> list = new LinkedList<>();

		list.add(new int[] { C, R });
		visited[R][C] = true;
		int time = 0;
		while (++time < L) {
			int size = list.size();
			while (size-- > 0) {
				int[] temp = list.poll();

				int x = temp[0];
				int y = temp[1];
				String info = type[map[y][x]];

				for (int d = 0; d < info.length(); d++) {
					int dir = info.charAt(d) - '0';
					int newX = x + dx[dir];
					int newY = y + dy[dir];

					if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
						continue;
					}

					if (visited[newY][newX] == true)
						continue;

					if(map[newY][newX]==0) {
						continue;
					}
					
					if (type[map[newY][newX]].contains(Integer.toString(3 - dir)) == true) {
						list.add(new int[] { newX, newY });
						visited[newY][newX] = true;
					}
				}
			}

		}

	}

}
