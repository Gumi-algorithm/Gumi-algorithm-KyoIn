import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
	
		for(int t=0;t<T;t++) {
			String[] str = br.readLine().split(" ");
			int J = Integer.parseInt(str[0]); // 사탕의 개수
			int N = Integer.parseInt(str[1]); // 상자의 개수
			
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0;i<N;i++) {
				String[] str1 = br.readLine().split(" ");
				list.add(Integer.parseInt(str1[0])*Integer.parseInt(str1[1]));
			}
			
			Collections.sort(list);
			int count=0;
			while(J>0) {
				count++;
				J-=list.get(N-count);
			}
			System.out.println(count);
		}

	}

}
