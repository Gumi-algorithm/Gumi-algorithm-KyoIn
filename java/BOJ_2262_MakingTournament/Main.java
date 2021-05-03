import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(str[i]));
		}

		int min = 0;
		int max = N;
		for (int i = 0; i < N - 1; i++) {
			int idx = list.indexOf(max);

			if (idx == 0) {
				min += (list.get(idx) - list.get(idx + 1));
			} else if (idx == list.size() - 1) {
				min += list.get(idx) - list.get(idx - 1);
			} else {
				min += Math.min(list.get(idx) - list.get(idx - 1), list.get(idx) - list.get(idx + 1));
			}

			list.remove(idx);
			max--;
		}

		System.out.println(min);
	}

}
