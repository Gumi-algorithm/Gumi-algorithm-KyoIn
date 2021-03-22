import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	// 1024* 3, 1024*2*3
	// (0 ≤ k ≤ 10, k는 정수)
	static char[][] arr = new char[3072+2][6144+2];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		
		//전부다 빈 칸으로 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N; j++) {
				arr[i][j] = ' ';
			}
		}
		//여기서 틀렸음 N-1
		draw(N, N - 1, 0);
		
		//sysout그냥 찍으면, 시간초과 생김 StringBuilder사용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void draw(int n, int x, int y) {
		if (n == 3) {
			// first
			arr[y][x] = '*';
			// Second
			arr[y + 1][x - 1] = '*';
			arr[y + 1][x + 1] = '*';
			// third
			for (int i = 0; i < 5; i++) {
				arr[y + 2][x - 2 + i] = '*';
			}
			return;

		}
    //규칙 찾기가 어려웠음.
    //막 해보다가 걸려서 다행이였음....
		draw(n / 2, x, y); // 맨위 
		draw(n / 2, x - (n / 2), y + (n / 2)); //왼쪽 아래
		draw(n / 2, x + (n / 2), y + (n / 2)); //오른쪽 아래

	}

}
