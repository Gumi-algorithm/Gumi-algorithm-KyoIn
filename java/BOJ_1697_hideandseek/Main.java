import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

	static int subin = 0;
	static int brother = 0;
	static int result = Integer.MAX_VALUE;
	static boolean[] visited;

	static void bfs() {
		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[] { subin, 0 });
		visited[subin] = true;

		while (!list.isEmpty()) {

			int[] temp = list.poll();
			int current = temp[0];
			int count = temp[1];

			for (int i = 0; i < 3; i++) {
				int next = 0;

				if (i == 0) {
					next = current - 1;
				} else if (i == 1) {
					next = current + 1;
				} else {
					next = current * 2;
				}

				if (current == brother) {
					result = count;
					
					return;
				}

				if (0 <= next && next <= 100000) {
					if (visited[next] == false) {
						list.offer(new int[] { next, count + 1 });
						visited[next] = true;
					}
				}

			}

		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		subin = Integer.parseInt(str[0]);
		brother = Integer.parseInt(str[1]);

		if (subin == brother) {
			System.out.println(0);
			return;
		}

		// 0 ≤ N, K ≤ 100,000
		visited = new boolean[100001];

		bfs();

		System.out.println(result);
	}

}
