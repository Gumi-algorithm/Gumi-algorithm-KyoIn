import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 모든 간선의 가중치는 10 이하의 자연수이다.
		// (1≤V≤20,000, 1≤E≤300,000)
		// 20000*300000 시간초과 남... 이코드 폐기
		String[] str = br.readLine().split(" ");
		int V = Integer.parseInt(str[0]);
		int E = Integer.parseInt(str[1]);

		// 시작 정점
		int K = Integer.parseInt(br.readLine());

		// 초기화
		int[][] map = new int[V + 1][V + 1];
		for (int i = 0; i <= V; i++) {
			for (int j = 0; j <= V; j++) {
				map[i][j] = Integer.MAX_VALUE / 2;
			}
		}

		// u에서 v로 가는 가중치 w인 간선이 존재
		// 1부터 V까지
		for (int i = 0; i < E; i++) {
			String[] str1 = br.readLine().split(" ");
			int u = Integer.parseInt(str1[0]);
			int v = Integer.parseInt(str1[1]);
			int w = Integer.parseInt(str1[2]);

			map[u][v] = w;
		}

		// 다익스트라용
		int[] arr = new int[V + 1];
		boolean[] visited = new boolean[V + 1];

		for (int i = 0; i <= V; i++) {
			arr[i] = Integer.MAX_VALUE / 2;
		}

		arr[K] = 0;
		for (int i = 1; i <= V; i++) {
			int temp_dist = Integer.MAX_VALUE / 2;
			int temp_V = 0;
			for (int j = 1; j <= V; j++) {
				if (visited[j] == false && temp_dist > arr[j]) {
					temp_dist = arr[j];
					temp_V = j;
				}
			}

			visited[temp_V] = true;

			for (int j = 1; j <= V; j++) {
				if (visited[j] == false && arr[j] > arr[temp_V] + map[temp_V][j]) {
					arr[j] = arr[temp_V]+ map[temp_V][j];
				}

			}
		}

		for (int i = 1; i <= V; i++) {
			if (i == K) {
				System.out.println("0");
			} else if (arr[i] == Integer.MAX_VALUE / 2) {
				System.out.println("INF");
			} else {
				System.out.println(arr[i]);
			}
		}

	}

}
