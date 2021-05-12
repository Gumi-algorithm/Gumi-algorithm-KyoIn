import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str = br.readLine();

		int result = 0;
		int index = 0;
		int left = 0;
		int right = 0;

		//인터넷에서...찾았음...
		//문제 진짜..어렵다...
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				index += 1;
				left += 1;
			} else {
				index -= 1;
				right += 1;
			}

			if (index <= 1) {
				left = 0;
			}
			if (index == -1) {
				result = right;
				break;
			}
		}

		if (index > 0) {
			result = left;
		}

		System.out.println(result);
	}

}
