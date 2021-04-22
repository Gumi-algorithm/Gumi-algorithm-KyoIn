import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;

/*
1
3 3 10
7 2 9
6 6 6
5 5 7
 */
public class Solution {

	static int N;
	static int M;
	static int C;
	static int[][] map;
	static int result;
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TestCase; tc++) {
			result = 0;
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]); // (3 ≤ N ≤ 10)
			M = Integer.parseInt(str[1]); // (1 ≤ M ≤ 5)
			C = Integer.parseInt(str[2]); // (10 ≤ C ≤ 30)

			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String[] str1 = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str1[j]);
				}
			}

			// 완탐
			get();

			System.out.println("#" + tc + " " + result);
		}
	}

	static int MaxSum;

	private static void get() {
		// 순열이 아니라 조합이였어...
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				// 이때는 그냥 넣으면 됨
				LinkedList<int[]> worker1 = new LinkedList<>();
				for (int a = j; a < j + M; a++) {
					worker1.add(new int[] { a, i, map[i][a] });
				}

				// 지금 행만 보기
				for (int j2 = j + M; j2 < N - M + 1; j2++) {
					LinkedList<int[]> worker2 = new LinkedList<>();
					for (int a = j2; a < M; a++) {
						worker2.add(new int[] { a, i, map[i][a] });
					}
					check(worker1, worker2);
				}

				// 다음 행부터 전부
				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 < N - M + 1; j2++) {
						LinkedList<int[]> worker2 = new LinkedList<>();
						for (int a = j2; a < j2 + M; a++) {
							worker2.add(new int[] { a, i2, map[i2][a] });
						}
						check(worker1, worker2);
					}
				}

			}
		}
	}

	private static void check(LinkedList<int[]> worker1, LinkedList<int[]> worker2) {
		int temp1 = 0;
		MaxSum = 0;
		makeSubSet(worker1, 0, 0, 0);
		temp1 = MaxSum;
		int temp2 = 0;
		MaxSum = 0;
		makeSubSet(worker2, 0, 0, 0);
		temp2 = MaxSum;

		result = Math.max(result, temp1 + temp2);
	}

	private static void makeSubSet(LinkedList<int[]> worker, int count, int sum, int pow_sum) {
		if (sum > C) {
			return;
		}
		if (count == worker.size()) {
			if (pow_sum > MaxSum) {
				MaxSum = pow_sum;
			}
			return;
		}

		makeSubSet(worker, count + 1, sum + worker.get(count)[2],
				pow_sum + (worker.get(count)[2] * worker.get(count)[2]));
		makeSubSet(worker, count + 1, sum, pow_sum);
	}

}
