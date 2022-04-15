// https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/successful-marathon-0691ec04/
// Dijkstra
// Fast I/O is used 

/* 
    Intuition 
    ---------
    Source(A) se leke sbka minimum pathCost nikalo and store in disSrc,
    Dest(B) se leke sbska minimum pathCost nikalao and store in disDest,
    
    Ab tumhare pass souce(A) se leke chocolate city tak ka minimum costPath para hai disSrc me,
    and Dest(B) se leke chocolate city tak ka minimum costPath para hai disDest me
    
    Ab dekh lo un saare chocolate city me se source CostPath + dest costPath me se minimum kya aara hai
    Also make a check of expiry or melting
*/

import java.util.*;
import java.io.*;

public class chocolateJourney {

  
    public static void dijikstra(int src, ArrayList<int[]>[] graph, int[] dis) {

        // {vtx, dis}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        pq.add(new int[] { src, 0 });
        dis[src] = 0;
        while (pq.size() != 0) {
            int[] p = pq.remove();
            int u = p[0], cost = p[1];

            if (dis[u] < cost) continue; // Ye nai likhoge tab v chalega

            for (int[] e : graph[u]) {
                int v = e[0], c = e[1];
                if (cost + c < dis[v]) {
                    dis[v] = cost + c;
                    pq.add(new int[] { v, cost + c });
                }
            }
        }
    }

    public static void chocolateJourney() throws IOException {
        Reader scn = new Reader();
        int n = scn.nextInt();
        int m = scn.nextInt();
        int k = scn.nextInt();
        int x = scn.nextInt();

        boolean[] chocolates = new boolean[n + 1];
        for (int i = 0; i < k; i++)
            chocolates[scn.nextInt()] = true;

        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        while (m-- > 0) {
            int u = scn.nextInt(), v = scn.nextInt(), w = scn.nextInt();
            graph[u].add(new int[] { v, w });
            graph[v].add(new int[] { u, w });
        }

        int src = scn.nextInt(), dest = scn.nextInt();

        // A se chocolate city tak ka minCostPath nikalo
        int[] disSrc = new int[n + 1];
        Arrays.fill(disSrc, (int) 1e9);
        dijikstra(src, graph, disSrc);

        // B se chocolate city tak ka minCostPath nikalo
        int[] disDest = new int[n + 1];
        Arrays.fill(disDest, (int) 1e9);
        dijikstra(dest, graph, disDest);

        // Add A minCostPath with B minCostPath and find min of all the chocolate cities
        int ans = (int) 1e9;
        for (int i = 1; i <= n; i++) {
            if (chocolates[i]) { // Jin vtx ko chocolate city kaha gaya hai, sirf unpe check karo
                if (disDest[i] < x && disSrc[i] != (int) 1e9) { // Melt hone se pehle pahuch rahe B/chocolate (since bidirectional) city tak and source se chocolate ciy tak ek rasta exist karta hai 
                    ans = Math.min(ans, disDest[i] + disSrc[i]); // Find minimmum, Note totalCost = (A costPath + B costPath) to chocolate city
                }
            }
        }

        if (ans != (int) 1e9) System.out.println(ans); // Min CostPath mil gaya jo melting time frame me pahuch paaye
        else System.out.println(-1);  // Koi aisa costPath nahi mila jo us melting time frame me pahuch paaye
    }

  
    public static void main(String[] args) throws IOException {
        chocolateJourney();
    }

  
    // Fast I/O
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}
