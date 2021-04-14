import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] solutions;

	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N은 2 이상 100,000 이하 완탐X
		N = Integer.parseInt(br.readLine());
		solutions = new int[N];

		// -1,000,000,000 이상 1,000,000,000 이하
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			solutions[i] = Integer.parseInt(str[i]);
		}
		// 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력
		Arrays.sort(solutions);
		// 정렬하면 되는데 이생각을 못하다니..nlogn이라서 시간내에 가능함!
		// 음수 양수 고르는거 어떻게 고를지 if문 하나씩 조건주고있었는데 sort하는거 기억하기..

		int start = 0;
		int end = N - 1;
		int sum = Math.abs(solutions[start] + solutions[end]);

		// 답 저장용
		int solution1 = start;
		int solution2 = end;

		while (start < end) {
			int temp = solutions[start] + solutions[end];
			// 계산보다 작으면 답을 업데이트
			if (Math.abs(temp) < Math.abs(sum)) {
				sum = temp;
				solution1 = start;
				solution2 = end;
				// 0이 나오면 끝
				if (temp == 0) {
					break;
				}
			}

			// 0보다 작으면 더 작은 음수를 더하면 됨
			if (temp < 0) {
				start++;
			} else { // 0보닫 크면 더 작은 양수를 더하면된다.
				end--;
			}

		}

		System.out.println(solutions[solution1] + " " + solutions[solution2]);
	}

}
