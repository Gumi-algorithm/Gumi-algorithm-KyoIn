import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[] arr = new int[N];

		String[] str = br.readLine().split(" ");
		LinkedList<Integer> list = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		for (int index = 0; index < N; index++) {
			while (!list.isEmpty() && arr[list.peek()] < arr[index]) {
				arr[list.pop()] = arr[index];
			}
			list.push(index);
		}

		while (!list.isEmpty()) {
			arr[list.pop()] = -1;
		}

		for (int i = 0; i < N; i++) {
			sb.append(arr[i] + " ");
		}
		System.out.println(sb.toString());

	}

}
