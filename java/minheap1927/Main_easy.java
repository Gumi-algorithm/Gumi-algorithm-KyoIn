import java.io.*;
import java.util.*;

public class Main_easy {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int times = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int i = 0; i < times; i++){
            int a = Integer.parseInt(br.readLine());
            if(a == 0){
                if (queue.isEmpty()) {
                    bw.write("0\n");
                 } else {
                    bw.write(queue.poll() + "\n");
                 }
            } else {
                queue.add(a);
            }
        }

        bw.flush();
        br.close();
        bw.close();
        
        
	}

}
