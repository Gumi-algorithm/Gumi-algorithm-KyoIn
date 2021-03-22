import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main15663 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int M;
	static int arr[];
	static int result[];
	static boolean visited[];
	

	public static void recursive(int cnt) throws Exception {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				bw.write(result[i] + " ");
			}
			bw.write("\n");
			return;
		}
		 int before=0;
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			//지금 레벨로 과연..?
			//지역 변수로 둬서 오면서 초기화되는걸 내 머리로 생각할수있나...
			//여이가 문제네
			if (before != arr[i]) {
				result[cnt] = arr[i];
				before = result[cnt];
				visited[i] = true;
				recursive(cnt + 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		

		String[] str1 = br.readLine().split(" ");
		arr = new int[N];
		result = new int[M];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str1[i]);
		}
		Arrays.sort(arr);

		recursive(0);

		bw.flush();
		bw.close();
		br.close();

	}

}
