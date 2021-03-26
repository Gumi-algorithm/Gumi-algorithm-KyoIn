import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String[] str = br.readLine().split(" ");
			int N = Integer.parseInt(str[0]);

			int[][] map = new int[N][N];
			int temp = 1;
			// 연결이 안되있는 부분을 MAX로 바꾸기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[temp++]);
					if(map[i][j]==0) {
						map[i][j]=10000000;
					}

				}
			}

			// 플루이드
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//			

			// 확인하기
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				temp = 0;
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					temp += map[i][j];
				}
				result = Math.min(result, temp);

			}

			System.out.println("#" + t + " " + result);
		}

	}

}
