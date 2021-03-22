package keylogger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*
2
<<BP<A>>Cd-
ThIsIsS3Cr3t
12
BA<-C>F
C<B<A<---
C<B<A>>--
C<B
Baek<<<-
j><>-<u->xb<<a
>>qbd>-
A<B<C<D<E
A-BCD<<<>>-->ED
A<<S-D-SDA<S>>-A<-<<>AAQW><EQ-
<<BP<A>>Cd-
ThIsIsS3Cr3t
 */

public class keylogger {

	static int size;

	static class Node {
		public char data;
		public Node before;
		public Node after;

		Node() {
		}

		Node(char data) {
			this.before = null;
			this.after = null;
			this.data = data;
		}

	}

	public static void main(String[] args) throws IOException {
		// List 자바나 c++에서 만들어진 API 사용하는 것으로 알고있는데, 시간이 넘어가더군요!! => 시간초과!
		// LinkedList<Character> list = new LinkedList<Character>();
		// Scanner보다 빠르다고함.

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String sc = br.readLine();
		// testcase
		int cases = Integer.parseInt(sc);
		Node head = new Node();
		head.data = ' ';
		for (int a = 0; a < cases; a++) {
			String save = br.readLine();
			size = 0;
			
			Node cursor = new Node();
			cursor=head;
			for (int j = 0; j < save.length(); j++) {
				char temp = save.charAt(j);
				switch (temp) {
				case '<':
					if (cursor == head) {// 처음이면 이동못하게
						break;
					} else {
						cursor = cursor.before;
						break;
					}

				case '>':
					if (cursor.after == null) {// 더이상 끝으로 못감
						break;
					} else {
						cursor = cursor.after;
						break;
					}

				case '-':					
					Node temp_node = cursor;
					if (cursor == head) {// 처음이면 삭제불가능
						break;
					}
					cursor = cursor.before;
					
					//
					if(cursor.after != null) {
						cursor.after = temp_node.after;
						Node temp1 = temp_node.after;
						if(temp1 !=null)
							temp_node.after.before=cursor;
					}
					size--;
					break;

				default:			
					Node newNode = new Node(temp);

					if (size == 0) {
						head.after = newNode;
						newNode.before = head;
						cursor = newNode;
					} else { // 중간 또는 끝
						newNode.before = cursor;
						if (cursor.after != null) {//중간이면
							cursor.after.before = newNode;//붙혀주고
							newNode.after = cursor.after;
							cursor.after = newNode;
							cursor = cursor.after; // 커서이동
						}else {
							cursor.after=newNode;
							cursor = cursor.after;
						}
					}
					size++;
				}
			}

			while(cursor.before !=null) {
				cursor=cursor.before;
			}//head 도착
			cursor=cursor.after;
			
			for (int k = 0; k < size; k++) {
				bw.write(cursor.data);
				if(k!=size-1)
					cursor = cursor.after;
			}
			bw.write("\n");

		}
		br.close();
		bw.close();
	}
}
