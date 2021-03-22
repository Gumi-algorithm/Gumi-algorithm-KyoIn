import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	// 상0 하1 좌2 우3
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static int L;
	static int R;
	static int size;
	static int[][] map;
	static int sum = 0;
	static int open = 0;

	static boolean[][] visited;
	static LinkedList<int[]> pos;

	static boolean check(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { x, y });
		pos.offer(new int[] { x, y });
		sum = map[y][x];
		open = 1;
		visited[y][x] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			y = current[1];
			x = current[0];

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if (ny < 0 || ny >= size || nx < 0 || nx >= size) {
					continue;
				}
				
				if(visited[ny][nx]==true)
					continue;
			
				
				int diff = Math.abs(map[ny][nx] - map[y][x]);
				
				if (L <= diff && diff <= R) {
					visited[ny][nx] = true;
					
					queue.add(new int[] { nx, ny });
					
					open++;
					sum += map[ny][nx];
					
					pos.add(new int[] { nx, ny });
				}
			}

		}
		if (open == 1) {
			pos.poll();
			return false;
		} else {
			sum = sum / open;
			while (!pos.isEmpty()) {
				int[] temp = pos.poll();
				map[temp[1]][temp[0]] = sum;
			}
	
			return true;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		size = Integer.parseInt(str[0]);
		// L명이상 R명 이하
		L = Integer.parseInt(str[1]);
		R = Integer.parseInt(str[2]);

		// map
		map = new int[size][size];
		for (int i = 0; i < size; i++) {
			String[] str1 = br.readLine().split(" ");
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(str1[j]);
			}
		}

		pos = new LinkedList<>();

		int result = 0;
		boolean flag = false;
		while (true) {
			visited = new boolean[size][size];

			// 하나씩 확인
			for (int y = 0; y < size; y++) {
				for (int x = 0; x < size; x++) {
					if (visited[y][x] == true)
						continue;
					
					if(check(x, y)==true) {
						flag=true;
					}
				}
			}
			if(flag == true) {
				result++;
			}else {
				break;
			}
			flag = false;
		}
		System.out.println(result);
	}

}
