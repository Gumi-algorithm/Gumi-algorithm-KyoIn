import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] dice;
	static int result = 0;
	static int[] selected;
	static int[][] map = { { 0, 1, 2, 3, 4, 5 }, // 0번자리
			{ 2, 2, 3, 4, 5, 9 }, // 1번자리
			{ 4, 3, 4, 5, 9, 10 }, // 2번자리
			{ 6, 4, 5, 9, 10, 11 }, // 3번자리
			{ 8, 5, 9, 10, 11, 12 }, // 4번자리
			{ 10, 6, 7, 8, 20, 29 }, // 5번자리
			{ 13, 7, 8, 20, 29, 30 }, // 6번자리
			{ 16, 8, 20, 29, 30, 31 }, // 7번자리
			{ 19, 20, 29, 30, 31, 32 }, // 8번자리
			{ 12, 10, 11, 12, 13, 14 }, // 9번자리
			{ 14, 11, 12, 13, 14, 15 }, // 10번자리
			{ 16, 12, 13, 14, 15, 16 }, // 11번자리
			{ 18, 13, 14, 15, 16, 17 }, // 12번자리
			{ 20, 18, 19, 20, 29, 30 }, // 13번자리
			{ 22, 15, 16, 17, 24, 25 }, // 14번자리
			{ 24, 16, 17, 24, 25, 26 }, // 15번자리
			{ 26, 17, 24, 25, 26, 27 }, // 16번자리
			{ 28, 24, 25, 26, 27, 28 }, // 17번자리
			{ 22, 19, 20, 29, 30, 31 }, // 18번자리
			{ 24, 20, 29, 30, 31, 32 }, // 19번자리
			{ 25, 29, 30, 31, 32, 32 }, // 20번자리
			{ 26, 20, 29, 30, 31, 32 }, // 21번자리
			{ 27, 21, 20, 29, 30, 31 }, // 22번자리
			{ 28, 22, 21, 20, 29, 30 }, // 23번자리
			{ 30, 23, 22, 21, 20, 29 }, // 24번자리
			{ 32, 26, 27, 28, 31, 32 }, // 25번자리
			{ 34, 27, 28, 31, 32, 32 }, // 26번자리
			{ 36, 28, 31, 32, 32, 32 }, // 27번자리
			{ 38, 31, 32, 32, 32, 32 }, // 28번자리
			{ 30, 30, 31, 32, 32, 32 }, // 29번자리
			{ 35, 31, 32, 32, 32, 32 }, // 30번자리
			{ 40, 32, 32, 32, 32, 32 }, // 31번자리
			{ 0, 32, 32, 32, 32, 32 } // 32번자리
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		dice = new int[10];
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(str[i]);
		}

		selected = new int[10];
		dfs(0);
		System.out.println(result);
	}

	static int check=0;
	private static void dfs(int count) {
		if (count == 10) {
			check();
			return;
		}

		for (int i = 0; i < 4; i++) {
			selected[count] = i;
			dfs(count + 1);
		}
	}
	private static void check() {
		int current_score = 0;
		int[] place = new int[35];
		int[] position = new int[4];

		place[0] = 4;

		for (int turn = 0; turn < 10; turn++) {

			int horse = selected[turn];
			
			int move = dice[turn];

			int current_position = position[horse];

			int next_position = map[current_position][move];

			int temp_score = map[next_position][0];

			// 한번이라도 return 생기면 점수 최대X
			if (place[next_position] > 0 && next_position != 32 && next_position != 0) {
				continue;
			} else {
				current_score += temp_score;
				place[next_position]++;
				place[current_position]--;
				position[horse] = next_position;
			}
		}
		result = Math.max(result, current_score);
	}

}
