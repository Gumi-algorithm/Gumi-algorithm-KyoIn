import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	static int N;
	static int M;

	static int[] arr;

	public static class Edge implements Comparable<Edge> {
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", distance=" + distance + "]";
		}

		int start;
		int end;
		double distance;

		public Edge(int start, int end, double distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		ArrayList<double[]> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] str1 = br.readLine().split(" ");
			//정수는... 소수점이 가능해...
			//정수는 자연수가 아니야...
			// 8번이나 제출한 내자신 반성해...
			//중학교...다시가자...
			//int[]이거써서...바보같이...
			double[] temp = new double[2];
			temp[0] = Double.parseDouble(str1[0]);
			temp[1] = Double.parseDouble(str1[1]);
			list.add(temp);
		}
		// 유니온파인드 배열
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i;
		}
		// 이미 연결
		for (int i = 0; i < M; i++) {
			String[] str2 = br.readLine().split(" ");
			int start = Integer.parseInt(str2[0]);
			int end = Integer.parseInt(str2[1]);

			union_find(start - 1, end - 1);
		}
		// edge 초기화
		ArrayList<Edge> edge_list = new ArrayList<Edge>();
		for (int i = 0; i < N ; i++) {
			double[] start = list.get(i);
			for (int j = i + 1; j < N; j++) {
				double[] end = list.get(j);

				double x = (start[0] - end[0]) * (start[0] - end[0]);
				double y = (start[1] - end[1]) * (start[1] - end[1]);

				double distance = Math.sqrt(x + y);

				edge_list.add(new Edge(i, j, distance));
			}
		}

		Collections.sort(edge_list);

//		System.out.println(Arrays.toString(arr));
//		System.out.println(edge_list);
		double result = 0;

		for (int i = 0; i < edge_list.size(); i++) {
			Edge temp = edge_list.get(i);
			if (union_find(temp.start, temp.end) == true) {
				result += temp.distance;
			}
		}
		System.out.println(String.format("%.2f", result));

	}

	private static boolean union_find(int start, int end) {
		start = find(start);
		end = find(end);

		if (start == end) {
			return false;
		}
		if (start < end) {
			arr[end] = start;
		} else {
			arr[start] = end;
		}
		return true;
	}

	private static int find(int x) {
		if (arr[x]==x)
			return x;
		return arr[x] = find(arr[x]);
	}

}
