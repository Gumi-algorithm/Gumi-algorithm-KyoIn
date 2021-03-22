import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Integer> heap = new ArrayList<Integer>();
	static int size=0;

	public static void insert(int input) {
		size++;
		//루트때문에 기억해둬야함 왜 안되미어라ㅣ먼아ㅣ러만이러ㅏㅣㅁㅇㄴㄹ
		if (heap.size() == size) 
			heap.add(input);
		else
			heap.set(size, input);

		int child = size;

		while (child / 2 >= 1) {
			//자식보다 자기가 작거나 같을때 끝
			if (heap.get(child) >= heap.get(child / 2))
				break;
			//아니면 바꿔야지!
			else {
				int tmp = heap.get(child);
				heap.set(child, heap.get(child/2));
				heap.set(child/2, tmp);
				child/=2;
			}
		}
	}

//	public int delete() {
//		if (heap.size() <= 1)
//			return 0;
	//안된다.......도저히....delete 지옥인데...
//	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		heap.add(-1); //루트!

		for (int i = 0; i < n; i++) {
			int data = in.nextInt();
			if (data == 0) {
				// System.out.println(heap.delete());
			} else {
				insert(data);
			}
		}

	}
}
