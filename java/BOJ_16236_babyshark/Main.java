import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 하도 안되서 찾아봤더니 문제를 잘못읽은 거였음 (이 조건이 매우 중요...)
// 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
//아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다. 따라서, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
public class Main {

	static int size;
	static int[][] map;
	static boolean[][] visited;
	static LinkedList<int[]> pos;
	static int babyshark = 2; // 가장 처음에 아기 상어의 크기는 2
	static int babyshark_ate = 0;
	static int[] babyshark_coordinate;
	static int result = 0;

	public static int dx[] = { -1, 1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };

	private static void bfs(int x, int y) {
		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[] { x, y, 0 }); // 상어자리
		map[y][x] = 0;
		visited[y][x] = true;

		while (!list.isEmpty()) {
			// 뽑기
			int[] temp = list.poll();
			int temp_x = temp[0];
			int temp_y = temp[1];
			int time = temp[2];

			for (int d = 0; d < 4; d++) {
				int newX = temp_x + dx[d];
				int newY = temp_y + dy[d];

				// 범위 밖
				if (newX < 0 || newY < 0 || newX >= size || newY >= size)
					continue;
				// 이미 간곳
				if (visited[newY][newX])
					continue;
				// 지나갈수없는 물고기들의 존재....ㅜㅜ
				if (map[newY][newX] > babyshark) {
					continue;
				}
				// 범위안, 간곳X, 지나갈수있는 물고기
				list.add(new int[] { newX, newY, time + 1 });
				visited[newY][newX] = true;

				// 먹을수 있는 물고기 담기
				if (map[newY][newX] != 0 && map[newY][newX] != 9 && map[newY][newX] < babyshark) {
					pos.add(new int[] { newX, newY, time + 1 }); // 물고기들
				}

			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());

		map = new int[size][size];
		visited = new boolean[size][size];
		pos = new LinkedList<int[]>();
		babyshark_coordinate = new int[2];
		// 입력받기
		for (int y = 0; y < size; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x = 0; x < size; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());

				if (map[y][x] == 9) {
					babyshark_coordinate[0] = x;
					babyshark_coordinate[1] = y;
				}
			}
		}
		boolean flag = true;
		// 물고기 찾으러 떠나기
		while (flag) {
			// 물고기 없으면 끝
			flag = false;
			// visited초기화 및 물고기 있는지 없는지 검사
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					visited[y][x] = false;
				}
			}
			// 물고기가 있으니까 돌릴수있음!
			bfs(babyshark_coordinate[0], babyshark_coordinate[1]);
			//만약 갈곳이 없으면 큰 물고기안에 갇혀있는 경우가 있을 것 
			//갈수있는 pos가없을 것이다.
			//인터넷에서 반례보고 알았음
			if (pos.isEmpty()) {
				break;
			}
			// 업데이트 됨 pos중에서 제일 위면서 제일 왼쪽
			int[] change = new int[3];
			if (!pos.isEmpty()) {
				change = pos.poll();

			}
			// 여기 어렵더라. 제대로 검사 안되면 답이 안나옴
			while (!pos.isEmpty()) {
				int pos_temp[] = pos.poll();
				if (change[2] > pos_temp[2]) { // 거리 제일 짧은거
					change[0] = pos_temp[0];
					change[1] = pos_temp[1];
					change[2] = pos_temp[2];
				} else if (change[2] == pos_temp[2]) { // 거리가 같다면
					if (change[1] == pos_temp[1]) {
						if (change[0] > pos_temp[0]) {
							change[0] = pos_temp[0];
							change[1] = pos_temp[1];
							change[2] = pos_temp[2];
						}
					} else if (change[1] > pos_temp[1]) {
						change[0] = pos_temp[0];
						change[1] = pos_temp[1];
						change[2] = pos_temp[2];
					}
				}
			}
			// 더해주기
			result += change[2];
			babyshark_coordinate[0] = change[0];
			babyshark_coordinate[1] = change[1];
			babyshark_ate++;

			// System.out.println(change[2]);
			// 아기상어 level up
			if (babyshark_ate == babyshark) {
				babyshark++;
				babyshark_ate = 0;
			}

			map[change[1]][change[0]] = 9;

			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					visited[y][x] = false;

					if (map[y][x] != 0 && map[y][x] != 9 && babyshark > map[y][x]) {
						flag = true;
					}
				}
			}
			pos.clear();

		}

		System.out.println(result);
	}

}
