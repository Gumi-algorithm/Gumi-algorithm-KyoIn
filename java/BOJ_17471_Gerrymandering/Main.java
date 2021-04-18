import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int[] ppl;
	static int N;
	static boolean[] selected;
	static int result = Integer.MAX_VALUE / 2;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String[] str = br.readLine().split(" ");
		ppl = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			ppl[i] = Integer.parseInt(str[i - 1]);
		}

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			String[] str1 = br.readLine().split(" ");
			int k = Integer.parseInt(str1[0]);
			map[i][0] = k;
			for (int j = 1; j <= k; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}

		selected = new boolean[N + 1];
		dfs(0);
		if (result == Integer.MAX_VALUE / 2) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}
	}

	private static void dfs(int index) {

		if (index == N) {
			LinkedList<Integer> list1 = new LinkedList<>();
			LinkedList<Integer> list2 = new LinkedList<>();

			for (int i = 1; i <= N; i++) {
				if (selected[i] == true) {
					list1.add(i);
				} else {
					list2.add(i);
				}
			}

			// 한곳은 무조건 있어야함.
			if (list1.isEmpty() || list2.isEmpty()) {
				return;
			}

			// 연결 확인
			if (check(list1) == false) {
				return;
			}
			if (check(list2) == false) {
				return;
			}

			// 차이 계산해서 확인하기
			int temp1 = 0;
			int temp2 = 0;
//			System.out.println(list1.toString());
//			System.out.println(list2.toString());
			for (int a : list1) {
				temp1 += ppl[a];
			}
			for (int b : list2) {
				temp2 += ppl[b];
			}
			result = Math.min(result, Math.abs(temp1 - temp2));

			return;
		}

		selected[index + 1] = true;
		dfs(index + 1);

		selected[index + 1] = false;
		dfs(index + 1);
	}

	private static boolean check(LinkedList<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.add(list.getFirst());
		visited[list.getFirst()] = true;

		// 연결되어있다면
		// 맨처음꺼 넣어서 전부다 true가 되어야함
		while (!q.isEmpty()) {
			int temp = q.poll();
			int k = map[temp][0];
			for (int i = 1; i <= k; i++) {
				int current = map[temp][i];
				if (visited[current] == true) {
					continue;
				}

				if (list.contains(current) == true) {
					q.add(current);
					visited[current] = true;
				}
			}

		}

		// 전부다 연결되어있음
		for (int a : list) {
			if (visited[a] == false) {
				return false;
			}
		}
		return true;
	}

}
