import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N * N];
		int index = 0;

//        for(int i=0; i<N; i++) {
//        	StringTokenizer st = new StringTokenizer(br.readLine());
//            for(int j=0; j<N; j++) {
//                arr[index++] = Integer.parseInt(st.nextToken());
//            }
//        }
// 
//        Arrays.sort(arr);
//		System.out.println(arr[N*N - N]);

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		//맨 첫줄
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			pq.offer(temp);
		}

		//두번째 줄~
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				//n개 유지
				pq.offer(temp);
				pq.poll();
			}
		}
		System.out.println(pq.poll());
	}

}
