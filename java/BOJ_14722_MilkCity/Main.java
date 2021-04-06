import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	static int N;
	static int[][] map;
	static int[][][] dp;

	// 동 남
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// (1 ≤ N ≤ 1000)
		N = Integer.parseInt(br.readLine());

		// 우유 정보
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		dp = new int[N][N][3];
		System.out.println(DFS(0,0,0));

	}

	private static int DFS(int x, int y, int milk) {

		// 범위 밖
		if (y >= N || x >= N)
			return 0;

		// 값이미 있다면 이미 가장 큰것이 저장되있으니까 가져오기
		int count = dp[y][x][milk];
		if (count != 0)
			return count;

		int NextMilk = milk;
		if (map[y][x] == milk) { // 다음에 먹어야할우유가 맞다면 count++;
			NextMilk = (NextMilk + 1) % 3;
			count++;
		}

		// 다음에 먹어야할 우유넣고 칸 이동시키기
		int temp1 = DFS(x + dx[0], y + dy[0], NextMilk);
		int temp2 = DFS(x + dx[1], y + dy[1], NextMilk);

		count += Math.max(temp1, temp2);
		dp[y][x][milk] = count; // y,x 지점에 milk우유를 먹었는 카운트가 이만큼이다

		return count;
	}

}
