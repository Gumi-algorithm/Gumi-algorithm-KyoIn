import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N = 0;
	static int M = 0;

	static int[][] map;
	static boolean[][] visited;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static public void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1 장애물
		// 2 식구
		// 3 청국장
		// 4 스시
		// 5 맥앤치즈

		// 초기화
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		map = new int[N][M];
		visited = new boolean[N][M];

		int[] start = new int[2];

		for (int i = 0; i < N; i++) {
			String str1 = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(str1.charAt(j));
				// 식구 위치
				if (map[i][j] == 2) {
					start[0] = j;// x
					start[1] = i;// y
				}
			}
		}

		// 9000000 9백만 한번만에 해결해야 시간초과 안남
		// bfs를 이용해서 최소거리 찾기
		Queue<int[]> list = new LinkedList<>(); // bfs용 queue
		ArrayList<int[]> answer = new ArrayList<int[]>(); // 답 저장용

		// x, y, 거리
		list.add(new int[] { start[0], start[1], 0 });
		visited[start[1]][start[0]] = true;

		while (!list.isEmpty()) {
			int[] temp = list.poll();
			//System.out.println(Arrays.toString(temp));
			int dist = temp[2];

			for (int d = 0; d < 4; d++) {
				int newX = temp[0] + dx[d];
				int newY = temp[1] + dy[d];
				int newDist = temp[2] + 1;

				// 범위 밖
				if (newX < 0 || newY < 0 || newX >= M || newY >= N) {
					continue;
				}
				// 재방문
				if (visited[newY][newX] == true) {
					continue;
				}
				// 벽
				if (map[newY][newX] == 1) {
					continue;
				}

				// 범위 O, 방문X, 벽X
				list.add(new int[] { newX, newY, newDist });
				visited[newY][newX] = true;

				// 만약 값이 있다면
				if (map[newY][newX] == 3 || map[newY][newX] == 4 || map[newY][newX] == 5)
					answer.add(new int[] { newX, newY, newDist });
			}
		}
		if (answer.isEmpty()) {
			System.out.println("NIE");
		} else {
			// 가장 빨리 도착할수있는 음식의 최단 거리
			int result = answer.get(0)[2];

			// 최단거리 찾기
			for (int[] a : answer) {
				if (result > a[2]) {
					result = a[2];
				}
			}
			System.out.println("TAK\n"+result);
		}

	}

}
