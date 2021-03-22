import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    // 톱니바퀴 [번호][방향]
    static int[][] arr = new int[4][8];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //이게 실버 1이 맞나...진짜...
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        // 톱니바퀴 회전
        for (int i = 0; i < k; i++) {
            String[] str1= br.readLine().split(" ");
            int idx = Integer.parseInt(str1[0]);
            int dir = Integer.parseInt(str1[1]);

            // 톱니바퀴 번호는 1~4, 인덱스는 0~3
            // 계속 안되서 결국 인터넷 참조 하였습니다....머리털 다 뽑히는줄...
            // 진지하게 진짜 어렵네요...ㅏ
            solution(idx - 1, dir);
        }

		/*
		 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점 
		 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점 
		 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점 
		 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
		 */
        int sum = 0;
        
        if(arr[0][0] == 1) {
        	sum+=1;
        }
        if(arr[1][0]== 1) {
        	sum+=2;
        }
        if(arr[2][0]==1) {
        	sum+=4;
        }
        if(arr[3][0]==1) {
        	sum+=8;
        }

        System.out.println(sum);
    }
    
    //밑으로 부터 인터넷 보고 따라쳤음... 
    //하다가 탈모올꺼같아서 바꿈...
    // 9시 방향은 2, 3시 방향은 6
   
    static void solution(int idx, int dir) {
        left(idx - 1, -dir);
        right(idx + 1, -dir);
        rotate(idx, dir);
    }

    // 왼쪽에 있던 톱니바퀴 회전 여부 결정
    static void left(int idx, int dir) {
        if (idx < 0) return;

        if (arr[idx][2] != arr[idx + 1][6]) {
            left(idx - 1, -dir);
            rotate(idx, dir);
        }
    }

    // 오른쪽에 있던 톱니바퀴 회전 여부 결정
    static void right(int idx, int dir) {
        if (idx > 3) return;

        if (arr[idx][6] != arr[idx - 1][2]) {
            right(idx + 1, -dir);
            rotate(idx, dir);
        }
    }

    // 얘는 되는데 
    static void rotate(int idx, int dir) {
        if (dir == 1) { //시계
            int temp = arr[idx][7];

            for (int i = 7; i > 0; i--) {
                arr[idx][i] = arr[idx][i - 1];
            }

            arr[idx][0] = temp;

        } else { //반시계
            int temp = arr[idx][0];

            for (int i = 0; i < 7; i++) {
                arr[idx][i] = arr[idx][i + 1];
            }

            arr[idx][7] = temp;
        }
    }
}
