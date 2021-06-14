package Others;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Random;

public class AngryRabbitInputGenerator {

    private static final int MAX_COOR = 1000000000;
    private static final int NUM_OF_N = 10;
    private static final int K = 5;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> hs = new HashSet<>();
        Random random = new Random();
        try {
            OutputStream outputStream = new FileOutputStream("C:/Users/82102/Desktop/eval_input.txt");
            sb.append(NUM_OF_N).append(" ").append(K).append("\n");
            int cnt = 0;
            while(cnt < NUM_OF_N) {
                int randNum = random.nextInt(MAX_COOR) + 1;
                if(hs.contains(randNum)) {
                    continue;
                } else {
                    hs.add(randNum);
                    sb.append(randNum).append("\n");
                    cnt++;
                }
            }
            byte[] bytes = sb.toString().getBytes();
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }
}
