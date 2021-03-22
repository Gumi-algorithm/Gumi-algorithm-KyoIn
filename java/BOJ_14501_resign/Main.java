import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

//greedy라고 생각하고 접근 sorting하고 그냥 끝값만 비교했으나 실패 함 예제 입력 4번에서 막혀버림. 
//알고리즘 분류에 브루트포스 알고리즘인것을 확인후 접근 방식을 변경함
//그냥 dfs처럼 전부다 고른뒤, 그 뒤에서 확인하는 방식으로 접근하였음

public class Main {
	static int[][] list;
	static int N;
	static boolean[] result;
	static int answer=0;

	static void dfs(int level) {
		if (level == N) {
			int temp_answer=0;
			int temp=0;
						
			for(int i=0;i<N;i++) {
				//상담 X 패스
				if(result[i] == false)
					continue;
				
				//상담 O
				//N일까지만 상담 가능
				//전에 선택한 상담 이후부터 가능 
				//전에 선택한 상담 끝 < 이번에 선택하는 상담 시작
				if(N>=list[i][1] &&  temp<list[i][0]) {
					temp =list[i][1]; //상담 시작 변경
					temp_answer+=list[i][2]; //수익 더하기
				}
			}
			// 더 큰 수익이 등장 하면 값을 변경
			answer = Math.max(answer,temp_answer);
			return;
		}
		
		// 상담 O 
		result[level]  = true;
		dfs(level+1);
		
		// 상담 X
		result[level] =false;
		dfs(level+1);
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		list = new int[N][3];
		result = new boolean[N];

		for (int p = 0; p < N; p++) {
			String[] str = br.readLine().split(" ");
			//상담 시작, 상담 끝, 상담 수익으로 넣기
			int start = p + 1;
			int end = start + Integer.parseInt(str[0]) - 1;
			int howmuch = Integer.parseInt(str[1]);
			list[p] = new int[] { start, end, howmuch };
		}
		
		//정렬 해버리기
		Arrays.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int result = o1[1] - o2[1];
				if (result == 0) {
					result = o1[0] - o1[0];
				}
				return result;
			}
		});
				
		dfs(0);
		System.out.println(answer);

	}

}
