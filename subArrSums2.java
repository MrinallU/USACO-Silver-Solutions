
import java.io.*;
import java.util.*;

public class subArrSums2 {

    public static void main(String[] args) throws IOException {
	Scanner sc = new Scanner(System.in); 
        int N = sc.nextInt(); int X = sc.nextInt(); int ans = 0;
        int [] arr = new int[N + 1]; long [] prefix = new long[N + 1];

        for (int i = 1; i <= N; i++) {
          arr[i] = sc.nextInt();
        }
        prefix[0] = 0;
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        for (int i = 1; i <= N ; i++) {
            long sum = 0;
            for (int j = i - 1; j >= 0; --j) {
                    sum =  prefix[i] - prefix[j];
                   if(sum == X){
                       ++ans;
                   }
            }
            for (int j = i; j <= N; j++) {
                   sum = prefix[j] - prefix[i - 1];
                   if(sum == X){
                       ++ans;
                   }
            }
        }
        System.out.println((int)Math.ceil(((double)ans / 2)));
        sc.close();
    }
}








