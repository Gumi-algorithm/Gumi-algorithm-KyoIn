import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static int N = 0;
	static int arr[];
	static boolean visited[];
	static ArrayList<Character> op = new ArrayList<>();
	static char[] result;
	//범위를 잘 생각해야되는 문제였음.
	//연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다.
	//앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		
		result = new char[N-1];
		visited = new boolean[N-1];

		String[] str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		// + - * /
		String[] str1 = br.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			if (Integer.parseInt(str1[i]) != 0) {
				switch(i) {
				case 0:
					for(int j=0;j<Integer.parseInt(str1[i]);j++)
						op.add('+');
					break;
				case 1:
					for(int j=0;j<Integer.parseInt(str1[i]);j++)
						op.add('-');
					break;
				case 2:
					for(int j=0;j<Integer.parseInt(str1[i]);j++)
						op.add('*');
					break;
				case 3:
					for(int j=0;j<Integer.parseInt(str1[i]);j++)
						op.add('/');
					break;
				}
			}
		}

		dfs(0,0);
		
		System.out.println(max+"\n"+min);
	}



	private static void dfs(int level, int count) {
		if(count==N-1) {
			int temp = arr[0];
			//System.out.println(Arrays.toString(result));
			for(int i=1;i<N;i++) {
				char operator = result[i-1];
				switch(operator) {
				case '+':
					temp=temp+arr[i];
					break;
				case '-':
					temp=temp-arr[i];
					break;
				case '*':
					temp = temp*arr[i];
					break;
				case '/':
					temp=temp/arr[i];
					break;
				}
			}
			
			max = Math.max(temp, max);
			min = Math.min(temp, min);
			
			return;
		}
		
		for(int i=0;i<op.size();i++) {
			if(visited[i] == true) 
				continue;
			
			visited[i]= true;
			result[count] = op.get(i);
			dfs(i,count+1);
			visited[i]=false;
			
		}
	}
	
	

}
