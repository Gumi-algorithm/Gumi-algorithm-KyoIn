import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase=1;
		while(true) {
			int T = Integer.parseInt(br.readLine());
			if(T==0) {
				return;
			}
			
			int[][] map = new int[T][T];
			
			for(int i=0;i<T;i++) {
				String[] str= br.readLine().split(" ");
				for(int j=0;j<T;j++) {
					map[i][j]=Integer.parseInt(str[j]);
				}
			}
			//오, 아래
			int[] dx = {0,0,-1,1};
			int[] dy = {1,-1,0,0};
			int[][] dp = new int[T][T];
			
			for(int i=0;i<T;i++) {
				for(int j=0;j<T;j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dp[0][0]=map[0][0];
			
			Queue<int[]> list = new LinkedList<int[]>();
			list.add(new int[] {0,0});
			
			while(!list.isEmpty()) {
				int[] temp = list.poll();
				
				for(int d=0;d<4;d++) {
					int newX = temp[0]+dx[d];
					int newY = temp[1]+dy[d];
					
					if(newX<0 || newY<0 || newX>=T || newY>=T) {
						continue;
					}
					
					if(dp[newY][newX]>dp[temp[1]][temp[0]]+map[newY][newX]) {
						dp[newY][newX]=dp[temp[1]][temp[0]]+map[newY][newX];
						list.add(new int[] {newX, newY});
					}
				}
			}
			
//			for(int i=0;i<T;i++) {
//				for(int j=0;j<T;j++) {
//					System.out.print(dp[i][j]+" ");
//				}System.out.println();
//			}
			
			System.out.println("Problem "+testcase+": "+dp[T-1][T-1]);
			testcase++;	
		}
		
	}

}
