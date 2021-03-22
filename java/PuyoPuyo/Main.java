import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int chain_result = 0;
	static boolean chain_flag = false;

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
// bfs -> 맵초기화 -> 개수세기 단계로 풀었음.

	// 위치 x, y 색
	static void bfs(int x, int y, char color) {
		// bfs로 갈수있는것 확인
		LinkedList<int[]> list = new LinkedList<>();
		// 같은 색 저장
		LinkedList<int[]> same_color = new LinkedList<>();

		list.add(new int[] { x, y });

		while (!list.isEmpty()) {
			int temp[] = list.poll();
			int cur_x = temp[0];
			int cur_y = temp[1];

			// 4방위 넣기
			for (int d = 0; d < 4; d++) {
				int newX = cur_x + dx[d];
				int newY = cur_y + dy[d];

				// 범위 나감
				if (newX < 0 || newX >= 6 || newY >= 12 || newY < 0)
					continue;
			

				// 색이 다름
				if (color != map[newY][newX])
					continue;
				
				// 재방문
				if (visited[newY][newX] == true)
					continue;

				// 같은 색 && 범위 안
				//System.out.println(newX + " " + newY + " " + color);
				visited[newY][newX] = true;
				list.add(new int[] { newX, newY });
				same_color.add(new int[] { newX, newY });
				//System.out.println(same_color.size()+" " +map[newY][newX]);
			}
		}

		//System.out.println(same_color.size());
		// 같은 색 없애기 + 연쇄 추가하기
		if (same_color.size() >= 4) {
			while (!same_color.isEmpty()) {
				int temp1[] = same_color.poll();
				int cur_X = temp1[0];
				int cur_Y = temp1[1];

				map[cur_Y][cur_X] = '.';
			}
			chain_flag = true;
		}
		
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 6; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];

		// map입력 받기 12줄 * 6줄
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		// R G B P Y 하나라도 만나면 거기서 bfs하고, 맵을 초기화하기

		// bfs 검사 완료
		// bfs(0,11,map[11][0]);

		// 연쇄 플래그 터트리기
		while (true) {
			visited = new boolean[12][6];
			chain_flag=false;
			
			for (int y = 11; y >= 0; y--) {
				for (int x = 0; x < 6; x++) {
					if (visited[y][x] == true)
						continue;
					
					if (map[y][x] != '.') {
						bfs(x, y, map[y][x]);
					}
				}
			}
			// 맵초기화 하기 reset_x, reset_y 완
			for (int re_x = 0; re_x < 6; re_x++) {
				LinkedList<Character> save = new LinkedList<>();

				for (int re_y = 11; re_y >= 0; re_y--) {
					if (map[re_y][re_x] != '.') {
						save.add(map[re_y][re_x]);
						map[re_y][re_x] = '.';
					}
				}
				int temp = 11;

				while (!save.isEmpty()) {
					map[temp][re_x] = save.poll();
					temp--;
				}
			}

			// 연쇄 플래그 완
			if (chain_flag == true) {
				chain_result++;
			} else {
				break;
			}

		}

		System.out.println(chain_result);

	}

}
