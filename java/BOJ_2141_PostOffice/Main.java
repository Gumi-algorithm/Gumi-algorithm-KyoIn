import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static class Town{
		public Town(long place, long ppl) {
			super();
			this.place = place;
			this.ppl = ppl;
		}

		long place;
		long ppl;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // N(1≤N≤100,000)

		ArrayList<Town> arr = new ArrayList<>();

		long total = 0;
		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			// |X[i]|≤1,000,000,000
			// 0≤A[i]≤1,000,000,000
			arr.add(new Town(Long.parseLong(str[0]), Long.parseLong(str[1]) ));
			total += Integer.parseInt(str[1]);

		}
		
		Collections.sort(arr, new Comparator<Town>() {
			@Override
			public int compare(Town o1, Town o2) {
				int result = Long.compare(o1.place, o2.place);
				if(result==0) {
					result=Long.compare(o1.ppl, o2.ppl);
				}
				return result;
			}
		});
		
		
		long sum_ppl=0;
		for (int i = 0; i < N; i++) {
			sum_ppl+=arr.get(i).ppl;
			if(sum_ppl>=(total+1)/2) {
				System.out.println(arr.get(i).place);
				return;
			}
		}

	}

}
