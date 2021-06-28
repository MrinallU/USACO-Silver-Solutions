
import java.io.*;
import java.util.*;


public class diamond {
    static Kattio sc;

    static {
        try {
            sc = new Kattio("diamond");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int n, k, p1, p2, ans = 0;
    static int [] arr;

    public static void main(String[] args){
        n = sc.nextInt();
        k = sc.nextInt();
        p1 = 0; p2 = 0;

        arr = new int [n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        int p1M = 0, p2M = 0;

        for (p2 = 0; p2 < n; p2++) {
            int curr = 0;
            while(arr[p2] - arr[p1] > k){
                ++p1;
            }

            curr = p2 - p1 + 1;


            // find the second max in both of the sides...
            if(p1 == 0 && p2 == n - 1){
                continue;
            }

            if(curr >= ans){
                ans = curr;
                p1M = p1;
                p2M = p2;
            }

        }

        int l = 0, r = 0;

        if(p1M != 0){
            int p3 = 0, p4;
            for (p4 = 0; p4 < p1M; p4++) {
                while(arr[p4] - arr[p3] > k){
                    ++p3;
                }
                l = Math.max(l, (p4 - p3 + 1));
            }
        }

        if(p2M != n - 1){
            int p3 = p2M + 1, p4;
            for (p4 = p2M + 1; p4 < n; p4++) {
                while(arr[p4] - arr[p3] > k){
                    ++p3;
                }
                r = Math.max(r, (p4 - p3 + 1));
            }
        }

        ans = ans + (Math.max(r, l));
        sc.println(ans);
        sc.close();
    }

    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        // standard input
        public Kattio() { this(System.in,System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(new FileWriter(problemName+".out"));
            r = new BufferedReader(new FileReader(problemName+".in"));
        }

        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) {}
            return null;
        }

        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }

}







