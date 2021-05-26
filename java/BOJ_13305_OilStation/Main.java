import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int cities = Integer.parseInt(br.readLine());
		long[] city = new long[cities];
		long[] line = new long[cities - 1];

		String[] str = br.readLine().split(" ");
		for (int i = 0; i < cities-1; i++) {
			line[i] = Integer.parseInt(str[i]);
		}

		str = br.readLine().split(" ");
		for (int i = 0; i < cities; i++) {
			city[i] = Integer.parseInt(str[i]);
		}

		long min = Integer.MAX_VALUE;
		long result_left = 0;
		result_left += line[0] * city[0];
		min = Math.min(city[0], min);
		for (int i = 1; i < cities-1; i++) {
			min = Math.min(city[i], min);
			result_left += min * line[i];
		}
		System.out.println(result_left);

	}

}
