
import java.io.*;
import java.util.*;

public class citystate {

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
            sc = new InputReader(new File("citystate.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
    6
MIAMI FL
DALLAS TX
FLINT MI
CLEMSON SC
BOSTON MA
ORLANDO FL
     */

   static HashMap<String, HashSet<String>> map = new HashMap<>();

   static class geoLocation{
       public String state, city;
       public geoLocation(String city,String state){
           this.city = city;
           this.state = state;
       }
   }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));
        int n = sc.nextInt(); int paris = 0;   geoLocation [] arr = new geoLocation[n]; // for looping through the cities
        for (int i = 0; i < n; i++) {
            String s = sc.next(); String s1 = sc.next();
            arr[i] = new geoLocation(s, s1);
            if(!s.substring(0, 2).equals(s1)) {
                map.computeIfAbsent(s1, k -> new HashSet<>()).add(s);
            }
        }


        for (int i = 0; i < n; i++) {
            if(map.containsKey(arr[i].city.substring(0,2))) {
                for (String s: map.get(arr[i].city.substring(0, 2))) {
                    if(s.substring(0, 2).equals(arr[i].state)){
                        ++paris;
                    }
                }
            }
        }
        out.println(paris / 2);



        out.close();
    }
}



