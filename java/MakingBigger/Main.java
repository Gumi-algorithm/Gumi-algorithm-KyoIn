import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
// 1 ≤ K < N ≤ 500,000
// 완탐 불가능...;;

//첫번째 시도
//제일 작은거 세서 지우기 불가능(숫자 몇개인지 각각 세서 하는거)
//반례
//6 3
//691123
//내가 한 답 -> 693
//실제 답 -> 923
//도저히 모르겠어서 여기서 인터넷 참조 함. 


//두번째 시도
//인터넷에서 참조 
//스택으로 풀면 풀린다고해서 생각을 다시함
//과제 탑과 유사
//자기보다 큰거 나오면 지우기

//세번째 시도 
//코드랑 설명 봄...
//반례
//4 2
//1111인경우 1111이 나옴 덜 빼주는 경우가 생길수있음.


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(str[0]);
		int k = Integer.parseInt(str[1]);

		String str1 = br.readLine();
		

		LinkedList<Character> list = new LinkedList<>();
		
		for (int i = 0; i < str1.length(); i++) {
			//들어오는 값이 원래 있는 값보다 크면 삭제해야함
			//1924
			//(1) 9 비교 // 1삭제,9삽입
			//(9) 2 비교 // 2 삽입
			//(9, 2) 4 비교 // 2삭제, 4 삽입
			//(9, 4)
			while(!list.isEmpty() && k>0 && str1.charAt(i)>list.getLast()) {
				list.removeLast();
				k--;
			}
			list.addLast(str1.charAt(i));			
		}
		
		//덜 빼지는 경우가 존재하는 경우 그만큼 출력을 덜해야함
		while(list.size()>k) {
			sb.append(list.pollFirst());
		}

		

		System.out.println(sb.toString());
	}

}
