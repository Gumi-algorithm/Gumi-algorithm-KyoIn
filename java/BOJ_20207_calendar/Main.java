import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int temp = Integer.compare(o1[0], o2[0]); 
				if (temp == 0) { //시작이 같을때
					return Integer.compare(o1[1], o2[1])*-1;
				}
				return temp;
			}
		});

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.add(new int[] { start, end });
		}


		//(1 ≤ N ≤ 1000), (1 ≤ S ≤ E ≤ 365)
		// 세로(일정), 가로 365일
		int[][] map = new int[1001][366]; 
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			int start = cur[0];
			int end = cur[1];
			
			for (int i = 1; i <= 1000; i++) {
				if (map[i][start]==0) {
					for (int j = start; j <= end; j++) {
						map[i][j] = 1;
					}
					break;
				}
			}
		}
		
//		for(int i=1;i<30;i++) {
//			for(int j=1;j<30;j++) {
//				System.out.print(map[i][j]+" ");
//			}System.out.println();
//		}System.out.println();
		
		int result = 0;
		int y_max = 0;
		int x_max = 0;
		
		int continue_flag = 0;
		for (int x = 1; x <= 365; x++) {
			continue_flag = 0;
			
			//세로
			for (int y = 1; y <= 1000; y++) {
				if (map[y][x]==1) {
					continue_flag = 1;
					y_max = Math.max(y_max,y);
				}
			}
			
			if (continue_flag==0) { //끊어짐
				result += (x_max* y_max);
				x_max = 0;
				y_max = 0;
			} else if(continue_flag==1) { //가로
				x_max++;
			}
		}
		//안더해진거 마무리
		if (continue_flag==1) {
			result += (y_max * x_max);
		}
		
		System.out.println(result);

	}

}
