import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main15654 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static int M;
	static int[] result;
	static int[] arr;
	static boolean[] isvisited;
	public static void recursive(int cnt) throws Exception {
		if(cnt == M) {
			for(int i=0;i<M;i++) {
				bw.write(result[i]+ " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(isvisited[i]) continue;
			
			result[cnt] = arr[i];
			isvisited[i] = true;
			recursive(cnt+1);
			isvisited[i] = false;
			
		}
		
		
	}
	
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		String[] str1 = br.readLine().split(" ");
		result = new int[M];
		arr = new int[N];
		isvisited = new boolean[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(str1[i]);
			
		}
		Arrays.sort(arr);
		recursive(0);
		
		
		bw.flush();
		bw.close();
		br.close();
		
	}

}
