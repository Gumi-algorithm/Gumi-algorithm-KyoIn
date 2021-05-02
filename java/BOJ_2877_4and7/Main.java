import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int K;
	static int[] arr;
	static StringBuilder sb;
	static int end;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		arr = new int[32];
		sb = new StringBuilder();

		K+=1;
		binary(0);
		
		System.out.println(Arrays.toString(arr));
		for(int i=end-2;i>=0;i--) {
			System.out.print(arr[i]);
			if(arr[i]==0) {
				sb.append('4');
			}else {
				sb.append('7');
			}
		}
		System.out.println();
		System.out.println(sb.toString());
	}

	private static void binary(int count) {
		if (K == 0) {
			end=count;
			return;
		}

		arr[count] = K % 2;
		K /= 2;
		binary(count + 1);

	}

}
