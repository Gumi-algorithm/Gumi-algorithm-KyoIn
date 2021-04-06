import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
	static ArrayList<Integer> sensors;
	static int N;
	static int K;
	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 센서의 개수 N(1<=N<=10,000),
		N = Integer.parseInt(br.readLine());
		// 집중국의 개수 K(1<=K<=1000)
		K = Integer.parseInt(br.readLine());
		// 센서 절대값 1,000,000 이하
		ArrayList<Integer> sensors = new ArrayList<>();
		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			sensors.add(Integer.parseInt(str[i]));
		}
		if (K >= N) {
			System.out.println("0");
			return;
		}

		// 정렬
		sensors.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});

		// 가능 영역의 길이의 합 최소
		// N개의 센서가 적어도 하나의 집중국과는 통신이 가능
		// 이분탐색인가 싶어서 이분탐색으로 했는데 아니였다.
		// 맨처음에 1부터 5, 5부터 9 이래서 5인줄. 반례 뒤지려고 질문검색 봤다가 알았다.
		// 1-3, 6-9 이렇게 5였음.
		// 한국어가 너무 어렵다. 
		
		ArrayList<Integer> diff = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			diff.add(sensors.get(i + 1) - sensors.get(i));
		}

		diff.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}

		});

		for (int i = 0; i < N - K; i++) {
			result += diff.get(i);
		}

		System.out.println(result);

		//이 사람 추천 그림 보고 이해함...
		//https://velog.io/@jkh9615/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-11000-%EA%B0%95%EC%9D%98%EC%8B%A4-%EB%B0%B0%EC%A0%95-Java-wskzqzk6
		
	}

}
