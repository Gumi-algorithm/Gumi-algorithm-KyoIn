import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int N;
	static ArrayList<Integer> SCV;
	static int[][][] visited;
	static int[] damage = new int[] { 9, 3, 1 };
	static int minCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// -9 -3 -1 모두 파괴 최소값
		// SCV의 수 N (1 ≤ N ≤ 3)
		// SCV N개의 체력 60보다 작거나 같은 자연수

		N = Integer.parseInt(br.readLine());

		SCV = new ArrayList<>();
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			SCV.add(Integer.parseInt(str[i]));
		}

		for (int i = N; i < 3; i++) {
			SCV.add(0);
		}


		visited = new int[61][61][61];
		minCnt = Integer.MAX_VALUE;
		
		DFS(SCV.get(0), SCV.get(1), SCV.get(2), 0);
		
		System.out.println(minCnt);

	}

	private static void DFS(Integer hp1, Integer hp2, Integer hp3, int cnt) {
		// 음수면 양수로 바꾸기
		hp1 = Math.max(0, hp1);
		hp2 = Math.max(0, hp2);
		hp3 = Math.max(0, hp3);

		// hp 정렬
		int max = Math.max(Math.max(hp1, hp2), hp3);
		int min = Math.min(Math.min(hp1, hp2), hp3);
		int mid = hp1 + hp2 + hp3 - max - min;

		hp1 = max;
		hp2 = mid;
		hp3 = min;

		// 결과
		if (hp1 <= 0 && hp2 <= 0 && hp3 <= 0) {
			minCnt = Math.min(cnt, minCnt);
			return;
		}

		// 재방문
		if (visited[hp1][hp2][hp3] == 1) {
			return;
		}
		// 미방문시 방문체크
		visited[hp1][hp2][hp3] = 1;
		
		//cnt많으면 return
		if (minCnt < cnt) {
			return;
		}

		//6가지 경우 다 때려넣기
		DFS(hp1 - 9, hp2 - 3, hp3 - 1, cnt + 1);
		DFS(hp1 - 9, hp2 - 1, hp3 - 3, cnt + 1);
		DFS(hp1 - 3, hp2 - 9, hp3 - 1, cnt + 1);
		DFS(hp1 - 3, hp2 - 1, hp3 - 9, cnt + 1);
		DFS(hp1 - 1, hp2 - 3, hp3 - 9, cnt + 1);
		DFS(hp1 - 1, hp2 - 9, hp3 - 3, cnt + 1);

	}

}
