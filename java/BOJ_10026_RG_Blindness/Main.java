import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	static int size = 0;
	static char[][] map;
	static boolean[][] visited;
	static int blind = 0;
	static int not_blind = 0;
	
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 입력 -> bfs(개수 세기) -> 초기화 && 배열 다시 설정 -> bfs(개수 세기)
	
	static void bfs(int x, int y, char colour, boolean flag) {
		LinkedList<int[]> list = new LinkedList<int[]>();
		list.add(new int[] { x, y });

		while (!list.isEmpty()) {
			int[] temp = list.poll();

			for (int d = 0; d < 4; d++) {
				int newX = temp[0] + dx[d];
				int newY = temp[1] + dy[d];

				// 범위 X
				if (newX < 0 || newY < 0 || newX >= size || newY >= size)
					continue;
				// 재방문
				if (visited[newY][newX] == true)
					continue;

				// 색이 다른경우
				if (map[newY][newX] != colour)
					continue;

				list.add(new int[] { newX, newY });
				visited[newY][newX] = true;
			}
		}
		//false인경우 적록 색약이 아닌사람 ++
		if(flag == false)
			not_blind++;
		else  //true인 경우 적록 색약인 사람 ++
			blind++;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());
		map = new char[size][size];
		visited = new boolean[size][size];

		// 입력받기
		for (int i = 0; i < size; i++) {
			String str = br.readLine();
			for (int j = 0; j < size; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// 적록색약이 아닌 사람
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (visited[y][x] == true)
					continue;
				//범위를 bfs에서 지우기때문에, bfs한번당 구역 한번 +1
				bfs(x, y, map[y][x], false);

			}
		}
		
		// visited 배열 초기화 && 적록색약에 맞게 배열 수정
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				visited[i][j] = false;
				// R-G구분을 못하기때문에, 그냥 하나로 바꿈
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}

		// 적록색약인 사람
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				if (visited[y][x] == true)
					continue;

				bfs(x, y, map[y][x], true);

			}
		}

		System.out.println(not_blind + " " + blind);

	}

}
