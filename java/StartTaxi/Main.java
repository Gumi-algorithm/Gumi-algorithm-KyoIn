import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	static int N; // 지도 크기
	static int M; // 사람수
	static int[][] map;
	static boolean[][] visited;
	static int oil; // 연료

	static int[] start = new int[2];

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int[][] guest;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 초기화
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		oil = Integer.parseInt(str[2]);

		map = new int[N][N];
		visited = new boolean[N][N];
		guest = new int[M][5];

		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}

		// 시작 지점
		String[] str2 = br.readLine().split(" ");
		// x, y
		start[0] = Integer.parseInt(str2[1]) - 1;
		start[1] = Integer.parseInt(str2[0]) - 1;

		// 승객 <출발지><목적지> <x.y> <x.y>

		boolean flag = true;

		for (int i = 0; i < M; i++) {
			String[] str3 = br.readLine().split(" ");
			// 시작점
			guest[i][0] = Integer.parseInt(str3[1]) - 1;
			guest[i][1] = Integer.parseInt(str3[0]) - 1;
			// 목표 지점
			guest[i][2] = Integer.parseInt(str3[3]) - 1;
			guest[i][3] = Integer.parseInt(str3[2]) - 1;
			// 방문 확인 0(방문X), 1(방문O)
			guest[i][4] = 0;

			if (map[guest[i][1]][guest[i][0]] == 1) {
				System.out.println("-1");
			}

			map[guest[i][1]][guest[i][0]] = i + 10;
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

		int guest_left = 0;
		while (true) {

			// 승객들 최단거리 구하기
			int guest_number = 0;
			int guest_far = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited[i][j] = false;
				}
			}

			int length = Integer.MAX_VALUE;

			boolean find_flag = false;
			int start_x = start[0];
			int start_y = start[1];

//			System.out.println((start_y + 1) + " " + (start_x + 1));

			if (map[start_y][start_x] - 10 >= 0) {
				find_flag = true;
				guest_number = map[start_y][start_x] - 10;
				guest_far = 0;

			} else {
				LinkedList<int[]> list = new LinkedList<>();

				list.add(new int[] { start[0], start[1], 0 });
				visited[start[1]][start[0]] = true;

				while (!list.isEmpty()) {

					int[] temp = list.poll();
					int temp_x = temp[0];
					int temp_y = temp[1];
					int temp_howfar = temp[2];

					for (int d = 0; d < 4; d++) {
						int newX = temp_x + dx[d];
						int newY = temp_y + dy[d];

						// 범위 밖
						if (newX < 0 || newY < 0 || newX >= N || newY >= N) {
							continue;
						}

						// 벽
						if (map[newY][newX] == 1) {
							continue;
						}

						// 재방문
						if (visited[newY][newX] == true) {
							continue;
						}
						// 게스트 확인
						if (map[newY][newX] - 10 >= 0) {
							if (guest[map[newY][newX] - 10][4] == -1) {

							} else {

								length = temp_howfar + 1;
//								System.out.println((newY + 1) + "" + (newX + 1) + " " + (map[newY][newX] - 10) + "까지의 거리" + length);
								find_flag = true;

								// 확인하기
								if (guest_far > length) {
									guest_number = map[newY][newX] - 10;
									guest_far = length;

								} else if (guest_far == length) {
									int now_x = guest[map[newY][newX] - 10][0];
									int now_y = guest[map[newY][newX] - 10][1];

									int before_x = guest[guest_number][0];
									int before_y = guest[guest_number][1];

									// 행 번호가 가장 작은 승객을,
									if (before_y > now_y) {
										guest_number = map[newY][newX] - 10;

									} 
									//행 번호 같은데
									if (before_y == now_y) {
										// 그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다.
										if (before_x > now_x) {
											guest_number = map[newY][newX] - 10;
										}
									}

								}
							}
						}
						// 범위 안, 벽X, 재방문X, 목적지X
						visited[newY][newX] = true;
//						if (start[0] == 1 && start[1] == 0) {
//							System.out.println((newY + 1) + " " + (newX + 1));
//						}
						list.add(new int[] { newX, newY, temp_howfar + 1 });

					}
				}

			}

			// 못 가는 경우는 pass
			if (find_flag == false) {
				flag = false;
				break;
			}

			// 방문 못하는 사람이 존재한다면 끝내고 -1 출력해야함
			if (flag == false) {
				break;
			}

			// 기름빼기
			oil -= guest_far;
//			System.out.println("승객까지 움직이는 거리 " + guest_far);
			if (oil < 0) {
				flag = false;
				break;
			}
			// 여기까지 오면 승객자리 까지
			start[0] = guest[guest_number][0];
			start[1] = guest[guest_number][1];
			// System.out.println(start[0]+" " +start[1]);
			// 승객 -> 목적지 거리 확인 연료 가능인경우 다녀오기
			int ToGoal = bfs_ToGoal(guest_number);
//			System.out.println("승객부터 목적지까지 거리" + ToGoal);
			if (ToGoal == -1) {
				flag = false;
				break;
			}
			// 기름 빼기
			oil -= ToGoal;
			if (oil < 0) {
				flag = false;
				break;
			}
			// 목적지 까지 이동 가능
			start[0] = guest[guest_number][2];
			start[1] = guest[guest_number][3];
			// System.out.println(start[0]+" "+start[1]);
			// oil채우기

			oil += (2 * ToGoal);
			// 승객 지우기
			guest[guest_number][4] = -1;
			map[guest[guest_number][1]][guest[guest_number][0]]=0;
			guest_left++;
			//System.out.println("남은 승객" + (M - guest_left));

			// 승객이 없는 경우 break;
			if (guest_left == M) {
				
				flag = true;
				break;
			}
		}
	

		if (flag == false) {
			System.out.println("-1");
		} else {
			System.out.println(oil);
		}

	}

	private static int bfs_ToGoal(int guest_number) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}

		int length = 0;

		int start_x = start[0];
		int start_y = start[1];

		//목적지
		int guest_X = guest[guest_number][2];
		int guest_Y = guest[guest_number][3];

		// 바로 그자리면 0반환
		if (guest_X == start_x && guest_Y == start_y) {
			return 0;
		}

		LinkedList<int[]> list = new LinkedList<>();

		list.add(new int[] { start_x, start_y, 0 });
		visited[start_y][start_x] = true;

		boolean find_flag = false;
		while (!list.isEmpty()) {

			int[] temp = list.poll();
			int temp_x = temp[0];
			int temp_y = temp[1];
			int temp_howfar = temp[2];

			for (int d = 0; d < 4; d++) {
				int newX = temp_x + dx[d];
				int newY = temp_y + dy[d];

				// 범위 밖
				if (newX < 0 || newY < 0 || newX >= N || newY >= N) {
					continue;
				}

				// 벽
				if (map[newY][newX] == 1) {
					continue;
				}

				// 재방문
				if (visited[newY][newX] == true) {
					continue;
				}

				// 1번 게스트 경우 끝
				if (newY == guest_Y && newX == guest_X) {
					length = temp_howfar + 1;
					find_flag = true;
					break;
				}

				// 범위 안, 벽X, 재방문X, 목적지X
				visited[newY][newX] = true;
				list.add(new int[] { newX, newY, temp_howfar + 1 });
			}

			if (find_flag == true) {
				break;
			}
		}

		// 자기자리는 아니면서 0이면 못가는 것
		if (length == 0) {
			return -1;
		}
		return length;
	}

}
