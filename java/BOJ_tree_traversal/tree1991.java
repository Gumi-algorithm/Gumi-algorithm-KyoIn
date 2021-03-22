package tree1991;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
7
A B C
B D .
C E F
E . .
F . G
D . .
G . .
 */
public class tree1991 {
	// search + add 한번에 하기!
	// 처음에는 return값으로 node를 주려고했으나, 재귀에서 return 값 하는게 어려웠음 
	// 모든 경우에도 무조건 return값이 존재해야된다고 계속 나와서 null이 반환되버리는 상황이 나와서
	// return void로 바꾸고, 값을 넘겨버렸음)
	// 이 부분이 제일 생각하기 어려웠음
	public static void search(Node n, char nodedata, char leftdata, char rightdata) {
		if(n == null) return;
		else if(n.c == nodedata) {
			if(leftdata != '.')
				n.left = new Node(leftdata);
			if(rightdata != '.')
				n.right = new Node(rightdata);
		}
		else {
			search(n.left, nodedata, leftdata, rightdata);
			search(n.right, nodedata, leftdata, rightdata);	
		}
	}

	// 중간이 먼저 일때 : 전위순회 중->왼->오
	public static void pre_read(Node n) throws IOException {
		bw.write(n.c);
		if (n.left != null) pre_read(n.left);
		if (n.right != null) pre_read(n.right);

	}
	// 중간이 중간일때 : 중위순회 왼->중->오
	public static void middle_read(Node n) throws IOException {
		if (n.left != null) middle_read(n.left);
		bw.write(n.c);
		if (n.right != null) middle_read(n.right);

	}
	// 중간이 마지막 : 후위순회 왼->오->중
	public static void post_read(Node n) throws IOException {
		if (n.left != null) post_read(n.left);
		if (n.right != null) post_read(n.right);
		bw.write(n.c);
	}
	// 첫시작은 무조건 있을 것이라고 예상
	static Node rootnode = null;
	static BufferedWriter bw; //이거 좋은 방법 없나요...? 무조건 static이 답인가....?
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int nodes = Integer.parseInt(br.readLine());

		for (int i = 0; i < nodes; i++) {
			String str[] = br.readLine().split(" ");
			// add하기
			if (rootnode == null) { // rootnode면 그냥 넣기
				Node node = new Node(str[0].charAt(0));
				rootnode = node;
				if (str[1].charAt(0) != '.')
					rootnode.left = new Node(str[1].charAt(0));
				if (str[2].charAt(0) != '.')
					rootnode.right = new Node(str[2].charAt(0));

			} else { // 아니면 찾아서 넣기
				search(rootnode, str[0].charAt(0), str[1].charAt(0), str[2].charAt(0));
			}

		}
		pre_read(rootnode); bw.write("\n");
		middle_read(rootnode); bw.write("\n");
		post_read(rootnode); bw.write("\n");
		
		br.close();
		bw.close();

	}

}

class Node {
	Node left;
	Node right;
	char c;

	public Node() {
		this.left = null;
		this.right = null;
		this.c = ' ';
	}

	public Node(char c) {
		this.left = null;
		this.right = null;
		this.c = c;
	}

}
