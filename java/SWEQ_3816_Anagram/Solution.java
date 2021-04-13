import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int result = 0;
			String[] str = br.readLine().split(" ");

			char[] str1 = str[0].toCharArray();
			char[] str2 = str[1].toCharArray();

			int[] counts = new int[26]; // 26개의 알파벳을 저장할 공간

			int first_size = str[0].length();
			int second_size = str[1].length();

			for (int i = 0; i < first_size; i++) {
				counts[str1[i] - 'a']++;
			}

			int[] tmp = new int[26];

			for (int i = 0; i < second_size - first_size + 1; i++) {

				if (i == 0) {
					for (int j = i; j < first_size + i; j++) {
						tmp[str2[j] - 'a']++;
					}
				} else {
					tmp[str2[i - 1] - 'a']--;
					tmp[str2[i+first_size-1] - 'a']++;
				}

				boolean insert_flag = true;
				for (int a = 0; a < 26; a++) {
					if (tmp[a] != counts[a]) {
						insert_flag = false;
						break;
					}
				}

				if (insert_flag == true) {
					result++;
				}
			}

			System.out.println("#" + t + " " + result);
		}
	}

}
