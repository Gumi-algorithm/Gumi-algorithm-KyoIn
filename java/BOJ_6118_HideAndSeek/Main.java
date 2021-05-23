import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		// N(2 <= N <= 20,000)
		// M(1<= M <= 50,000)개의 양방향 길
		// A_i 와 B_i(1<= A_i <= N; 1 <= B_i <= N; A_i != B_i)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);

		ArrayList[] list = new ArrayList[20001];
		for (int i = 0; i < 20001; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			int from = Integer.parseInt(str[0]);
			int to = Integer.parseInt(str[1]);

			list[from].add(to);
			list[to].add(from);
		}

		int result_dist = 0;
		int result_node = 0;
		int cntNode = 0;

		boolean[] visited = new boolean[20001];
		LinkedList<int[]> q = new LinkedList<>();
		visited[1] = true; // 1에서 시작
		q.offer(new int[] { 1, 0 }); // 1, 거리
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int current = temp[0];
			int dist = temp[1];

			if (dist > result_dist) {
				result_dist = dist;
				result_node = current;
				cntNode = 1;
			} else if (dist == result_dist) {
				result_node = Math.min(result_node, current);
				cntNode++;
			}

			for (int i = 0; i < list[current].size(); i++) {
				int next = (int) list[current].get(i);
				if (visited[next] == true) {
					continue;
				}

				visited[next] = true;
				q.addLast(new int[] { next, dist + 1 });
			}
		}
		System.out.println(result_node + " " + result_dist + " " + cntNode);
	}

}
