import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
3
2
1 2 100000
2 3 100000
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n(2 ≤ n ≤ 100) 도시 100 *100 *100 시간초과 X
		int N = Integer.parseInt(br.readLine());
		// 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000
		int M = Integer.parseInt(br.readLine());

		// 초기화
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.MAX_VALUE/2;
			}
		}

		// 비용은 100,000보다 작거나 같은 자연수이다.
		// 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다. -> 그중에서 작은거 넣기
		for (int i = 0; i < M; i++) {
			String[] str = br.readLine().split(" ");
			int start = Integer.parseInt(str[0]) - 1;
			int end = Integer.parseInt(str[1]) - 1;
			int price = Integer.parseInt(str[2]);

			if (map[start][end] > price) {
				map[start][end] = price;
			}
		}

		// 플루이드 돌리기
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		// 출력하기
		// 버스를 한 번 타는 데 드는 비용이 100000보다 작다고 해서 총 비용도 그러하리라는 보장은 없습니다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) { //자기자신일때
					System.out.print("0 ");
				} else if (map[i][j] == Integer.MAX_VALUE/2) { //갈수없는 경우
					System.out.print("0 ");
				} else {

					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}

	}

}
