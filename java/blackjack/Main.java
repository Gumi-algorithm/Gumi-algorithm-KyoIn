import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int result[];
	static int goal;
	static int arr[];
	static int now_max = 0;

	public static void recursive(int cnt,int level) {
		if (cnt == 3) {
			int tmp = 0;
			//System.out.println(Arrays.toString(result));
			for (int i = 0; i < 3; i++) {
				tmp += result[i];
			}
			if (goal >= tmp) {
				now_max = Math.max(tmp, now_max);
			}
			return;
		}
		
		for(int i=level;i<N;i++) {
			result[cnt] = arr[i];
			recursive(cnt+1, i+1);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		goal = Integer.parseInt(str[1]);

		arr = new int[N];
		result = new int[3];
		String[] str1 = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str1[i]);
		}
		recursive(0,0);
		bw.write(now_max+"\n");
		
		bw.flush();
		bw.close();
		br.close();

	}

}
