
/*
ID: your_id_here
LANG: JAVA
TASK: lemonade
*/
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
class cows {
    int vals;
    public cows(int vals)
    {
        this.vals = vals;
    }
    public int getVals(){
        return this.vals;
    }
}
class Sort implements Comparator<cows> {            // Comparator for a specialized in O(log N) for simple time limits.
    public int compare(cows a, cows b) {
        return Integer.compare(b.vals, a.vals);
    }
}
    public class lemonade{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("lemonade.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));
        int N = sc.nextInt();  ArrayList<cows> arr = new ArrayList<>(); int ans = 0;
        for(int i = 0 ; i < N; i++){
            arr.add(new cows(sc.nextInt()));
        }

        while (true){
            arr.sort(new Sort());
            int toRemove = arr.get(0).getVals();
            if(toRemove >= ans){
                arr.remove(0);
                ans++;
            }else{
                break;
            }
        }
        out.println(ans);
        out.close();
    }
}




