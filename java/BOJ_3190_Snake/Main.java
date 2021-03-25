import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N; // 보드의 크기
	static int K; // 사과의 개수
	static int L; // 뱀의 방향 변환 정도

	static int[][] map;// map

	static LinkedList<int[]> snake;// 뱀

	// 오른쪽 아래 왼쪽 위
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class motion {
		int sec;
		char C;

		public motion(int sec, char c) {
			super();
			this.sec = sec;
			C = c;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 보드의 크기
		K = Integer.parseInt(br.readLine()); // 사과의 개수

		map = new int[N][N];

		for (int k = 0; k < K; k++) {
			String[] str = br.readLine().split(" ");
			// 1행 1열 부터 시작함.
			int y = Integer.parseInt(str[0]) - 1;// 행
			int x = Integer.parseInt(str[1]) - 1;// 열
			map[y][x] = 1; // 사과
		}

		snake = new LinkedList<>();
		snake.push(new int[] { 0, 0 }); // 시작 위치는 0,0

		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 정도

		int direction = 0;// 처음에는 오른쪽으로 감
		int sec = 0;

		// 뱀의 방향 변환 횟수 L 저장용 motion_list
		ArrayList<motion> motion_list = new ArrayList<>();
		for (int i = 0; i < L; i++) {
			String[] str1 = br.readLine().split(" ");
			int X = Integer.parseInt(str1[0]);
			char C = str1[1].charAt(0);
			motion_list.add(new motion(X, C));
		}

		// 처음으로 방향전환 할 것을 골라놓기
		int motion_th = 0;
		int X = motion_list.get(motion_th).sec;
		char C = motion_list.get(motion_th).C;

		while (true) {
			// 시간
			sec++;

			// 뱀은 몸길이를 늘려 머리를 다음칸에 위치
			int[] temp = snake.peek();
			int curX = temp[0] + dx[direction];
			int curY = temp[1] + dy[direction];

			// 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
			// 벽만나서 out
			if (curX < 0 || curY < 0 || curX >= N || curY >= N) {
				System.out.println(sec);
				return;
			}

			// 자기자신 만나서 out
			for (int s = 0; s < snake.size(); s++) {
				int[] check = snake.get(s);
				if (curX == check[0] && curY == check[1]) {
					System.out.println(sec);
					return;
				}
			}

			// 사과가 있다면, 사과가 없어지고 꼬리는 움직이지 않는다.
			if (map[curY][curX] == 1) {
				snake.addFirst(new int[] { curX, curY });
				map[curY][curX] = 0;
			}
			// 사과가 없다면 몸길이를 줄여서 꼬리 위치 칸을 비워준다.
			else if (map[curY][curX] == 0) {
				snake.push(new int[] { curX, curY });
				snake.removeLast();
			}

			// 시간이 되면 C로 방향을 바꾸기
			// 오른쪽 아래 왼쪽 위 
			if (sec == X) {
				if (C == 'D') {
					direction += 1;
					if (direction == 4) {
						direction = 0;
					}
				} else if (C == 'L') {
					direction -= 1;
					if (direction == -1) {
						direction = 3;
					}
				}
				//다음 뱀의 방향은?
				motion_th++;
				if (motion_th != L) { //ArrayList를 넘어갈경우 고르면 X 
					X = motion_list.get(motion_th).sec;
					C = motion_list.get(motion_th).C;
				}
			}

		}

	}

}
