import java.io.*;
import java.util.*;

public class lightson {

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

        public double nextDouble() {
            return Double.parseDouble(next());
        } // reads in the next double
    }

    static InputReader sc;

    static {
        try {
            sc = new InputReader(new File("lightson.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    static room[][] grid; // the grid itself
    static int n; // grid dimensions, rows and columns
    static boolean[][] visited; // keeps track of which nodes have been visited
    static int roomsVisited = 0; // reset to 0 each time we start a new component
    static connection[] connections;

    static class room {
        public boolean isLightSwitch = false;
        public boolean isIlluminated = false;
        public int illuminatesRoomX, getIlluminatesRoomY = -1;

        public room(boolean isLightSwitch, boolean isIlluminated, int illuminatesRoomX, int getIlluminatesRoomY) {
            this.isLightSwitch = isLightSwitch;
            this.isIlluminated = isIlluminated;
            this.illuminatesRoomX = illuminatesRoomX;
            this.getIlluminatesRoomY = getIlluminatesRoomY;
        }
    }

    static class connection {
        public int x, y, connectedX, getConnectedY;
        public boolean hasBeenUsed;

        public connection(int x, int y, int connectedX, int getConnectedY, boolean hasBeenUsed) {
            this.x = x;
            this.y = y;
            this.connectedX = connectedX;
            this.getConnectedY = getConnectedY;
            this.hasBeenUsed = hasBeenUsed;
        }
    }

    static void floodfill(int r, int c, boolean color) {
        if (r < 0 || r >= n || c < 0 || c >= n) return; // if outside grid
        if (grid[r][c].isIlluminated != color) return; // wrong color
        if (visited[r][c]) return; // already visited this square
        if(!grid[r][c].isIlluminated) return;
        visited[r][c] = true; // mark current square as visited
        for (connection c1 : connections) {
            if (!c1.hasBeenUsed && c1.x == c && c1.y == r) {
                c1.hasBeenUsed = true;
                grid[r][c].isLightSwitch = true;
                grid[r][c].illuminatesRoomX = c1.connectedX;
                grid[r][c].getIlluminatesRoomY = c1.getConnectedY;
                int x = grid[r][c].illuminatesRoomX;
                int y = grid[r][c].getIlluminatesRoomY;
                grid[x][y].isIlluminated = true;
            }
        }
        roomsVisited++; // increment the size for each square we visit
        // ^ also input problem specific stuff for the node here
        // recursively call floodfill for neighboring squares
        floodfill(r, c + 1, color);
        floodfill(r, c - 1, color);
        floodfill(r - 1, c, color);
        floodfill(r + 1, c, color);
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));
        n = sc.nextInt();
        int k = sc.nextInt();
        grid = new room[n][n];
        connections = new connection[k];
        visited = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            // subtract by 1 to covert into zero indexed coordinates
            connections[i] = new connection(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, false);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = new room(false, false, -1, -1);
            }
        }
        grid[0][0].isIlluminated = true;
        roomsVisited = 0;
        floodfill(0, 0, true);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (!visited[i][j]) {
//                    roomsVisited = 0;
//                    floodfill(i, j, grid[i][j].isIlluminated);
//                    // start a floodfill if the square hasn't already been visited,
//                    // and then store or otherwise use the component size
//                    // for whatever it's needed for
//                }
//            }
//        }
        out.println(roomsVisited);
        out.close();
    }

}









