import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
//https://m.blog.naver.com/kks227/220917078260
//동빈나 
	static int[] failtable; // 최대 일치길이 저장 테이블

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String target = br.readLine();
		String check = br.readLine();

		// 예시-> 어느세월에 이렇게 비교하겠어 :/
		// ABAABAA
		// ABAC ===> (그냥하나씩 비교하기, ABA가 일치하네 한번에 옮겨도 될까? => 시간X, ABA안에서 시작할수있는 여지)
		//  ABAC
		//   ABAC

		// KMP ->
		// ABAABAA
		// ABAC
		//   ABAC ===> ABA (A+B+A A가 접미사이면서 A가 접두사 이다, 앞쪽의 AB를 건너뛰게된다)
		//    ABAC

		// 하나씩 옮기면 너무 시간이 오래걸린다!!!! 이걸 해결하려면?
		// KMP -> 모든 문자열을 비교하지 않아도 부분 문자열을 찾을수있음 ( 반복되는 연산을 얼마나 줄일수있나)
		// 접두사 접미사 개념을 사용한다. 구해야 하는것은 => 접두사와 접미사가 일치하는 최대길이
		// 접두사 접미사가 일치 -> 건너뜀, 접두사 접미사의 최대 일치길이를 어떻게 구할까?

		if (KMP(target, check) == true) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}

	}

	static boolean KMP(String parent, String pattern) {
		int parentSize = parent.length(); // O(N)
		int patternSize = pattern.length();
		failtable(pattern);
		System.out.println(Arrays.toString(failtable));
		int j = 0;
		for (int i = 0; i < parentSize; i++) {
			// 일치X 이전단계값으로 옮겨주기
			while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
				j = failtable[j - 1];
			}
			if (parent.charAt(i) == pattern.charAt(j)) {
				if (j == patternSize - 1) { // 완벽히 일치
					/* 문제와 상관없는 코드
					// 같은 모든 부분을 출력하기위해 해당위치 만큼 건너뛰고 다시보기위함
					// System.out.println(i-patternSize+2+"번째");
					// j = failtable[j];
					 */
					return true;
				} else { // 계속 찾기
					j++;
				}
			}
		}
		System.out.println(Arrays.toString(failtable));
		return false;
	}

	// 접두사와 접미사를 늘려가며 비교하다가 일치하지 않는 경우 발생시
	// 최대 일치했던 부분으로 돌아가서 검사하는 방식
	static void failtable(String pattern) {
		int patternSize = pattern.length();
		failtable = new int[patternSize];
		int j = 0;
		// i는 비교하는 곳
		// j는 길이
		for (int i = 1; i < patternSize; i++) {
			// 일치하지 않는경우 j-1인 바로전으로 돌아감
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = failtable[j - 1];
			}
			// 일치하면 i, j둘다증가
			if (pattern.charAt(i) == pattern.charAt(j)) {
				failtable[i] = ++j;
			}
		}

	}

}
