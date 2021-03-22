package teaching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class teaching {
	static int n; // 단어개수 n
	static int k; // 가르치는 알파벳 수
	static String[] words;
	static int result=0;
	static boolean[] alpahet = new boolean[26];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] str = br.readLine().split(" ");

		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);
		
		if (k < 5) {
			System.out.println(0);
			return;
		} else if (k == 26) {
			System.out.println(n);
			return;
		}
		words = new String[n];

		alpahet['a' - 'a'] = true;
		alpahet['t' - 'a'] = true;
		alpahet['n' - 'a'] = true;
		alpahet['i' - 'a'] = true;
		alpahet['c' - 'a'] = true;

		for (int i = 0; i < n; i++) {
			String temp = br.readLine().replaceAll("anta|tica", "");
			words[i] = temp;
		}
		k -= 5;
		//시간 초과로 인한 인터넷 참조..!
		dfs(0, 0);
		System.out.println(result);
	}

	private static void dfs(int start, int count) {
		//나랑 비슷한 것같았는데, 다시한번 체크하는 과정을 없애고 그냥 돌려버렸음
		if (count == k) {
			int temp = 0;
			for (int i = 0; i < n; i++) {
				boolean isTrue = true;
				for (int j = 0; j < words[i].length(); j++) {
					//이 부분때문에 인터넷 참조함... 왜 이생각을 못했을까?
					if (alpahet[words[i].charAt(j) - 'a'] == false) {
						isTrue = false;
						break;
						
					}
				}
				if(isTrue) {
					temp++;
				}
			}
			result = Math.max(result, temp);
			return;
		}
		//백트래킹 + 완전탐색 (모든 경우 그냥 검사 다해버리기~)
		for(int i=start;i<26;i++) {
			if(!alpahet[i]) {
				alpahet[i]=true;
				dfs(i, count+1);
				alpahet[i]=false;
			}
		}
	}

}
