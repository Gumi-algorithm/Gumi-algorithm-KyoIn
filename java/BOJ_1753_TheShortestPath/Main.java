import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
1 1
1
1 2 2

간선 정보는 인접 행렬이 아니라 인접 리스트로 저장해야 합니다.
 인접 행렬을 사용하게 되면 2만 개의 정점에 대해 2만 개 정점으로 가는 간선들을 저장해야 하는데, 간선 하나당 1바이트에 저장한다고 하더라도 4억 바이트,
  약 400MB의 메모리를 쓰게 될 뿐 아니라 시간복잡도 역시 O(V^2)으로 좋지 못하게 됩니다. 어떻게든 압축하면 통과는 시킬 수 있을지도  모르겠지만, 사서 고생입니다. 
현재 정점에 연결된 간선들의 번호만 리스트로 저장해서 사용하는 것이 좋습니다.

 */
public class Main {

	static class Node implements Comparable<Node> {
		int vertex;

		public Node(int vertex, int totalDist) {
			super();
			this.vertex = vertex;
			this.totalDist = totalDist;
		}

		int totalDist;

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(totalDist, o.totalDist);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 모든 간선의 가중치는 10 이하의 자연수이다.
		// (1≤V≤20,000, 1≤E≤300,000)
		// 20000*300000 시간초과 남... 이코드 폐기
		// 인접리스트로만 가능하대!!!
		String[] str = br.readLine().split(" ");
		int V = Integer.parseInt(str[0]);
		int E = Integer.parseInt(str[1]);

		// 시작 정점
		int K = Integer.parseInt(br.readLine());

		// 초기화
//		int[][] map = new int[V + 1][V + 1];
//		for (int i = 0; i <= V; i++) {
//			for (int j = 0; j <= V; j++) {
//				map[i][j] = Integer.MAX_VALUE / 2;
//			}
//		}

		ArrayList<Node>[] list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		// u에서 v로 가는 가중치 w인 간선이 존재
		// 1부터 V까지
		for (int i = 0; i < E; i++) {
			String[] str1 = br.readLine().split(" ");
			int u = Integer.parseInt(str1[0]);
			int v = Integer.parseInt(str1[1]);
			int w = Integer.parseInt(str1[2]);

			list[u].add(new Node(v, w));

		}

		// 다익스트라용
		int[] D = new int[V + 1];
		Arrays.fill(D, Integer.MAX_VALUE / 2);

		D[K] = 0;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(K, D[K]));
		Node current = null;

		boolean[] visited = new boolean[V + 1];
		while (!queue.isEmpty()) {
			current = queue.poll();

			if (visited[current.vertex] == true) {
				continue;
			}

			visited[current.vertex] = true;
			//for (int i = 1; i <= V; i++) { 연결리스트라서 정점 개수만큼 없을수가있다.
			for(Node node : list[current.vertex]){
				if (D[node.vertex] > D[current.vertex] + node.totalDist) {
					D[node.vertex] = D[current.vertex] + node.totalDist;
					queue.offer(new Node(node.vertex, D[node.vertex]));
				}
			}

		}

		for (int i = 1; i <= V; i++) {
			if (i == K) {
				System.out.println("0");
			} else if (D[i] == Integer.MAX_VALUE / 2) {
				System.out.println("INF");
			} else {
				System.out.println(D[i]);
			}
		}

	}

}
