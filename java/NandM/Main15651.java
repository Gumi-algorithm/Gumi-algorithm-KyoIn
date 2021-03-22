import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15651 {
	static int N;
	static int M;
	static int result[];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void comb(int cnt) throws Exception {
		if(cnt == M) {
			for(int i=0;i<result.length;i++) {
				bw.write(result[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int j=0;j<N;j++) {
			result[cnt] = j+1;
			comb(cnt+1);	
			
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		result = new int[M];
		
		comb(0);
		
		bw.flush();
		br.close();
		bw.close();
	}

}
