import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

	static int N; // 세로
	static int M; // 가로

	static int island;

	static int[][] map; // 지도
	static int[] arr;

	// 아래 위 오른쪽 왼쪽
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Edge {
		@Override
		public String toString() {
			return "Edge [from=" + Arrays.toString(from) + ", to=" + Arrays.toString(to) + ", length=" + length + "]";
		}

		public Edge(int[] from, int[] to, int length, int arrow) {
			super();
			this.from = from;
			this.to = to;
			this.length = length;
			this.arrow = arrow;
		}

		int[] from;
		int[] to;
		int length;
		int arrow;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		// 1 ≤ N, M ≤ 10 완탐..?
		// 3 ≤ N×M ≤ 100
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = str1[j].charAt(0) - '0';
			}
		}
		// 2 ≤ 섬의 개수 ≤ 6 완탐이다 이거 확인
		// 다리 놓을수있는 위치를 먼저 확인해서
		// 부분집합을 만들면
		// 하나씩 될꺼같은데...?
		// 결과적으로 MST만들기임
		// 섬개수 + map업데이트
		island = howmany_Island();

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		// 서로간의 거리를 구하기
		LinkedList<Edge> edge_list = new LinkedList<>();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				// 섬인경우 4방향으로 탐색
				if (map[y][x] != 0) {
					// 아래 위 오른쪽 왼쪽
					for (int d = 0; d < 4; d++) {
						int distance = 0;
						int curY = y;
						int curX = x;
						while (true) {
							curY = curY + dy[d];
							curX = curX + dx[d];
							distance++;

							// 범위밖
							if (curX < 0 || curY < 0 || curY >= N || curX >= M) {
								break;
							}
							// 자기자신 섬인경우 break;
							if (map[curY][curX] == map[y][x]) {
								break;
							}
							// 다른섬을 만났으면 넣기
							if (map[curY][curX] != 0) {
								if (distance >= 3) {
									boolean flag = true;
									int[] start = new int[] { x, y, map[y][x] };
									int[] End = new int[] { curX, curY, map[curY][curX] };
									for (int i = 0; i < edge_list.size(); i++) { //반복 없애기
										Edge temp = edge_list.get(i);
										if (start[0] == temp.to[0] && start[1] == temp.to[1] && End[0] == temp.from[0]
												&& End[1] == temp.from[1]) {
											flag = false;
										}
									}
									if (flag == true)
										edge_list.add(new Edge(start, End, distance, d));
								}
								break;
							}
						}
					}
				}
			}
		}

		edge_list.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Integer.compare(o1.length, o2.length);
			}
		});
		// 크루스칼
		int edgecount = 0;
		int ans = 0;
		arr = new int[island + 1];
		for (int i = 0; i < island + 1; i++) {
			arr[i] = i;
		}
		while (edgecount < island - 1) {
			if (edge_list.isEmpty())
				break;

			Edge curEdge = edge_list.poll();
			boolean flag = union(curEdge.from[2], curEdge.to[2]);
			if (flag == true) {
				edgecount++;
				ans += (curEdge.length - 1);
			}
		}

		if (edgecount == island- 1) {
			System.out.println(ans);
		} else {
			System.out.println("-1");
		}
	}

	private static boolean union(int i, int j) {
		i = find(i);
		j = find(j);

		if (i == j)
			return false;

		arr[j] = i;
		return true;
	}

	private static int find(int i) {
		if (arr[i] == i)
			return i;
		return arr[i] = find(arr[i]);
	}

	private static int howmany_Island() {
		boolean[][] visited = new boolean[N][M];

		int temp = 0;

		for (int i = 0; i < N; i++) { // 세로
			for (int j = 0; j < M; j++) { // 가로
				if (visited[i][j] == true) {
					continue;
				}

				if (map[i][j] == 1) {
					temp++;

					LinkedList<int[]> list = new LinkedList<>();
					visited[i][j] = true;
					list.add(new int[] { j, i }); // x,y
					map[i][j] = temp;

					while (!list.isEmpty()) {
						int[] cur = list.poll();

						for (int d = 0; d < 4; d++) {
							int newX = cur[0] + dx[d];
							int newY = cur[1] + dy[d];
							// 범위
							if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
								continue;
							}
							// 재방문
							if (visited[newY][newX] == true) {
								continue;
							}
							// 바다인경우
							if (map[newY][newX] == 0) {
								continue;
							}

							list.add(new int[] { newX, newY });
							visited[newY][newX] = true;
							if (map[newY][newX] == 1) {
								map[newY][newX] = temp;
							}
						}
					}
				}
			}

		}

		return temp;
	}
}
