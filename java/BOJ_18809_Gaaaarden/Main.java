import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//계속 오류 떠서 도저히 못잡아서... 
//테케도 통과를 못해서...
//인터넷 소스 그대로 일단 내고 ... 나중에 다시 해보겠음...
//테케도 안되서 미치는줄...
//https://hoho325.tistory.com/164
//인터넷 소스를 참조하였습니다.

//초록색 배양액과 빨간색 배양액이 동일한 시간에 도달한 경우 땅에서 꽃이 핀다.
//꽃이 핀 땅에서는 배양액이 사려저 더 이상 배양액이 퍼지지 않는다.
//이거 두개 너무 어려워..
class Pos {
	int x;
	int y;
	int time;

	Pos(int x, int y) {
		this.x = x;
		this.y = y;
		this.time = 0;
	}
}

class Pair {
	int time;
	int type;

	Pair() {

	}

	Pair(int time, int type) {
		this.time = time;
		this.type = type;
	}
}

class Main {
	static int row, col;
	static int green, red;
	static int[][] garden;
	static ArrayList<Pos> possible;
	static boolean[] visited;
	static int[] greens, reds;
	static int max;
	static final int RED = 3;
	static final int GREEN = 4;
	static final int FLOWER = 5;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		// 행, 열, 초록색 배양액, 빨간색 배양액 입력 받기
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		green = Integer.parseInt(st.nextToken());
		red = Integer.parseInt(st.nextToken());
		possible = new ArrayList<>();

		// 정원의 상태 입력 받기
		// 0: 호수, 1: 배양액X, 2: 배양액 O
		garden = new int[row][col];
		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < col; j++) {
				garden[i][j] = Integer.parseInt(st.nextToken());
				if (garden[i][j] == 2)
					possible.add(new Pos(i, j));
			}
		}

		greens = new int[green];
		reds = new int[red];
		visited = new boolean[10];
		perm_green(0, 0);
		System.out.println(max);
	}

	//조합 두개 쓰는것도 진짜 생각 못한듯..
	private static void perm_green(int start, int r) {
		if (r == green) {
			perm_red(0, 0);
			return;
		}

		for (int i = start; i < possible.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				greens[r] = i;
				perm_green(i + 1, r + 1);
				visited[i] = false;
			}
		}
	}

	private static void perm_red(int start, int r) {
		if (r == red) {
			// 배양액 퍼트리기
			bfs();
			return;
		}

		for (int i = start; i < possible.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				reds[r] = i;
				perm_red(i + 1, r + 1);
				visited[i] = false;
			}
		}
	}

	private static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		Pair[][] state = new Pair[row][col];

		// state 초기화
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++)
				state[i][j] = new Pair();

		// 배양지로 선택한 곳에 배양액 놓기
		for (int i = 0; i < red; i++) {
			Pos p = possible.get(reds[i]);
			state[p.x][p.y] = new Pair(0, RED);
			q.offer(new Pos(p.x, p.y));
		}
		for (int i = 0; i < green; i++) {
			Pos p = possible.get(greens[i]);
			state[p.x][p.y] = new Pair(0, GREEN);
			q.offer(new Pos(p.x, p.y));
		}

		int sum = 0;
		// 위, 아래, 왼쪽, 오른쪽
		int[] xdir = { -1, 1, 0, 0 };
		int[] ydir = { 0, 0, -1, 1 };

		while (!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int curtime = state[x][y].time;
			int curtype = state[x][y].type;
			// 꽃이 핀 자리라면 퍼지지 않음
			if (state[x][y].type == FLOWER)
				continue;
			// 4 방향으로 퍼트리기
			for (int i = 0; i < 4; i++) {
				int dx = x + xdir[i];
				int dy = y + ydir[i];
				// 유효한 위치이고 호수가 아닌 경우
				if (isValidPosition(dx, dy) && garden[dx][dy] != 0) {
					/* 이거 처리가 안되서 인터넷 소스 참조...함...
					 * 4. 이동할때 다음과 같이 3가지 경우로 나뉩니다. 
					 * 4.1 아직 배양액이 퍼지지 않은 상태 => 퍼트림
					 * 4.2 빨간색이 있을때 초록색이 퍼지는 경우 => 꽃, 퍼지지 않기 때문에 queue에 담지 않음
					 * 4.3 초록색이 있을때 빨간색이 퍼지는 경우 => 꽃, 퍼지지 않기 때문에 queue에 담지 않음
					 */
					// 아직 배양액이 퍼지지 않았다면 퍼트림
					if (state[dx][dy].type == 0) {
						state[dx][dy] = new Pair(curtime + 1, curtype);
						q.offer(new Pos(dx, dy));
					}
					// 빨간색이 있을때 초록색이 퍼지는 거라면 꽃을 피우고 count
					else if (state[dx][dy].type == RED) {
						if (curtype == GREEN && state[dx][dy].time == curtime + 1) {
							sum++;
							state[dx][dy].type = FLOWER;
						}
					}
					// 초록색이 있을때 빨간색이 퍼지는 거라면 꽃을 피우고 count
					else if (state[dx][dy].type == GREEN) {
						if (curtype == RED && state[dx][dy].time == curtime + 1) {
							sum++;
							state[dx][dy].type = FLOWER;
						}
					}
				}
			}
		}
		// max 값 update
		max = max < sum ? sum : max;
	}

	private static boolean isValidPosition(int x, int y) {
		if (x < 0 || y < 0 || x >= row || y >= col)
			return false;
		return true;
	}
}