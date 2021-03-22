import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15650 {
	static int N;
	static int M;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] result;
	static boolean[] visited;
	
	static void combination(int start, int cnt) throws Exception{
		if(cnt ==M) {
			for(int i=0;i<M;i++) {
				bw.write((result[i]+1) + " ");
			}
			bw.write("\n");
			return; // 이 부분 틀렸었는데 고쳤음.
		}
		
		for(int j=start;j<N;j++) {
			result[cnt]= j;
			combination(j+1, cnt+1);
			
		}
		
	}
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		result = new int[M];
		visited = new boolean[N];
		
		combination(0, 0);
		
		bw.flush();
		bw.close();
		br.close();
	}

}
