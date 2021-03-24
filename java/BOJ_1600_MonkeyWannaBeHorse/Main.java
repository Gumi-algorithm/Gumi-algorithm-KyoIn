import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
4
6 10
0 0 1 1 1 1
0 1 1 0 1 1
0 1 1 1 1 0
0 1 1 1 1 0
0 1 1 1 1 0
0 1 1 1 1 0
0 1 1 0 1 1
0 1 1 1 1 1
1 1 1 1 0 0
1 0 0 1 1 0
1
4 4
0 1 1 1
0 0 1 1
1 0 1 1
1 1 1 0
 */
public class Main {

	//"이미 탐색했던 정점이더라도, 다른 경우일 수가 있다"
	// 교수님이 말하셨던 것과 동일함...! 벽돌깨기 문제
	// 처음에 2차원 배열로 해보려했는데 
	// 2.1을 오는 방법이 여러개 일수있다는것, 한번 온경우는 다시 올수없기때문이 이에 대한 처리가 필요하다.
	
	// Visit[x][y][a] 를 사용하였는데 이 배열의 의미는 "(x,y)지점에 능력을 a번 사용해서 왔습니다"
	// 이생각이 매우 중요한 것같다.
	
	// bfs는 무조건 먼저니까, 먼저 오면 탈출
	// 매번 Queue가 반복될 때마다, 현재 능력사용횟수와, 능력을 사용할 수 있는 최대 횟수 값을 비교
	// 아직 사용할 수 있는 기회가 남아있다면, 사용할 수 있도록 구현

	//0 ~ 8 말의 움직임, 9~11 원숭이 움직임
	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2, 0, 0, -1, 1 };
	static int[] dy = { -1, -2, -2, -1, 1, 2, 2, 1, 1, -1, 0, 0 };
	static int K = 0;

	static int W = 0;
	static int H = 0;

	static int[][] arr;
	static boolean[][][] visited;

	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		String[] str = br.readLine().split(" ");
		W = Integer.parseInt(str[0]);
		H = Integer.parseInt(str[1]);
		arr = new int[H][W];
		visited = new boolean[H][W][K+1];

		for (int i = 0; i < H; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(str1[j]);
			}
		}

		bfs();
	}

	private static void bfs() {
		LinkedList<int[]> queue = new LinkedList<>();
		queue.add(new int[] { 0, 0, 0, 0 });
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {

			int[] temp = queue.poll();
			
			int curX = temp[0];
			int curY = temp[1];
			int cnt = temp[2];
			int ability = temp[3];

			if (curX == W - 1 && curY == H - 1) {
				System.out.println(cnt);
				return;
			}
			
			if (ability <K) {
				for (int d = 0; d < 8; d++) {

					int newX = curX + dx[d];
					int newY = curY + dy[d];

					// 범위밖
					if (newX < 0 || newY < 0 || newX >= W || newY >= H) {
						continue;
					}
					// 벽이있을때
					if (arr[newY][newX] == 1) {
						continue;
					}
					// 능력 몇번써서 왔니?
					if (visited[newY][newX][ability+1] == true) {
						continue;
					}

					// 방문체크
					visited[newY][newX][ability+1] = true;
					queue.add(new int[] { newX, newY, cnt + 1, ability +1 });

				}
			}

			for (int d = 8; d < 12; d++) {

				int newX = curX + dx[d];
				int newY = curY + dy[d];

				// 범위밖
				if (newX < 0 || newY < 0 || newX >= W || newY >= H) {
					continue;
				}
				// 벽이있을때
				if (arr[newY][newX] == 1) {
					continue;
				}
				// 능력 몇번써서 왔니?
				if (visited[newY][newX][ability] == true) {
					continue;
				}

				// 방문체크
				visited[newY][newX][ability] = true;
				queue.add(new int[] { newX, newY, cnt + 1, ability});

			}

		}
		System.out.println("-1");
	}

}
