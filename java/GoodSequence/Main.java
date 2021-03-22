import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N = 0;
	static boolean end = false;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dfs("", 0);
	}

	private static void dfs(String str, int count) {
		if (end)
			return;
		if (count == N) {
			// 여기까지오면 처음온게 무조건 제일 작음
			System.out.println(str);
			// 나머지 처리 (계속 올꺼니까)
			end = true;
			return;
		}

		for (int i = 1; i <= 3; i++) {
			if (check(str + i))
				dfs(str + i, count + 1);
		}
	}

	private static boolean check(String str) {
		// 인터넷 참조함...
		int length = str.length() / 2;

		for (int i = 1; i <= length; i++) {
			//자바 String substring(int index) index위치 포함 그 이후의 모든 문자열을 return
			//String substring(int begin, int end) begin위치 포함부터 end 전까지 리턴
			//11
			//1212
			//123123 잡기 가능
			if (str.substring(str.length() - i).equals(str.substring(str.length() - 2 * i, str.length() - i))) {
				return false;
			}
		}

		
//		// 같은 숫자 연속 11, 33, 22
//		for (int i = 0; i < str.length() - 1; i++) {
//			if (str.charAt(i) == str.charAt(i + 1)) {
//				return false;
//			}
//		}
//		
//		// 같은 숫자 반복 1212, 1313,
//		for (int i = 0; i < str.length() - 4; i++) {
//			if (str.charAt(i) == str.charAt(i + 2) && str.charAt(i + 1) == str.charAt(i + 3)) {
//				return false;
//			}
//		}
//		// 같은 숫자 반복 123123,
//		for (int i = 0; i < str.length() - 6; i++) {
//			if (str.charAt(i) == str.charAt(i + 3) && str.charAt(i + 1) == str.charAt(i + 4) && str.charAt(i+2)==str.charAt(i+5)) {
//				return false;
//			}
//		}

		return true;
	}

}
