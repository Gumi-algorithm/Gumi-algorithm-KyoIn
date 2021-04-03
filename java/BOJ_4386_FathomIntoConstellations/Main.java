import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static class Edge {
		@Override
		public String toString() {
			return "Edge [dist=" + dist + ", start=" + start + ", end=" + end + "]";
		}

		public Edge(int start, int end, double dist) {
			super();
			this.dist = dist;
			this.start = start;
			this.end = end;
		}

		double dist;
		int start;
		int end;
	}

	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayList<double[]> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			list.add(new double[] { Double.parseDouble(str[0]), Double.parseDouble(str[1]) });
		}

		// makeedge
		ArrayList<Edge> edges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			double[] start = list.get(i);
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;

				double[] end = list.get(j);

				double x = (start[0] - end[0]) * (start[0] - end[0]);
				double y = (start[1] - end[1]) * (start[1] - end[1]);

				double dist = Math.sqrt(x + y);

				// 같은거 있으면 안넣기
				boolean flag = true;
				for (int e = 0; e < edges.size(); e++) {
					Edge temp = edges.get(e);
					if (temp.start == j && temp.end == i && temp.dist == dist) {
						flag = false;
						break;
					}

				}

				if (flag == true) {
					edges.add(new Edge(i, j, dist));
				}

			}
		}

		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return Double.compare(o1.dist, o2.dist);
			}
		});

		// makeset
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i;
		}

		System.out.println(Arrays.toString(arr));
		double result = 0;

		for (int index = 0; index < edges.size(); index++) {

			Edge temp_edge = edges.get(index);

			if (union(temp_edge.start, temp_edge.end)==true) {
				result += temp_edge.dist;
			}
		}

		System.out.println(result);
	}

	private static boolean union(int start, int end) {
		start = find(start);
		end = find(end);

		if (start == end) {
			return false;
		}
		if(start >end)
            arr[start] = end;
        else
            arr[end] = start;
		return true;
	}

	private static int find(int x) {
		if (arr[x] == x)
			return x;

		return arr[x] = find(arr[x]);
	}

}
