import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		// N, M은 500,000 이하
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		
		
		String[] list = new String[N];
		for(int i=0;i<N;i++) {
			String name = br.readLine();
			list[i]=name;
		}
		
		Arrays.sort(list);

		
		StringBuilder sb = new StringBuilder();
		int count=0;
		
		ArrayList<String> answer = new ArrayList<>();
		for(int i=0;i<M;i++) {
			String name = br.readLine();
			
			int start=0;
			int end = N-1;
			
			while(start<=end) {
				int mid = (start+end)/2;
				
				if(list[mid].compareTo(name)==0) {
					answer.add(name);
					count++;
					break;
				}else if(list[mid].compareTo(name)>1) {
					end = mid-1;
				}else {
					start= mid+1;
				}
			}
			
		}
		
		Collections.sort(answer);
		
		System.out.println(answer.size());
		for(String index : answer) {
			System.out.println(index);
		}
		
	}

}
