package tomato_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class DOT {
	int x;
	int y;

	DOT(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class tomato {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		int M = Integer.parseInt(str[0]);
		int N = Integer.parseInt(str[1]);

		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		BFS(arr, N, M);
	}

	public static void BFS(int[][] arr, int N, int M) {
		Queue<DOT> q = new LinkedList<DOT>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1)
					q.add(new DOT(i, j));
			}
		}

		while (!q.isEmpty()) {
			DOT dot = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = dot.x + dx[i];
				int nextY = dot.y + dy[i];

				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
					continue;
				}
				if (arr[nextX][nextY] != 0) {
					continue;
				}
				arr[nextX][nextY] = arr[dot.x][dot.y] + 1;
				q.add(new DOT(nextX, nextY));
			}
		}
		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, arr[i][j]);
			}
		}
		System.out.println(max - 1);

	}

	public static void print(int[][] arr, int N, int M) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.println(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
