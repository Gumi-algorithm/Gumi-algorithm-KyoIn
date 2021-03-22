import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

//문제 이해가 제일 어려운 문제였다.
//몇시간 동안 아무리 해도 답이 안나와서 인터넷에 검색 해봤더니. 돌아가는데  원형이 아니였다.
//지옥 갔다왔다...
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		int N = Integer.parseInt(str[0]);
		int K = Integer.parseInt(str[1]);

		int[] list = new int[2 * N];
		boolean[] robot = new boolean[N];

		// 내구도 입력
		String[] str1 = br.readLine().split(" ");
		for (int i = 0; i < 2 * N; i++) {
			list[i] = Integer.parseInt(str1[i]);
		}

		boolean flag = true;
		// 단계
		int result = 0;
		
		while (flag) {
			// 단계 증가
			result++;

			// 1. 벨트 한칸 회전
			// 뒤로 부터 생각하면 편한데, 이것도 안되서 엄청 시간씀...
			// 벨트 회전
			int end = list[2 * N - 1];
			for (int i = 2 * N - 2; i > -1; i--) {
				list[i + 1] = list[i];
			}
			list[0] = end;
			// 로봇 이때 회전해야함, 이거 안넣어줌.... 미치는줄
			for (int i = N - 2; i > -1; i--) {
				robot[i + 1] = robot[i];
			}
			robot[0] = false;

			//로봇 내리기
			if (robot[N - 1] == true) {
				robot[N - 1] = false;
			}
			
			//2. 로봇 한칸씩 옮기기
			for (int i = N - 2; i > 0; i--) {
				// 로봇 ㅇ, 옆에는 로봇 X, 내구도는 1보다 큼
				if (robot[i] == true && robot[i + 1] == false && list[i + 1] > 0) {
					// 옮기기
					robot[i + 1] = true;
					robot[i] = false;
					// 내구도 감소
					list[i + 1]--;
				}
			}
			// 3. 올라가는 위치에 로봇이 없다면 로봇을 올린다
			if (list[0] > 0 && robot[0] == false) {
				robot[0] = true;
				list[0]--;
			}
			// 4. 내구도가 0인 칸 개수가 K이상이라면 과정을 종료
			int check=0;
			for(int i=0;i<2*N;i++) {
				if(list[i]==0) {
					check++;
				}
			}
			if(check>=K) {
				flag = false;
			}
		}

		System.out.println(result);
	}

}
