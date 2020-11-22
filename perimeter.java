import java.io.*;
import java.util.*;

public class perimeter{
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader(File stream) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(stream), 32768);
            tokenizer = null;
        }

        String next() { 
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
        }

        public long nextLong() {
            return Long.parseLong(next());
        } 

        public double nextDouble() {
            return Double.parseDouble(next());
        } 
    }

    static InputReader sc;

    static {
        try {
            sc = new InputReader(new File("perimeter.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



static int checkRight(int r, int c) {
        ++c;
        if (c >= N) {
        return 1;
        } else if (grid[r][c] == '.') {
        return 1;
        }
        return 0;
        }

static int checkLeft(int r, int c) {
        --c;
        if (c < 0) {
        return 1;
        } else if (grid[r ][c] == '.') {
        return 1;
        }
        return 0;
        }

static int checkUp(int r, int c) {
        r++;
        if (r >= N) {
        return 1;
        } else if (grid[r][c] == '.') {
        return 1;
        }
        return 0;
        }

static int checkDown(int r, int c) {
        --r;
        if (r < 0) {
        return 1;
        } else if (grid[r][c] == '.') {
        return 1;
        }
        return 0;
        }

static void floodfill(int r, int c, int color) {
        if (r < 0 || r >= N || c < 0 || c >= N) return; 
        if (grid[r][c] == '.') return; 
        if (grid[r][c] != color) return; 
        if (visited[r][c]) return; 
        visited[r][c] = true;
        currentArea++; 


        currentPerimeter += checkDown(r, c) + checkRight(r, c) + checkLeft(r, c) + checkUp(r, c);
        floodfill(r, c + 1, color);
        floodfill(r, c - 1, color);
        floodfill(r - 1, c, color);
        floodfill(r + 1, c, color);
        }

static int N;
static char[][] grid;
static boolean[][] visited;
static int currentArea, currentPerimeter = 0;


public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        N = sc.nextInt();
        grid = new char[N][N];
        visited = new boolean[N][N];
        int maxArea = 0;
        int perimeter = 0;
        for (int i = 0; i < N; i++) {
        String s = sc.next();
        char[] arr = s.toCharArray();
        System.arraycopy(arr, 0, grid[i], 0, N);
        }
        for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
        if (!visited[i][j]) {
        currentArea = 0;
        currentPerimeter = 0;
        floodfill(i, j, grid[i][j]);
        if(maxArea == currentArea){
        perimeter = Math.min(perimeter, currentPerimeter);
        }else if(maxArea < currentArea){
        maxArea = currentArea;
        perimeter = currentPerimeter;
        }
      
        }
        }

        }
        out.println(maxArea + " " + perimeter);
        out.close();
        }
        }


