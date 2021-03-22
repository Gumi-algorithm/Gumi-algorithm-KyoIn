import java.util.Scanner;

public class Main15649 {
	static int N;
	static int M;
	static int result[];
	static boolean visited[];
	public static void permutation(int cnt) {
		if(cnt == M) {
			for(int i=0;i<result.length;i++) {
				System.out.print(result[i]+ " ");
			}
			System.out.println();
			return;
		}
		
		for(int j=0;j<N;j++) {
			if(visited[j]) continue;
			
			result[cnt] = j+1;
			visited[j] = true;
			permutation(cnt+1);	
			visited[j] = false;
			
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();	
		
		visited = new boolean[N];
		result = new int[M];
		
		permutation(0);
	}

}
