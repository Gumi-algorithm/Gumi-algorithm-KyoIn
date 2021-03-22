import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15652 {
	static int N;
	static int M;
	static int result[];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	static void recursive(int cnt, int start) throws Exception{
		if(cnt==M) {
			for(int i=0;i<M;i++) {
				bw.write((result[i]+1) + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=start;i<N;i++) {
			result[cnt] = i;
			recursive(cnt+1, i);
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str[] = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		
		result = new int[M];
		recursive(0,0);
		
		bw.flush();
		bw.close();
		br.close();
		

	}

}
