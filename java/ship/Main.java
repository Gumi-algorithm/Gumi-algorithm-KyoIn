import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		LinkedList<Integer> crane = new LinkedList<>();

		String[] str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++)
			crane.push(Integer.parseInt(str[i]));

		int B = Integer.parseInt(br.readLine());
		LinkedList<Integer> box = new LinkedList<>();
		String[] str1 = br.readLine().split(" ");

		//처음에 크레인 정렬을 
		crane.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		for (int i = 0; i < B; i++) {
			int temp = Integer.parseInt(str1[i]);
			box.push(temp);
		}

		box.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		if (box.peek() > crane.peek()) {
			System.out.println("-1");
			return;
		}

		int result = 0;
		while (!box.isEmpty()) {
			boolean flag = true;
			for (int i = 0; i < crane.size(); i++) {
				if (box.isEmpty())
					break;

				if (box.peek() <= crane.get(i)) {
					box.poll();
				} else {
					//크레인을 삭제해주지않는 경우 시간초과가 계속 생김, 
					//배열에서 linkedlist로 변경하고 시간초과로부터 탈출~
					for (int j = 0; j < box.size(); j++) {
						if (box.get(j) <= crane.get(i)) {
							box.remove(j);
							flag = false;
							break;
						}
					}
					
					if(flag == true) {
						crane.remove(i);
						i--;
					}
				}

			}
			result++;

		}
		System.out.println(result);

	}

}
