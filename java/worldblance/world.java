package theworldblanced;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Stack;

public class world {

	public static void main(String[] args) throws IOException {
		//LIFO이길래 그냥 스택으로 써버렸습니다ㅋㅋㅋㅋㅋ
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Character> buffer = new Stack<>();

		boolean result = false;
		String str = br.readLine();
		while (true) {
			if (str.charAt(0) == '.')
				break;

			for (int i = 0; i < str.length(); i++) {

				char temp = str.charAt(i);
				if (temp == '(') {//넣기
					buffer.push('(');
				} else if (temp == ')') {//빼기 
					if (buffer.isEmpty()) {//빼려고했는데 없으면 no
						result = false;
						break;
					} else {//뺏는데 다르면 no
						if (buffer.pop() != '(') {
							result = false;
							break;
						}
					}
				} else if (temp == '[') {//위와 동일!
					buffer.push('[');
				} else if (temp == ']') {
					if (buffer.isEmpty()) {
						result = false;
						break;
					} else {
						if (buffer.pop() != '[') {
							result = false;
							break;
						}
					}
					
				} else if (temp == '.') {//끝내기
					if (buffer.isEmpty() == true) {
						result = true;
					} else {
						result = false;
					}
					break;
				} else {
					continue;
				}

			}
			//결과 출력
			if (result == false) {
				bw.write("no\n");
			} else {
				bw.write("yes\n");
			}

			buffer.clear();//비우기
			result = false;
			str = br.readLine();
		}
		br.close();
		bw.close();
	}

}
