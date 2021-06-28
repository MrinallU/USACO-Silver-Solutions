
import java.io.*;
import java.util.*;

public class balancing {

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader(File stream) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(stream), 32768);
            tokenizer = null;
        }

        String next() { // reads in the next string
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        } // reads in the next int

        public long nextLong() {
            return Long.parseLong(next());
        } // reads in the next long
        public char nextChar() {
            return (next().charAt(0));
        } // reads in the next int

        public double nextDouble() {
            return Double.parseDouble(next());
        } // reads in the next double
    }

    static InputReader sc;

    static {
        try {
            sc = new InputReader(new File("balancing.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class point{
        public int x;
        public int y;

        public point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    /**
     * 1. Binary Search
     * Validate
     * --------
     *  a. Check each possible vlaues of the fence, with the x values limiting the x from max to min x cow coord and the same for y
     *  b. After creating the funce locations inside the for-loop, check if x is odd(else continue;) and the y is odd(break)
     *  c. finally after storing the cows in a region of the fence (check some math for this)
     *  d. find the max region @return true or false the if the max region fits the M value provided
     *
     * NOTE
     * ______
     * You can precompute the max region fo seach region of the fence and store all values in a set then interate through to find if any of the configurations match the M
     *
     * */
    public static boolean f(int x){
        for (int i: set) {
            if(i <= x){
                return true;
            }
        }
        return false;

        //function f(x) returns true or false
    }
    public static int fstTrue(int lo, int hi) {
        for (hi ++; lo < hi; ) {
            // returns smallest x in [lo,hi] that satisfies f
            // hi+1 if no x satisfies f
            int mid = (lo+hi)/2;
            if(f(mid)) hi = mid; else lo = mid+1;
        }
        return lo;
    }

    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));
        int n = sc.nextInt(); // binary search for all of the values of n
        point [] coords = new point[n]; int maxX = 0; int maxY = 0; int minX = Integer.MAX_VALUE; int minY = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int x = sc.nextInt(); int y = sc.nextInt();
            coords[i] = new point(x, y);
            maxX = Math.max(maxX, x); maxY = Math.max(y, maxY);
            minY = Math.min(minY, y); minX = Math.min(minX, x);
        }


        // precompute values

        for (int i = minX - 1; i <= maxX + 1; i += 2 ) { // controls vertical fence
            for (int j = minY - 1; j <=  maxY + 1; j += 2) { // controls horizontal fence
                // System.out.println(i +  " " + j);
                // Loop through each of the coordinates of the cows and then identify each as one of the four regions...
                int r1 = 0; int r2 = 0; int r3 = 0; int r4 = 0;
                for (int k = 0; k < n; ++k) {
                    if(coords[k].x > i && coords[k].y > j){
                        ++r1;
                    }else if(coords[k].x < i && coords[k].y > j){
                        ++r2;
                    }else if(coords[k].x > i && coords[k].y < j){
                        ++r4;
                    }else if(coords[k].x < i && coords[k].y < j){
                        ++r3;
                    }
                }
                set.add(Math.max(r1, Math.max(r2, Math.max(r3, r4))));
            }
        }

        out.println(fstTrue(1, 1000));

        out.close();

    }

}


