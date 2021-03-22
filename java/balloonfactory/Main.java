package ballonfactory15810;

import java.util.Scanner;

//static int BSearchRecursive(int arr[], int target, int low, int high) {
//	// 종료
//	if (low > high)
//		return -1;
//	// 중간 자르기
//	int mid = (low + high) / 2;
//	// target이면 끝
//	if (arr[mid] == target)
//		return mid;
//	// 작은 부분
//	else if (arr[mid] > target)
//		return BSearchRecursive(arr, target, low, mid - 1);
//	// 큰부분
//	else
//		return BSearchRecursive(arr, target, mid + 1, high);
//}

public class Main {
	static long sum = 0;
	static long min_time = Integer.MAX_VALUE;
	static int people;
	static long balloon;
	static long[] ppl;

	static long binary(long low, long high) {
		// 범위 탈출
		if (low > high)
			return -1;

		// 중간자르기
		long mid = (low + high) / 2;

		// mid분에 최소 m개 만들수있는지 검사
		long sum = 0;
		for (int i = 0; i < people; i++) {
			sum += mid / ppl[i];
		}
		
		// 더 작은게 가능한가 확인하는 코드
   		long tmp = 0;

		// 더 작은게 가능한가 확인하는 코드
		// 무조건 여기다 걸려서 -1 절대 못나오게 
		if (sum >= balloon) {
			tmp = binary(low, mid - 1);
			if (tmp != -1 && tmp < mid)
				return tmp;
			else
				return mid;
		}
		
		return binary(mid + 1, high);
	
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		people = sc.nextInt();
		balloon = sc.nextInt();
		ppl = new long[people];

		for (int i = 0; i < people; i++) {
			long time = sc.nextInt();
			// 시간 제일 작은거 고르기
			if (min_time > time)
				min_time = time;
			ppl[i] = time;
		}
		// 시간 제일 작은 사람 * 풍선 개수 => bound 보다는 절대로 클수없다
		// int로 하면 overflow 나는지 안되더라
		long bound = min_time * balloon;
		long result = binary(0, bound);
		System.out.println(result);

	}

}
