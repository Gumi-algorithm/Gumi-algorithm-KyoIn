import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main15657 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int M;
	static int arr[];
	static int result[];
	public static void recursive(int cnt, int level) throws Exception {
		if(cnt == M) {
			for(int i=0;i<M;i++)
				bw.write(result[i]+" ");
			bw.write("\n");
			return;
		}
		for(int i=level;i<N;i++) {
			result[cnt] = arr[i];
			recursive(cnt+1, i);
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		String[] str1 = br.readLine().split(" ");
		arr = new int[N];
		result = new int[M];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(str1[i]);
		}
		Arrays.sort(arr);
		
		recursive(0,0);
		
		bw.flush();
		bw.close();
		br.close();
		
	}

}
