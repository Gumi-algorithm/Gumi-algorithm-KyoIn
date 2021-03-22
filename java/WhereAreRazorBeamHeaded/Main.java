import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N = 0;
	static int R = 0;

	static boolean flag = false;
	static int[][] map;
	static int[] result = new int[2];
	// 위 아래 왼 오
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testcase = Integer.parseInt(br.readLine());

		for (int t = 0; t < testcase; t++) {

			String[] str = br.readLine().split(" ");

			N = Integer.parseInt(str[0]);
			R = Integer.parseInt(str[1]);

			// 판 밖에 위치해야 하므로 레이저의 좌표에는 0 혹은 n + 1이 포함된다
			map = new int[N + 2][N + 2];

			for (int r = 0; r < R; r++) {
				String[] str1 = br.readLine().split(" ");

				int y = Integer.parseInt(str1[0]);
				int x = Integer.parseInt(str1[1]);

				map[y][x] = 1;
			}

			String[] str2 = br.readLine().split(" ");

			int start_y = Integer.parseInt(str2[0]);
			int start_x = Integer.parseInt(str2[1]);
			// 위 아래 왼 오
			if (start_x == 0) // 오른쪽으로
				dfs(start_x + 1, start_y, 3);
			else if (start_x == N + 1) { // 왼쪽으로
				dfs(start_x - 1, start_y, 2);
			} else if (start_y == N + 1) { // 위로
				dfs(start_x, start_y-1, 0);
			} else if (start_y == 0) { // 아래로
				dfs(start_x, start_y+1, 1);
			}
		}

	}

	private static void dfs(int x, int y, int d) {
    //범위 확인
		if (x == 0 || x == N + 1 || y == 0 || y == N + 1) {
			System.out.println(y+" " +x);
			return;
		}
		//거울이 있으면
		if(map[y][x]==1) {
			// 위 아래 왼 오
			// 처음에 바꿔진 방향이 들어가야되는데 한칸 더하고 거울 확인하는식으로 하는 바람에
      // 한칸 더 이동하고 하는 식으로 해버려서 고생했음..
			if(d==0)
				dfs(x + dx[3], y + dy[3], 3);
			
			else if(d==1)
				dfs(x + dx[2], y + dy[2], 2);
			else if(d==2)
				dfs(x + dx[0], y + dy[0], 0);
			else if(d==3)
				dfs(x + dx[1], y + dy[1], 1);
		}else {
			dfs(x + dx[d], y + dy[d], d);
		}
	}

}
