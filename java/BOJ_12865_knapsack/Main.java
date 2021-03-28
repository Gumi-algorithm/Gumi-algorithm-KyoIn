import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str = br.readLine().split(" ");
		int N= Integer.parseInt(str[0]); // 물품의 수 N(1 ≤ N ≤ 100)
		int K = Integer.parseInt(str[1]); // 무게 K(1 ≤ K ≤ 100,000)
		
		//각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)
		int[][] arr = new int[N+1][2];;
		for(int i=0;i<N;i++) {
			String[] str1 = br.readLine().split(" ");
			int temp_w = Integer.parseInt(str1[0]);
			int temp_v = Integer.parseInt(str1[1]);
			arr[i+1][0]=temp_w;
			arr[i+1][1]=temp_v;
		}
//		for(int i=0;i<=N;i++) {
//			for(int j=0;j<2;j++) {
//				System.out.print(arr[i][j]+" ");
//			}System.out.println();
//		}
//		
		int[][] dp = new int[N+1][K+1];
		
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=K;j++) {
				if(arr[i][0]<=j) {//자신의 무게보다 클때
					//넣을수있다면 자신을 넣은거 vs 자신을 넣지 않은거
					dp[i][j] = Math.max(dp[i-1][j-arr[i][0]] + arr[i][1], dp[i-1][j]);
				}else {
					dp[i][j]=dp[i-1][j];
				}
			}
		}
//		for(int i=0;i<=N;i++) {
//			for(int j=0;j<=K;j++) {
//				System.out.print(dp[i][j]+" ");
//			}System.out.println();
//		}
		System.out.println(dp[N][K]);
		
	}

}
