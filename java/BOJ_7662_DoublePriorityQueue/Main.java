import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCase = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TestCase; tc++) {
			int K = Integer.parseInt(br.readLine());
			// 1. 배열로 하려고 했는데 숫자의 범위가 없음..
			// 2. priorityQueue 두개 사용하려고했는데 , 둘다 넣는거로, 빼는게 힘들어서 안되는 것같다.
			// 3. 문제를 서치를 해봤는데 -> Treemap 이라는게 존재함, 정렬이 되있어서 더 빠르다

			// 1. HashMap 중복키값 저장X, 해쉬알고리즘으로 검색속도가 매우 빠름
			// <검색 O(1)>
			// 2. TreeMap (키,값) 이진 검색트리(레드-블랙 트리(Red-Black tree)) 자동으로 오름차순, 추가 제거가 매우 빠름 Comparator가능
			// key값 기준 정렬, “숫자 > 알파벳 대문자 > 알파벳 소문자 > 한글” , <가져오기, 확인 O(logn)>
			// 3. LinkedHashMap 링크드 리스트로 저장됨.

			// 값표시, 값의 개수
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for (int i = 0; i < K; i++) {
				String[] str = br.readLine().split(" ");
				int current = Integer.parseInt(str[1]);

				if (str[0].contains("I")) {
					if (map.containsKey(current) == false) { // map에 없는 경우
						map.put(current, 1); // 넣어주기
					} else { // map에 존재하는 경우
						map.put(current, 1 + map.get(current)); // 값을 올려준다.

					}
				} else if (str[0].contains("D")) {
					if (map.isEmpty()) {// 비어있는경우 무시하라
						continue;
					}

					if (current == 1) {
						int max = map.lastKey(); // 제일 큰값을 가져온다

						int remaining = map.get(max);
						if (remaining == 1) {// 값이 하나만 존재
							map.remove(max);
						} else {// 값이 여러개
							map.put(max, remaining - 1);
						}
					} else if (current == -1) {
						int min = map.firstKey(); // 제일 작은 값

						int remaining = map.get(min);
						if (remaining == 1) {// 값이 하나일때
							map.remove(min);
						} else { // 값이 여러개일때
							map.put(min, remaining - 1);
						}
					}
				}
			}

			if (map.isEmpty() == true) {
				System.out.println("EMPTY");
			} else {
				System.out.println(map.lastKey() + " " + map.firstKey());
			}

		}
	}

}
