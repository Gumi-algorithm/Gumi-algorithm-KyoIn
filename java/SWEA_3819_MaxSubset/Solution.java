import java.io.IOException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {

		//String으로 하나씩 받으면 메모리 초과나네...너무 어렵다... 스캐너로 무조건 받아야햄....
		Scanner sc = new Scanner(System.in);
		int TestCase = sc.nextInt();

		for (int tc = 1; tc <= TestCase; tc++) {

			int N = sc.nextInt();
			int[] arr = new int[N];

			arr[0] = sc.nextInt();

			int max = arr[0];
			//dp배열 선언하면 메모리초과나던데
			//공백 중간에 케이스에 들어가있음... 그래서 그런듯;
			for (int i = 1; i < N; i++) {
				int num = sc.nextInt();
                arr[i] = num;
				if (arr[i - 1] + arr[i] > arr[i]) {
					arr[i] = arr[i - 1] + arr[i];
				}
				if (max < arr[i]) {
					max = arr[i];
				}
			}

			System.out.println("#" + tc + " " + max);
		}

	}

}
